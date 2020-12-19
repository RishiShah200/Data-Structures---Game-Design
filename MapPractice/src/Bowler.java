public class Bowler implements Comparable<Bowler> {
    private String firstName;
    private String lastName;
    private int score;

    public Bowler(String firstName, String lastName, int score) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int compareTo(Bowler other) {
        if(getLastName().compareTo(other.getLastName()) > 0){
            return 1;
        }
        if(getLastName().compareTo(other.getLastName()) < 0){
            return -1;
        }
        if(getFirstName().compareTo(other.getFirstName()) > 0){
            return 1;
        }
        if(getFirstName().compareTo(other.getFirstName()) < 0){
            return -1;
        }
        return 0;
    }

    public String toString(){
        return firstName + " " + lastName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
