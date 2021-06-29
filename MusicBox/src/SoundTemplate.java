import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;


public class SoundTemplate extends JFrame implements Runnable, ActionListener, AdjustmentListener {

    JToggleButton button[][] = new JToggleButton[37][180];

    JScrollBar tempoBar;

    JMenuBar menuBar;
    JMenu file, instrumentMenu, colMenu, prebuiltSongsMenu;
    JMenuItem[] instrumentItems, songItems;
    String[] clipNames, instrumentNames = {"Bell", "Glockenspiel", "Marimba", "Oboe", "Oh Ah", "Piano"};
    String[] songNames = {"Super Mario","Hedwig's Theme","Twinkle Twinkle"};

    JMenuItem save, load, add, remove;
    JFileChooser fileChooser;
    JButton stopPlay, clear, randomButton, reset;


    JLabel[] labels = new JLabel[button.length];
    JScrollPane buttonPane;
    JPanel buttonPanel, labelPanel, tempoPanel, menuButtonPanel;
    JLabel tempoLabel;
    JFrame frame = new JFrame();

    boolean notStopped = true, playing = false;

    Clip[] clip;
    int tempo, row = 0, col = 0;
    Font font = new Font("Times New Roman", Font.PLAIN, 10);

    public SoundTemplate() {

        setSize(1000,800);
        clipNames = new String[]{"C0", "B1", "ASharp1", "A1", "GSharp1", "G1", "FSharp1", "F1", "E1", "DSharp1",
                "D1", "CSharp1", "C1", "B2", "ASharp2", "A2", "GSharp2", "G2", "FSharp2",
                "F2", "E2", "DSharp2", "D2", "CSharp2", "C2", "B3", "ASharp3", "A3", "GSharp3",
                "G3", "FSharp3", "F3", "E3", "DSharp3", "D3", "CSharp3", "C3"};

        clip = new Clip[clipNames.length];
        String initInstrument = instrumentNames[0];
        try {
            for (int x = 0; x < clipNames.length; x++) {

                URL url = this.getClass().getClassLoader().getResource(initInstrument + " - " + clipNames[x] + ".wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                clip[x] = AudioSystem.getClip();
                clip[x].open(audioIn);
            }

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(button.length, button[0].length, 2, 5)); //Last two numbers "space out" the buttons

        for (int r = 0; r < button.length; r++) {

            String name = clipNames[r].replaceAll("Sharp", "#");
            for (int c = 0; c < button[0].length; c++) {

                button[r][c] = new JToggleButton();
                button[r][c].setFont(font);
                button[r][c].setText(name);
                button[r][c].setPreferredSize(new Dimension(30, 30));
                button[r][c].setMargin(new Insets(0, 0, 0, 0));
                buttonPanel.add(button[r][c]);
            }
        }

        tempoBar = new JScrollBar(JScrollBar.HORIZONTAL, 200, 0, 50, 1000);
        tempoBar.addAdjustmentListener(this);
        tempo = tempoBar.getValue();
        tempoLabel = new JLabel(String.format("%s%6s", "Tempo: ", tempo));
        tempoPanel = new JPanel(new BorderLayout());
        tempoPanel.add(tempoLabel, BorderLayout.WEST);
        tempoPanel.add(tempoBar, BorderLayout.CENTER);

        String currDir = System.getProperty("user.dir");
        fileChooser = new JFileChooser(currDir);

        menuBar = new JMenuBar();
        menuBar.setLayout(new GridLayout(1,2));

        file = new JMenu("File");
        save = new JMenuItem("Save");
        load = new JMenuItem("Load");
        save.addActionListener(this);
        load.addActionListener(this);
        file.add(save);
        file.add(load);

        instrumentMenu = new JMenu("Instruments");
        instrumentItems = new JMenuItem[instrumentNames.length];

        prebuiltSongsMenu = new JMenu("Prebuilt-Songs");
        songItems = new JMenuItem[songNames.length];

        for (int x = 0; x<instrumentNames.length; x++) {

            instrumentItems[x] = new JMenuItem(instrumentNames[x]);
            instrumentItems[x].addActionListener(this);
            instrumentMenu.add(instrumentItems[x]);
        }

        for (int x = 0; x<songNames.length; x++) {

            songItems[x] = new JMenuItem(songNames[x]);
            songItems[x].addActionListener(this);
            prebuiltSongsMenu.add(songItems[x]);
        }

        colMenu = new JMenu("Columns");
        add = new JMenuItem("Add column");
        remove = new JMenuItem("Remove column");
        add.addActionListener(this);
        remove.addActionListener(this);
        colMenu.add(add);
        colMenu.add(remove);

        menuBar.add(file);
        menuBar.add(instrumentMenu);
        menuBar.add(colMenu);
        menuBar.add(prebuiltSongsMenu);

        menuButtonPanel = new JPanel();
        menuButtonPanel.setLayout(new GridLayout(1,4));
        stopPlay = new JButton("Play");
        stopPlay.addActionListener(this);
        menuButtonPanel.add(stopPlay);
        clear = new JButton("Clear");
        clear.addActionListener(this);
        reset = new JButton("Reset");
        reset.addActionListener(this);
        menuButtonPanel.add(reset);
        menuButtonPanel.add(clear);
        randomButton = new JButton("Random");
        randomButton.addActionListener(this);
        menuButtonPanel.add(randomButton);
        menuBar.add(menuButtonPanel, BorderLayout.EAST);

        buttonPane = new JScrollPane(buttonPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(buttonPane, BorderLayout.CENTER);
        this.add(tempoPanel, BorderLayout.SOUTH);
        this.add(menuBar, BorderLayout.NORTH);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Thread timing = new Thread(this);
        timing.start();
    }

    public void run() {
        do {
            try {

                if (!playing) {
                    new Thread().sleep(0);
                }

                else {

                    for (int r = 0; r<button.length; r++) {
                        if (button[r][col].isSelected()) {
                            clip[r].start();
                            button[r][col].setForeground(Color.YELLOW);
                        }
                    }

                    new Thread().sleep(tempo);
                    for (int r = 0; r<button.length; r++) {
                        if (button[r][col].isSelected()) {

                            clip[r].stop();
                            clip[r].setFramePosition(0);
                            button[r][col].setForeground(Color.BLACK);
                        }
                    }

                    col++;
                    if (col == button[0].length)
                        col = 0;
                }
            }
            catch(InterruptedException e) {e.printStackTrace();}

        } while(notStopped);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == add || e.getSource() == remove) {

            int cc = button[0].length;

            if (e.getSource() == add)
                button = new JToggleButton[37][cc+1];

            if (e.getSource() == remove)
                button = new JToggleButton[37][(cc > 0) ? cc-1 : 0];

            System.out.println(button[0].length);

            buttonPanel.removeAll();
            buttonPanel.setLayout(new GridLayout(button.length, button[0].length, 2, 5));

            for (int r = 0; r < button.length; r++) {

                String name = clipNames[r].replaceAll("Sharp", "#");
                for (int c = 0; c < button[0].length; c++) {

                    button[r][c] = new JToggleButton();
                    button[r][c].setFont(font);
                    button[r][c].setText(name);
                    button[r][c].setPreferredSize(new Dimension(30, 30));
                    button[r][c].setMargin(new Insets(0, 0, 0, 0));
                    buttonPanel.add(button[r][c]);
                }
            }
        }

        if(e.getSource() == save){
            saveSong();
        }

        if(e.getSource() == load){
            int returnVal = fileChooser.showOpenDialog(this);

            if(returnVal == JFileChooser.APPROVE_OPTION){
                try{
                    File loadFile = fileChooser.getSelectedFile();
                    BufferedReader input = new BufferedReader(new FileReader(loadFile));
                    String temp;
                    temp = input.readLine();
                    tempo = Integer.parseInt(temp.substring(0,3));
                    tempoBar.setValue(tempo);
                    Character[][] song = new Character[button.length][temp.length()-2];

                    int r = 0;
                    while((temp=input.readLine()) != null){
                        for(int c = 2;c<song[0].length;c++){
                            song[r][c-2] = temp.charAt(c);
                        }
                        r++;
                    }
                    setNotes(song);
                }catch (IOException ee){

                }
                col = 0;
                playing = false;
                stopPlay.setText("Play");
            }
        }

        if (e.getSource() == stopPlay) {
            playing = !playing;
            if (!playing)
                stopPlay.setText("Play");
            else
                stopPlay.setText("Stop");
        }

        if(e.getSource() == reset) {
            stopPlay.setText("Play");
            for (int c = 0; c < button[0].length; c++) {
                button[row][c].setSelected(false);
                col = 0;
                playing = false;
                stopPlay.setSelected(false);
            }
        }

        if (e.getSource() == clear) {

            for (int r = 0; r<button.length; r++){
                for (int c = 0; c<button[0].length; c++){
                    button[r][c].setSelected(false);
                }
            }
            col = 0;
            playing = false;
            stopPlay.setSelected(false);
        }

        if (e.getSource() == randomButton) {

            for (int i = 0; i < button.length; i++)
                for (int j = 0; j < button[0].length; j++)
                    if ( (int)(Math.random()*20) == 0)
                        button[i][j].setSelected(true);
        }

        for (int y = 0; y<instrumentItems.length; y++) {

            if (e.getSource() == instrumentItems[y]) {

                String selectedInstrument = instrumentNames[y];
                if (selectedInstrument.equals("Oh Ah"))
                    selectedInstrument = "oh_ah";

                try {

                    for (int x = 0; x < clipNames.length; x++) {
                        URL url = this.getClass().getClassLoader().getResource(selectedInstrument+" - "+clipNames[x]+".wav");
                        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                        clip[x] = AudioSystem.getClip();
                        clip[x].open(audioIn);
                    }

                } catch (UnsupportedAudioFileException ee) {
                    ee.printStackTrace();
                } catch (IOException ee) {
                    ee.printStackTrace();
                } catch (LineUnavailableException ee) {
                    ee.printStackTrace();
                }

                col = 0;
                playing = false;
                stopPlay.setText("Play");
            }
        }

        for (int x = 0; x<songItems.length; x++) {

            if (e.getSource() == songItems[x]) {
                try{
                    File loadFile = new File("src/" + songNames[x] + ".txt");
                    BufferedReader input = new BufferedReader(new FileReader(loadFile));
                    String temp;
                    temp = input.readLine();
                    tempo = Integer.parseInt(temp.substring(0,3));
                    tempoBar.setValue(tempo);
                    Character[][] song = new Character[button.length][temp.length()-2];

                    int r = 0;
                    while((temp=input.readLine()) != null){
                        for(int c = 2;c<song[0].length;c++){
                            song[r][c-2] = temp.charAt(c);
                        }
                        r++;
                    }
                    setNotes(song);
                }catch (IOException ee){
                    ee.printStackTrace();
                }
                col = 0;
                playing = false;
                stopPlay.setText("Play");


            }
        }

        revalidate();
        repaint();

    }

    public void saveSong() {
        FileFilter filter = new FileNameExtensionFilter("*.txt","txt");
        fileChooser.setFileFilter(filter);
        if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            try{
                String st = file.getAbsolutePath();
                if(st.indexOf(".txt")>=0)
                    st = st.substring(0,st.length()-4);
                String output = "";
                String[] noteNames = {" ","c ","b ","a-","a ","g-","g ","f-","f ","e ","d-","d ","c-","c ","b ",
                        "a-","a ","g-","g ","f-","f ","e ","d-","d ","c-","c ","b ",
                        "a-","a ","g-","g ","f-","f ","e ","d-","d ","c-","c "};

                for(int r = 0;r<button.length+1;r++){
                    if(r==0){
                        output+= tempo;
                        for(int x = 0;x<button[0].length;x++)
                            output+=" ";
                    }
                    else{
                        output += noteNames[r];
                        for(int c = 0;c<button[0].length;c++){
                            if(button[r-1][c].isSelected())
                                output+="X";
                            else output+="-";
                        }
                    }
                    output+="\n";
                }

                BufferedWriter outputStream = null;
                outputStream = new BufferedWriter(new FileWriter(st+".txt"));
                outputStream.write(output);
                outputStream.close();
            }catch (IOException e){

            }
        }
    }

    public void setNotes(Character[][] notes){
        buttonPane.remove(buttonPanel);


        buttonPanel = new JPanel();
        button = new JToggleButton[37][notes[0].length];
        buttonPanel.setLayout(new GridLayout(button.length, button[0].length, 2, 5));
        for(int r = 0;r<button.length;r++){
            String name = clipNames[r].replaceAll("Sharp","#");
            for(int c = 0;c<button[0].length;c++){
                button[r][c] = new JToggleButton();
                button[r][c].setFont(font);
                button[r][c].setText(name);
                button[r][c].setPreferredSize(new Dimension(30,30));
                button[r][c].setMargin(new Insets(0,0,0,0));
                buttonPanel.add(button[r][c]);
            }
        }
        this.remove(buttonPane);
        buttonPane = new JScrollPane(buttonPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(buttonPane,BorderLayout.CENTER);

        for(int r = 0;r<button.length;r++){
            for(int c = 0;c<button[0].length;c++){
                try{
                    if(notes[r][c] == 'X')
                        button[r][c].setSelected(true);
                    else button[r][c].setSelected(false);

                }catch(NullPointerException e){

                }catch(ArrayIndexOutOfBoundsException ee){

                }
            }
            this.revalidate();
        }


    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        tempo = tempoBar.getValue();
        tempoLabel.setText(String.format("%s%6s", "Tempo: ", tempo));
    }

    public static void main(String args[]) {
        SoundTemplate app = new SoundTemplate();
    }
}