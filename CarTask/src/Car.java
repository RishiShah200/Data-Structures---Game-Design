public class Car implements Comparable<Car> {

    public int id;
    public int mpg;
    public int engineSize;
    public int horsepower;
    public int weight;
    public int acceleration;
    public int origin;
    public int numOfCylinders;



    public Car(int id, int mpg, int engineSize, int horsepower, int weight, int acceleration, int origin, int numOfCylinders) {
        this.id = id;
        this.mpg = mpg;
        this.engineSize = engineSize;
        this.horsepower = horsepower;
        this.weight = weight;
        this.acceleration = acceleration;
        this.origin = origin;
        this.numOfCylinders = numOfCylinders;
    }

    public int getId() {
        return id;
    }

    public int getMpg() {
        return mpg;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public int getWeight() {
        return weight;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public int getOrigin() {
        return origin;
    }

    public int getNumOfCylinders() {
        return numOfCylinders;
    }

    @Override
    public int compareTo(Car o) {
        if(getAcceleration() < o.getAcceleration()){
            return -1;
        }
        else if(o.getAcceleration() < getAcceleration()){
            return 1;
        }
        if(getMpg() < o.getMpg()){
            return -1;
        }
        else if(o.getMpg() < getMpg()){
            return 1;
        }
        if(getHorsepower() < o.getHorsepower()){
            return -1;
        }
        else if(o.getHorsepower() < getHorsepower()){
            return 1;
        }
        if(getEngineSize() < o.getEngineSize()){
            return -1;
        }
        else if(o.getEngineSize() < getEngineSize()){
            return 1;
        }
        if(getWeight() < o.getWeight()){
            return -1;
        }
        else if(o.getWeight() < getWeight()){
            return 1;
        }
        if(getNumOfCylinders() < o.getNumOfCylinders()){
            return -1;
        }
        else if(o.getNumOfCylinders() < getNumOfCylinders()){
            return 1;
        }
        if(getId() < o.getId()){
            return -1;
        }
        else if(o.getId() < getId()){
            return 1;
        }
        return 1;
    }

    public String toString(){
        return String.format("%-6s %-6s %-12s %-6s %-8s %-16s %-8s %-12s",getId(),getMpg(),getEngineSize(),getHorsepower(),
                getWeight(),getAcceleration(),getOrigin(),getNumOfCylinders());
    }



}
