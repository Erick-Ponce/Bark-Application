package GroupProject;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.*;

/**
 *
 * @author Owner
 */
public class AdminPage {

    public BarkApp parentForm;

    public AdminPage() {
        Stage primaryStage = new Stage();
        GridPane primaryPane = new GridPane();
        Label lblSelection = new Label("What would you like to do: ");
        Button btnCreateEvent = new Button("Create new BARK event");
        Button btnCreateService = new Button("Create new BARK service");
        primaryPane.add(lblSelection, 0, 0);
        primaryPane.add(btnCreateEvent, 0, 1);
        primaryPane.add(btnCreateService, 1, 1);
        primaryPane.setAlignment(Pos.CENTER);
        Scene primaryScene = new Scene(primaryPane, 300, 300);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("BARK Admin");
        primaryStage.show();

        btnCreateEvent.setOnAction(a -> {
            CreateEvent ce = new CreateEvent();

        });
        btnCreateService.setOnAction(b -> {
            CreateService cs = new CreateService();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
