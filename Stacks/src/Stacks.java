import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Stack;

public class Stacks {

    public Stacks(){
        DecimalToBinary(10);
        System.out.println();
        ReverseString("hellothisisthestringichose");

        try{
            File data = new File("C:\\Users\\rishi\\Documents\\SBHS\\12th\\Data-Structures---Game-Design\\Stacks\\src\\StarWarsCharacters.csv");
            BufferedReader input = new BufferedReader(new FileReader(data));
            String text;
            String[] characterData;
            Stack<Character> maleCharacters = new Stack<Character>();
            Stack<Character> femaleCharacters = new Stack<Character>();
            Stack<Character> droids = new Stack<Character>();
            Stack<Character> validBirthYear = new Stack<Character>();
            input.readLine();
            while((text=input.readLine())!=null){
                if(text.contains("\"")){
                    text = text.substring(0, text.indexOf("\"")) + text.substring(text.indexOf("\"")).replaceFirst(",", "/");
                }
                characterData = text.split(",");
                if(characterData[6].equals("male")){
                    maleCharacters.push(new Character(characterData[0],characterData[5],characterData[6],characterData[7],characterData[8]));
                }
                if(characterData[6].equals("female")){
                    femaleCharacters.push(new Character(characterData[0],characterData[5],characterData[6],characterData[7],characterData[8]));
                }
                if(characterData[8].equals("Droid")){
                    droids.push(new Character(characterData[0],characterData[5],characterData[6],characterData[7],characterData[8]));
                }
                if(!characterData[5].equals("NA")){
                    validBirthYear.push(new Character(characterData[0],(characterData[5]).substring(0,characterData[5].length()-3),characterData[6],characterData[7],characterData[8]));
                }
            }

            System.out.println("\n");
            System.out.println("Male Characters");
            System.out.printf("%-24s %-24s","Name","Homeworld");
            System.out.println();
            while(!maleCharacters.isEmpty()){
                Character character = maleCharacters.pop();
                System.out.printf("%-24s %-24s \n",character.getName(),character.getHomeWorld().replaceAll("NA","Unknown"));
            }

            System.out.println("\n");
            System.out.println("Female Characters");
            System.out.printf("%-24s %-24s","Name","Homeworld");
            System.out.println();
            while(!femaleCharacters.isEmpty()){
                Character character = femaleCharacters.pop();
                System.out.printf("%-24s %-24s \n",character.getName(),character.getHomeWorld().replaceAll("NA","Unknown"));
            }

            System.out.println("\n");
            System.out.println("Droids");
            System.out.printf("%-24s %-24s","Name","Homeworld");
            System.out.println();
            while(!droids.isEmpty()){
                Character character = droids.pop();
                System.out.printf("%-24s %-24s \n",character.getName(),character.getHomeWorld().replaceAll("NA","Unknown"));
            }

            System.out.println("\n");
            System.out.println("Ages");
            System.out.printf("%-24s %-24s %-24s","Name","Homeworld","Birth Year (BBY)");
            System.out.println();
            while(!validBirthYear.isEmpty()){
                Character character = validBirthYear.pop();
                System.out.printf("%-24s %-24s %-24s \n",character.getName(),character.getHomeWorld().replaceAll("NA","Unknown"),character.getBirthYear());
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void DecimalToBinary(int num){
        Stack<Integer> stack = new Stack<Integer>();
        while(num!=0){
            stack.push(num%2);
            num/=2;
        }
        while(!stack.isEmpty()){
            System.out.print(stack.pop());
        }
    }

    public void ReverseString(String s){
        Stack<String> stack = new Stack<String>();
        for(int x = 0;x<s.length();x++){
            stack.push(s.substring(x,x+1));
        }
        while(!stack.isEmpty()){
            System.out.print(stack.pop());
        }
    }

    public static void main(String[] args){
        Stacks app = new Stacks();
    }

    public class Character{
        private String name;
        private String birthYear;
        private String gender;
        private String homeWorld;
        private String species;
        public Character(String name,String birthYear,String gender,String homeWorld,String species){
            this.name = name;
            this.birthYear = birthYear;
            this.gender = gender;
            this.homeWorld = homeWorld;
            this.species = species;
        }


        public String getName() {
            return name;
        }

        public String getBirthYear() {
            return birthYear;
        }

        public String getGender() {
            return gender;
        }

        public String getHomeWorld() {
            return homeWorld;
        }

        public String getSpecies() {
            return species;
        }
    }
}
