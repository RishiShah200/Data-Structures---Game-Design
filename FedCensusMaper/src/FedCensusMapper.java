import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.NumberFormat;
import java.util.*;

public class FedCensusMapper {

    ArrayList<Citizen> list;
    public FedCensusMapper(){
        File file = new File("src/FedCensus1930_CambriaCountyPA.txt");
        String text;
        TreeSet<Citizen> set = new TreeSet<Citizen>();
        TreeMap<String, TreeSet<Citizen>> citizenMap = new TreeMap<String, TreeSet<Citizen>>();

        list = new ArrayList<Citizen>();
        try{
            BufferedReader input = new BufferedReader(new FileReader(file));
            while((text=input.readLine())!=null){
                if(text.length() > 2 && text.substring(0,2).equals("17")){
                    String first = text.substring(71,88).trim();
                    String last = text.substring(55,71).trim();
                    String street = text.substring(20,36).trim();
                    String streetNumber = text.substring(36,45).trim();
                    String relation = text.substring(88,108).trim();
                    String rentOwn = text.substring(108,113).trim();
                    String propValue = text.substring(113,121).trim();
                    String gender = text.substring(133,134).trim();
                    String age = text.substring(143,151).trim();
                    String maritalStatus = text.substring(151,156).trim();
                    String ageFirstMarriage = text.substring(156,162).trim();
                    String attendSchool = text.substring(162,167).trim();
                    String canRead = text.substring(167,173).trim();
                    String birthPlace = text.substring(173,190).trim();
                    String fathersBirthPlace = text.substring(190,207).trim();
                    String mothersBirthPlace = text.substring(207,224).trim();
                    String motherTongue = text.substring(224,235).trim();
                    String immigrationYear = text.substring(235,241).trim();
                    String occupation = text.substring(252,274).trim();
                    String industry = text.substring(274,303).trim();
                    String transcriperRemarks = text.substring(342,text.length()-1).trim();

                    list.add(new Citizen(first,last,street,streetNumber,relation,rentOwn,propValue,gender,age,maritalStatus,ageFirstMarriage,attendSchool,
                            canRead,birthPlace,fathersBirthPlace,mothersBirthPlace,motherTongue,immigrationYear,occupation,industry,transcriperRemarks));

                }

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        for(int x = list.size()-1;x>=0;x--){
            String last = list.get(x).getLast();
            String first = list.get(x).getFirst();
            if(last.equals(".") && first.equals("."))
                list.remove(x);
        }
        Collections.sort(list);
        for(Citizen citizen: list)
            System.out.println(citizen);

        citizensOnStreets();
        spacing();
        birthPlaceAndAge();
        spacing();
        motherTongueName();
        spacing();
        occupationFatherBirthPlace();
        spacing();
        genderRemarks();
        spacing();
        rentOrOwn();
        spacing();
        industryAndAge();

    }
    public static void main(String[]args){
        FedCensusMapper app = new FedCensusMapper();
    }

    public void spacing(){
        System.out.println("**********************************************************************************************************\n\n");
    }

    public void citizensOnStreets(){
        TreeMap<String,TreeSet<Citizen>> map = new TreeMap<String,TreeSet<Citizen>>();
        for(Citizen c: list){
            if(!map.containsKey(c.getStreet()))
                map.put(c.getStreet(),new TreeSet<Citizen>());
            map.get(c.getStreet()).add(c);
        }
        Iterator<String> iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            String street = iterator.next();
            System.out.println(street + ":");
            TreeSet<Citizen> temp = map.get(street);
            for(Citizen c: temp)
                System.out.println("\t" + c);
        }
    }
    public void birthPlaceAndAge() {
        TreeMap<String, PriorityQueue<Double>> map = new TreeMap<String, PriorityQueue<Double>>();
        for (Citizen c : list) {
            if (!map.containsKey(c.getBirthPlace()))
                map.put(c.getBirthPlace(), new PriorityQueue<Double>());
            map.get(c.getBirthPlace()).add(c.getAge());
        }

        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String birthPlace = iterator.next();
            System.out.println(birthPlace + ":");
            PriorityQueue<Double> temp = map.get(birthPlace);
            System.out.print("[");
            while (!temp.isEmpty()) {
                double age = temp.poll();
                if (age >= 0) {
                    if (temp.peek() != null)
                        System.out.print(age + ", ");
                    else
                        System.out.print(age);
                }
            }
            System.out.println("]");

        }
    }

    public void motherTongueName(){
        TreeMap<String, ArrayList<String>> map = new TreeMap<>();
        for(Citizen c: list){
            if(!map.containsKey(c.getMotherTongue()))
                map.put(c.getMotherTongue(),new ArrayList<String>());
            map.get(c.getMotherTongue()).add(c.getFirst() + " " + c.getLast());
        }
        Iterator<String> iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            String motherTongue = iterator.next();
            System.out.println(motherTongue + ":");
            ArrayList<String> temp = map.get(motherTongue);
            for(String c: temp)
                System.out.println("\t" + c);
        }
    }

    public void occupationFatherBirthPlace(){
        TreeMap<String, HashSet<String>> map = new TreeMap<>();
        for(Citizen c: list){
            if(!map.containsKey(c.getOccupation()))
                map.put(c.getOccupation(),new HashSet<String>());
            map.get(c.getOccupation()).add(c.getFathersBirthPlace());
        }
        Iterator<String> iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            String occupation = iterator.next();
            System.out.println(occupation + ":");
            HashSet<String> temp = map.get(occupation);
            Iterator<String> it = temp.iterator();
            while(it.hasNext())
                System.out.println("\t" + it.next());
        }
    }

    public void genderRemarks(){
        TreeMap<String, HashSet<String>> map = new TreeMap<>();
        for(Citizen c: list){
            if(!map.containsKey(c.getGender()))
                map.put(c.getGender(),new HashSet<String>());
            map.get(c.getGender()).add(c.getTranscriperRemarks());
        }
        Iterator<String> iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            String gender = iterator.next();
            System.out.println(gender + ":");
            HashSet<String> temp = map.get(gender);
            Iterator<String> it = temp.iterator();
            for(String string : temp)
                System.out.println("\t" + string);
        }
    }

    public void rentOrOwn(){
        TreeMap<String,TreeSet<Double>> map = new TreeMap<String,TreeSet<Double>>();
        for(Citizen c: list){
            if(!map.containsKey(c.getRentOwn()))
                map.put(c.getRentOwn(),new TreeSet<Double>());
            map.get(c.getRentOwn()).add(c.getPropValue());
        }
        Iterator<String> iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            String rentOrOwn = iterator.next();
            System.out.println(rentOrOwn + ":");
            TreeSet<Double> temp = map.get(rentOrOwn);
            for(Double nums: temp)
                System.out.println("\t" + nums);
        }
    }

    public void industryAndAge() {
        TreeMap<String, PriorityQueue<Double>> map = new TreeMap<String, PriorityQueue<Double>>();
        for (Citizen c : list) {
            if (!map.containsKey(c.getIndustry()))
                map.put(c.getIndustry(), new PriorityQueue<Double>());
            map.get(c.getIndustry()).add(c.getAge());
        }

        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String industry = "";
                industry = iterator.next();


            System.out.println(industry + ":");
            PriorityQueue<Double> temp = map.get(industry);
            System.out.print("[");
            while (!temp.isEmpty()) {
                double age = temp.poll();
                if (age >= 0) {
                    if (temp.peek() != null)
                        System.out.print(age + ", ");
                    else
                        System.out.print(age);
                }
            }
            System.out.println("]");

        }
    }
}
