import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.*;

public class FileTemplate {

    public FileTemplate(){

        File fileName = new File("C:\\Users\\rishi\\Documents\\SBHS\\12th\\Data-Structures---Game-Design\\FirstDemo\\src\\example.txt");

        try{
            BufferedReader input = new BufferedReader(new FileReader(fileName));
            String text;
            int sum = 0;
            while((text=input.readLine()) != null){
                String[] pieces = text.split(",");
                int num;
                try{
                    for(int x = 0;x< pieces.length;x++){
                        num = Integer.parseInt(pieces[x]);
                        sum+=num;
                    }
                } catch(NumberFormatException nfe){}
            }
            System.out.println(sum);
        }catch(IOException ioe){
            System.out.println("File not found.");
            System.out.println(ioe);
        }

    }


    public static void main(String[]args){
        FileTemplate app = new FileTemplate();
    }
}
