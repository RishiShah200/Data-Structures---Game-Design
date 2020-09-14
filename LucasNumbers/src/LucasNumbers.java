import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class LucasNumbers {

    public LucasNumbers(){
        File myFile = new File("C:\\Users\\rishi\\Documents\\SBHS\\12th\\Data-Structures---Game-Design\\LucasNumbers\\src\\LucasNumFile.txt");

        try{
            BufferedReader input = new BufferedReader(new FileReader(myFile));
            String text;
            while((text= input.readLine()) != null){
                int num = Integer.parseInt(text);
                System.out.println("Term: " + num + " Value: " + findTerm(num));
            }
        }catch (IOException e){

        }

    }

    public BigInteger findTerm(int x){
        BigInteger sum = new BigInteger(""+BigInteger.valueOf(0));
        ArrayList<BigInteger> list = new ArrayList<>();
        list.add(BigInteger.valueOf(2));
        list.add(BigInteger.valueOf(1));

        if(x == 0){
            return new BigInteger(String.valueOf(BigInteger.valueOf(2)));
        }
        if(x == 1){
            return new BigInteger(String.valueOf(BigInteger.valueOf(1)));
        }

        for (BigInteger bi = BigInteger.valueOf(0);
             bi.compareTo(BigInteger.valueOf(x)) <= 0;
             bi = bi.add(BigInteger.ONE)) {

            list.add(sum.add(list.get(bi.intValue()).add(list.get(bi.intValue()+1))));

        }
        return list.get(x);
    }


    public static void main(String[]args){
        LucasNumbers app = new LucasNumbers();
    }
}
