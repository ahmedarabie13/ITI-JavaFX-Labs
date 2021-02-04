package com.arabie;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class User {
    private Image image;
    private String name;

    public User(Image image, String name) {
        this.image = image;
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Parent getAvatar(String msg){
        ImageView imageView=new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        imageView.setPreserveRatio(true);
        Label userName =new Label(name);
        userName.setPrefHeight(10);
        userName.setMaxWidth(40);
        userName.setFont(Font.font(null, FontWeight.NORMAL,null,10));
        userName.setTextFill(Color.web("#A52A2A"));
        var abstractAvatar = new VBox();
        abstractAvatar.getChildren().addAll(imageView,userName);
        var avatar =new HBox();
        var msgLabel =new Label(msg);
        msgLabel.setWrapText(true);
        msgLabel.setMaxHeight(Double.POSITIVE_INFINITY);
        msgLabel.setPrefHeight(Double.POSITIVE_INFINITY);
        msgLabel.prefHeight(Double.POSITIVE_INFINITY);
        msgLabel.setTextFill(Color.web("#A52A2A"));
        avatar.getChildren().addAll(abstractAvatar,msgLabel);
        avatar.prefHeight(Double.POSITIVE_INFINITY);
        return avatar;
    }
}
