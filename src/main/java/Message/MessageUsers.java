package Message;

public class MessageUsers extends Message {
    private String toLogin;
    private boolean read = false;
    public MessageUsers(){}
    public MessageUsers (String login, String toLogin, String message){
        super(login,message);
        this.toLogin = toLogin;
    }

    public String getToLogin() {
        return toLogin;
    }

    public void setToLogin(String toLogin) {
        this.toLogin = toLogin;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
