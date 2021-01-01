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
        if(getLastName().toLowerCase().compareTo(other.getLastName().toLowerCase()) == 0)
            return getFirstName().toLowerCase().compareTo(other.getFirstName().toLowerCase());
        return getLastName().toLowerCase().compareTo(other.getLastName().toLowerCase());
    }

    public String toString(){
        return getFirstName() + " " + getLastName();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
