package com.arabie;

import java.io.IOException;
import java.net.URL;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;


public class SecondaryController implements Initializable {
    private User user;

    @FXML
    private Label userName;
    @FXML
    private ImageView userImage;
    @FXML
    private VBox chatWindow;
    @FXML
    private TextField textFieldMsg;
    @FXML
    private ScrollPane scrlPane;


    @FXML
    private void enterClicked() throws IOException {
        String msgText =textFieldMsg.getText();
        textFieldMsg.clear();
//        FXMLLoader loader =new FXMLLoader(App.class.getResource("chatMsg.fxml"));
//        Parent msg = loader.load();
//        ChatMsg msgCtrl =  loader.getController();
//        msgCtrl.setMsgBody(msgText);
//        msgCtrl.setSenderName(user.getName());
//        msgCtrl.setSenderImg(user.getImage());
        if (!msgText.equals("")) {

            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = myDateObj.format(myFormatObj);
            Parent msg = App.addMsgItem(new Message(msgText, formattedDate, null));
            scrlPane.vvalueProperty().bind(chatWindow.heightProperty());
            chatWindow.prefWidthProperty().bind(scrlPane.widthProperty());
            chatWindow.getChildren().add(msg);

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user= (User) resourceBundle.getObject("");
        userName.setText(user.getName());
//        userName.textProperty().bind();
        userImage.setImage(user.getImage());
    }
}