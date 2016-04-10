/**
 * Created by Alex Durre on 3/25/2016.
 */
public class User {
    String username;
    String password;
    DynamicBuffer inbox;
    long counter;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.inbox = new DynamicBuffer(2);
        this.counter = 0;
    }
    public String getName() {
        return this.username;
    }

    public boolean checkPassword(String password) {
        if (password.equals(this.password)) {
            return true;
        }
        else {
            return false;
        }
    }

    public int numEmail() {
        return inbox.numElements();
    }

    public void receiveEmail(String sender, String message) {
        this.counter++;
        Email email = new Email(this.username, sender, counter, message);
        inbox.add(email);
    }

    public Email[] retrieveEmail(int n) {
        Email[] temp =  this.inbox.getNewest(n);
        return temp;
    }

    public boolean removeEmail(long emailID) {
        for (int i = 0; i < this.inbox.numElements(); i++) {
            if (this.inbox.array[i].getID() == emailID) {   //todo check this, not sure if it makes sense / right index?
                inbox.remove(i);
                this.counter--;
                return true;
            }
        }
        return false;
    }
}