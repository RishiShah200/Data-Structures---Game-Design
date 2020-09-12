import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AmicableNumbers {

    public AmicableNumbers(){

        File fileName = new File("C:\\Users\\rishi\\Documents\\SBHS\\12th\\Data-Structures---Game-Design\\AmicableNumbers\\src\\AmicableNumsFile.txt");

        try{
            BufferedReader input = new BufferedReader(new FileReader(fileName));
            String text;
            while((text= input.readLine()) != null){
                String[] nums = text.split(" ");
                int firstNum = Integer.parseInt(nums[0]);
                int secondNum = Integer.parseInt(nums[1]);

                ArrayList<Integer> firstList = getFactors(firstNum);
                firstList.remove(firstList.size()-1);
                ArrayList<Integer> secondList = getFactors(secondNum);
                secondList.remove(secondList.size()-1);

                int firstSum = getSumOfList(firstList);
                int secondSum = getSumOfList(secondList);

                if((firstNum == secondSum) && (secondNum == firstSum)){
                    System.out.printf("The numbers %s and %2d are amicable %n",firstNum,secondNum);
                    System.out.printf("\tFactors of %s are %s. Sum is %2d %n",firstNum,printList(firstList),firstSum);
                    System.out.printf("\tFactors of %s are %s. Sum is %2d %n",secondNum,printList(secondList),secondSum);
                }
                else{
                    System.out.printf("The numbers %s and %2d are not amicable %n",firstNum,secondNum);
                    System.out.printf("\tFactors of %s are %s. Sum is %2d %n",firstNum,printList(firstList),firstSum);
                    System.out.printf("\tFactors of %s are %s. Sum is %2d %n",secondNum,printList(secondList),secondSum);
                }


            }
        }catch(IOException e){
            System.out.println(e);
        }



    }

    public ArrayList<Integer> getFactors(int num){
        ArrayList<Integer> list = new ArrayList<>();

        for(int x = 1;x<= num;x++){
            if(num % x == 0){
                list.add(x);
            }
        }

        return list;
    }

    public int getSumOfList(ArrayList<Integer> list){
        int sum = 0;
        for(int num:list){
            sum+=num;
        }
        return sum;
    }

    public String printList(ArrayList<Integer> list){
        String result = "";
        for(int num:list.subList(0,list.size()-2)){
            result+= num + ", ";
        }
        result+="and " + list.get(list.size()-1);
        return result;

    }

    public static void main(String[]args){
        AmicableNumbers app = new AmicableNumbers();
    }
}
