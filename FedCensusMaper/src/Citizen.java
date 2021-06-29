public class Citizen implements Comparable<Citizen> {

    String first;
    String last;
    String street;
    int streetNumber;
    String relation;
    String rentOwn;
    double propValue;
    String gender;
    double age;
    String maritalStatus;
    int ageFirstMarriage;
    boolean attendSchool;
    boolean canRead;

    String birthPlace;
    String fathersBirthPlace;
    String mothersBirthPlace;
    String motherTongue;
    int immigrationYear;
    String occupation;
    String industry;
    String transcriperRemarks;

    public Citizen(String first, String last, String street, String streetNumber, String relation, String rentOwn, String propValue, String gender, String age, String maritalStatus, String ageFirstMarriage, String attendSchool, String canRead, String birthPlace, String fathersBirthPlace, String mothersBirthPlace, String motherTongue, String immigrationYear, String occupation, String industry, String transcriperRemarks) {
        this.first = first;
        this.last = last;
        this.street = street;
        try{
            this.streetNumber = Integer.parseInt(streetNumber);
        }catch(NumberFormatException e){
            this.streetNumber = -1;
        }
        this.relation = relation;
        this.rentOwn = rentOwn.substring(0,1);

        if(propValue.charAt(0) == '$')
            propValue = propValue.substring(1);
        try{
            this.propValue = Double.parseDouble(propValue);
        }catch (NumberFormatException e){
            if(propValue.contains("/")){
                String firstPart = propValue.substring(0,propValue.indexOf(" "));
                String numerator = propValue.substring(propValue.indexOf(" "),propValue.indexOf("/"));
                String denominator = propValue.substring(propValue.indexOf("/")+1);
                this.propValue = Double.parseDouble(firstPart) + Double.parseDouble(numerator) / Double.parseDouble(denominator);
            }
        }
        this.gender = gender;
        try {
            this.age = Double.parseDouble(age);
        }catch (NumberFormatException e){
            if(age.charAt(0) == '.' || age.equals("un"))
                this.age = -1;
            else if(age.charAt(1) == ' ' && age.contains("/")){
                String whole = age.substring(0,age.indexOf(" "));
                double dec;
                if(age.substring(age.indexOf(" ")+1,age.indexOf("/")).contains("*"))
                    dec = 0.5;
                else{
                    String numerator = age.substring(age.indexOf(" "),age.indexOf("/"));
                    String denominator = age.substring(age.indexOf("/")+1);
                    dec = Double.parseDouble(numerator) / Double.parseDouble(denominator);
                }
                this.age = Double.parseDouble(whole) + dec;
            }
            else if(age.contains("*")){
                this.age = Double.parseDouble(age.substring(0,age.indexOf("*")));
            }
            else{
                String numerator = age.substring(0,age.indexOf("/"));
                String denominator = age.substring(age.indexOf("/")+1);
                this.age = Double.parseDouble(numerator) / Double.parseDouble(denominator);
            }
        }
        this.maritalStatus = maritalStatus;
        try{
            this.ageFirstMarriage = Integer.parseInt(ageFirstMarriage);
        }catch (NumberFormatException e){
            this.ageFirstMarriage = -1;
        }
        if(attendSchool.equals("Yes"))
            this.attendSchool = true;
        else
            this.attendSchool = false;
        if(canRead.equals("Yes"))
            this.canRead = true;
        else
            this.canRead = false;
        this.birthPlace = birthPlace;
        this.fathersBirthPlace = fathersBirthPlace;
        this.mothersBirthPlace = mothersBirthPlace;
        this.motherTongue = motherTongue;
        try{
            this.immigrationYear = Integer.parseInt(immigrationYear);
        }catch (NumberFormatException e){
            this.immigrationYear = -1;
        }
        this.occupation = occupation.substring(0,1).toUpperCase() + occupation.substring(1).toLowerCase();
        this.industry = industry;
        this.transcriperRemarks = transcriperRemarks;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getStreet() {
        return street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public String getRelation() {
        return relation;
    }

    public String getRentOwn() {
        return rentOwn;
    }

    public double getPropValue() {
        return propValue;
    }

    public String getGender() {
        return gender;
    }

    public double getAge() {
        return age;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public int getAgeFirstMarriage() {
        return ageFirstMarriage;
    }

    public boolean isAttendSchool() {
        return attendSchool;
    }

    public boolean isCanRead() {
        return canRead;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public String getFathersBirthPlace() {
        return fathersBirthPlace;
    }

    public String getMothersBirthPlace() {
        return mothersBirthPlace;
    }

    public String getMotherTongue() {
        return motherTongue;
    }

    public int getImmigrationYear() {
        return immigrationYear;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getIndustry() {
        return industry;
    }

    public String getTranscriperRemarks() {
        return transcriperRemarks;
    }

    @Override
    public int compareTo(Citizen o) {
        if(getFirst().compareTo(o.getFirst()) < 0)
            return -1;
        if(getFirst().compareTo(o.getFirst()) > 0)
            return 1;
        if(getLast().compareTo(o.getLast()) < 0)
            return -1;
        if(getLast().compareTo(o.getLast()) > 0)
            return 1;

        if(getStreet().compareTo(o.getStreet()) < 0)
            return -1;
        if(getStreet().compareTo(o.getStreet()) > 0)
            return 1;

        if(getStreetNumber() < o.getStreetNumber())
            return -1;
        if(getStreetNumber() > o.getStreetNumber())
            return 1;

        if(getRelation().compareTo(o.getRelation()) < 0)
            return -1;
        if(getRelation().compareTo(o.getRelation()) > 0)
            return 1;

        if(getRentOwn().compareTo(o.getRentOwn()) < 0)
            return -1;
        if(getRentOwn().compareTo(o.getRentOwn()) > 0)
            return 1;

        if(getPropValue() < o.getPropValue())
            return -1;
        if(getPropValue() > o.getPropValue())
            return 1;

        if(getGender().compareTo(o.getGender()) < 0)
            return -1;
        if(getGender().compareTo(o.getGender()) > 0)
            return 1;

        if(getAge() < o.getAge())
            return -1;
        if(getAge() > o.getAge())
            return 1;

        if(getMaritalStatus().compareTo(o.getMaritalStatus()) < 0)
            return -1;
        if(getMaritalStatus().compareTo(o.getMaritalStatus()) > 0)
            return 1;

        if(getAgeFirstMarriage() < o.getAgeFirstMarriage())
            return -1;
        if(getAgeFirstMarriage() > o.getAgeFirstMarriage())
            return 1;

        if(isAttendSchool())
            return -1;
        if(!isAttendSchool())
            return 1;
        return 0;
    }

    public String toString(){
        return String.format("%-25sAge: %s", last + ", " + first, age);
    }
}
