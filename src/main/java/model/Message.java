package model;

import java.io.Serializable;

public class Message implements Serializable {
    private MessageType type;
    private String msg;
    private String name;

    public Message(MessageType type, String msg, String name) {
        this.type = type;
        this.msg = msg;
        this.name = name;
    }

    public Message(String name) {
        this.name = name;
        type = MessageType.START;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
