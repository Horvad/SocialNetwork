package com.itacademy.courses.Controller;

import com.itacademy.courses.Model.Account;
import com.itacademy.courses.Servise.AccountService;

import java.util.ArrayList;
import java.util.Scanner;

public class ControllerAccount {
    private final Scanner in = new Scanner(System.in);

    private int selection = -1;
    private final AccountService accountService = new AccountService();

    public void viewAccountForm(Account account){
        while (selection!=0){
            System.out.print("\n");
            System.out.print("" +
                    "1. Просмотр информации об аккаунте.\n" +
                    "2. Изменение профиля аккунта.\n" +
                    "3. Поиск аккаунта по логину\n" +
                    "4. Поиск аккаунта по имени и фамилии\n" +
                    "5. Поиск аккаунта по возрасту\n" +
                    "6. Добавление(удаление) аккаунта в(из) друзья(друзей)\n" +
                    "7. Просмотр друзей\n" +
                    "8. Просмотр всех аккаунтов\n" +
                    "9. Удаление аккаунта\n" +
                    "0. Возврат в предыдущее меню\n" +
                    "Сделайте выбор: ");
            selection = in.nextInt();
            if(selection>8||selection<0)
                System.out.print("\n\n\nНе верный ввод\n");
            switch (selection){
                case 1:
                    viewAccount(account);
                    break;
                case 2:
                    removeAccount(account);
                    break;
                case 3:
                    System.out.print("\n\n\nВведите логин для поиска: ");
                    String loginSearch = in.next();
                    searchAccount(loginSearch,account);
                    break;
                case 4:
                    System.out.print("\n\n\nВведте имя для поиска: ");
                    String nameSearch = in.next();
                    System.out.print("\n\n\nВведте фамилию для поиска: ");
                    String surnameSearch = in.next();
                    searchAccount(nameSearch,surnameSearch);
                    break;
                case 5:
                    System.out.print("Введите возраст для поиска: ");
                    int searchAge = in.nextInt();
                    searchAccount(searchAge);
                    break;
                case 6:
                    System.out.print("\n\n\nВведите логин аккаута, который хотите довавить в друзья (удалить из друзей): ");
                    String loginFriend = in.next();
                    addNewFriend(loginFriend,account);
                    break;
                case 7:
                    viewFriend(account);
                    break;
                case 8:
                    viewAllAccounts();
                    break;
                case 9:
                    System.out.print("Введте пароль: ");
                    String password = in.next();
                    deleteAccount(password,account);
            }
            accountService.save(account);
        }
    }

    public void viewAccount(Account account){
        System.out.print("Логин: "+account.getLogin()+"\n");
        if(account.getProfile().getName()!=null)
            if(!account.getProfile().getName().equals(""))
                System.out.print("Имя: "+account.getProfile().getName()+"\n");
        if(account.getProfile().getSurname()!=null)
            if(!account.getProfile().getSurname().equals(""))
                System.out.print("Фамилия: "+account.getProfile().getSurname()+"\n");
        if(account.getProfile().getAddress()!=null)
            if(!account.getProfile().getAddress().equals(""))
            System.out.print("Адрес: "+account.getProfile().getAddress()+"\n");
        if(account.getProfile().getAge()!=0)
            System.out.print("Возраст: "+account.getProfile().getAge()+"\n");
    }

    public void removeAccount(Account account){
        System.out.print("Введите имя: ");
        account.getProfile().setName(in.next());
        System.out.print("Введите фамилию: ");
        account.getProfile().setSurname(in.next());
        System.out.print("Введите адрес: ");
        account.getProfile().setAddress(in.next());
        System.out.print("Введите возраст: ");
        account.getProfile().setAge(in.nextInt());
        accountService.save(account);
    }

    public void searchAccount(String login, Account account){
        Account accountSearch = accountService.viewAccount(login);
        if(account!=null)
            viewAccount(accountSearch);
        else
            System.out.print("ТАКОГО АККАУНТА НЕ СУЩЕСТВУЕТ\n");
    }

    public void searchAccount(String name, String surname){
        ArrayList<Account> accounts = accountService.viewAccount(name,surname);
        if(accounts.size()!=0){
            for(int i = 0; i<accounts.size(); i++){
                viewAccount(accounts.get(i));
            }
        } else
            System.out.print("ТАКОГО АККАУНТА НЕ СУЩЕСТВУЕТ\n");
    }

    public void searchAccount(int age){
        ArrayList<Account> accounts = accountService.viewAccount(age);
        if(accounts.size()!=0){
            for(int i = 0; i<accounts.size(); i++){
                viewAccount(accounts.get(i));
            }
        } else
            System.out.print("ТАКОГО АККАУНТА НЕ СУЩЕСТВУЕТ\n");
    }

    public void addNewFriend(String loginFriend, Account account){
        System.out.print("" +
                "1. Добавить в друзья\n" +
                "2. Удалить из друзей\n" +
                "0. Возврат в предыдущее меню.\n" +
                "Сделайте выбор: ");
        selection = in.nextInt();
        boolean addRemoveFriend = false;
        if(selection==1)
            addRemoveFriend = accountService.addFriend(account, loginFriend);
        if(selection==2)
            addRemoveFriend = accountService.removeFriend(account, loginFriend);
        if(selection==0)
            return;
        if(addRemoveFriend)
            System.out.print("Действие выполнено!\n");
        else
            System.out.print("Действие не выполнено!\n");
    }

    public void viewFriend(Account account){
        if(account.getProfile().getFriends().length()!=0){
            System.out.print("Друзья:\n");
            System.out.print(account.getProfile().getFriends());
        } else {
            System.out.print("У Вас нет друзей\n");
        }
    }

    public void viewAllAccounts(){
        System.out.print(accountService.viewAllAccounts());
    }

    public void deleteAccount(String password,Account account){
        accountService.deleteAccount(account.getLogin(),password);
        selection = 0;
    }
}
