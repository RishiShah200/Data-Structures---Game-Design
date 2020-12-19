import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MazeProgram extends JPanel implements KeyListener{


    ArrayList<Wall> walls;
    int size = 15;
    boolean draw3D = false;
    Location loc;

    JFrame frame;

    int numRows = 24;
    int numCols = 66;
    char[][] maze = new char[numRows][numCols];

    boolean[][] lightUpMaze = new boolean[numRows][numCols];
    Hero hero;

    int stepCounter;
    boolean completed;

    Font font;
    float time = 0.0f;
    ScheduledExecutorService scheduledExecutorService;
    ScheduledFuture scheduledFuture;
    boolean gameStart;

    JTextField jtextfield;
    String userName;

    public static int fov;

    int stopWriting;

    JFrame leaderBoard;
    JTextArea textArea;
    JScrollPane jScrollPane;

    static Clip clip;

    ArrayList<Leaderboard> leaders;
    int depth;

    public MazeProgram() {
        depth = 5;
        leaders = new ArrayList<Leaderboard>();
        stopWriting = 0;
        gameStart = false;
        font = new Font("Comic Sans MS", Font.BOLD, 50);
        stepCounter = 0;
        completed = false;


        leaderBoard = new JFrame("Leaderboard");
        leaderBoard.setSize(500, 100);
        leaderBoard.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        leaderBoard.setVisible(true);

        textArea = new JTextArea(5, 30);
        textArea.setEditable(false);

        try {
            File gameData = new File("C:\\Users\\rishi\\Documents\\SBHS\\12th\\Data-Structures---Game-Design\\MazeProgramReal\\gamedata.txt");
            BufferedReader readGameData = new BufferedReader(new FileReader(gameData));
            String text;
            String[] data = null;

            while ((text = readGameData.readLine()) != null) {
                System.out.print(text);
                data = text.split(",");
                leaders.add(new Leaderboard(data[0], Integer.parseInt(data[1]), Float.parseFloat(data[2])));
            }
            Collections.sort(leaders);

            for (Leaderboard l : leaders) {
                textArea.append(l.toString() + "\n");
            }

            System.out.println(leaders.toString());

            setLocationToTopRight(leaderBoard);

        } catch (Exception e) {
            e.printStackTrace();
        }

        jScrollPane = new JScrollPane(textArea);
        leaderBoard.add(jScrollPane, BorderLayout.CENTER);

        leaderBoard.revalidate();
        leaderBoard.repaint();

        frame = new JFrame("Maze Program");
        frame.add(this);

        jtextfield = new JTextField(200);
        frame.getContentPane().add(jtextfield, BorderLayout.SOUTH);
        jtextfield.addActionListener(event -> {
            userName = jtextfield.getText();
            frame.requestFocusInWindow();
            System.out.println(userName);
        });


        setBoard();
        loc = new Location(1, 0);
        hero = new Hero(loc, size, 1, Color.BLUE, Color.YELLOW);
        frame.setSize(1500, 1000);
        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        lightUpMaze[hero.getLoc().getR()][hero.getLoc().getC()] = true;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        repaint();
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, frame.getWidth(), frame.getHeight());

        g2.setColor(new Color(235, 162, 45));

        g2.setFont(font);

        if (completed) {
            g2.setPaint(Color.BLACK);
            g2.drawRect(frame.getWidth(), frame.getHeight(), 0, 0);
            g2.setColor(Color.GREEN);
            g2.drawString("You have won the game!", (frame.getWidth() / 2)-50, (frame.getHeight() / 2)-50);
        }

        if (!gameStart) {
            g2.drawString("Welcome to the Maze Game: Click 'p' to continue", 200, 500);
        }

        if (gameStart && !completed) {

            g2.drawString("Steps: " + stepCounter, 100, 800);

            g2.drawString("Time: " + time, 400, 800);

            if (!draw3D) {
                g2.setColor(Color.BLACK);
                for (int c = 0; c < maze[0].length; c++) {
                    for (int r = 0; r < maze.length; r++) {
                        if (maze[r][c] == ' ') {
                            if (lightUpMaze[r][c]) {
                                g2.setColor(Color.GRAY);
                                g2.fillRect(c * size + size, r * size + size, size, size);
                            } else {
                                g2.setColor(Color.BLACK);
                                g2.fillRect(c * size + size, r * size + size, size, size);
                                g2.setColor(Color.GRAY);
                            }
                        }
                    }
                }
                g2.setColor(hero.getBody());
                g2.fill(hero.getRect());
                g2.setColor(Color.GREEN);
                g2.setStroke(new BasicStroke(3));
                g2.draw(hero.getRect());
            } else {
                if (completed) {
                    g2.setPaint(Color.BLACK);
                    g2.drawRect(frame.getWidth(), frame.getHeight(), 0, 0);
                    g2.setColor(Color.GREEN);
                    g2.drawString("You have won the game!", frame.getWidth() / 2, frame.getHeight() / 2);
                } else {
                    walls = getWalls(depth, 50);
                    for (Wall w : walls) {
                        g2.setPaint(w.getPaint(time));
                        if (w.isFilled()) {
                            g2.fill(w.getPoly());
                        }
                        g2.setColor(Color.BLACK);
                        g2.setStroke(new BasicStroke(1));
                        g2.draw(w.getPoly());

                    }
                }
            }

        }

    }

    public ArrayList<Wall> getWalls(int depth, int size) {
        ArrayList<Wall> walls = new ArrayList<Wall>();
        int row = hero.getLoc().getR();
        int col = hero.getLoc().getC();
        int[] colCoordinates;
        int[] rowCoordinates;
        Color color;

        boolean isSquare = false;
        for (fov = 0; fov < depth; fov++) {

            if (isSquare) {
                break;
            }

            //floor
            colCoordinates = new int[]{100 + (size * fov), 150 + (size * fov), 750 - (size * fov), 800 - (size * fov)};
            rowCoordinates = new int[]{750 - (size * fov), 700 - (size * fov), 700 - (size * fov), 750 - (size * fov)};
            color = Color.GRAY;
            walls.add(new Wall("bottom trapezoid", colCoordinates, rowCoordinates, color, size, true, fov));

            //ceiling
            colCoordinates = new int[]{100 + (size * fov), 150 + (size * fov), 750 - (size * fov), 800 - (size * fov)};
            rowCoordinates = new int[]{50 + (size * fov), 100 + (size * fov), 100 + (size * fov), 50 + (size * fov)};
            color = Color.GRAY;
            walls.add(new Wall("top trapezoid", colCoordinates, rowCoordinates, color, size, true, fov));


            //left rectangles
            colCoordinates = new int[]{100 + (size * fov), 150 + (size * fov), 150 + (size * fov), 100 + (size * fov)};
            rowCoordinates = new int[]{100 + (size * fov), 100 + (size * fov), 700 - (size * fov), 700 - (size * fov)};
            color = Color.GRAY;
            walls.add(new Wall("left rectangle", colCoordinates, rowCoordinates, color, size, false, fov));

            //right rectangles
            colCoordinates = new int[]{800 - (size * fov), 750 - (size * fov), 750 - (size * fov), 800 - (size * fov)};
            rowCoordinates = new int[]{100 + (size * fov), 100 + (size * fov), 700 - (size * fov), 700 - (size * fov)};
            color = Color.GRAY;
            walls.add(new Wall("right rectangle", colCoordinates, rowCoordinates, color, size, false, fov));


            switch (hero.getDir()) {
                case 0: //up
                    if ((row - fov >= 0) && (col + 1 <= maze[0].length - 1) && (maze[row - fov][col + 1] == '#')) {    //right trapezoid
                        colCoordinates = new int[]{800 - (size * fov), 750 - (size * fov), 750 - (size * fov), 800 - (size * fov)};
                        rowCoordinates = new int[]{50 + (size * fov), 100 + (size * fov), 700 - (size * fov), 750 - (size * fov)};
                        color = Color.BLUE;
                        walls.add(new Wall("right trapezoid", colCoordinates, rowCoordinates, color, size, true, fov));
                    }
                    if ((row - fov >= 0) && (col - 1 >= 0) && (maze[row - fov][col - 1] == '#')) {    //left walls
                        colCoordinates = new int[]{100 + (size * fov), 150 + (size * fov), 150 + (size * fov), 100 + (size * fov)};
                        rowCoordinates = new int[]{50 + (size * fov), 100 + (size * fov), 700 - (size * fov), 750 - (size * fov)};
                        color = Color.BLUE;
                        walls.add(new Wall("left trapezoid", colCoordinates, rowCoordinates, color, size, true, fov));
                    }
                    if ((row - fov >= 0) && maze[row - fov][col] == '#') {   //square
                        colCoordinates = new int[]{150 + (size * fov), 750 - (size * fov), 750 - (size * fov), 150 + (size * fov)};
                        rowCoordinates = new int[]{100 + (size * (fov)), 100 + (size * (fov)), 700 - (size * (fov)), 700 - (size * (fov))};
                        color = Color.BLUE;
                        walls.add(new Wall("square", colCoordinates, rowCoordinates, color, size, true, fov));
                        isSquare = true;
                        break;
                    }
                    break;
                case 1:     //right
                    if ((col + fov <= maze[0].length - 1) && (row - 1 >= 0) && (maze[row - 1][col + fov] == '#')) {  //left walls
                        colCoordinates = new int[]{100 + (size * fov), 150 + (size * fov), 150 + (size * fov), 100 + (size * fov)};
                        rowCoordinates = new int[]{50 + (size * fov), 100 + (size * fov), 700 - (size * fov), 750 - (size * fov)};
                        color = Color.GRAY;
                        walls.add(new Wall("left trapezoid", colCoordinates, rowCoordinates, color, size, true, fov));
                    }
                    if ((col + fov <= maze[0].length - 1) && (row + 1 <= maze.length - 1) && (maze[row + 1][col + fov] == '#')) {  //right walls
                        colCoordinates = new int[]{800 - (size * fov), 750 - (size * fov), 750 - (size * fov), 800 - (size * fov)};
                        rowCoordinates = new int[]{50 + (size * fov), 100 + (size * fov), 700 - (size * fov), 750 - (size * fov)};
                        color = Color.BLUE;
                        walls.add(new Wall("right trapzeoid", colCoordinates, rowCoordinates, color, size, true, fov));
                    }
                    if ((col + fov <= maze[0].length - 1) && maze[row][col + fov] == '#') {   //square
                        colCoordinates = new int[]{150 + (size * fov), 750 - (size * fov), 750 - (size * fov), 150 + (size * fov)};
                        rowCoordinates = new int[]{100 + (size * (fov)), 100 + (size * (fov)), 700 - (size * (fov)), 700 - (size * (fov))};
                        color = Color.BLUE;
                        walls.add(new Wall("square", colCoordinates, rowCoordinates, color, size, true, fov));
                        isSquare = true;
                        break;
                    }
                    break;
                case 2: //down
                    //System.out.println("FOV: " + fov + " Row: " + row + " Col: " + col + " maze[0].length-1: " + (maze.length-1)+"");
                    if ((row + fov <= maze.length - 1) && (col - 1 >= 0) && (maze[row + fov][col - 1] == '#')) {//right walls
                        colCoordinates = new int[]{800 - (size * fov), 750 - (size * fov), 750 - (size * fov), 800 - (size * fov)};
                        rowCoordinates = new int[]{50 + (size * fov), 100 + (size * fov), 700 - (size * fov), 750 - (size * fov)};
                        color = Color.BLUE;
                        walls.add(new Wall("right trapezoid", colCoordinates, rowCoordinates, color, size, true, fov));
                    }
                    if ((row + fov <= maze.length - 1) && (col + 1 <= maze[0].length - 1) && (maze[row + fov][col + 1] == '#')) {//left walls
                        colCoordinates = new int[]{100 + (size * fov), 150 + (size * fov), 150 + (size * fov), 100 + (size * fov)};
                        rowCoordinates = new int[]{50 + (size * fov), 100 + (size * fov), 700 - (size * fov), 750 - (size * fov)};
                        color = Color.GRAY;
                        walls.add(new Wall("left trapezoid", colCoordinates, rowCoordinates, color, size, true, fov));
                    }
                    if ((row + fov <= maze.length - 1) && maze[row + fov][col] == '#') {   //square
                        colCoordinates = new int[]{150 + (size * fov), 750 - (size * fov), 750 - (size * fov), 150 + (size * fov)};
                        rowCoordinates = new int[]{100 + (size * (fov)), 100 + (size * (fov)), 700 - (size * (fov)), 700 - (size * (fov))};
                        color = Color.BLUE;
                        walls.add(new Wall("square", colCoordinates, rowCoordinates, color, size, true, fov));
                        isSquare = true;
                        break;
                    }
                    break;
                case 3:     //left
                    if ((col - fov >= 0) && (row - 1 >= 0) && (maze[row - 1][col - fov] == '#')) {//right walls
                        colCoordinates = new int[]{800 - (size * fov), 750 - (size * fov), 750 - (size * fov), 800 - (size * fov)};
                        rowCoordinates = new int[]{50 + (size * fov), 100 + (size * fov), 700 - (size * fov), 750 - (size * fov)};
                        color = Color.BLUE;
                        walls.add(new Wall("right trapezoid", colCoordinates, rowCoordinates, color, size, true, fov));
                    }
                    if ((col - fov >= 0) && (row + 1 <= maze[0].length) && (maze[row + 1][col - fov] == '#')) {       //left walls
                        colCoordinates = new int[]{100 + (size * fov), 150 + (size * fov), 150 + (size * fov), 100 + (size * fov)};
                        rowCoordinates = new int[]{50 + (size * fov), 100 + (size * fov), 700 - (size * fov), 750 - (size * fov)};
                        color = Color.GRAY;
                        walls.add(new Wall("left trapezoid", colCoordinates, rowCoordinates, color, size, true, fov));
                    }
                    if ((col - fov >= 0) && maze[row][col - fov] == '#') {   //square
                        colCoordinates = new int[]{150 + (size * fov), 750 - (size * fov), 750 - (size * fov), 150 + (size * fov)};
                        rowCoordinates = new int[]{100 + (size * (fov)), 100 + (size * (fov)), 700 - (size * (fov)), 700 - (size * (fov))};
                        color = Color.BLUE;
                        walls.add(new Wall("square", colCoordinates, rowCoordinates, color, size, true, fov));
                        isSquare = true;
                        break;
                    }
                    break;
            }

        }

        return walls;
    }

    public void setBoard() {
        File name = new File("C:\\Users\\rishi\\Documents\\SBHS\\12th\\Data-Structures---Game-Design\\MazeProgramReal\\src\\MazeFile.txt");

        try {
            BufferedReader input = new BufferedReader(new FileReader(name));
            String text;
            int r = 0;

            while ((text = input.readLine()) != null) {
                for (int c = 0; c < text.length(); c++) {
                    maze[r][c] = text.charAt(c);
                    lightUpMaze[r][c] = false;
                }
                r++;
            }


        } catch (IOException e) {
            System.out.println("File Error!");
            e.printStackTrace();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == 80) {   //p to start game
            gameStart = true;

            playMusic("C:\\Users\\rishi\\Documents\\SBHS\\12th\\Data-Structures---Game-Design\\MazeProgramReal\\src\\Music\\gamePlayingMusic.wav");
            scheduledExecutorService =
                    Executors.newSingleThreadScheduledExecutor();

            scheduledFuture =
                    scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            time += 0.1f;
                        }
                    }, 100, 100, TimeUnit.MILLISECONDS);

        }

        if (hero.getLoc().getR() == 1 && hero.getLoc().getC() == 64) {
            completed = true;
        }

        if (completed && stopWriting != 1) {
            scheduledExecutorService.shutdown();
            stopMusic("C:\\Users\\rishi\\Documents\\SBHS\\12th\\Data-Structures---Game-Design\\MazeProgramReal\\src\\Music\\gamePlayingMusic.wav");
            playMusic("C:\\Users\\rishi\\Documents\\SBHS\\12th\\Data-Structures---Game-Design\\MazeProgramReal\\src\\Music\\victoryMusic.wav");
            try {
                File nameOfFile = new File("gamedata.txt");
                String text;
                text = userName + "," + stepCounter + "," + time;
                BufferedWriter writer = new BufferedWriter(new FileWriter(nameOfFile, true));
                writer.append('\n');
                writer.append(text);

                writer.close();
                stopWriting = 1;
            } catch (Exception b) {

            }

        }
        if (e.getKeyCode() == 38) {
            lightUpMaze[hero.getLoc().getR()][hero.getLoc().getC()] = true;
        }

        if (e.getKeyCode() == 38 || e.getKeyCode() == 37 || e.getKeyCode() == 39) {
            if (!scheduledFuture.isDone())
                stepCounter++;
        }

        if (e.getKeyCode() == 32) {
            draw3D = !draw3D;
        }

        if (!completed) {
            hero.move(e.getKeyCode(), maze);
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) {
        MazeProgram app = new MazeProgram();
    }

    public static void playMusic(String filepath) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filepath));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopMusic(String filepath) {
        clip.stop();
    }

    static void setLocationToTopRight(JFrame frame) {
        GraphicsConfiguration config = frame.getGraphicsConfiguration();
        Rectangle bounds = config.getBounds();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(config);

        int x = bounds.x + bounds.width - insets.right - frame.getWidth();
        int y = bounds.y + insets.top;
        frame.setLocation(x, y);
    }

}
