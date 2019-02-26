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
public class Service {
   public String serviceType;
   public String serviceLocation;
   public String serviceDesc;
   public int totaMiles;
   public String volunteerName;
   
   public Service(String serviceType, String serviceLocation, String serviceDesc, int totalMiles)
   {
       this.serviceDesc = serviceDesc;
       this.serviceLocation = serviceLocation;
       this.serviceType = serviceType;
       this.totaMiles = totalMiles;
       
   }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(String serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public int getTotaMiles() {
        return totaMiles;
    }

    public void setTotaMiles(int totaMiles) {
        this.totaMiles = totaMiles;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }
   
}
