package com.itacademy.courses.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private String login;
    private String forAccount;
    private long dateMilliseconds;
    private String massage;

    public Message(){}

    public Message(String login, String forAccount, String massage){
        this.login = login;
        this.forAccount = forAccount;
        this.massage = massage;
        setDate();
    }

    public String getForAccount() {
        return forAccount;
    }

    public void setForAccount(String forAccount) {
        this.forAccount = forAccount;
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

    public String dateToString(){
        Date date = new Date(dateMilliseconds);
        return date.toString();
    }

    @Override
    public String toString() {
        return "["+dateToString()+"], "+this.login+": "+this.massage;
    }
}
