import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class TimeTravel {

    public TimeTravel(){

        File theData = new File("C:\\Users\\rishi\\Documents\\SBHS\\12th\\Data-Structures---Game-Design\\TimeTravel\\src\\TravelFile.txt");

        try{
            BufferedReader input = new BufferedReader(new FileReader(theData));
            String text;
            String[] dates;
            int trip = 1;
            while((text=input.readLine()) != null){
                dates = text.split(" ");
                int days = Integer.parseInt(dates[0]);
                int hours = Integer.parseInt(dates[1]);
                int minutes = Integer.parseInt(dates[2]);
                System.out.printf("Trip%2d:\n",trip);
                System.out.printf("\tDeparture Date and Time: %s\n",getDepartureDate());
                System.out.printf("\tArrival Date and Time: %s\n",getTimeTravelledDate(days,hours,minutes));
                trip++;
            }
        }catch(IOException e){

        }
    }

    public static void main(String[]args){
        TimeTravel app = new TimeTravel();
    }

    public String getDepartureDate(){
        Calendar calendar = Calendar.getInstance();
        
        SimpleDateFormat dateTime = new SimpleDateFormat("hh:mm a ");
        SimpleDateFormat dayMonthYear = new SimpleDateFormat("M/dd/yyyy");
        return String.format("%-8son %-8s",dateTime.format(calendar.getTime()),dayMonthYear.format(calendar.getTime()));
    }

    public String getTimeTravelledDate(int days, int hours, int minutes){
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.add(Calendar.DATE,days);
        myCalendar.add(Calendar.HOUR,hours);
        myCalendar.add(Calendar.MINUTE,minutes);

        SimpleDateFormat dateTime = new SimpleDateFormat("hh:mm a ");
        SimpleDateFormat dayMonthYear = new SimpleDateFormat("M/dd/yyyy");
        return String.format("%-8son %-8s",dateTime.format(myCalendar.getTime()),dayMonthYear.format(myCalendar.getTime()));
    }
}
