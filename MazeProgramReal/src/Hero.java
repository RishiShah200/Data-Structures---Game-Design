import java.awt.*;

public class Hero {

    private Location loc;

    private int size;
    private int dir;
    private Color body, outline;

    public Hero(Location loc, int size, int dir, Color body, Color outline) {
        this.loc = loc;
        this.size = size;
        this.dir = dir;
        this.body = body;
        this.outline = outline;

    }

    public int getSize() {
        return size;
    }

    public int getDir() {
        return dir;
    }

    public Color getBody() {
        return body;
    }

    public Color getOutline() {
        return outline;
    }

    public Location getLoc() {
        return loc;
    }

    public void move(int key, char[][] maze) {
        int r = getLoc().getR();
        int c = getLoc().getC();

        if (key == 38) {    //forward

            if (dir == 0) {
                if (r > 0 && maze[r - 1][c] == ' ') {
                    getLoc().setR(-1);

                }
            }

            if (dir == 1) { //right
                if (c < maze[0].length - 1 && maze[r][c + 1] == ' ') {
                    getLoc().setC(+1);

                }
            }

            if (dir == 2) {
                if (r < maze.length - 1 && maze[r + 1][c] == ' ') {
                    getLoc().setR(+1);

                }
            }

            if (dir == 3) {
                if (c > 0 && maze[r][c - 1] == ' ') {
                    getLoc().setC(-1);

                }
            }

        }

        if (key == 37) {        //rotate left
            dir--;
            if (dir < 0) {
                dir = 3;
            }
        }

        if (key == 39) {    //rotate right
            dir++;
            if (dir > 3) {
                dir = 0;
            }
        }
    }

    public Rectangle getRect() {
        int r = getLoc().getR();
        int c = getLoc().getC();

        return new Rectangle(c * size + size, r * size + size, size, size);
    }


}
