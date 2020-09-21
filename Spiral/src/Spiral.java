import java.util.Scanner;

public class Spiral {
    public Spiral(Scanner reader){
        food(20,reader);
    }
    public static int food(int x,Scanner reader){
        System.out.print("Please enter the value :");
        int value = reader.nextInt();
        while(value<=1){
            System.out.print("The value you have entered is invalid, please repick ");
            value = reader.nextInt();
        }
        int mincol = 0;
        int maxcol = value-1;
        int minrow = 0;
        int maxrow = value-1;
        int cnt = 1;
        int [][] arr = new int [x][x];
        while(cnt<=value*value){
            for(int y = mincol;y<=maxcol;y++){
                arr[minrow][y] = cnt;
                cnt++;
            }
            for(int y = minrow+1;y<=maxrow;y++){
                arr[y][maxcol] = cnt;
                cnt++;
            }
            for(int y = maxcol-1;y>=mincol;y--){
                arr[maxrow][y] = cnt;
                cnt++;
            }
            for(int y = maxrow-1;y>=minrow+1;y--){
                arr[y][mincol] = cnt;
                cnt++;
            }
            mincol++;
            minrow++;
            maxcol--;
            maxrow--;
        }
        for(int h = 0;h<arr.length;h++){
            for(int p = 0;p<arr.length;p++){
                System.out.print(arr[h][p]+"\t");
            }
            System.out.println();
        }
        return value;
    }
    public static void main(String[]args){
        Scanner reader = new Scanner (System.in);
        Spiral app = new Spiral(reader);

    }
}
