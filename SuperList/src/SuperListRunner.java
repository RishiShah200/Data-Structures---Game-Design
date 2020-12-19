import java.util.LinkedList;
import java.util.Queue;

public class SuperListRunner {

    public SuperListRunner(){
        SuperList<Integer> list = new SuperList<Integer>();
        for(int x = 0;x<30;x++){
            list.add((int)(Math.random()*1000) + 1);
        }
        System.out.println("ArrayList: " + list.toString());
        System.out.println("List Size: " + list.size());

        SuperList<Integer> stack = new SuperList<Integer>();
        while(!list.isEmpty())
            stack.push(list.remove(0));

        System.out.println("Stack Version: " + stack);
        SuperList<Integer> queue = new SuperList<Integer>();
        while(!stack.isEmpty()){
            int temp = stack.pop();
            queue.add(temp);
        }
        System.out.println();

        System.out.println("Queue Version: " + queue);
        while(!queue.isEmpty()){
            int temp = queue.poll();
            int index = (int)(Math.random()*list.size());
            list.add(index,temp);
        }
        System.out.println();

        System.out.println("Random Index Position ArrayList Version: " + list);

        int sum = 0;
        int evenSum = 0;
        int oddSum = 0;
        for(int x = 0;x<list.size();x++){
            sum+=list.get(x);
            if(x%2==0){
                evenSum+=list.get(x);
            }
            else if(x%2==1){
                oddSum+=list.get(x);
            }
        }

        System.out.println("Overall Sum: " + sum);
        System.out.println("Even Sum: " + evenSum);
        System.out.println("Odd Sum: " + oddSum);

        int unchangedListSize = list.size();
        for(int x = 0;x<unchangedListSize;x++){
            if(list.get(x)%2==0){
                list.add(list.get(x));
            }
        }

        System.out.println("Duplicate Even Numbers: " + list);
        int realSize = list.size();
        for(int x = 0;x<realSize;x++){
            if(list.get(x)%3==0){
                list.remove(x);
                realSize--;
                x--;
            }
        }

        System.out.println("No divisible by 3: " + list);

        list.add(4,55555);
        System.out.println("Insert in 4th position of list: " + list);

        for(int x = 0;x<list.size()-1;x++){
            int index = x;
            while(index>= 0 && list.get(index) > list.get(index+1)){
                list.add(index,list.remove(index+1));
                index--;
            }
        }

        System.out.println("Sorted in Ascending Order: " + list);

        double median = 0;
        int sizeOfList = list.size();
        if(sizeOfList%2==1){
            median = list.get(sizeOfList/2);
        }
        else{
            median = (double)(list.get(sizeOfList/2) + list.get((sizeOfList/2)-1))/2;
        }
        System.out.println("Median: " + median);

        int lastindex = -1;
        System.out.print("Values before median: [");
        for(int x = 0;x<list.size();x++){
            if((double)list.get(x) < median)
                System.out.print(list.get(x));
            else
                break;
            if(x+1 < list.size() && list.get(x+1) < median)
                System.out.print(", ");
            else
                lastindex = x;
        }
        System.out.print("]");

        System.out.print("Value after median: [");

        for(int x = lastindex;x<list.size()-1;x++){
            if((double)list.get(x) > median)
                System.out.print(list.get(x) + ", ");
        }
        System.out.println(list.get(list.size()-1) + "]");
        System.out.println();

        SuperList<String> stringList = new SuperList<String>();
        String sentence = "He decided water-skiing on a frozen lake wasn’t a good idea.";
        System.out.println("Unchanged Sentence: " + sentence);
        sentence = sentence.replaceAll("[^a-zA-Z\\d\\s]","");
        String[] words = sentence.split(" ");
        for(String word: words){
            stringList.add(word);
        }
        System.out.println("Words: " + stringList);

        for(int x = 0;x<stringList.size();x++){
            if(stringList.get(x).length() <= 3) {
                stringList.remove(x);
                x--;
            }
        }

        System.out.println("Removed Small Words: " + stringList);
    }



    public static void main(String[]args){
        SuperListRunner app = new SuperListRunner();
    }
}
