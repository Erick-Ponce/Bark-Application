package GroupProject;

import java.sql.*;
import java.util.*;
import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
//import oracle.jdbc.pool.OracleDataSource;

public class BarkApp extends Application {

 

    String adminUser = "admin";
    String adminPw = "admin";
    String volUser = "vol";
    String volPw = "vol";
    String checkAdminUser, checkAdminPw, checkVolUser, checkVolPw;
    String jdbcConnectionURL = "jdbc:oracle:thin:@localhost:1521:XE";
    String userID = "javauser";
    String userPASS = "javapass";

    Connection conn;
    Statement stmt;
    ResultSet rset;

    RadioButton rbAdmin = new RadioButton("Admin");
    RadioButton rbVol = new RadioButton("Volunteer");

    ToggleGroup accGroup = new ToggleGroup();

    Label lblWelcome = new Label("Welcome to the Home Site of BARK!");
    Label lblUsername = new Label("Username: ");
    Label lblPassword = new Label("Password: ");
    Label lblType = new Label("Account Type: ");
    Label lblVolStatus = new Label("Volunteer Status: ");
    Label lblStatusTxt = new Label("");
    Label currentSess = new Label("Current Session: ");
    Label lblAppName = new Label("Name: ");
    Label lblAppAddress = new Label("Address: ");
    Label lblAppDOB = new Label("DOB: ");
    Label lblAppInfo = new Label("Personal Info: ");
    Label lblAppExperience = new Label("Experience: ");
    Label lblAppEmail = new Label("Email Address: ");
    Label lblAppPhone = new Label("Phone Number: ");
    Label lblFillOut = new Label("Fill out the information below");
    Label lblServiceType = new Label("Service Type: ");
    Label lblServiceLocation = new Label("Service Location: ");
    Label lblServiceDescription = new Label("Service Description: ");
    Label lblServiceMileage = new Label("Total Mileage: ");
    Label lblVolSpec = new Label("Specialization: ");
    Label lblCheck = new Label("");
    Label lblAssignVol = new Label("Volunteers");
    Label lblAssignSpec = new Label("Specialization");

    ComboBox cbVolSpec = new ComboBox(); // probably need to change this
    String[] specStrings = {"test1", "test2"};

    PasswordField txtPassword = new PasswordField();
    TextField txtUsername = new TextField();
    TextField txtAppName = new TextField();
    TextField txtAppAddress = new TextField();
    TextField txtAppDOB = new TextField();
    TextField txtAppInfo = new TextField();
    TextField txtAppExperience = new TextField();
    TextField txtAppEmail = new TextField();
    TextField txtAppPhone = new TextField();
    TextField txtServiceType = new TextField();
    TextField txtServiceLocation = new TextField();
    TextField txtServiceDescription = new TextField();
    TextField txtServiceMileage = new TextField();
    TextField txtSpecializationAdd = new TextField();

    Button btnCreateService = new Button("Create BARK service");
    Button btnLogin = new Button("Log In");
    Button btnApprove = new Button("Approve Volunteers");
    Button btnAssignSpecialization = new Button("Assign Specialization");
    Button btnCreateEvent = new Button("Create BARK Event");
    Button btnViewRpt = new Button("View Volunteer Reports");
    Button btnLogout = new Button("Log Out");
    Button btnVolSpec = new Button("Specializations");
    Button btnCheckIn = new Button("Check In");
    Button btnCheckOut = new Button("Check Out");
    Button btnNew = new Button("New");
    Button btnAdminBackHome = new Button("Admin Home");
    Button btnVolBackHome = new Button("Volunteer Home");
    Button btnAppSubmit = new Button("Submit Application");
    Button btnAppBack = new Button("Back");
    Button btnAppPage = new Button("New? Apply Here!");
    Button btnDocServ = new Button("Document Service");
    Button btnServiceEnter = new Button("Enter");
    Button btnServiceBack = new Button("Back");
    Button btnEditSpecialization = new Button("Edit Specilization");
    Button btnSpecializationAdd = new Button("Add Specialization");
    Button btnSpecializationDelete = new Button("Delete Specialization");
    Button btnAssign = new Button("Assign");
    Button btnVolForEvent = new Button("Volunteer for this event");

    Alert loginError = new Alert(AlertType.ERROR, "Username or password entered is incorrect. Try again.");
    Alert radioError = new Alert(AlertType.ERROR, "Please pick your account type before attempting to log in.");
    Alert loginCorrect = new Alert(AlertType.INFORMATION, "Login Successful. Taking you to your home page.");
    Alert logout = new Alert(AlertType.INFORMATION, "You are now logged out of this account. Going back to the login page.");
    Alert checkIn = new Alert(AlertType.INFORMATION, "Checked In Successfully.");
    Alert checkOut = new Alert(AlertType.INFORMATION, "Checked Out Successfully.");

    Image pic = new Image("https://i.imgur.com/qttHtVz.jpg");
    ImageView imageView = new ImageView(pic);

    ListView<String> specializationTable = new ListView<>();    //Edit Specialization Table
    ObservableList<String> specializationData = FXCollections.observableArrayList();
    ListView<String> assignVolTable = new ListView<>();   //Volunteers Table for assigning specialization
    ObservableList<String> assignVolData = FXCollections.observableArrayList(); //need to put volunteer names in this 
    ListView<String> assignSpecTable = new ListView<>();   //Specializations to be assigned, uses specializationData
    static ListView<Event> eventTable = new ListView<>();
    static ObservableList<Event> eventData = FXCollections.observableArrayList();

    public String eventName;
    public String eventLocation;
    public int eventTime;
    public String eventInfo;

    GridPane mainGrid = new GridPane();

    @Override
    public void start(Stage primaryStage) {

        mainGrid.setAlignment(Pos.CENTER);
        loginPage();

        btnAppPage.setOnAction(a -> {
            VolunteerForm vf = new VolunteerForm();
        });

        btnLogin.setOnAction(e -> {
            if (accGroup.getSelectedToggle() == rbAdmin) {
                checkAdminUser = txtUsername.getText();
                checkAdminPw = txtPassword.getText();
                if (checkAdminUser.equals(adminUser) && checkAdminPw.equals(adminPw)) {
                    loginCorrect.showAndWait();
                    adminPage();
                    clearTxts();
                } else {
                    loginError.showAndWait();
                    clearTxts();
                }
            } else if (accGroup.getSelectedToggle() == rbVol) {
                checkVolUser = txtUsername.getText();
                checkVolPw = txtPassword.getText();
                if (checkVolUser.equals(volUser) && checkVolPw.equals(volPw)) {
                    loginCorrect.showAndWait();
                    volPage();
                    clearTxts();
                } else {
                    loginError.showAndWait();
                    clearTxts();
                }
            } else {
                radioError.showAndWait();
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

        btnAppBack.setOnAction(e -> {
            loginPage();
        });

        btnDocServ.setOnAction(e -> {
            volServicePage();
        });

        btnServiceBack.setOnAction(e -> {
            volPage();
        });

        btnCheckIn.setOnAction(e -> {
            checkIn();
        });

        btnCheckOut.setOnAction(e -> {
            checkOut();
        });

        Scene scene = new Scene(mainGrid, 500, 300);
        primaryStage.setTitle("BARK Application");
        primaryStage.setScene(scene);
        primaryStage.show();
        mainGrid.setVgap(10);
        mainGrid.setHgap(10);
        imageView.setFitHeight(150);
        imageView.setFitWidth(160);

    }

    public void clearTxts() {
        txtUsername.clear();
        txtPassword.clear();
    }

    public static void populateEvents(Event newEvent) {
        eventData.add(newEvent);
        eventTable.setItems(eventData);
    }

    public void loginPage() {
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


    public void adminPage() {
        mainGrid.getChildren().clear();
        mainGrid.add(btnApprove, 0, 0);
        mainGrid.add(btnEditSpecialization, 0, 1);
        mainGrid.add(btnAssignSpecialization, 0, 2);
        mainGrid.add(btnCreateEvent, 0, 3);
        mainGrid.add(btnCreateService, 0, 4);
        mainGrid.add(btnViewRpt, 0, 5);
        mainGrid.add(btnLogout, 0, 6);

        btnCreateEvent.setOnAction(a -> {
            CreateEvent CE = new CreateEvent();

        });

        btnEditSpecialization.setOnAction(e -> {  // specilization page in admin
            mainGrid.getChildren().clear();
            mainGrid.add(specializationTable, 0, 0, 2, 1);
            mainGrid.add(txtSpecializationAdd, 0, 1, 2, 1);
            mainGrid.add(btnSpecializationAdd, 0, 3);
            mainGrid.add(btnSpecializationDelete, 1, 3);
            mainGrid.add(btnAdminBackHome, 0, 5);
            specializationTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            specializationTable.setItems(specializationData);

            btnSpecializationAdd.setOnAction(a -> { //add the selected to the table and vol combobox
                specializationData.add(txtSpecializationAdd.getText());
                specializationTable.setItems(specializationData);
                cbVolSpec.setItems(specializationData);
                txtSpecializationAdd.clear();
            });

            btnSpecializationDelete.setOnAction(d -> { // deleting selected  from category list
                String selectedCat = specializationTable.getSelectionModel().getSelectedItem();
                specializationData.remove(selectedCat);
                specializationTable.setItems(specializationData);
                cbVolSpec.setItems(specializationData);
                txtSpecializationAdd.clear();
            });
        });

        btnAssignSpecialization.setOnAction(b -> {    //assign specialization page (curently not really assigning anything, instead its just displaying as if it was assigning)
            mainGrid.getChildren().clear();
            assignVolTable.setMaxSize(250, 200);
            assignSpecTable.setMaxSize(250, 200);
            mainGrid.add(lblAssignVol, 0, 0);
            mainGrid.add(lblAssignSpec, 2, 0);
            mainGrid.add(assignVolTable, 0, 1, 2, 1);
            mainGrid.add(assignSpecTable, 2, 1, 2, 1);
            mainGrid.add(btnAssign, 2, 2);
            mainGrid.add(btnAdminBackHome, 0, 2);
            assignSpecTable.setItems(specializationData);

            btnAssign.setOnAction(a -> { //assign button on the assign specialization page
                //actual assinging code goes here
                Alert assignAlert = new Alert(AlertType.INFORMATION, "'Vol name goes here' was assigned to " + assignSpecTable.getSelectionModel().getSelectedItem());
                assignAlert.show();
            });

        });

    }

    public void volPage() {
        mainGrid.getChildren().clear();
        mainGrid.add(lblVolStatus, 0, 0);
        mainGrid.add(lblStatusTxt, 1, 0);
        mainGrid.add(btnLogout, 1, 10);
        mainGrid.add(btnDocServ, 1, 9);
        mainGrid.add(lblVolSpec, 0, 1);
        mainGrid.add(cbVolSpec, 1, 1);
        mainGrid.add(btnNew, 2, 1);
        mainGrid.add(btnCheckIn, 1, 2);
        mainGrid.add(btnCheckOut, 2, 2);
        mainGrid.add(btnVolForEvent, 1, 6);
        mainGrid.add(eventTable, 1, 5);
        mainGrid.add(lblCheck, 1, 8);
        eventTable.setItems(eventData);
        lblStatusTxt.setText("Pre-Application"); // just testing
        if (lblStatusTxt.getText() == "Pre-Application") {
            // no features
        } else if (lblStatusTxt.getText() == "Awaiting Approval") {
            // no features awaiting approval
        } else if (lblStatusTxt.getText() == "In Training") {
            // features 
        } else if (lblStatusTxt.getText() == "Volunteer") {
            // features
        }
    }

    public void volAppPage() {  //Volunteer Application Page
        mainGrid.getChildren().clear();
        mainGrid.add(lblFillOut, 0, 0);
        mainGrid.add(lblAppName, 0, 2);
        mainGrid.add(lblAppAddress, 0, 3);
        mainGrid.add(lblAppInfo, 0, 4);
        mainGrid.add(lblAppExperience, 0, 5);
        mainGrid.add(lblAppEmail, 0, 6);
        mainGrid.add(lblAppPhone, 0, 7);
        mainGrid.add(txtAppName, 1, 2);
        mainGrid.add(txtAppAddress, 1, 3);
        mainGrid.add(txtAppInfo, 1, 4);
        mainGrid.add(txtAppExperience, 1, 5);
        mainGrid.add(txtAppEmail, 1, 6);
        mainGrid.add(txtAppPhone, 1, 7);
        mainGrid.add(btnAppSubmit, 1, 9);
        mainGrid.add(btnAppBack, 0, 9);
    }

    public void volServicePage() { //Volunteer Service/Document Page
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
        mainGrid.add(btnServiceBack, 0, 9);
    }

    public void checkIn() {
        Calendar cal = new GregorianCalendar();
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        lblCheck.setText("Checked in at  " + hour + " : " + minute);
        checkIn.showAndWait();
    }

    public void checkOut() {
        Calendar cal = new GregorianCalendar();
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        lblCheck.setText("Checked out at  " + hour + " : " + minute);
        checkOut.showAndWait();
    }

    // simple method to pass a query to the db
//    public void sendDBCommand(String sqlQuery) {
//        OracleDataSource ds;
//        // System.out.println(sqlQuery); // see query passed in (leave commented out)
//        try {
//            ds = new OracleDataSource();
//            ds.setURL(jdbcConnectionURL);
//            conn = ds.getConnection(userID, userPASS);
//            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            rset = stmt.executeQuery(sqlQuery); // Sends the Query to the DB
//        } catch (SQLException e) {
//            System.out.println(e.toString());
//        }
//    }

    public static void main(String[] args) {
        launch(args);
    }

}
