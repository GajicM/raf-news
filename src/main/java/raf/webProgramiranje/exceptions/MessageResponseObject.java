package raf.webProgramiranje.exceptions;

public class MessageResponseObject {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public MessageResponseObject(String s){
        this.message=s;
    }
}
