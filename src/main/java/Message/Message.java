package Message;

import java.util.Date;

public class Message {
    protected String login;
    protected long dateMilliseconds;
    protected String massage;

    public Message(){}

    public Message(String login, String massage){
        this.login = login;
        this.massage = massage;
        setDate();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getDateMilliseconds() {
        return dateMilliseconds;
    }

    public void setDateMilliseconds(long dateMilliseconds) {
        this.dateMilliseconds = dateMilliseconds;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public void setDate() {
        Date dateRealTime = new Date();
        dateMilliseconds = dateRealTime.getTime();
    }

    public String DateToString(){
        Date date = new Date(dateMilliseconds);
        return date.toString();
    }

    @Override
    public String toString() {
        return "["+DateToString()+"], "+this.login+": "+this.massage;
    }
}
