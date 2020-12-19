import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MazeProject extends JPanel implements KeyListener {

    JFrame frame;
    int c = 500;
    int r = 300;
    public MazeProject(){
        frame = new JFrame("A-Mazing Program");
        frame.add(this);
        frame.setSize(1000,600);
        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,frame.getWidth(),frame.getHeight());

        g2.setColor(new Color(180,0,120));
        g2.fillOval(c,r,50,50);
        g2.setColor(Color.YELLOW);
        g2.setStroke(new BasicStroke(5));
        g2.drawOval(c,r,50,50);
    }

    public static void main(String[]args){
        MazeProject app = new MazeProject();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if(e.getKeyCode() == 38){   //forward
            if(dir==0)
                r-=50;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
