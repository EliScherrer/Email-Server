import java.util.Date;
/**
 * Created by Alex Durre on 3/25/2016.
 */
public class Email {
    String recipient;    //is the username of the person the email is to
    String sender;       //is the username of the person the email is from
    long id;             //is a unique semi-global id (i.e. ids only have to be unique within a user's mailbox)
    String message;      //is the text of the email
    Date date;
    String theDate;

    public Email(String recipient, String sender, long id, String message) {
        this.recipient = recipient;
        this.sender = sender;
        this.id = id;
        this.message = message;
        this.date = new Date();
        this.theDate = this.date.toString();
    }
    public long getID() {
        return this.id;
    }
    public String getOwner() {
        return this.recipient;
    }
    public String getSender() {
        return this.sender;
    }
    public String getMessage() {
        return this.message;
    }
    public String toString() {
        String temp = "";
        temp = this.id + ";" + this.theDate + "; From: " + this.sender + " " + this.message;
        return temp;
    }
}