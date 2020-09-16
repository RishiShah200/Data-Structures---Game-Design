public class FootballPlayer implements Comparable<FootballPlayer> {

    private double avgPickLocation;
    private String name;
    private String pos;
    private String team;
    private int bye;
    private double overallPick;
    private double stdDev;
    private double highRD;
    private double lowRD;
    private int timesDrafted;


    public FootballPlayer(String[] playerInfo){
        avgPickLocation = Double.parseDouble(playerInfo[0]);
        name = playerInfo[1];
        pos = playerInfo[2];
        team = playerInfo[3];
        bye = Integer.parseInt(playerInfo[4]);
        overallPick = Double.parseDouble(playerInfo[5]);
        stdDev = Double.parseDouble(playerInfo[6]);
        highRD = Double.parseDouble(playerInfo[7]);
        lowRD = Double.parseDouble(playerInfo[8]);
        timesDrafted = Integer.parseInt(playerInfo[9]);
    }


    public String toString() {
        return String.format("%-8s %-24s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-16s %-8s"
                ,avgPickLocation,name,pos,team,bye,overallPick,stdDev,highRD,lowRD,timesDrafted, getPickConsistency());
    }

    public int getPickConsistency(){
        String highPick = highRD+"";
        String lowPick = lowRD+"";

        String[]lowPickNums = lowPick.split("\\.");
        String[]highPickNums = highPick.split("\\.");

        int lowRound = Integer.parseInt(lowPickNums[0]);
        int lowNum = Integer.parseInt(lowPickNums[1]);

        if(lowPickNums[1].length() == 1){
            lowNum*=10;
        }

        int highRound = Integer.parseInt(highPickNums[0]);
        int highNum = Integer.parseInt(highPickNums[1]);

        if(highPickNums[1].length() == 1){
            highNum*=10;
        }

        return ((lowRound - 1) * 12 + lowNum) - ((highRound-1) * 12 + highNum);

    }

    public double getADP(){
        return overallPick;
    }


    @Override
    public int compareTo(FootballPlayer other) {
        if(getPickConsistency() > other.getPickConsistency()){
            return 1;
        }
        else if(getPickConsistency() < other.getPickConsistency()){
            return -1;
        }
        if(getADP() < other.getADP()){
            return 1;
        }
        else if(getADP() > other.getADP()){
            return -1;
        }
        return 0;
    }
}
