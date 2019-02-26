/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupProject;

public class Applicant {

    public String name;
    public String address;
    public int DOB;
    public String email;
    public String selfInfo;
    public int phoneNbr;
    public String experince;
public Applicant(String name, String address, int DOB, String email, String selfInfo, int phoneNbr, String experince)
{
    this.name = name;
    this.address = address;
    this.DOB = DOB;
    this.email = email;
    this.selfInfo = selfInfo;
    this.phoneNbr = phoneNbr;
    this.experince = experince;
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
