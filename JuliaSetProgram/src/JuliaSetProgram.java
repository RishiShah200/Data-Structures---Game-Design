import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JuliaSetProgram extends JPanel implements AdjustmentListener, MouseListener, ActionListener {

    JFrame frame;
    JScrollBar aBar, bBar, zoomBar, hueBar, saturationBar, brightnessBar;



    JPanel scrollPanel, labelPanel, bigPanel;
    JLabel aValueLabel, bValueLabel, zoomLabel, hueLabel, saturationLabel, brightnessLabel;

    JButton clear;
    JButton save;
    JPanel buttonPanel;

    String currDir;
    JFileChooser fileChooser;

    double a,b;

    DecimalFormat df;

    int pixelSize = 1;
    int maxPixelSize = 3;

    double zoom;

    float hue;
    float saturation;
    float brightness;

    BufferedImage image;
    public JuliaSetProgram(){
        frame = new JFrame("Julia Set Program");
        frame.add(this);
        frame.setSize(1000,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        aBar = new JScrollBar(JScrollBar.HORIZONTAL,0,0,-2000,2000);  //orientation,starting value,doodad size, min value, max value
        a = aBar.getValue() / 1000.0;
        aValueLabel = new JLabel("A: " + a);
        aBar.addAdjustmentListener(this);
        aBar.addMouseListener(this);

        bBar = new JScrollBar(JScrollBar.HORIZONTAL,0,0,-2000,2000);  //orientation,starting value,doodad size, min value, max value
        b = bBar.getValue() / 1000.0;
        bValueLabel = new JLabel("B: " + b);
        bBar.addAdjustmentListener(this);
        bBar.addMouseListener(this);

        zoomBar = new JScrollBar(JScrollBar.HORIZONTAL,10,0,0,100);  //orientation,starting value,doodad size, min value, max value
        zoom = zoomBar.getValue() / 10.0;
        zoomLabel = new JLabel("Zoom: " + zoom);
        zoomBar.addAdjustmentListener(this);
        zoomBar.addMouseListener(this);

        hueBar = new JScrollBar(JScrollBar.HORIZONTAL,1000,0,0,1000);  //orientation,starting value,doodad size, min value, max value
        hue = hueBar.getValue() / 1000.0f;
        hueLabel = new JLabel("Hue: " + hue);
        hueBar.addAdjustmentListener(this);
        hueBar.addMouseListener(this);

        saturationBar = new JScrollBar(JScrollBar.HORIZONTAL,1000,0,0,1000);  //orientation,starting value,doodad size, min value, max value
        saturation = saturationBar.getValue() / 1000.0f;
        saturationLabel = new JLabel("Saturation: " + saturation);
        saturationBar.addAdjustmentListener(this);
        saturationBar.addMouseListener(this);

        brightnessBar = new JScrollBar(JScrollBar.HORIZONTAL,1000,0,0,1000);  //orientation,starting value,doodad size, min value, max value
        brightness = brightnessBar.getValue() / 1000.0f;
        brightnessLabel = new JLabel("Brightness: " + brightness);
        brightnessBar.addAdjustmentListener(this);
        brightnessBar.addMouseListener(this);

        GridLayout grid = new GridLayout(6,1);

        clear = new JButton("Clear");
        save = new JButton("Save");
        clear.addActionListener(this);
        save.addActionListener(this);
        buttonPanel = new JPanel();
        buttonPanel.add(clear);
        buttonPanel.add(save);

        labelPanel = new JPanel();
        labelPanel.setLayout(grid);
        labelPanel.add(aValueLabel);
        labelPanel.add(bValueLabel);
        labelPanel.add(zoomLabel);
        labelPanel.add(hueLabel);
        labelPanel.add(saturationLabel);
        labelPanel.add(brightnessLabel);


        scrollPanel = new JPanel();
        scrollPanel.setLayout(grid);
        scrollPanel.add(aBar);
        scrollPanel.add(bBar);
        scrollPanel.add(zoomBar);
        scrollPanel.add(hueBar);
        scrollPanel.add(saturationBar);
        scrollPanel.add(brightnessBar);

        bigPanel = new JPanel();
        bigPanel.setLayout(new BorderLayout());
        bigPanel.add(labelPanel,BorderLayout.WEST);
        bigPanel.add(scrollPanel,BorderLayout.CENTER);
        bigPanel.add(buttonPanel,BorderLayout.EAST);


        //flow layout
        frame.add(bigPanel,BorderLayout.SOUTH);
        frame.setVisible(true);

        df = new DecimalFormat("#.000");

        currDir = System.getProperty("user.dir");
        fileChooser = new JFileChooser(currDir);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(drawJulia(),0,0,null);
    }
    public BufferedImage drawJulia(){

        //getting values for width and height of the window
        int w = frame.getWidth();
        int h = frame.getHeight();

        //declaring the BufferedImage
        image = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);

        for(int x = 0;x<w;x+=pixelSize){ //loop through the width
            for(int y = 0;y<h;y+=pixelSize){ //loop through the height

                //declare zoom, iterations, zx, zy values

                float iterations = 300f;
                double zx = 1.5 * ((x - (0.5 * w)) / (0.5 * zoom * w));
                double zy = ((y - (0.5 * h)) / (0.5 * zoom * h));

                //pre-test loop
                while((zx * zx) + (zy * zy) < 6.0 && iterations > 0){
                    double temp = (zx * zx) - (zy * zy) + a;
                    zy = (2.0 * zx * zy) + b;
                    zx = temp;
                    iterations--;
                }

                //setting the color of the pixel at each coordinate (a+bi) which is really (x,y)
                int c;
                if(iterations > 0)
                    c = Color.HSBtoRGB(hue * (iterations / 300f) % 1, saturation, brightness);
                else
                    c = Color.HSBtoRGB(iterations / 300f, 1, 0);
                image.setRGB(x,y,c);
            }
        }
        return image;
    }

    public static void main(String[]args){
        JuliaSetProgram app = new JuliaSetProgram();
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        if(e.getSource()==aBar) {
            a = aBar.getValue() / 1000.0;
            aValueLabel.setText("A: " + df.format(a) + "\t\t");
        }
        else if(e.getSource()==bBar) {
            b = bBar.getValue() / 1000.0;
            bValueLabel.setText("A: " + df.format(b) + "\t\t");
        }
        else if(e.getSource()==zoomBar) {
            zoom = zoomBar.getValue() / 10.0;
            zoomLabel.setText("Zoom: " + df.format(zoom) + "\t\t");
        }
        else if(e.getSource()==hueBar) {
            hue = hueBar.getValue() / 1000.0f;
            hueLabel.setText("Hue: " + df.format(hue) + "\t\t");
        }
        else if(e.getSource()==saturationBar) {
            saturation = saturationBar.getValue() / 1000.0f;
            saturationLabel.setText("Saturation: " + df.format(saturation) + "\t\t");
        }
        else if(e.getSource()==brightnessBar) {
            brightness = brightnessBar.getValue() / 1000.0f;
            brightnessLabel.setText("Brightness: " + df.format(brightness) + "\t\t");
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        pixelSize = maxPixelSize;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pixelSize = 1;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == clear){
            clearImage();
        }
        else if(e.getSource()==save){
            saveImage();
        }
    }

    public void saveImage(){
        System.out.println("Reached here");
        if(image!=null){
            FileFilter filter=new FileNameExtensionFilter("*.png","png");
            fileChooser.setFileFilter(filter);
            if(fileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
            {
                File file=fileChooser.getSelectedFile();
                try
                {
                    String st=file.getAbsolutePath();
                    if(st.indexOf(".png")>=0)
                        st=st.substring(0,st.length()-4);
                    ImageIO.write(image,"png",new File(st+".png"));
                }catch(IOException e)
                {
                }

            }
        }
    }

    public void clearImage(){
        a = 0.000f;
        b = 0.000f;
        zoom = 1.0f;
        brightness = 1.0f;
        saturation = 1.0f;
        hue = 1.0f;

        aBar.setValue(0);
        bBar.setValue(0);
        zoomBar.setValue(10);
        hueBar.setValue(1000);
        saturationBar.setValue(1000);
        brightnessBar .setValue(1000);

        aValueLabel.setText("A: " + a);
        bValueLabel.setText("B: " + b);
        zoomLabel.setText("Zoom: " + zoom);
        hueLabel.setText("Hue: " + hue);
        saturationLabel.setText("Saturation: " + saturation);
        brightnessLabel.setText("Brightness: " + brightness);

        repaint();
    }
}
