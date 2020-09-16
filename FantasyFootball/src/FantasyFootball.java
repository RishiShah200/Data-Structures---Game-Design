import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FantasyFootball {

    public FantasyFootball(){

        File fantasyFootballData = new File("C:\\Users\\rishi\\Documents\\SBHS\\12th\\Data-Structures---Game-Design\\FantasyFootball\\src\\FantasyFootballData.txt");
        try{

            BufferedReader input = new BufferedReader(new FileReader(fantasyFootballData));
            String text;

            boolean firstLine = true;
            String[] headers = new String[9];

            ArrayList<FootballPlayer> players = new ArrayList<FootballPlayer>();

            while((text=input.readLine()) != null){
                if(firstLine){
                    headers = text.split(";");
                    firstLine = false;
                }
                else{
                    String[] playerData = text.split(";");
                    FootballPlayer footballPlayer = new FootballPlayer(playerData);
                    players.add(footballPlayer);
                }

            }
            System.out.printf("%-8s %-24s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-16s %-8s \n",
                    headers[0], headers[1], headers[2], headers[3], headers[4], headers[5], headers[6], headers[7], headers[8], headers[9], "PickConsistency");
            Collections.sort(players);

            for(FootballPlayer player: players){
                System.out.println(player);

            }
        }catch (IOException e){

        }


    }




    public static void main(String[]args){
        FantasyFootball app = new FantasyFootball();
    }

}
