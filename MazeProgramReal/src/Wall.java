import java.awt.*;

public class Wall{

    private int[] cols;
    private int[] rows;
    private Color color;
    private int size;
    private boolean filled;
    private String wallName;
    private int fov;

    public Wall(String wallName, int[] cols, int[] rows, Color color, int size, boolean filled, int fov) {
        this.wallName = wallName;
        this.cols = cols;
        this.rows = rows;
        this.color = color;
        this.size = size;
        this.filled = filled;
        this.fov = fov;
    }

    public int[] getCols() {
        return cols;
    }

    public int[] getRows() {
        return rows;
    }

    public Color getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public void setColor(Color c){
        color = c;
    }

    public boolean isFilled(){
        return filled;
    }


    public GradientPaint getPaint(float time){
        int factor = (int)(size*0.7);
        int start = 205;
        int end = 205;
        if(filled){
            start-= factor * fov;
            end -= factor * (fov+1);
        }
        else{
            start=0;
            end=0;
        }
        if (wallName.equals("top trapezoid") || wallName.equals("bottom trapezoid"))
            return new GradientPaint(cols[0],rows[0], new Color(start,start,start),cols[0],rows[1],new Color(end,end,end));
        return new GradientPaint(cols[0],rows[0], new Color(start,start,start),cols[1],rows[0],new Color(end,end,end));
    }

    public Polygon getPoly(){
        return new Polygon(getCols(),getRows(),4);
    }

}