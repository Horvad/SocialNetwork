package com.itacademy.courses.Servise;
import com.itacademy.courses.Dao.AccountDao;
import com.itacademy.courses.Model.Account;

import java.util.ArrayList;

public class AccountService {

    private final AccountDao accountDao = new AccountDao();

    public Account loginAccount(String login, String password){
        ArrayList<Account> accounts = accountDao.readAccounts();
        for(Account account : accounts){
            if(account.getLogin().equals(login)){
                if(account.getPassword().equals(password)){
                    return account;
                }
            }
        }
        return null;
    }

    public boolean createNewAccount(String login, String password){
        boolean newAccount = true;
        ArrayList<Account> accounts = accountDao.readAccounts();
        if(accounts.size()!=0){
            for(Account oldAccount : accounts){
                if(oldAccount.getLogin().equals(login)){
                    newAccount = false;
                }
            }
        }
        if(newAccount){
            Account account = new Account(login, password);
            accounts.add(account);
            accountDao.writeAccount(accounts);
            return true;
        }
        return false;
    }

    public String deleteAccount(String login, String password){
        ArrayList<Account> accounts = accountDao.readAccounts();
        for(Account account : accounts){
            if(account.equals(login)){
                if(account.getPassword().equals(password)){
                    accounts.remove(account);
                    accountDao.writeAccount(accounts);
                    return "Account deleted";
                }
                return "Invalid password";
            }
        }
        return "Account not found";
    }

    public Account viewAccount(String login){
        ArrayList<Account> accounts = accountDao.readAccounts();
        for(int i = 0;i<accounts.size();i++){
            if(accounts.get(i).getLogin().equals(login)){
                return accounts.get(i);
            }
        }
        return null;
    }

    public ArrayList viewAccount(String name, String surname){
        ArrayList<Account> accounts = accountDao.readAccounts();
        ArrayList<Account> accountsView = new ArrayList<>();
        for(int i = 0;i<accounts.size();i++){
            if(accounts.get(i).getProfile().getName().equals(name)&&accounts.get(i).getProfile().getSurname().equals(surname)){
                accountsView.add(accounts.get(i));
            }
        }
        return accountsView;
    }

    public ArrayList viewAccount(int age){
        ArrayList<Account> accountsView = new ArrayList<>();
        ArrayList<Account> accounts = accountDao.readAccounts();
        for(int i = 0;i<accounts.size();i++){
            if(accounts.get(i).getProfile().getAge()==age){
                accountsView.add(accounts.get(i));
            }
        }
        return accountsView;
    }

    public boolean addFriend(Account account,String loginFriend){
        boolean add = false;
        Account accountFriend = viewAccount(loginFriend);
        if(accountFriend!=null){
            String friends = account.getProfile().getFriends();
            if(friends==null){
                friends = loginFriend+"; ";
            } else {
                friends = friends + loginFriend+"; ";
            }
            add = true;
            account.getProfile().setFriends(friends);
        }
        return add;
    }

    public boolean removeFriend(Account account, String loginFriend){
        boolean remove = false;
        Account accountFriend = viewAccount(loginFriend);
        if(accountFriend!=null){
            String friends = account.getProfile().getFriends();
            loginFriend = loginFriend+"; ";
            friends.replaceAll(friends,loginFriend);
            if(!friends.equals(account.getProfile().getFriends()))
                remove = true;
            account.getProfile().setFriends(friends);
        }
        return remove;
    }

    public String viewAllAccounts(){
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Account> accounts = accountDao.readAccounts();
        for(Account account : accounts){
            stringBuilder.append("Login: "+account.getLogin()+", Name: "+account.getProfile().getName()+"\n");
        }
        return stringBuilder.toString();
    }


    public void save(Account account){
        ArrayList<Account> accounts = accountDao.readAccounts();
        for(int i = 0;i<accounts.size();i++){
            if(accounts.get(i).getLogin().equals(account.getLogin())){
                accounts.get(i).clone(account);
            }
            accountDao.writeAccount(accounts);
        }
    }
}
