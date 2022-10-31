package Account;

import java.util.ArrayList;

public class AccountService {

    private AccountDao accountDao = new AccountDao();
    private ArrayList<Account> accounts = accountDao.readAccounts();

    public AccountService(){}

    public Account loginAccount(String login, String password){
        for(Account account : accounts){
            if(account.getLogin().equals(login)){
                if(account.getPassword().equals(password)){
                    return account;
                }
            }
        }
        return null;
    }

    public String createNewAccount(String login, String password){
        boolean newAccount = true;
        if(accounts.size()!=0){
        for(Account oldAccount : accounts){
            if(oldAccount.getLogin().equals(login)){
                newAccount = false;
            }
        }}
        if(newAccount){
            Account account = new Account(login, password);
            accounts.add(account);
            accountDao.writeAccount(accounts);
            return "OK";
        }
        return "Account is been";
    }

    public String deleteAccount(String login, String password){
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
        for(int i = 0;i<accounts.size();i++){
            if(accounts.get(i).getLogin().equals(login)){
                return accounts.get(i);
            }
        }
        return null;
    }

    public void save(Account account){
        for(int i = 0;i<accounts.size();i++){
            if(accounts.get(i).getLogin().equals(account.getLogin())){
                accounts.get(i).clone(account);
            }
            accountDao.writeAccount(accounts);
        }
    }
}
