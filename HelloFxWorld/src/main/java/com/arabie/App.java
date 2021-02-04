package com.arabie;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URI;


public class App extends Application {

    @Override
    public void start(Stage stage) {
        Text text =new Text("Hello,JavaFxWorld");
//        String fontLoc="file:D:\\iTi\\GUI\\JavaFX\\HelloFxWorld\\src\\main\\resources\\Fonts\\Goldman-Regular.ttf";
        String fontLoc=App.class.getResource("/Fonts/Goldman-Regular.ttf").toString();
        text.setFont(Font.loadFont(fontLoc, 40));
        System.out.println(fontLoc);
        DropShadow dropShadow=new DropShadow();
        dropShadow.setOffsetY(3.0f);
        dropShadow.setColor(Color.color(0.4f, 0.4f, 0.4f));
        text.setFill(Color.web("#f00000"));

        InnerShadow is = new InnerShadow();
        is.setOffsetX(2.0f);
        is.setOffsetY(2.0f);

        Blend blend = new Blend();
        blend.setMode(BlendMode.MULTIPLY);
        blend.setBottomInput(dropShadow);
        blend.setTopInput(is);

        Blend blend2 = new Blend();
        blend2.setMode(BlendMode.MULTIPLY);
        blend2.setBottomInput(blend);
        blend2.setTopInput(new Reflection());

        text.setEffect(blend2);


//        var label =new Label("Hello,JavaFxWorld");
//        label.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD,25));
//        label.setTextFill(Color.web("#ff0000"));
//        text.setEffect(new Reflection());

        var stackPane=new StackPane(text);
        var scene = new Scene(stackPane, 640, 480);
        Stop[] stops = new Stop[] { new Stop(0, Color.BLACK),
                                    new Stop(0.5, Color.WHITE),
                                    new Stop(1, Color.BLACK)
        };
        LinearGradient lg1 = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        stackPane.setBackground(new Background(new BackgroundFill(lg1,
                null,
                null)
        ));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}