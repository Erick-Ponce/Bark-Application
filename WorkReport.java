/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupProject;

/**
 *
 * @author Owner
 */
public class WorkReport {
    public String workDone;
    public String volunteerName;
    public int hrsWorked;
    
    public WorkReport(String workDone, String volunteerName, int hrsWorked)
    {
        this.hrsWorked = hrsWorked;
        this.volunteerName = volunteerName;
        this.workDone = workDone;
    }

    public String getWorkDone() {
        return workDone;
    }

    public void setWorkDone(String workDone) {
        this.workDone = workDone;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public int getHrsWorked() {
        return hrsWorked;
    }

    public void setHrsWorked(int hrsWorked) {
        this.hrsWorked = hrsWorked;
    }

}
