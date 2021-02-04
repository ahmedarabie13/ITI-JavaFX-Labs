package com.arabie;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        var label =new Label("Hello,JavaFxWorld");
//        label.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD,25));
//        label.setFont(Font.loadFont("com.arabie/Goldman-Regular.ttf",40));
//        label.setTextFill(Color.web("#ff0000"));
        label.setEffect(new Reflection());
//        label.setFont(Font.loadFont(App.class.getResource("/Goldman-Regular.ttf").toString(),40));
//        String fontLoc =App.class.getResource("/Goldman-Regular.ttf").toString();
//        String fontLoc="file:D:\\iTi\\GUI\\JavaFX\\HelloFxWorld\\src\\main\\resources\\Fonts\\Goldman-Regular.ttf";
        var stackPane=new StackPane(label);
        var scene = new Scene(stackPane, 640, 480);
        scene.getStylesheets().add("com.arabie/myCss.css");
        stackPane.getStyleClass().add("pane");
        label.getStyleClass().add("my-robot-label2");
//        label.setFont(Font.loadFont(fontLoc,40));

//        Stop[] stops = new Stop[] { new Stop(0, Color.BLACK),
//                new Stop(0.5, Color.WHITE),
//                new Stop(1, Color.BLACK)
//        };
//        LinearGradient lg1 = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
//        stackPane.setBackground(new Background(new BackgroundFill(lg1,
//                null,
//                null)
//        ));

//        stackPane.setStyle("-fx-background-color: linear-gradient(#000000 0%, #ffffff 50%, #000000 100%)");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}