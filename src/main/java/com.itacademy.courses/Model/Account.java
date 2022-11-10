package com.itacademy.courses.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
    private String login;
    private String password;
    private Profile profile = new Profile();
    private LinkedList<String> newMessageNames = new LinkedList<>();
    public Account(){

    }

    public Account(String login, String password){
        this.login = login;
        this.password = password;
    }

    public void clone (Account account){
        this.login = account.getLogin();
        this.password = account.getPassword();
        this.profile.setName(account.profile.getName());
        this.profile.setSurname(account.profile.getSurname());
        this.profile.setAddress(account.profile.getAddress());
        this.profile.setAge(account.profile.getAge());
        this.profile.setFriends(account.profile.getFriends());
        this.newMessageNames.clear();
        this.newMessageNames.addAll(account.getNewMessageNames());
    }

    public int countNewMessages(){
        int count = 0;
        if(newMessageNames!=null){
            count = newMessageNames.size();
        }
        return count;
    }

    public LinkedList<String> getNewMessageNames() {
        return newMessageNames;
    }

    public void addNewMessageName(String loginFrom){
        this.newMessageNames.add(loginFrom);
    }

    public void setNewMessageNames(LinkedList<String> newMessageNames) {
        this.newMessageNames = newMessageNames;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean equals(String login){
        if(this.login.equals(login)){
            return true;
        } else {
            return false;
        }
    }

    public String toString(){
        return "Логин :"+login+"\nИмя: "+profile.getName()+"\nФамилия: "+profile.getSurname()+"\nАдрес: "+
                profile.getAddress()+"\nВозраст: "+profile.getAge();
    }


}
