import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Celebrity {

    public Celebrity(){
        File file = new File("C:\\Users\\rishi\\Documents\\SBHS\\12th\\Data-Structures---Game-Design\\CelebrityACSII\\src\\asciiPhoto9.txt");
        try{
            BufferedReader input = new BufferedReader(new FileReader(file));
            String text;
            System.out.println(input.readLine());
        }catch (Exception e){

        }

    }

    public static void main(String[]args){
        Celebrity app = new Celebrity();
    }
}
