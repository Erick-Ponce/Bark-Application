// Names            Kidane Gebrejesus, Grey Steinberg, Erick Ponce, Tyler Johnson, Raymond Brogan
// Date             6/25/2018
// Program Name     BARK Beta
// Purpose          
package GroupProject;

import javafx.scene.control.DatePicker;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.*;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

public class BARKMainApp extends Application {

    String adminUser = "admin";
    String adminPw = "admin";
    String volUser = "vol";
    String volPw = "vol";
    String checkAdminUser, checkAdminPw, checkVolUser, checkVolPw;
    String jdbcConnectionURL = "jdbc:oracle:thin:@localhost:1521:XE";
    String userID = "javauser";
    String userPASS = "javapass";
    String vName = "Name";
    String vEmail = "Email";
    String vStatus = "Pre-App";
    int q;
    String assignName = "";
    String assignSpec = "";

    Connection conn;
    Statement stmt;
    ResultSet rset;

    RadioButton rbAdmin = new RadioButton("Admin");
    RadioButton rbVol = new RadioButton("Volunteer");

    final DatePicker datePicker = new DatePicker();

    ToggleGroup accGroup = new ToggleGroup();

    Label lblDOB = new Label("Date Of Birth: ");
    Label lblUsername = new Label("Username: ");
    Label lblPassword = new Label("Password: ");
    Label lblType = new Label("Account Type: ");
    Label lblVolStatus = new Label("Status: ");
    Label lblVolWelcomeName = new Label("Welcome, 'name'!");
    Label lblVolWelcomeEmail = new Label("Email");
    Label lblFillOut = new Label("Fill out the information below");
    Label lblServiceType = new Label("Service Type: ");
    Label lblServiceLocation = new Label("Service Location: ");
    Label lblServiceDescription = new Label("Service Description: ");
    Label lblServiceMileage = new Label("Total Mileage: ");
    Label lblCheck = new Label("");
    Label lblAssignVol = new Label("Volunteers");
    Label lblAssignSpec = new Label("Specialization");
    Label lblServiceName = new Label("Service Name: ");
    Label lblServiceDesc = new Label("Service Description: ");
    Label lblTotalMiles = new Label("Total Miles: ");
    Label lblEventName = new Label("Event Name: ");
    Label lblEventLocation = new Label("Event Location: ");
    Label lblEventInfo = new Label("Event Info: ");
    Label lblEventTime = new Label("Event Date: ");
    Label lblName = new Label("Name: ");
    Label lblAddress = new Label("Address: ");
    Label lblPhoneNbr = new Label("Phone Number: ");
    Label lblEmail = new Label("Email: ");
    Label lblSelfInfo = new Label("Tell us a little bit about yourself: ");
    Label lblExperince = new Label("Prior experience: ");
    Label volTotHours = new Label("Hours Logged: ");

    PasswordField txtPassword = new PasswordField();

    TextField txtEventTime = new TextField();
    TextField txtEventInfo = new TextField();
    TextField txtEventLocation = new TextField();
    TextField txtEventName = new TextField();
    TextField txtTotalMiles = new TextField();
    TextField txtServiceDesc = new TextField();
    TextField txtServiceName = new TextField();
    TextField txtServiceLocation = new TextField();
    TextField txtDOB = new TextField();
    TextField txtUsername = new TextField();
    TextField txtServiceType = new TextField();
    TextField txtServiceDescription = new TextField();
    TextField txtServiceMileage = new TextField();
    TextField txtSpecializationAdd = new TextField();
    TextField txtName = new TextField();
    TextField txtAddress = new TextField();
    TextField txtEmail = new TextField();
    TextField txtPhoneNbr = new TextField();
    TextField txtExperience = new TextField();
    TextField txtSelfInfo = new TextField();
    TextArea txtSocialFeed = new TextArea();

    Button btnLogin = new Button("Log In");
    Button btnLogout = new Button("Log Out");
    Button btnApprovalPage = new Button("Approve Volunteers");
    Button btnApprove = new Button("Approve");
    Button btnAssignSpecialization = new Button("Assign Specializations");
    Button btnCreateBarkEvent = new Button("Create BARK Events");
    Button btnViewRpt = new Button("View Reports");
    Button btnClockIn = new Button("Clock In");
    Button btnClockOut = new Button("Clock Out");
    Button btnVolSpec = new Button("Specialization(s)");
    Button btnAdminBackHome = new Button("Admin Home");
    Button btnVolBackHome = new Button("Volunteer Home");
    Button btnAppSubmit = new Button("Submit");
    Button btnBack = new Button("Back");
    Button btnAppPage = new Button("New? Apply Here!");
    Button btnDocServ = new Button("Document Service");
    Button btnServiceEnter = new Button("Enter");
    Button btnEditSpecialization = new Button("Edit Specialization List");
    Button btnSpecializationAdd = new Button("Add Specialization");
    Button btnSpecializationDelete = new Button("Delete Specialization");
    Button btnAssign = new Button("Assign");
    Button btnEvents = new Button("BARK Events");
    Button btnCreateEvent = new Button("Create Event");
    Button btnVolForEvent = new Button("Volunteer for Event");
    Button btnSaveSelected = new Button("Save Selected");
    Button viewVolInfo = new Button("Account Info");
    Button viewHistory = new Button("Clock In/Out History");
    Button btnViewApplicant = new Button("View Applicant");

    Alert loginError = new Alert(AlertType.ERROR, "Username or password entered is incorrect. Try again.");
    Alert radioError = new Alert(AlertType.ERROR, "Please pick your account type before attempting to log in.");
    Alert loginCorrect = new Alert(AlertType.INFORMATION, "Login Successful. Taking you to your home page.");
    Alert logout = new Alert(AlertType.INFORMATION, "You are now logged out. Going back to the login page.");
    Alert clockIn = new Alert(AlertType.INFORMATION, "");
    Alert clockOut = new Alert(AlertType.INFORMATION, "");
    Alert volHistory = new Alert(AlertType.INFORMATION, "");
    Alert notApproved = new Alert(AlertType.ERROR, "Your application has not been approved yet");
    Alert specSaved = new Alert(AlertType.INFORMATION, "Specilization saved.");

    Image pic = new Image("https://i.imgur.com/qttHtVz.jpg");
    ImageView imageView = new ImageView(pic);

    ListView<String> approvalTable = new ListView<>();    //Edit Specialization Table
    ObservableList<String> approvalData = FXCollections.observableArrayList();

    ListView<String> specializationTable = new ListView<>();    //Edit Specialization Table
    ObservableList<String> specializationData = FXCollections.observableArrayList();
    ListView<String> assignVolTable = new ListView<>();   //Volunteers Table for assigning specialization
    ObservableList<String> assignVolData = FXCollections.observableArrayList(); //need to put volunteer names in this 
    ListView<String> assignSpecTable = new ListView<>();   //Specializations to be assigned, uses specializationData
    static ListView<String> eventTable = new ListView<>(FXCollections.observableArrayList());
    static ObservableList<Event> events = FXCollections.observableArrayList();
    static ObservableList<String> eventData = FXCollections.observableArrayList();
    static ListView<Volunteer> volunteerList = new ListView<>();
    static ObservableList<Volunteer> volunteerData = FXCollections.observableArrayList();
    static ListView<Applicant> applicantList = new ListView<>();
    static ObservableList<Applicant> applicantData = FXCollections.observableArrayList();
    static ListView<String> LoginHistoryLV = new ListView<>();
    static ObservableList<String> LoginHistoryOL = FXCollections.observableArrayList();

    String[] LoginArray;
    static int LoginHistoryIndex = 0;
    String timeClockIn;
    String timeClockOut;
    long shiftLength;
    long totalTimeWorked;

    GridPane mainGrid = new GridPane();

    @Override
    public void start(Stage primaryStage) throws SQLException {

        loadSpecialization(); //calls method to load specialization from DB
        mainGrid.setAlignment(Pos.CENTER);

        loginPage();
        String sql = "Select * From javauser.Applicant";
        sendDBCommand(sql);
        ResultSet rst;
        rst = stmt.executeQuery(sql);

        while (rst.next()) {
            Applicant applicant = new Applicant();
            applicantList.getItems().add(applicant);

        }
        sql = "Select * From javauser.Volunteer";
        sendDBCommand(sql);
        rst = stmt.executeQuery(sql);

        while (rst.next()) {
            Volunteer volunteer = new Volunteer();
            volunteerList.getItems().add(volunteer);

        }

        btnLogin.setOnAction(e -> {
            try {
                if (accGroup.getSelectedToggle() == rbAdmin) {
                    checkAdminUser = txtUsername.getText();
                    checkAdminPw = txtPassword.getText();
                    if (checkAdminUser.equals(adminUser) && checkAdminPw.equals(adminPw)) {
                        loginCorrect.showAndWait();
                        adminPage();
                        clearInput();
                    } else {
                        loginError.showAndWait();
                        clearInput();
                    }
                } else if (accGroup.getSelectedToggle() == rbVol) {
                    checkVolUser = txtUsername.getText();
                    checkVolPw = txtPassword.getText();
                    String sqlQuery0 = "select username, password from javauser.loginInformation where username = '" + checkVolUser + "' and password = '" + checkVolPw + "'";
                    sendDBCommand(sqlQuery0);
                    int count = 0;
                    while (rset.next()) //looks through result and if it finds a match count will equal to 1.
                    {
                        count++;
                    }
                    if ((checkVolUser.equals(volUser) && checkVolPw.equals(volPw)) || (count == 1)) {  //default vol login OR actual vol login
                        loginCorrect.showAndWait();

                        lblVolWelcomeEmail.setText("");
                        findVolEmail();

                        lblVolWelcomeName.setText("");
                        findVolName();
                        findAppName();

                        vStatus = "Pre-App";
                        lblVolStatus.setText("Status: " + vStatus);
                        findVolStatus();

                        // new display data after refreshes above
                        lblVolWelcomeName.setText("Welcome, " + vName + "!");   //sets name, email, and status
                        lblVolWelcomeEmail.setText("Email: " + vEmail);
                        lblVolStatus.setText("Status: " + vStatus);
                        volPage(); // display the page content

                        if (checkVolUser.equals(volUser) && checkVolPw.equals(volPw)) //this if statement can be deleted, its just used for default vol login
                        {
                            lblVolWelcomeName.setText("Welcome, None!");
                            lblVolWelcomeEmail.setText("Email: None");
                            lblVolStatus.setText("Status: None");
                        }
                        clearInput();
                    } else {
                        loginError.showAndWait();
                        clearInput();
                    }
                } else {
                    radioError.showAndWait();
                }
            } catch (SQLException s) {
                loginError.showAndWait();
            }
        });

        btnLogout.setOnAction(e -> {
            logout.showAndWait();
            loginPage();
        });

        btnAdminBackHome.setOnAction(e -> {
            adminPage();
        });

        btnVolBackHome.setOnAction(e -> {
            volPage();
        });

        btnAppPage.setOnAction(e -> {
            volAppPage();
        });

        btnDocServ.setOnAction(e -> {
            volServicePage();
        });

        btnCreateBarkEvent.setOnAction(a -> {
            createEventPage();
            //CreateEvent ce = new CreateEvent();
        });

        btnViewRpt.setOnAction(e -> {
            viewRpt();
        });

        btnEditSpecialization.setOnAction(e -> {  // specilization page in admin
            adminSpecPage();
        });

        btnVolSpec.setOnAction(e -> {
            volSpecPage();
        });

        btnSpecializationAdd.setOnAction(a -> { //add the selected to the table and vol combobox
            specializationData.add(txtSpecializationAdd.getText());
            specializationTable.setItems(specializationData);
            txtSpecializationAdd.clear();
        });

        btnSpecializationDelete.setOnAction(d -> { // deleting selected  from category list
            String selectedCat = specializationTable.getSelectionModel().getSelectedItem();
            specializationData.remove(selectedCat);
            String sqlQuery = "delete from javauser.specializationlist where specialization = '" + selectedCat + "'";
            sendDBCommand(sqlQuery);
            specializationTable.setItems(specializationData);
            txtSpecializationAdd.clear();
        });

        assignVolTable.setOnMouseClicked(e -> {
            assignName = assignVolTable.getSelectionModel().getSelectedItem();
            System.out.println(assignName);
        });
        
        assignSpecTable.setOnMouseClicked(e -> {
            assignSpec = assignSpecTable.getSelectionModel().getSelectedItem();
            System.out.println(assignSpec);
        });
        
        btnAssign.setOnAction(a -> { //admin side, assign specialization
            try{
            String[] ss = assignName.split(" : ");  //splits the selected name from email
            String sName = ss[0]; //selected name
            String sEmail = ss[1]; //selected email
            String previousSkills = "";
            String sqlQuery0 = "select volunteerskills from javauser.volunteer where volunteeremail = '" + sEmail + "'";
            sendDBCommand(sqlQuery0);
            while (rset.next())
            {
                previousSkills = rset.getString("volunteerskills");
            }
            // add spec to volunteer
            if (previousSkills.equalsIgnoreCase("Unspecified")) //if unspecified add in new skill
            {
                String sqlQuery1 = "update javauser.volunteer set volunteerskills = '"
                        + assignSpec + "' where volunteeremail = '" + sEmail + "'";
                sendDBCommand(sqlQuery1);
                specSaved.showAndWait();
            }
            else    //concat previous skill with the selected skill(specilization)
            {
                assignSpec = assignSpec + ", " + previousSkills;
                String sqlQuery2 = "update javauser.volunteer set volunteerskills = '"
                        + assignSpec + "' where volunteeremail = '" + sEmail + "'";
                sendDBCommand(sqlQuery2);
                specSaved.showAndWait();
            }
            }catch(SQLException ex)
            {
                System.out.println(ex.toString());
            }
        });

        btnAssignSpecialization.setOnAction(b -> {    //assign specialization page (curently not really assigning anything, instead its just displaying as if it was assigning)
            assignSpecPage();
        });

        btnEvents.setOnAction(e -> {
            volEventPage();

        });

        btnCreateEvent.setOnAction(a -> {

            datePicker.setOnAction(new EventHandler() {
                public void handle(Event t) {
                    LocalDate date = datePicker.getValue();
                    System.err.println("Selected date: " + date);
                }

                @Override
                public void handle(javafx.event.Event event) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            String eventName = txtEventName.getText();
            String eventLocation = txtEventLocation.getText();
            String eventInfo = txtEventInfo.getText();
            String eventTime = String.valueOf(datePicker.getValue());
            Event newEvent = new Event(eventName, eventLocation, eventTime, eventInfo);
            events.add(new Event(eventName, eventLocation, eventTime, eventInfo));
            String sqlQuery1 = "";
            Event tempRef = events.get(events.size() - 1);
            sqlQuery1 += "insert into javauser.bark_events (eventid,eventname,eventlocation,eventtime,eventinfo) values (";
            sqlQuery1 += "'" + tempRef.getEventID() + "',";
            sqlQuery1 += "'" + tempRef.getEventName() + "',";
            sqlQuery1 += "'" + tempRef.getEventLocation() + "',";
            sqlQuery1 += "' " + tempRef.getEventTime() + "',";
            sqlQuery1 += "'" + tempRef.getEventInfo() + "')";
            sendDBCommand(sqlQuery1);
            Alert docSubmit = new Alert(AlertType.INFORMATION, "Your BARK Event has been saved in the database. Thanks!");
            docSubmit.showAndWait();
            clearInput();

        });

        btnApprovalPage.setOnAction(e -> {
            approvalPage();
        });

        btnViewApplicant.setOnAction(e -> {
            viewApplicantWindow();
        });

        Scene scene = new Scene(mainGrid, 500, 500);

        primaryStage.setTitle("B.A.R.K. Beta 0.6");
        primaryStage.setScene(scene);
        primaryStage.show();
        mainGrid.setVgap(10);
        mainGrid.setHgap(10);
        imageView.setFitHeight(150);
        imageView.setFitWidth(160);

    } // end of main

 public void viewRpt() {
        try {
            mainGrid.getChildren().clear();
            mainGrid.add(LoginHistoryLV, 0, 0);
            mainGrid.add(btnBack, 0,1);
            for (int i = 0; i < LoginArray.length; i++) {
                LoginHistoryLV.getItems().add(LoginArray[LoginHistoryIndex]);
            }
        } catch (NullPointerException npEx) {
            System.out.println(npEx.toString());
        }
        btnBack.setOnAction(e -> {
            adminPage();
        });
    }

    public void findVolEmail() {
        try {
            //gets the email from the logged in user
            String sqlQuery = "select username, password, volunteeremail from javauser.loginInformation where username = '" + checkVolUser + "' and password = '" + checkVolPw + "'";
            sendDBCommand(sqlQuery);
            while (rset.next()) {
                vEmail = rset.getString("volunteeremail");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    public void findAppName() {
        try {
            //gets the name of the applicant when they login, if we use volunteer name it will show default welcome
            String sqlQuery2 = "select applicantname from javauser.applicant where applicantemail = '" + vEmail + "'";
            sendDBCommand(sqlQuery2);
            while (rset.next()) {
                vName = rset.getString("applicantname");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void findVolName() {
        try {
            //gets the name of the applicant when they login, if we use volunteer name it will show default welcome
            String sqlQuery4 = "select volunteername from javauser.volunteer where volunteeremail = '" + vEmail + "'";
            sendDBCommand(sqlQuery4);
            while (rset.next()) {
                vName = rset.getString("volunteername");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void findVolStatus() {
        try {
            //gets the name of the volunteer 
            String sqlQuery3 = "select volunteerstatus from javauser.volunteer where volunteeremail = '" + vEmail + "'";
            sendDBCommand(sqlQuery3);
            while (rset.next()) {
                vStatus = rset.getString("volunteerstatus");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void clearInput() {
        txtUsername.clear();
        txtPassword.clear();
        txtEventName.clear();
        txtEventLocation.clear();
        txtEventInfo.clear();
        txtEventTime.clear();
        txtAddress.clear();
        txtDOB.clear();
        txtEmail.clear();
        txtEventName.clear();
        txtExperience.clear();
        txtName.clear();
        txtPhoneNbr.clear();
        txtSelfInfo.clear();
        txtServiceDesc.clear();
        txtServiceDescription.clear();
        txtServiceLocation.clear();
        txtServiceMileage.clear();
        txtServiceName.clear();
        txtServiceType.clear();
        txtSpecializationAdd.clear();
        txtTotalMiles.clear();
    }

    public void loginPage() { // login page
        mainGrid.getChildren().clear();
        mainGrid.add(rbVol, 2, 0);
        mainGrid.add(rbAdmin, 3, 0);
        mainGrid.add(imageView, 4, 0, 1, 4);
        mainGrid.add(lblType, 1, 0);
        mainGrid.add(lblUsername, 1, 1);
        mainGrid.add(lblPassword, 1, 2);
        mainGrid.add(txtUsername, 2, 1, 2, 1);
        mainGrid.add(txtPassword, 2, 2, 2, 1);
        mainGrid.add(btnLogin, 2, 3);
        mainGrid.add(btnAppPage, 3, 3);
        rbAdmin.setToggleGroup(accGroup);
        rbVol.setToggleGroup(accGroup);
    }

    public void adminPage() { // admin home page
        mainGrid.getChildren().clear();
        mainGrid.add(btnApprovalPage, 0, 0);
        mainGrid.add(btnEditSpecialization, 0, 1);
        mainGrid.add(btnAssignSpecialization, 0, 2);
        mainGrid.add(btnCreateBarkEvent, 0, 3);
        mainGrid.add(btnViewRpt, 0, 4);
        mainGrid.add(btnLogout, 0, 5);

    }

    public void createEventPage() { // admin create event page
        mainGrid.add(datePicker, 0, 8);
        mainGrid.getChildren().clear();
        mainGrid.add(lblEventName, 0, 0);
        mainGrid.add(txtEventName, 1, 0);
        mainGrid.add(lblEventLocation, 0, 1);
        mainGrid.add(txtEventLocation, 1, 1);
        mainGrid.add(lblEventInfo, 0, 2);
        mainGrid.add(txtEventInfo, 1, 2);
        mainGrid.add(lblEventTime, 0, 3);
        mainGrid.add(datePicker, 1, 3);
        mainGrid.add(btnCreateEvent, 1, 4);
        mainGrid.add(btnBack, 0, 4);

        btnBack.setOnAction(e -> {
            adminPage();
        });

    }

    public void approvalPage() {    //approval page in admin
        mainGrid.getChildren().clear();
        approvalData.clear();   //clears OL to avoid duplicate data when going back to the page
        mainGrid.add(lblAssignVol, 0, 0);
        mainGrid.add(approvalTable, 0, 1, 2, 1);
        mainGrid.add(btnApprove, 3, 2);
        mainGrid.add(btnBack, 0, 2);
        mainGrid.add(btnViewApplicant, 2, 2);
        refreshApprovalTable();

        btnApprove.setOnAction(e -> {
            //Approval starts here
            try {
                String selected = approvalTable.getSelectionModel().getSelectedItem();
                String[] ss = selected.split(" : ");
                String sName = ss[0];
                String sEmail = ss[1];
                String sqlQuery0 = "select applicantID, "
                        + "applicantname, "
                        + "applicantaddress, "
                        + "applicantDOB, "
                        + "applicantemail, "
                        + "applicantphonenumber, "
                        + "applicantselfinformation, "
                        + "applicantexperience from javauser.applicant where applicantemail = '" + sEmail + "'";
                sendDBCommand(sqlQuery0);    //gets the data from appplicant
                String volID = "";
                String volName = "";
                String volAddress = "";
                String volDOB = "";
                String volEmail = "";
                String volPhone = "";
                String volExperience = "";
                while (rset.next()) {
                    volID = rset.getString("applicantID");
                    volName = rset.getString("applicantname");
                    volAddress = rset.getString("applicantaddress");
                    volDOB = rset.getString("applicantDOB");
                    volEmail = rset.getString("applicantemail");
                    volPhone = rset.getString("applicantphonenumber");
                    volExperience = rset.getString("applicantexperience");
                }
                //System.out.println(volName + " " + volAddress + " " + volEmail + " " + volPhone);
                String sqlQuery1 = "insert into javauser.volunteer (volunteerID, "
                        + "volunteername, "
                        + "volunteeraddress, "
                        + "volunteerDOB, "
                        + "VolunteerExperience, "
                        + "volunteeremail, "
                        + "volunteerphonenumber, "
                        + "volunteerskills, "
                        + "volunteerstatus, "
                        + "hoursserved) values (";
                sqlQuery1 += "'" + volID + "',";
                sqlQuery1 += "'" + volName + "',";
                sqlQuery1 += "'" + volAddress + "',";
                sqlQuery1 += "'" + volDOB + "',";
                sqlQuery1 += "'Experience',";
                sqlQuery1 += "'" + volEmail + "',";
                sqlQuery1 += "'" + volPhone + "',";
                sqlQuery1 += "'Unspecified',";
                sqlQuery1 += "'Training',"; // dont change this one
                sqlQuery1 += "'0')";
                sendDBCommand(sqlQuery1);  //stores applicant data into volunteer

                String sqlQuery2 = "delete from javauser.applicant where applicantemail = '" + sEmail + "'";   //delete applicant data after its been stored in volunteer
                sendDBCommand(sqlQuery2);   //delets the old applicant data

                Alert approve = new Alert(AlertType.INFORMATION, sName + " has been approved as a volunteer.");
                approve.showAndWait();
                refreshApprovalTable();
            } catch (SQLException se) {
                System.out.println(se.toString());
            }
        });

        btnBack.setOnAction(e -> {
            adminPage();
        });
    }

    public void viewApplicantWindow() {
        try {
            String selected = approvalTable.getSelectionModel().getSelectedItem();
            String[] ss = selected.split(" : ");
            String sName = ss[0];
            String sEmail = ss[1];
            String sqlQuery = "select * from javauser.applicant where applicantemail = '" + sEmail + "'";
            sendDBCommand(sqlQuery);
            TextArea taApplicant = new TextArea();
            taApplicant.setMaxSize(300, 400);
            taApplicant.setEditable(false);
            while (rset.next()) {
                taApplicant.appendText("Volunteer Applicantion:\n==========\n\n"
                        + "Name: " + rset.getString("applicantname")
                        + "\nAddress: " + rset.getString("applicantaddress")
                        + "\nDOB: " + rset.getString("applicantdob")
                        + "\nEmail: " + rset.getString("applicantemail")
                        + "\nPhone Number: " + rset.getString("applicantphonenumber")
                        + "\nSelf Information: " + rset.getString("applicantselfinformation")
                        + "\nExperience: " + rset.getString("applicantexperience"));
            }
            GridPane applicantGrid = new GridPane();
            applicantGrid.setAlignment(Pos.TOP_CENTER);
            applicantGrid.add(taApplicant, 0, 0);
            Scene applicantScene = new Scene(applicantGrid);
            Stage applicantStage = new Stage();
            applicantStage.setScene(applicantScene);
            applicantStage.setTitle("Volunteer Application");
            applicantStage.show();

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }

    public void assignSpecPage() { // admin assign spec page
        mainGrid.getChildren().clear();
        assignVolTable.setMaxSize(250, 200);
        assignSpecTable.setMaxSize(250, 200);
        mainGrid.add(lblAssignVol, 0, 0);
        mainGrid.add(lblAssignSpec, 2, 0);
        mainGrid.add(assignVolTable, 0, 1, 2, 1);
        mainGrid.add(assignSpecTable, 2, 1, 2, 1);
        mainGrid.add(btnAssign, 2, 2);
        mainGrid.add(btnBack, 0, 2);
        refreshAssignVolTable();
        assignSpecTable.setItems(specializationData);

        btnBack.setOnAction(e -> {
            adminPage();
        });
    }

    public void volPage() { // volunteer home page
        mainGrid.getChildren().clear();
        volTotHours.setText("Hours Logged: " + totalTimeWorked);
        mainGrid.add(lblVolStatus, 1, 0);
        mainGrid.add(volTotHours, 0, 2);
        mainGrid.add(btnLogout, 1, 6);
        mainGrid.add(btnDocServ, 1, 4);
        mainGrid.add(btnVolSpec, 0, 6);
        mainGrid.add(btnClockIn, 0, 3);
        mainGrid.add(btnClockOut, 1, 3);
        mainGrid.add(btnEvents, 0, 4);
        mainGrid.add(viewHistory, 0, 5);
        mainGrid.add(viewVolInfo, 1, 5);
        mainGrid.add(txtSocialFeed, 0, 7, 2, 6);
        mainGrid.add(lblVolWelcomeName, 0, 0);
        mainGrid.add(lblVolWelcomeEmail, 0, 1);
        mainGrid.add(lblCheck, 1, 7);
        btnClockIn.setDisable(false);
        btnClockOut.setDisable(false);
        btnEvents.setDisable(false);
        btnVolSpec.setDisable(false);
        btnDocServ.setDisable(false);
        viewHistory.setDisable(false);

        if (vStatus == "Pre-App") {
            btnClockIn.setDisable(true);
            btnClockOut.setDisable(true);
            btnEvents.setDisable(true);
            btnVolSpec.setDisable(true);
            btnDocServ.setDisable(true);
            viewHistory.setDisable(true);
        }

        viewVolInfo.setOnAction(e -> {
/////////////////////////display account info
        });

        btnClockIn.setOnAction(e -> { // clock in, start a variable
            clockIn.setContentText("Clocked in successfully at " + getTime());
            timeClockIn = getTime();
            txtSocialFeed.appendText(vName + " clocked in at " + getTime() + "\n");
            clockIn.showAndWait();
        });

        btnClockOut.setOnAction(e -> { // add hours to db, reflect total logged in GUI
            clockOut.setContentText("Clocked out successfully at " + getTime());
            timeClockOut = getTime();
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");

            try {
                shiftLength = (format.parse(timeClockOut).getTime()) - (format.parse(timeClockIn).getTime());
                totalTimeWorked += shiftLength;
                String sqlQuery = "";
                sqlQuery += "update javauser.volunteer set hoursserved = '"
                        + totalTimeWorked + "' where volunteeremail = '" + vEmail + "'";
                sendDBCommand(sqlQuery);
            } catch (ParseException ex) {
                System.out.println(ex.toString());
            }
            txtSocialFeed.appendText(vName + " clocked out at " + getTime() + "\n");
            clockOut.showAndWait();

        });

        viewHistory.setOnAction(z -> {
            try {
                String history = ("Clocked in: " + timeClockIn + " Clocked out: " + timeClockOut + " Shift Length: " + shiftLength + " Total Hours: " + totalTimeWorked);
                volHistory.setContentText(history);
                LoginArray[LoginHistoryIndex] = history;
                LoginHistoryOL.add(history);

            } catch (NullPointerException npEx) {
                System.out.println(npEx.toString());
            }
        });

        //txtSocialFeed.appendText()
    }

    public void volAppPage() {  // Volunteer Application Page
        mainGrid.getChildren().clear();
        mainGrid.add(lblName, 0, 0);
        mainGrid.add(txtName, 1, 0);
        mainGrid.add(lblAddress, 0, 1);
        mainGrid.add(txtAddress, 1, 1);
        mainGrid.add(lblDOB, 0, 2);
        mainGrid.add(txtDOB, 1, 2);
        mainGrid.add(lblPhoneNbr, 0, 3);
        mainGrid.add(txtPhoneNbr, 1, 3);
        mainGrid.add(lblEmail, 0, 4);
        mainGrid.add(txtEmail, 1, 4);
        mainGrid.add(lblSelfInfo, 0, 5);
        mainGrid.add(txtSelfInfo, 1, 5);
        mainGrid.add(lblExperince, 0, 6);
        mainGrid.add(txtExperience, 1, 6);
        mainGrid.add(btnAppSubmit, 1, 7);
        mainGrid.add(btnBack, 0, 7);

        btnBack.setOnAction(e -> {
            loginPage();
        });

        btnAppSubmit.setOnAction(a -> { // create username and pass as well
            try {
                applicantData.add(new Applicant(txtName.getText(), txtAddress.getText(), String.valueOf(txtDOB.getText()),
                        txtEmail.getText(), txtSelfInfo.getText(), txtPhoneNbr.getText(), txtExperience.getText()));
                String name = txtName.getText();
                String sqlQuery = "";
                Applicant tempRef = applicantData.get(applicantData.size() - 1);

                sqlQuery += "insert into javauser.Applicant (applicantid,applicantname,applicantaddress,applicantdob,applicantemail,applicantphonenumber,applicantselfinformation,applicantexperience) values (";
                sqlQuery += "'" + ((Applicant) tempRef).getApplicantID() + "',";
                sqlQuery += "'" + tempRef.getName() + "',";
                sqlQuery += "'" + tempRef.getAddress() + "',";
                sqlQuery += "' " + tempRef.getDOB() + "',";
                sqlQuery += "'" + tempRef.getEmail() + "',";
                sqlQuery += "' " + tempRef.getPhoneNbr() + "',";
                sqlQuery += "'" + tempRef.getSelfInfo() + "',";
                sqlQuery += "'" + tempRef.getExperince() + "')";
                sendDBCommand(sqlQuery);

                Alert appSubmit = new Alert(AlertType.INFORMATION, "Application submitted. Your username and password are... \n\n" + getUserPass(name, tempRef.getEmail())
                        + " \n\nPlease be patient while waiting for approval.");
                appSubmit.showAndWait();
                clearInput();
                loginPage();

            } catch (IndexOutOfBoundsException ex) {
                System.out.println(ex.toString());
            }

        });

    }

    public String getUserPass(String name, String email) {
        String[] fullName = name.split(" ");
        String firstName = fullName[0];
        String lastName = fullName[1];
        while (lastName.length() < 3) {
            lastName += "x";
        }
        String userName = firstName.substring(0, 1) + lastName.substring(0, 3);  //4 letter username
        Random ran = new Random();
        int iPassword = ran.nextInt(8999) + 1000;   //4 number password (from 1000 to 10000)
        String password = String.valueOf(iPassword);  //turned password to string because we save it as varchar in the DB
        try {
            String sqlQuery = "";
            sqlQuery += "insert into javauser.LoginInformation (username, password, isadmin, volunteeremail) values (";
            sqlQuery += "'" + userName + "',";
            sqlQuery += "'" + password + "',";
            sqlQuery += "'N',";
            sqlQuery += "'" + email + "')";
            sendDBCommand(sqlQuery);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.toString());
        }

        return "Username: " + userName + "\nPassword: " + password;

    }

    public void refreshApprovalTable() {
        try {
            String sqlQuery = "select * from javauser.applicant";
            sendDBCommand(sqlQuery);
            approvalData.clear();
            while (rset.next()) {
                String s = rset.getString("applicantname");
                String t = rset.getString("applicantemail");
                s = s + " : " + t;
                approvalData.add(s);    //adds the names from the DB to the OL
            }
            approvalTable.setItems(approvalData);   //sets the OL to the DB
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    public void refreshAssignVolTable() {
        try {
            String sqlQuery = "select * from javauser.volunteer";
            sendDBCommand(sqlQuery);
            assignVolData.clear();
            while (rset.next()) {
                String s = rset.getString("volunteername");
                String t = rset.getString("volunteeremail");
                s = s + " : " + t;
                assignVolData.add(s);    //adds the names from the DB to the OL
            }
            assignVolTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            assignVolTable.setItems(assignVolData);   //sets the OL to the DB
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void refreshEventTable() {
        try {
            String sqlQuery = "select * from javauser.BARK_Events";
            sendDBCommand(sqlQuery);
            while (rset.next()) {
                String s = rset.getString("eventname");
                String t = rset.getString("eventtime");
                s = s + " : " + t;
                eventData.add(s);    //adds the names from the DB to the OL
            }
            eventTable.setItems(eventData);   //sets the OL to the DB
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void volEventPage() { // volunteer event sign up page
        mainGrid.getChildren().clear();
        mainGrid.add(btnBack, 0, 1);
        mainGrid.add(btnVolForEvent, 1, 1);
        mainGrid.add(eventTable, 0, 0, 2, 1);
        refreshEventTable();
        //eventTable.setItems(events);

        btnVolForEvent.setOnAction(e -> {

        });

        btnBack.setOnAction(e -> {
            volPage();
        });
    }

    public void volServicePage() { // Volunteer document service
        mainGrid.getChildren().clear();
        mainGrid.add(lblFillOut, 0, 0);
        mainGrid.add(lblServiceType, 0, 2);
        mainGrid.add(lblServiceLocation, 0, 3);
        mainGrid.add(lblServiceDescription, 0, 4);
        mainGrid.add(lblServiceMileage, 0, 5);
        mainGrid.add(txtServiceType, 1, 2);
        mainGrid.add(txtServiceLocation, 1, 3);
        mainGrid.add(txtServiceDescription, 1, 4);
        mainGrid.add(txtServiceMileage, 1, 5);
        mainGrid.add(btnServiceEnter, 1, 9);
        mainGrid.add(btnBack, 0, 9);

        btnBack.setOnAction(e -> {
            volPage();
        });

        btnServiceEnter.setOnAction(e -> {
            //enter
            try {
                String serviceType = txtServiceType.getText();
                String serviceLocation = txtServiceLocation.getText();
                String serviceDesc = txtServiceDesc.getText();
                String totalMiles = txtServiceMileage.getText(); //changed to string so they can enter for example 5 miles 
                Service newService = new Service(serviceType, serviceLocation, serviceDesc, totalMiles);

                String sqlQuery = "";
                sqlQuery += "insert into javauser.service (servicetype,servicelocation,servicedescription,totalmileage,volunteerid) values (";
                sqlQuery += "'" + txtServiceType.getText() + "',";
                sqlQuery += "'" + txtServiceLocation.getText() + "',";
                sqlQuery += "'" + txtServiceDescription.getText() + "',";
                sqlQuery += "'" + txtServiceMileage.getText() + "',";
                sqlQuery += "'" + serviceTable() + "')";
                sendDBCommand(sqlQuery);
                txtSocialFeed.appendText(vName + " submitted a new report at " + getTime() + "\n"); // sets the social feed to show someone documenting a service
                Alert docSubmit = new Alert(AlertType.INFORMATION, "Your service has been saved in the database. Thanks!");
                docSubmit.showAndWait();
                clearInput();

            } catch (IndexOutOfBoundsException ex) {
                System.out.println(ex.toString());
            }

        });

    }

    public void adminSpecPage() { // admin create/delete spec
        mainGrid.getChildren().clear();
        mainGrid.add(specializationTable, 0, 0, 2, 1);
        mainGrid.add(txtSpecializationAdd, 0, 1, 2, 1);
        mainGrid.add(btnSpecializationAdd, 0, 3);
        mainGrid.add(btnSpecializationDelete, 1, 3);
        mainGrid.add(btnBack, 0, 5);
        specializationTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        specializationTable.setItems(specializationData);

        btnBack.setOnAction(e -> {
            adminPage();
        });
    }

    public int serviceTable() {
        String sqlQuery1 = "select * from javauser.volunteer";
        sendDBCommand(sqlQuery1);

        try {
            while (rset.next()) {
                q = rset.getInt("volunteerid");
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return q;
    }

    public void volSpecPage() { // volunteer create spec
        mainGrid.getChildren().clear();
        mainGrid.add(specializationTable, 0, 0, 2, 1);
        mainGrid.add(txtSpecializationAdd, 0, 1, 2, 1);
        mainGrid.add(btnSpecializationAdd, 0, 3);
        mainGrid.add(btnSaveSelected, 1, 3);
        //mainGrid.add(btnSpecializationDelete, 1, 3); VOLUNTEERS CANNOT DELETE
        mainGrid.add(btnBack, 0, 5);
        specializationTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        specializationTable.setItems(specializationData);

        btnSaveSelected.setOnAction(e -> {
            try{
            String previousSkills = "";
            String sqlQuery0 = "select volunteerskills from javauser.volunteer where volunteeremail = '" + vEmail + "'";
            sendDBCommand(sqlQuery0);
            while (rset.next())
            {
                previousSkills = rset.getString("volunteerskills");
            }
            // add spec to volunteer
            if (previousSkills.equalsIgnoreCase("Unspecified")) //if unspecified add in new skill
            {
                String selected = specializationTable.getSelectionModel().getSelectedItem();
                String sqlQuery1 = "";
                sqlQuery1 += "update javauser.volunteer set volunteerskills = '"
                        + selected + "' where volunteeremail = '" + vEmail + "'";
                sendDBCommand(sqlQuery1);
                specSaved.showAndWait();
            }
            else    //concat previous skill with the selected skill(specilization)
            {
            String selected = specializationTable.getSelectionModel().getSelectedItem();
            selected = selected + ", " + previousSkills;
            String sqlQuery2 = "";
            sqlQuery2 += "update javauser.volunteer set volunteerskills = '"
                    + selected + "' where volunteeremail = '" + vEmail + "'";
            sendDBCommand(sqlQuery2);
            specSaved.showAndWait();
            }
            } catch(SQLException ex){
                System.out.println(e.toString());
            }
        });

        btnBack.setOnAction(e -> {
            volPage();
        });
    }

    public String getTime() {
        String timeStamp = new SimpleDateFormat("h:mm a").format(Calendar.getInstance().getTime());
        return timeStamp;
    }

    public void sendDBCommand(String sqlQuery) { // command passing
        OracleDataSource ds;
        // System.out.println(sqlQuery); // to see query
        try {
            ds = new OracleDataSource();
            ds.setURL(jdbcConnectionURL);
            conn = ds.getConnection(userID, userPASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rset = stmt.executeQuery(sqlQuery); // Sends the Query to the DB
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    public void saveSpecialization()
    {
        specializationData.forEach((e) -> {
            String sqlQuery = "insert into javauser.specializationlist (specialization) values ('";
            sqlQuery += e + "')";
            sendDBCommand(sqlQuery);
            });  
    }
    
    public void loadSpecialization()
    {
        try{
        String sqlQuery = "select specialization from specializationlist";
        sendDBCommand(sqlQuery);
        while(rset.next())
        {
            specializationData.add(rset.getString("specialization"));
        }
        }catch(SQLException ex)
        {
            System.out.println(ex.toString());
        }
       
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void stop() 
    {
        saveSpecialization();
    }
    
    

} // end
