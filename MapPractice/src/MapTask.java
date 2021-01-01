import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MapTask {

    public MapTask(){
        File file = new File("C:\\Users\\rishi\\Documents\\SBHS\\12th\\Data-Structures---Game-Design\\MapPractice\\src\\BowlingData.txt");
        try{
            BufferedReader input = new BufferedReader(new FileReader(file));
            TreeMap<Integer,PriorityQueue<Bowler>> map = new TreeMap<Integer,PriorityQueue<Bowler>>();
            String text;
            while((text=input.readLine())!= null){
                String[] pieces = text.split(" ");
                int score = Integer.parseInt(pieces[2]);
                if(!map.containsKey(score)){
                    map.put(score,new PriorityQueue<Bowler>());
                }
                Bowler bowler = new Bowler(pieces[0],pieces[1],score);
                map.get(score).add(bowler);
            }

            System.out.println(map);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("+++++++++++++++++++++++KEYS+++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");


            Iterator<Integer> keys = map.keySet().iterator();

            while(keys.hasNext()){
                System.out.println(keys.next());
            }

            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("+++++++++++++++++++++++ENTRY SET++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");

            for(int key : map.keySet())
                System.out.println(key + "=" + printPriorityQueue(map.get(key)));


            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("+++++++++++++++++++++++VALUES+++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");

            for(PriorityQueue<Bowler> pq : map.values())
                System.out.println(printPriorityQueue(pq));



            while(keys.hasNext()){
                System.out.println(keys);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }


    }

    public String printPriorityQueue(PriorityQueue<Bowler> priorityQueue){
        PriorityQueue<Bowler> temp = new PriorityQueue<Bowler>(priorityQueue);
        String output = "[";
        while(!temp.isEmpty()){
            output += temp.poll() + (temp.isEmpty() ? "]" : ",");
        }
        return output;
    }

    public static void main(String[]args){
        MapTask app = new MapTask();
    }
}
