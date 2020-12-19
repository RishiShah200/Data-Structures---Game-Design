public class Leaderboard implements Comparable<Leaderboard>{

    private String name;
    private int steps;
    private float time;

    public Leaderboard(String name, int steps, float time) {
        this.name = name;
        this.steps = steps;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    @Override
    public int compareTo(Leaderboard o) {
        if(getTime() < o.getTime())
            return -1;
        else if(getTime() > o.getTime())
            return 1;
        return 0;
    }

    public String toString() {
        return String.format("%-24s %-12d %-48f",getName(), getSteps(), getTime());
    }
}
