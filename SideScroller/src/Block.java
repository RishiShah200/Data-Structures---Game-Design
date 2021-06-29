import java.awt.geom.Rectangle2D;

public class Block {

    int blockX, blockY, width, height;
    String type;

    public Block(int blockX, int blockY, int width, int height, String type){
        this.blockX = blockX;
        this.blockY =blockY;
        this.width = width;
        this.height = height;
        this.type = type;
    }


    public boolean isTopBlock() {return type.equals("B");}

    public int getX(){
        return blockX;
    }

    public int getY(){
        return blockY;
    }

    public Rectangle2D getCollisionBox(){
        return new Rectangle2D.Double(getX(),getY(),width,height);
    }

    public void updateX(int num){
        blockX+=num;
        if(blockX<=-50)
            blockX+=1000;
    }
}
