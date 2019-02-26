package GroupProject;

import javafx.application.Application;
import javafx.event.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.StackPane;
import javafx.stage.*;

/**
 *
 * @author Owner
 */
public class VolunteerForm {

    public BarkApp parentForm;
    public String name;
    public String address;
    public int DOB;
    public String email;
    public String selfInfo;
    public int phoneNbr;
    public String experince;

    public VolunteerForm() {
        Stage primaryStage = new Stage();
        GridPane primaryPane = new GridPane();
        
        Label lblName = new Label("Name: ");
        Label lblAddress = new Label("Address: ");
        Label lblDOB = new Label("Date Of Birth: ");
        Label lblPhoneNbr = new Label("Phone Number: ");
        Label lblEmail = new Label("Email: ");
        Label lblSelfInfo = new Label("Tell us a little bit about yourself: ");
        Label lblExperince = new Label("Please list any prior experience: ");
        TextField txtName = new TextField();
        TextField txtAddress = new TextField();
        TextField txtDOB = new TextField();
        TextField txtEmail = new TextField();
        TextField txtPhoneNbr = new TextField();
        TextField txtExperince = new TextField();
        TextField txtSelfInfo = new TextField();
        Button btnSubmit = new Button("Submit Application");
        primaryPane.setAlignment(Pos.CENTER);
        primaryPane.add(lblName, 0, 0);
        primaryPane.add(txtName, 1, 0);
        primaryPane.add(lblAddress, 0,1);
        primaryPane.add(txtAddress, 1,1);
        primaryPane.add(lblDOB, 0, 2);
        primaryPane.add(txtDOB,1,2);
        primaryPane.add(lblPhoneNbr,0, 3);
        primaryPane.add(txtPhoneNbr, 1, 3);
        primaryPane.add(lblEmail, 0, 4);
        primaryPane.add(txtEmail, 1, 4);
        primaryPane.add(lblSelfInfo, 0, 5);
        primaryPane.add(txtSelfInfo, 1, 5);
        primaryPane.add(lblExperince, 0, 6);
        primaryPane.add(txtExperince, 1, 6);
        primaryPane.add(btnSubmit, 0, 7);
        Scene primaryScene = new Scene(primaryPane, 300, 300);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("BARK Volunteer Application Form");
        primaryStage.show();
        
        btnSubmit.setOnAction(a -> {
           name = txtName.getText();
           address = txtAddress.getText();
           DOB = Integer.parseInt(txtDOB.getText());
           phoneNbr = Integer.parseInt(txtPhoneNbr.getText());
           email = txtEmail.getText();
           selfInfo = txtSelfInfo.getText();
           experince = txtExperince.getText();
           
           Applicant newApplicant = new Applicant(name, address, DOB, email, selfInfo, phoneNbr, experince); 
        });
        
    }

}
