import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GuitarHero {


    public GuitarHero() {

        File data = new File("src/GuitarTabFile.txt");


        int cnt = 1;

        int[][] helper = new int[5][6];

        for (int x = helper[0].length - 1; x >= 0; x--) {
            for (int j = helper.length - 1; j >= 0; j--) {
                if (cnt > 10) {
                    helper[j][x] = cnt - 1;
                } else {
                    helper[j][x] = cnt;
                }
                cnt++;
            }

        }


        for (int x = 0; x < helper.length; x++) {
            for (int j = 0; j < helper[0].length; j++) {
                System.out.print(helper[x][j] + " ");
            }
            System.out.println();
        }

        String[][] inputText = null;
        boolean declared = false;
        int counter = 0;

        try {
            BufferedReader input = new BufferedReader(new FileReader(data));
            String text;

            while ((text = input.readLine()) != null) {
                String[] pieces = text.split(",");
                if (!declared) {
                    inputText = new String[5][pieces.length];
                }
                for (int x = 0; x < pieces.length; x++) {
                    inputText[cnt][x] = pieces[x];
                }
                counter++;
            }
            input.close();
        } catch (IOException e) {

        }


        String[][] grid = new String[30][inputText.length + 1];
        String[] notes = {"Measure", ""};

        /*for (int m = 0; m < grid.length; m++) {
            for (int col = 0; col < 6; col++) {
                if (m == 0) {
                    if (col == 0) {
                        grid[m][col] = "Measure";
                    }
                    if (inputText[m][col].charAt() == 'o' || inputText[m].charAt(col) == '*') {

                    }
                }
            }
        }*/



    }


    public static void main(String[] args) {
        GuitarHero app = new GuitarHero();
    }

}
