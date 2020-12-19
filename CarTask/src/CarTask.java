import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class CarTask {

    public CarTask(){

        try{
            File file = new File("C:\\Users\\rishi\\Documents\\SBHS\\12th\\Data-Structures---Game-Design\\CarTask\\src\\CarData.txt");
            BufferedReader input = new BufferedReader(new FileReader(file));
            Queue<Car> queue = new LinkedList<Car>();
            Stack<Car> stack = new Stack<>();
            PriorityQueue<Car> pq = new PriorityQueue<>();
            String text;
            String[] data;
            input.readLine();
            while((text=input.readLine())!=null){
                data = text.split("[\\s+]");
                queue.add(new Car(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]),Integer.parseInt(data[3]),
                        Integer.parseInt(data[4]),Integer.parseInt(data[5]),Integer.parseInt(data[6]),Integer.parseInt(data[7])));
            }
            System.out.printf("%-6s %-6s %-12s %-6s %-8s %-16s %-8s %-12s\n","ID","MPG","Engine Size","HP","Weight",
                    "Acceleration","Origin","Cylinders");
            for(Car c:queue){
                System.out.println(c.toString());
            }

            System.out.println("STACK STARTS HERE" + "\n\n\n\n");

            System.out.printf("%-6s %-6s %-12s %-6s %-8s %-16s %-8s %-12s\n","ID","MPG","Engine Size","HP","Weight",
                    "Acceleration","Origin","Cylinders");

            while(!queue.isEmpty()){
                stack.push(queue.poll());
            }
            for(Car c:stack){
                pq.add(c);
            }
            while(!stack.empty()){
                System.out.println(stack.pop());
            }

            System.out.println("PriorityQueue STARTS HERE" + "\n\n\n\n");

            System.out.printf("%-6s %-6s %-12s %-6s %-8s %-16s %-8s %-12s\n","ID","MPG","Engine Size","HP","Weight",
                    "Acceleration","Origin","Cylinders");

            while(!pq.isEmpty()){
                System.out.println(pq.poll());
            }

        }catch(Exception e){

        }


    }


    public static void main(String[]args){
        CarTask app = new CarTask();
    }

}
