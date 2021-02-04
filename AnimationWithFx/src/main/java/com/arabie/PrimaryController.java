package com.arabie;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class PrimaryController implements Initializable {

    @FXML
    private SVGPath svgPath;
    @FXML
    private AnchorPane anchorPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ImageView car1 = new ImageView(new Image("car.png",70,70,false,true));

        car1.setScaleX(-1);
        ImageView car2 = new ImageView(new Image("car.png",70,70,false,true));
        car2.setScaleX(-1);
        svgPath.setFill(Color.TRANSPARENT);
        svgPath.setStrokeWidth(40.0);
        svgPath.setStroke(Color.LIGHTGREY);
//        svgPath.setContent("M 787.49,150 C 787.49,203.36 755.56,247.27 712.27,269.5 S 622.17,290.34 582.67,279.16 508.78,246.56 480,223.91 424.93,174.93 400,150 348.85,98.79 320,76.09 256.91,32.03 217.33,20.84 130.62,8.48 87.73,30.5 12.51,96.64 12.51,150 44.44,247.27 87.73,269.5 177.83,290.34 217.33,279.16 291.22,246.56 320,223.91 375.07,174.93 400,150 451.15,98.79 480,76.09 543.09,32.03 582.67,20.84 669.38,8.48 712.27,30.5 787.49,96.64 787.49,150 z");
//        svgPath.setContent("M380.52.5h152s66,6,86.67,85.33-78,114.67-78,114.67H380.52L238.52.5h-154S-.82,19.17.52,97.5s71.33,103.67,79.33,103,157.33,0,157.33,0Z");
        anchorPane.getChildren().add(car1);
        anchorPane.getChildren().add(car2);
        addCar(car1,10000);
        addCar(car2,15000);





    }

    private void doBinding(ImageView car) {

        car.layoutXProperty().bindBidirectional(svgPath.layoutXProperty());
        car.layoutYProperty().bindBidirectional(svgPath.layoutYProperty());

    }

    private void addCar(ImageView car, int duration) {

        doBinding(car);
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(duration));
        pt.setPath(svgPath);
        pt.setNode(car);
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setInterpolator(Interpolator.LINEAR);
        car.setOnMousePressed((e)->{

            pt.setRate(pt.getRate()*-1);
            car.setScaleX(-1*car.getScaleX());

        });

      pt.play();

    }

}
