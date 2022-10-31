//Form entry form for editing an account
import Account.Account;

import java.util.Scanner;
public class ControllerEditAccount {
    ControllerEditAccount(){}

    public void viewAccount(Account account){
        System.out.println("Логин: "+account.getLogin());
        if(account.getProfile().getName()!=null){
            System.out.println("Имя: "+account.getProfile().getName());
        }
        if(account.getProfile().getSurname()!=null){
            System.out.println("Фамилия: "+account.getProfile().getSurname());
        }
        if(account.getProfile().getAddress()!=null){
            System.out.println("Адрес: "+account.getProfile().getAddress());
        }
        if(account.getProfile().getAge()!=0){
            System.out.println("Возраст: "+account.getProfile().getAge());
        }
    }

    public Account editAccount(Account account){
        Account newAccount = new Account();
        Scanner in = new Scanner(System.in);
        newAccount.setLogin(account.getLogin());
        System.out.print("Введите имя: ");
        newAccount.getProfile().setName(in.next());
        System.out.print("Введите фамилию: ");
        newAccount.getProfile().setSurname(in.next());
        System.out.print("Введите адресс: ");
        newAccount.getProfile().setAddress(in.next());
        System.out.print("Введите возраст: ");
        newAccount.getProfile().setAge(in.nextInt());
        newAccount.setPassword(account.getPassword());
        return newAccount;
    }
}
