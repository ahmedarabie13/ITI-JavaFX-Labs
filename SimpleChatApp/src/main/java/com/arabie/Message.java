package com.arabie;

import javafx.scene.control.Label;
import org.kordamp.ikonli.javafx.FontIcon;

public class Message {
    private String msgBody;
    private String timeStamp;
    private FontIcon statusIcon;

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Message(String msgBody, String timeStamp, FontIcon statusIcon) {
        this.msgBody = msgBody;
        this.timeStamp = timeStamp;
        this.statusIcon = statusIcon;
    }

    public FontIcon getStatusIcon() {
        return statusIcon;
    }

    public void setStatusIcon(FontIcon statusIcon) {
        this.statusIcon = statusIcon;
    }
}
