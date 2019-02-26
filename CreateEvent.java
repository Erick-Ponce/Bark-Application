/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupProject;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Owner
 */
public class CreateEvent {

    public String eventName;
    public String eventLocation;
    public String eventInfo;
    public int eventTime;
    public BarkApp parentForm;

    public CreateEvent() {
        Stage primaryStage = new Stage();
        GridPane primaryPane = new GridPane();
        Label lblEventName = new Label("Event Name: ");
        TextField txtEventName = new TextField();
        Label lblEventLocation = new Label("Event Location: ");
        TextField txtEventLocation = new TextField();
        Label lblEventInfo = new Label("Event Info: ");
        TextField txtEventInfo = new TextField();
        Label lblEventTime = new Label("Event Time: ");
        TextField txtEventTime = new TextField();
        Button btnCreateEvent = new Button("Submit");

        primaryPane.add(lblEventName, 0, 0);
        primaryPane.add(txtEventName, 1, 0);
        primaryPane.add(lblEventLocation, 0, 1);
        primaryPane.add(txtEventLocation, 1, 1);
        primaryPane.add(lblEventInfo, 0, 2);
        primaryPane.add(txtEventInfo, 1, 2);
        primaryPane.add(lblEventTime, 0, 3);
        primaryPane.add(txtEventTime, 1, 3);
        primaryPane.add(btnCreateEvent, 0, 4);

        primaryPane.setAlignment(Pos.CENTER);
        Scene primaryScene = new Scene(primaryPane, 300, 300);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Create BARK Event");
        primaryStage.show();

        btnCreateEvent.setOnAction(a -> {
            eventName = txtEventName.getText();
            eventLocation = txtEventLocation.getText();
            eventInfo = txtEventInfo.getText();
            eventTime = Integer.parseInt(txtEventTime.getText());
            Event newEvent = new Event (eventName, eventLocation, eventTime, eventInfo);
            BarkApp.populateEvents(newEvent);
            txtEventName.clear();
            txtEventLocation.clear();
            txtEventInfo.clear();
            txtEventTime.clear();
            
        });
    }
}
