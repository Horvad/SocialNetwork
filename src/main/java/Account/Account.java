package Account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
    private String login;
    private String password;
    private Profile profile = new Profile();
    private int newMessage = 0;
    public Account(){

    }

    public Account(String login, String password){
        this.login = login;
        this.password = password;
    }

    public void clone (Account account){
        this.login = account.getLogin();
        this.password = account.getPassword();
        this.profile.name = account.profile.name;
        this.profile.surname = account.profile.surname;
        this.profile.address = account.profile.address;
        this.profile.age = account.profile.age;
    }

    public int isNewMessage() {
        return newMessage;
    }

    public void setNewMessage(int countNewMessage) {
        this.newMessage = this.newMessage + countNewMessage;
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
        return "Логин :"+login+"\nИмя: "+profile.name+"\n+Фамилия: "+profile.surname+"\nАдрес: "+
                profile.address+"\nВозраст: "+profile.age;
    }

    public class Profile{
        private String name;
        private String surname;
        private String address;
        int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
