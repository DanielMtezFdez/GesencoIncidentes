package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        primaryStage.setTitle("Gesenco Incidentes");

        Image icon = new Image("img/logo_gesenco.jpg");
        primaryStage.getIcons().add(icon);

        primaryStage.setScene(new Scene(root, 1315, 807));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


}
