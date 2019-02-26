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
public class CreateService {

    public String serviceType;
    public String serviceLocation;
    public String serviceDesc;
    public int totalMiles;
    public BarkApp parentForm;

    public CreateService() {
        Stage primaryStage = new Stage();
        GridPane primaryPane = new GridPane();
        Label lblServiceName = new Label("Service Name: ");
        TextField txtServiceName = new TextField();
        Label lblServiceLocation = new Label("Service Location: ");
        TextField txtServiceLocation = new TextField();
        Label lblServiceDesc = new Label("Service Description: ");
        TextField txtServiceDesc = new TextField();
        Label lblTotalMiles = new Label("Total Miles: ");
        TextField txtTotalMiles = new TextField();
        Button btnCreateService = new Button("Submit");

        primaryPane.add(lblServiceName, 0, 0);
        primaryPane.add(txtServiceName, 1, 0);
        primaryPane.add(lblServiceLocation, 0, 1);
        primaryPane.add(txtServiceLocation, 1, 1);
        primaryPane.add(lblServiceDesc, 0, 2);
        primaryPane.add(txtServiceDesc, 1, 2);
        primaryPane.add(lblTotalMiles, 0, 3);
        primaryPane.add(txtTotalMiles, 1, 3);
        primaryPane.add(btnCreateService, 0, 4);

        primaryPane.setAlignment(Pos.CENTER);
        Scene primaryScene = new Scene(primaryPane, 300, 300);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Create BARK Service");
        primaryStage.show();

        btnCreateService.setOnAction(a
                -> {
            serviceType = txtServiceName.getText();
            serviceLocation = txtServiceLocation.getText();
            serviceDesc = txtServiceDesc.getText();
            totalMiles = Integer.parseInt(txtTotalMiles.getText());
            Service newService = new Service(serviceType, serviceLocation, serviceDesc, totalMiles);
        }
        );
    }
}
