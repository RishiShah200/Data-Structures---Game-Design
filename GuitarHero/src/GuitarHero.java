import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GuitarHero {


    public GuitarHero() {

        File data = new File("src/GuitarTabFile.txt");

        int cnt = 1;
        int[][] helper = {{29, 24, 19, 14, 10, 5},
                {28, 23, 18, 13, 9, 4},
                {27, 22, 17, 12, 8, 3},
                {26, 21, 16, 11, 7, 2},
                {25, 20, 15, 10, 6, 1}
        };

        String[] noteNames = {"G#", "G", "F#", "F", "E", "D#", "D", "C#", "C", "B", "A#", "A", "G#",
                "G", "F#", "F", "E", "D#", "D", "C#", "C", "B", "A#", "A", "G#", "G", "F#", "F", "E"};

//        for (int x = helper[0].length - 1; x >= 0; x--) {
//            for (int j = helper.length - 1; j >= 0; j--) {
//                if (cnt > 10) {
//                    helper[j][x] = cnt - 1;
//                } else {
//                    helper[j][x] = cnt;
//                }
//                cnt++;
//            }
//        }


        String[][] grid = null;

        try {
            BufferedReader input = new BufferedReader(new FileReader(data));
            String text;
            int counter = 0;
            while ((text = input.readLine()) != null) {
                String[] pieces = text.split(",");
                if (grid == null) {
                    grid = new String[30][pieces.length + 1];
                    grid[0][0] = "Measure";
                    for (int m = 1; m <= pieces.length; m++)
                        grid[0][m] = "" + m;

                    for (int r = 1; r <= noteNames.length; r++)
                        grid[r][0] = noteNames[r - 1];
                }

                for (int measure = 0; measure < pieces.length; measure++) {
                    for (int col = 0; col < 6; col++) {
                        if (pieces[measure].charAt(col) == '*' || pieces[measure].charAt(col) == 'o') {
                            grid[helper[counter][col]][measure + 1] = "O";
                        }
                    }
                }
                counter++;
            }
            showOutput(grid);
        } catch (IOException e) {

        }
    }

    public void showOutput(String[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == null) {
                    System.out.print("\t");
                } else {
                    System.out.print(grid[r][c] + "\t");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GuitarHero app = new GuitarHero();
    }

}
