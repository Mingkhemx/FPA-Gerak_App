package Gerak;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resource/Dashboard.fxml"));
        Parent root = loader.load();
        
        // Get the controller
        DashboardController controller = loader.getController();
        
        // Set up the stage
        primaryStage.setTitle("FitLife Dashboard");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(700);
        
        // Set up close handler to clean up animations
        primaryStage.setOnCloseRequest(e -> {
            controller.cleanup();
        });
        
        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}