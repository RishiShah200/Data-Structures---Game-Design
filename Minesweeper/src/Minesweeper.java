import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Minesweeper extends JFrame implements ActionListener, MouseListener {

    JToggleButton[][] board;
    JPanel boardPanel;
    boolean firstClick;
    int numMines;

    Font mineFont;
    ImageIcon mineIcon, flagIcon, loseIcon, smileIcon, waitIcon, winIcon;
    GraphicsEnvironment ge;

    ImageIcon[] numbers;

    JMenuBar menuBar;
    JMenu difficulty;
    JMenuItem beginner, intermediate, expert;
    JButton reset;
    String currentDifficulty;

    Timer timer;
    int timePassed;
    JTextField textField;
    Font timerFont;


    public Minesweeper() {
        firstClick = true;
        numMines = 10;
        mineFont = new Font("Times New Roman", Font.PLAIN, 12);
        currentDifficulty = "Beginner";
        try {
            ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            mineFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/mine-sweeper.ttf"));
            timerFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/digital-7.ttf"));
            ge.registerFont(mineFont);
            ge.registerFont(timerFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        numbers = new ImageIcon[8];
        for (int x = 0; x < 8; x++) {
            numbers[x] = new ImageIcon("src/" + (x + 1) + ".png");
            numbers[x] = new ImageIcon(numbers[x].getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        }

        System.out.println(mineFont);
        mineIcon = new ImageIcon("src/mine.png");
        mineIcon = new ImageIcon(mineIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));

        flagIcon = new ImageIcon("src/flag.png");
        flagIcon = new ImageIcon(flagIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));

        loseIcon = new ImageIcon("src/loseIcon.png");
        loseIcon = new ImageIcon(loseIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));

        smileIcon = new ImageIcon("src/smileIcon.png");
        smileIcon = new ImageIcon(smileIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));

        waitIcon = new ImageIcon("src/waitIcon.png");
        waitIcon = new ImageIcon(waitIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));

        winIcon = new ImageIcon("src/winIcon.png");
        winIcon = new ImageIcon(winIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));


        UIManager.put("ToggleButton.select", new Color(255, 157, 0));

        createBoard(9,9);

        menuBar = new JMenuBar();

        difficulty = new JMenu("Difficulty");

        beginner = new JMenuItem("Beginner");
        beginner.addActionListener(this);
        difficulty.add(beginner);

        intermediate = new JMenuItem("Intermediate");
        intermediate.addActionListener(this);
        difficulty.add(intermediate);

        expert = new JMenuItem("Expert");
        expert.addActionListener(this);
        difficulty.add(expert);

        reset = new JButton();
        reset.addActionListener(this);
        reset.setFocusPainted(false);
        reset.setIcon(smileIcon);

        textField = new JTextField();
        textField.setFont(timerFont);
        textField.setForeground(Color.RED.darker());
        textField.setBackground(Color.WHITE);
        textField.setFont(timerFont.deriveFont(30f));
        textField.setHorizontalAlignment(JTextField.CENTER);


        menuBar.setLayout(new GridLayout(1,3));
        menuBar.add(difficulty);
        menuBar.add(reset);
        menuBar.add(textField);


        this.add(menuBar,BorderLayout.NORTH);


        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public class UpdateTimer extends TimerTask{
        @Override
        public void run() {
            timePassed++;
            textField.setText(timePassed+"");
        }
    }

    public static void main(String[] args) {
        Minesweeper app = new Minesweeper();
    }

    public void createBoard(int row, int col) {
        if (boardPanel != null)
            this.remove(boardPanel);
        boardPanel = new JPanel();
        board = new JToggleButton[row][col];
        boardPanel.setLayout(new GridLayout(row, col));

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                board[r][c] = new JToggleButton();
                board[r][c].putClientProperty("row", r);
                board[r][c].putClientProperty("col", c);
                board[r][c].putClientProperty("state", 0); //what constitutes a mine

                board[r][c].setBorder(BorderFactory.createBevelBorder(0));
                board[r][c].setFont(mineFont.deriveFont(16f));
                board[r][c].setFocusPainted(false);
                board[r][c].addMouseListener(this);
                boardPanel.add(board[r][c]);
            }
        }
        this.setSize(40 * col, 40 * row);
        this.add(boardPanel);
        this.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Current Difficulty: " + currentDifficulty);
        if(e.getSource() == reset){
            firstClick = true;
            timePassed = 0;
            textField.setText(timePassed+"");
            timer.cancel();
            switch(currentDifficulty){
                case "Beginner":
                    numMines = 10;
                    createBoard(9,9);
                    break;
                case "Intermediate":
                    numMines = 40;
                    createBoard(16,16);
                    break;
                case "Expert":
                    numMines = 99;
                    createBoard(16,40);
                    break;
            }
            reset.setIcon(smileIcon);
            revalidate();
        }
        if(e.getSource() == beginner){
            firstClick = true;
            timePassed = 0;
            textField.setText(timePassed+"");
            timer.cancel();
            numMines = 10;
            createBoard(9, 9);
            currentDifficulty = "Beginner";
            reset.setIcon(smileIcon);
            revalidate();
        }
        if(e.getSource() == intermediate){
            firstClick = true;
            timePassed = 0;
            textField.setText(timePassed+"");
            timer.cancel();
            numMines = 40;
            createBoard(16, 16);
            currentDifficulty = "Intermediate";
            reset.setIcon(smileIcon);
            revalidate();
        }
        if(e.getSource() == expert){
            firstClick = true;
            timePassed = 0;
            textField.setText(timePassed+"");
            timer.cancel();
            numMines = 99;
            createBoard(16, 40);
            reset.setIcon(smileIcon);
            currentDifficulty = "Expert";
            revalidate();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        reset.setIcon(smileIcon);
        int row = (int) ((JToggleButton) e.getComponent()).getClientProperty("row");
        int col = (int) ((JToggleButton) e.getComponent()).getClientProperty("col");

        if (e.getButton() == MouseEvent.BUTTON1 && board[row][col].isEnabled()) {
            if (firstClick) {
                setMinesAndCounts(row, col);
                firstClick = false;
                timer = new Timer();
                timer.schedule(new UpdateTimer(),0,1000);
            }

            int state = (int) ((JToggleButton) board[row][col]).getClientProperty("state");
            if (state == -1) {
                board[row][col].setIcon(mineIcon);
                board[row][col].setContentAreaFilled(false);
                board[row][col].setOpaque(true);
                board[row][col].setBackground(Color.RED);

                timer.cancel();
                reset.setIcon(loseIcon);
                revealMines();
                //JOptionPane.showMessageDialog(null, "You are a loser!");
                //show all of the mines

                //stop the user from having the ability to click on buttons until they reset the game
            } else {
                expand(row, col);
                checkWin();
            }
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            if(!firstClick){
                if (!board[row][col].isSelected()) {
                    if(board[row][col].getIcon() == null){
                        board[row][col].setIcon(flagIcon);
                        board[row][col].setDisabledIcon(flagIcon);
                        board[row][col].setEnabled(false);

                        board[row][col].setContentAreaFilled(false);
                        board[row][col].setOpaque(true);
                    }
                    else{
                        //if(board[row][col].getIcon() == flagIcon)
                        board[row][col].setIcon(null);
                        board[row][col].setDisabledIcon(null);
                        board[row][col].setEnabled(true);
                    }

                }
            }


        }
    }

    public void revealMines() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                int state = (int) board[r][c].getClientProperty("state");
                if (state == -1) {
                    board[r][c].setIcon(mineIcon);
                    board[r][c].setDisabledIcon(mineIcon);
                    board[r][c].setSelected(true);
                }
                board[r][c].setEnabled(false);
            }
        }
    }

    public void write(int row, int col, int state) {
        Color color;
        switch (state) {
            case 1:
                color = Color.BLUE;
                break;
            case 2:
                color = Color.GREEN;
                break;
            case 3:
                color = Color.RED;
                break;
            case 4:
                color = Color.BLUE.darker().darker();
                break;
            case 5:
                color = Color.RED.darker().darker();
                break;
            case 6:
                color = Color.MAGENTA;
                break;
            case 7:
                color = Color.CYAN;
                break;
            case 8:
                color = Color.ORANGE;
                break;
            default:
                color = Color.BLACK;
        }
        if (state > 0) {
            /*board[row][col].setForeground(color);
            board[row][col].setText("" + state);*/
            board[row][col].setIcon(numbers[state - 1]);
            board[row][col].setDisabledIcon(numbers[state - 1]);
        }
        if (state == -1) {
            // board[row][col].setIcon(mineIcon);
        }
    }

    public void checkWin() {
        int dimR = board.length;
        int dimC = board[0].length;
        int totalSpaces = dimR * dimC;
        int count = 0;

        for (int r = 0; r < dimR; r++) {
            for (int c = 0; c < dimC; c++) {
                int state = (int) board[r][c].getClientProperty("state");
                if (board[r][c].isSelected())
                    count++;
            }
        }

        if (numMines == totalSpaces - count){
            //JOptionPane.showMessageDialog(null, "How cool are you?!");
            reset.setIcon(winIcon);
            timer.cancel();
        }

    }


    public void expand(int row, int col) {

        if (!board[row][col].isSelected())
            board[row][col].setSelected(true);

        int state = (int) ((JToggleButton) board[row][col]).getClientProperty("state");
        if (state != 0) {
            write(row, col, state);
        } else {
            for (int r3x3 = row - 1; r3x3 <= row + 1; r3x3++) {
                for (int c3x3 = col - 1; c3x3 <= col + 1; c3x3++) {
                    try {
                        if (!board[r3x3][c3x3].isSelected()) {
                            expand(r3x3, c3x3);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }


                }
            }
        }
    }

    public void setMinesAndCounts(int currRow, int currCol) {
        int count = numMines;
        int dimR = board.length;
        int dimC = board[0].length;
        while (count > 0) {
            int randR = (int) (Math.random() * dimR);
            int randC = (int) (Math.random() * dimC);
            int state = (int) ((JToggleButton) board[randR][randC]).getClientProperty("state");
            if (state == 0 && ((Math.abs(randR - currRow) > 1) || (Math.abs(randC - currCol) > 1))) {
                board[randR][randC].putClientProperty("state", -1);
                count--;
            }
        }


        //find counts, it's O(n^4) <-- 4 for loops
        for (int r = 0; r < dimR; r++) {
            for (int c = 0; c < dimC; c++) {
                int adjMines = 0;
                int state = (int) ((JToggleButton) board[r][c]).getClientProperty("state");
                if (state != -1) {
                    for (int rr = r - 1; rr <= r + 1; rr++) {
                        for (int cc = c - 1; cc <= c + 1; cc++) {
                            try {
                                int innerState = (int) ((JToggleButton) board[rr][cc]).getClientProperty("state");
                                if (innerState == -1)
                                    adjMines++;

                            } catch (Exception e) {
                            }
                        }
                    }
                    board[r][c].putClientProperty("state", adjMines);
                }
            }
        }

        //comment this out when necessary
        /*for(int r = 0;r<dimR;r++){
            for(int c = 0;c<dimC;c++){
                int state = (int)((JToggleButton)board[r][c]).getClientProperty("state");
                 write(row,col,state);
            }
        }*/

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            reset.setIcon(waitIcon);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
