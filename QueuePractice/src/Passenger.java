import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Passenger implements Comparable<Passenger> {

    private String firstName;
    private String lastName;
    private String city;
    private String time;

    public Passenger(String firstName, String lastName, String city, String time) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.time = time;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String flightCity() {
        return city;
    }

    public String flightTime() {
        return time;
    }

    public String etdCalc(){
        try{
            Date date = new SimpleDateFormat("hh:mm aa").parse("9:03 AM");
            Date secondDate = new SimpleDateFormat("hh:mm aa").parse(flightTime());
            long millis = (Math.abs(secondDate.getTime() - date.getTime()));
            return String.format("%d:%d", TimeUnit.MILLISECONDS.toHours(millis),(TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))));
        }catch(ParseException e){

        }
        return "ERROR";
    }

    public String toString(){
        return String.format("%-34s %-14s %-12s",getLastName()+", "+getFirstName()+" -",flightCity()+" -",etdCalc());
    }

    @Override
    public int compareTo(Passenger o) {
        return getLastName().toLowerCase().compareTo(o.getLastName().toLowerCase());
    }

}
