import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueuePractice {

    public QueuePractice(){
        File file = new File("C:\\Users\\rishi\\Documents\\SBHS\\12th\\Data-Structures---Game-Design\\QueuePractice\\src\\text.txt");

        try{
            BufferedReader input = new BufferedReader(new FileReader(file));
            String text;
            PriorityQueue<Word> pq = new PriorityQueue<>();
            Queue<Word> queue = new LinkedList<>();
            while((text=input.readLine()) != null){
                text = text.replaceAll("[^a-zA-Z0-9\\s]","");
                String[] words = text.split(" ");
                for (String s : words) {
                    pq.add(new Word(s));
                    queue.add(new Word(s));
                }
            }

            while(!pq.isEmpty()){
                System.out.printf("%-24s %-24s \n",pq.poll(),queue.poll());
            }


        }catch(Exception e){

        }

        //Passenger Stuff Starts here.
        Queue<Passenger> passenger = new LinkedList<>();
        PriorityQueue<Passenger> ordered = new PriorityQueue<>();
        File passengerInfo = new File("C:\\Users\\rishi\\Documents\\SBHS\\12th\\Data-Structures---Game-Design\\QueuePractice\\src\\PassengerInfo.txt");
        int cnt = 0;
        try{
            BufferedReader input = new BufferedReader(new FileReader(passengerInfo));
            String text;
            ArrayList<String> list = new ArrayList<>();
            while((text=input.readLine())!=null){
                list.add(text);
                list.add(input.readLine());
                list.add(input.readLine());
                String[] names = list.get(0).split(" ");
                passenger.add(new Passenger(names[0],names[1],list.get(1),list.get(2)));

                list.clear();
            }

            for(Passenger p: passenger) {
                String[] time = p.etdCalc().split(":");
                if((Integer.parseInt(time[1]) < 60) & Integer.parseInt(time[0]) == 0) {
                    ordered.add(p);
                }
                //System.out.println(p.toString());
            }

           System.out.println("\n\n");
            while(!ordered.isEmpty()){
                System.out.println(ordered.poll());
            }

        }catch (Exception f){
            f.printStackTrace();
        }



    }

    public static void main(String[]args){
        QueuePractice app = new QueuePractice();
    }
}
