package GroupProject;

public class Volunteer extends Applicant {

   
    public int hrsServed;
    public String volunteerSkills;
    public String volunteerStatus;

    public Volunteer(String name, String email, String address, int phoneNbr, int DOB, String experince, String selfInfo, String volunteerSkills, String volunteerStatus, int hrsWorked) {
        super(name, address, DOB, email, selfInfo, phoneNbr, experince);
        this.volunteerStatus = volunteerStatus;
        this.hrsServed = hrsServed;
        this.volunteerSkills = volunteerSkills;
    }

    public int getHrsServed() {
        return hrsServed;
    }

    public void setHrsServed(int hrsServed) {
        this.hrsServed = hrsServed;
    }

    public String getVolunteerSkills() {
        return volunteerSkills;
    }

    public void setVolunteerSkills(String volunteerSkills) {
        this.volunteerSkills = volunteerSkills;
    }

    public String getVolunteerStatus() {
        return volunteerStatus;
    }

    public void setVolunteerStatus(String volunteerStatus) {
        this.volunteerStatus = volunteerStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDOB() {
        return DOB;
    }

    public void setDOB(int DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSelfInfo() {
        return selfInfo;
    }

    public void setSelfInfo(String selfInfo) {
        this.selfInfo = selfInfo;
    }

    public int getPhoneNbr() {
        return phoneNbr;
    }

    public void setPhoneNbr(int phoneNbr) {
        this.phoneNbr = phoneNbr;
    }

    public String getExperince() {
        return experince;
    }

    public void setExperince(String experince) {
        this.experince = experince;
    }

}