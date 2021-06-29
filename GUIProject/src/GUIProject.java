import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class GUIProject extends JPanel implements ActionListener  {

    JFrame frame;
    JMenuBar menu;
    JPanel buttonPanel, bigPanel;
    GridLayout buttonLayout, bigLayout;
    JButton north, south, east, west, reset;
    JMenu fontOptions, fontSizeOptions, bgColorOptions, fgColorOptions, borderOptions;
    JMenuItem[] fontOption,fontSizeOption, bgColorOption, fgColorOption, borderOption;
    String[] fontNames;
    JTextArea textArea;
    Font currFont;
    int currFontSize;
    Font[] fonts;
    int[] fSizes;
    Color[] bColor, tColor, oColors, tbColors;
    String[] bgColors, textColors, borderOutline;
    public GUIProject(){
        frame = new JFrame("GUI");
        frame.setLayout(new BorderLayout());
        menu = new JMenuBar();

        fontOptions = new JMenu("Font");
        fontOption = new JMenuItem[3];

        fontSizeOptions = new JMenu("Sizes");
        fontSizeOption = new JMenuItem[3];

        bgColorOptions = new JMenu("Bg Color");
        bgColorOption = new JMenuItem[3];

        fgColorOptions = new JMenu("Text Color");
        fgColorOption = new JMenuItem[3];

        borderOptions = new JMenu("Border");
        borderOption = new JMenuItem[4];


        menu.setLayout(new GridLayout(1,2));
        menu.add(fontOptions);
        menu.add(fontSizeOptions);
        menu.add(bgColorOptions);
        menu.add(fgColorOptions);
        menu.add(borderOptions);


        frame.add(this);

        fontNames = new String[]{"Times New Roman","Consolas","Arial"};
        fonts = new Font[fontNames.length];
        fSizes = new int[]{18,24,30};

        for(int x = 0;x<fontNames.length;x++){
            fonts[x] = new Font(fontNames[x],Font.PLAIN,fSizes[0]);
            fontOption[x] = new JMenuItem(fontNames[x]);
            fontOption[x].setFont(fonts[x]);
            fontOption[x].addActionListener(this);
            fontOptions.add(fontOption[x]);
        }

        for(int x = 0;x<fSizes.length;x++){
            fontSizeOption[x] = new JMenuItem(fSizes[x]+"");
            fontSizeOption[x].addActionListener(this);
            fontSizeOptions.add(fontSizeOption[x]);

        }



        currFontSize = fSizes[0];
        currFont = fonts[0];
        oColors = new Color[1];
        oColors[0] = Color.MAGENTA;

        bColor = new Color[]{Color.WHITE, Color.BLACK, Color.BLUE};
        bgColors = new String[]{"WHITE","BLACK","RANDOM"};

        tColor = new Color[]{Color.BLACK, Color.WHITE, Color.CYAN};
        textColors = new String[]{"BLACK","WHITE","RANDOM"};

        tbColors = new Color[]{Color.YELLOW, Color.GREEN, Color.MAGENTA};
        borderOutline = new String[]{"NONE","YELLOW", "GREEN", "MAGENTA"};

        reset = new JButton("Reset");
        reset.addActionListener(this);
        menu.add(reset);

        for(int x = 0;x<bgColors.length;x++){
            bgColorOption[x] = new JMenuItem(bgColors[x]);
            bgColorOption[x].addActionListener(this);
            bgColorOptions.add(bgColorOption[x]);
        }

        for(int x = 0;x<textColors.length;x++){
            fgColorOption[x] = new JMenuItem(textColors[x]);
            fgColorOption[x].addActionListener(this);
            fgColorOptions.add(fgColorOption[x]);
        }

        for(int x = 0;x<borderOutline.length;x++){
            borderOption[x] = new JMenuItem(borderOutline[x]);
            borderOption[x].addActionListener(this);
            borderOptions.add(borderOption[x]);
        }

        north = new JButton("North");
        east = new JButton("East");
        west = new JButton("West");
        south = new JButton("South");

        bigLayout = new GridLayout(1,2);
        bigPanel = new JPanel();
        bigPanel.setLayout(bigLayout);

        buttonPanel = new JPanel();
        buttonLayout = new GridLayout(1,4);
        buttonPanel.setLayout(buttonLayout);
        buttonPanel.add(north);
        buttonPanel.add(east);
        buttonPanel.add(south);
        buttonPanel.add(west);

        north.addActionListener(this);
        east.addActionListener(this);
        south.addActionListener(this);
        west.addActionListener(this);

        north.setBorder(new LineBorder(oColors[0]));
        east.setBorder(new LineBorder(oColors[0]));
        south.setBorder(new LineBorder(oColors[0]));
        west.setBorder(new LineBorder(oColors[0]));
        reset.setBorder(new LineBorder(oColors[0]));

        textArea = new JTextArea();
        textArea.setBackground(Color.WHITE);
        textArea.setForeground(Color.BLACK);
        textArea.setFont(fonts[0]);

        bigLayout = new GridLayout(1,2);
        bigPanel = new JPanel();
        bigPanel.setLayout(bigLayout);
        bigPanel.add(buttonPanel);
        bigPanel.add(menu);

        frame.add(bigPanel,BorderLayout.NORTH);
        frame.add(textArea,BorderLayout.CENTER);
        frame.setSize(1000,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public static void main(String[]args){
        GUIProject app = new GUIProject();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == north){
            frame.remove(bigPanel);
            buttonLayout = new GridLayout(1,4);
            bigLayout = new GridLayout(1,2);
            buttonPanel.setLayout(buttonLayout);
            bigPanel.setLayout(bigLayout);
            bigPanel.remove(menu);
            bigPanel.remove(buttonPanel);

            menu.setLayout(new GridLayout(1,2));
            menu.remove(fontOptions);

            menu.remove(reset);

            menu.add(fontOptions);

            menu.add(reset);

            bigPanel.add(buttonPanel);
            bigPanel.add(menu);

            frame.add(bigPanel, BorderLayout.NORTH);
        }
        if(e.getSource() == east){
            frame.remove(bigPanel);
            buttonLayout = new GridLayout(4,1);
            bigLayout = new GridLayout(2,1);
            buttonPanel.setLayout(buttonLayout);
            bigPanel.setLayout(bigLayout);
            bigPanel.remove(menu);
            bigPanel.remove(buttonPanel);

            menu.setLayout(new GridLayout(2,1));
            menu.remove(fontOptions);

            menu.remove(reset);

            menu.add(fontOptions);

            menu.add(reset);

            bigPanel.add(buttonPanel);
            bigPanel.add(menu);

            frame.add(bigPanel, BorderLayout.EAST);
        }
        if(e.getSource() == south){
            frame.remove(bigPanel);
            buttonLayout = new GridLayout(1,4);
            bigLayout = new GridLayout(1,2);
            buttonPanel.setLayout(buttonLayout);
            bigPanel.setLayout(bigLayout);
            bigPanel.remove(menu);
            bigPanel.remove(buttonPanel);

            menu.setLayout(new GridLayout(1,2));
            menu.remove(fontOptions);

            menu.remove(reset);

            menu.add(fontOptions);

            menu.add(reset);

            bigPanel.add(buttonPanel);
            bigPanel.add(menu);

            frame.add(bigPanel, BorderLayout.SOUTH);
        }
        if(e.getSource() == west){
            frame.remove(bigPanel);
            buttonLayout = new GridLayout(4,1);
            bigLayout = new GridLayout(2,1);
            buttonPanel.setLayout(buttonLayout);
            bigPanel.setLayout(bigLayout);
            bigPanel.remove(menu);
            bigPanel.remove(buttonPanel);

            menu.setLayout(new GridLayout(2,1));
            menu.remove(fontOptions);

            menu.remove(reset);

            menu.add(fontOptions);

            menu.add(reset);

            bigPanel.add(buttonPanel);
            bigPanel.add(menu);

            frame.add(bigPanel, BorderLayout.WEST);
        }
        if(e.getSource() == reset){
            frame.remove(bigPanel);
            buttonLayout = new GridLayout(1,4);
            bigLayout = new GridLayout(1,2);
            buttonPanel.setLayout(buttonLayout);
            bigPanel.setLayout(bigLayout);
            bigPanel.remove(menu);
            bigPanel.remove(buttonPanel);

            menu.setLayout(new GridLayout(1,2));
            menu.remove(fontOptions);
            menu.remove(fontSizeOptions);
            menu.remove(bgColorOptions);
            menu.remove(fgColorOptions);
            menu.remove(borderOptions);
            menu.remove(reset);

            menu.add(fontOptions);
            menu.add(fontSizeOptions);
            menu.add(bgColorOptions);
            menu.add(fgColorOptions);
            menu.add(borderOptions);
            menu.add(reset);

            bigPanel.add(buttonPanel);
            bigPanel.add(menu);


            frame.add(bigPanel, BorderLayout.NORTH);

            textArea.setText(null);
            textArea.setBackground(bColor[0]);
            textArea.setForeground(tColor[0]);
            Font temp = new Font(fontNames[0],Font.PLAIN,fSizes[0]);
            textArea.setFont(temp);
            north.setBorder(new LineBorder(oColors[0]));
            east.setBorder(new LineBorder(oColors[0]));
            south.setBorder(new LineBorder(oColors[0]));
            west.setBorder(new LineBorder(oColors[0]));
            reset.setBorder(new LineBorder(oColors[0]));
        }
        for (int x = 0; x < fontNames.length; x++){

            if (e.getSource() == fontOption[x]){

                currFont = new Font(fontNames[x], Font.PLAIN, currFontSize);
                textArea.setFont(currFont);
                north.setFont(currFont);
                east.setFont(currFont);
                south.setFont(currFont);
                west.setFont(currFont);
                fontOptions.setFont(currFont);
                fontSizeOptions.setFont(currFont);
                bgColorOptions.setFont(currFont);
                fgColorOptions.setFont(currFont);
                borderOptions.setFont(currFont);
                reset.setFont(currFont);

            }
        }

        for (int x = 0; x < fSizes.length; x++){

            if (e.getSource() == fontSizeOption[x]){

                //currFontSize = fSizes[x];
                int tempFontSize = fSizes[x];
                currFont = new Font(currFont.getName(), Font.PLAIN, tempFontSize);
                textArea.setFont(currFont);
            }
        }

        for (int x = 0; x < bgColors.length; x++){

            if (e.getSource() == bgColorOption[x]){
                if(bgColors[x].equals("RANDOM")){
                    int R = (int)(Math.random()*256);
                    int G = (int)(Math.random()*256);
                    int B= (int)(Math.random()*256);
                    Color color = new Color(R, G, B);
                    textArea.setBackground(color);
                }else{
                    textArea.setBackground(stringToColor(bgColors[x]));
                }

            }
        }


        for (int x = 0; x < textColors.length; x++){

            if (e.getSource() == fgColorOption[x]){
                if(textColors[x].equals("RANDOM")){
                    int R = (int)(Math.random()*256);
                    int G = (int)(Math.random()*256);
                    int B= (int)(Math.random()*256);
                    Color color = new Color(R, G, B);
                    textArea.setForeground(color);
                }else{
                    textArea.setForeground(stringToColor(textColors[x]));
                }

            }
        }

        for (int x = 0; x < borderOutline.length; x++){

            if (e.getSource() == borderOption[x]){
                if(borderOutline[x].equals("RANDOM")){
                    int R = (int)(Math.random()*256);
                    int G = (int)(Math.random()*256);
                    int B= (int)(Math.random()*256);
                    Color color = new Color(R, G, B);
                    north.setBorder(new LineBorder(color));
                    east.setBorder(new LineBorder(color));
                    south.setBorder(new LineBorder(color));
                    west.setBorder(new LineBorder(color));
                    reset.setBorder(new LineBorder(color));
                }else if(borderOutline[x].equals("NONE")){
                    north.setBorder(new LineBorder(Color.WHITE));
                    east.setBorder(new LineBorder(Color.WHITE));
                    south.setBorder(new LineBorder(Color.WHITE));
                    west.setBorder(new LineBorder(Color.WHITE));
                    reset.setBorder(new LineBorder(Color.WHITE));
                }else{
                    north.setBorder(new LineBorder(stringToColor(borderOutline[x])));
                    south.setBorder(new LineBorder(stringToColor(borderOutline[x])));
                    east.setBorder(new LineBorder(stringToColor(borderOutline[x])));
                    west.setBorder(new LineBorder(stringToColor(borderOutline[x])));
                    reset.setBorder(new LineBorder(stringToColor(borderOutline[x])));

                }

            }
        }

        frame.revalidate();
    }

    public static Color stringToColor(final String value) {
        if (value == null) {
            return Color.black;
        }
        try {
            return Color.decode(value);
        } catch (NumberFormatException e) {
            try {
                final Field f = Color.class.getField(value);

                return (Color) f.get(null);
            } catch (Exception ee) {
                return Color.black;
            }
        }
    }
}
