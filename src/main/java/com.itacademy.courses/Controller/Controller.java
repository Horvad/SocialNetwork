package com.itacademy.courses.Controller;//Form.Form of communication using
//Т.к классы Controller.Controller будут скорее всего передаланы под Post и Get запросы, я решил не заморачиваться по поводу красоты кода.

import com.itacademy.courses.Model.Account;
import com.itacademy.courses.Servise.AccountService;

import java.util.Scanner;

//в контроллере будет бесконечный цикл для добаления и удаления пользователей,
//авторизация, а также вывод доски для чата
public class Controller {
    private final static Scanner in = new Scanner(System.in);
    private static int selection = -1;
    public static void main(String[] args){
        while (selection!=0){
            System.out.print(
                    "1. Авторизация\n" +
                    "2. Создание нового аккаунта\n" +
                    "0. Вход из программы.\n" +
                    "Сделайте выбор: ");
            selection = in.nextInt();
            if(selection==0) break;
            if(selection>2||selection<0) System.out.print("\n\n\nНе верно введено значение.\n");
            switch (selection){
                case 1:
                    authorization();
                    selection = -1;
                    break;
                case 2:
                    createNewAccount();
                    break;
            }
        }
    }

    //Authorization
    public static void authorization(){
        System.out.print("Введите логин: ");
        String login = in.next();
        System.out.print("Введите пароль: ");
        String password = in.next();
        AccountService accountService = new AccountService();
        Account account = accountService.loginAccount(login,password);
        if(accountService.loginAccount(login,password)==null){
            System.out.print("\n\n\nНе верный логин или пароль!!!\n");
        } else {
            while (selection!=0){
                System.out.print("\n\n\n");
                if(account.getNewMessageNames()!=null)
                    System.out.print("У Вас "+account.getNewMessageNames().size()+" новых сообщений\n");
                System.out.print(
                            "1. Перейти в раздел аккаунтов.\n" +
                            "2. Перейти в раздел сообщений.\n" +
                            "0. Возврат в предыдущее меню.\n" +
                            "Сделайте выбор: ");
                selection = in.nextInt();
                if(selection>2||selection<0) System.out.print("\n\n\nНе верно введено значение.\n");
                switch (selection){
                    case 1:
                        ControllerAccount controllerAccount = new ControllerAccount();
                        controllerAccount.viewAccountForm(account);
                        break;
                    case 2:
                        ControllerMessage controllerMessage = new ControllerMessage();
                        controllerMessage.viewMessageForm(account);
                        break;
                }
            }
        }
    }

    public static void createNewAccount(){
        System.out.print("Введите Login: ");
        String login = in.next();
        System.out.print("Введите пароль: ");
        String password = in.next();
        AccountService accountService = new AccountService();
        if(accountService.createNewAccount(login,password)){
            System.out.print("\n\n\nАккаунт создан\n");
        } else
            System.out.print("\n\n\nАккаунт с таким инменем сущуствует.\n");
    }

    public static void save(Account account){
        AccountService accountService = new AccountService();
        accountService.save(account);
    }

}
