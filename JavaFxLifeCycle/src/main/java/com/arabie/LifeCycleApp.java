package com.arabie;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LifeCycleApp extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    public LifeCycleApp(){
        String name =Thread.currentThread().getName();
        System.out.println(name+" is running the Constructor");
    }

    @Override
    public void stop() throws Exception {
        String name =Thread.currentThread().getName();
        System.out.println(name+" is running the stop() method");
        super.stop();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label label = new Label("Hello, JavaFX !!!");

        StackPane pane = new StackPane();
        pane.getChildren().addAll(label);

        Scene scene = new Scene(pane, 400, 300);

        String name =Thread.currentThread().getName();
        System.out.println(name+" is running the start() method");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello World !!!");
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        String name =Thread.currentThread().getName();
        System.out.println(name+" is running the init() method");
        super.init();
    }
}
