import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Spiral {

    public Spiral(){
        File data = new File("src/Spiraling.txt");

        try{
            BufferedReader input = new BufferedReader(new FileReader(data));
            String text;
            while((text=input.readLine()) != null){
                int size = Integer.parseInt(text);
                String[][] array = new String[size][size];

                int startR = 0;
                int startC = 0;
                int endR = size - 1;
                int endC = size - 1;

                for (int x = 0; x<array[0].length; x++){
                    for (int y = 0; y<array.length; y++){
                        array[x][y] = "-";
                    }
                }

                while(startR<= endR && startC<=endC){

                    for(int c = startC;c<=endC;c++){ //going right
                        array[startR][c] = "*";

                    }
                    if(startC >= 1){
                        startC++;
                    }
                    startR++;

                    for(int r = startR;r<=endR;r++){    //going down
                        array[r][endC] = "*";
                    }
                    endC--;

                    for(int c = endC;c>=startC;c--){
                        array[endR][c] = "*";
                    }
                    endR--;

                    for(int r = endR; r >= startR+1;r--){
                        array[r][startC] = "*";
                    }
                    startC++;
                    startR++;
                    endC--;
                    endR--;

                }

                if(array.length%4==2){
                    array[size/2][(size/2)-1] = "-";
                }

                for (int x = 0; x<array[0].length; x++){
                    for (int y = 0; y<array.length; y++){
                        System.out.print(array[x][y]);
                    }
                    System.out.println();
                }
                System.out.println();

            }
        }catch (IOException e){
            System.out.print(e);
        }
    }

    public static void main(String[]args){
        Spiral app = new Spiral();
    }
}
