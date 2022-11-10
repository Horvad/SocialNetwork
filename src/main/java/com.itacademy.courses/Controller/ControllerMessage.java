package com.itacademy.courses.Controller;

import com.itacademy.courses.Model.Account;
import com.itacademy.courses.Model.Message;
import com.itacademy.courses.Servise.AccountService;
import com.itacademy.courses.Servise.MessageService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


//Добавить сохранение в методы!!!!!!!!!!!!!
public class ControllerMessage {
    private final Scanner in = new Scanner(System.in);
    private final MessageService messageService = new MessageService();

    private int selection = -1;

    public void viewMessageForm(Account account){
        while (selection!=0){
            System.out.print("" +
                    "1. Просмотр чата\n" +
                    "2. Просмотр новых сообщений\n" +
                    "3. Просмотр сообщений с пользователем\n" +
                    "4. Отправка сообщения\n" +
                    "5. Оправка сообщения пользователю\n" +
                    "0. Возврат в предыдущее меню\n" +
                    "Сделайта выбор: ");
            selection = in.nextInt();
            switch (selection){
                case 1:
                    viewChat();
                    break;
                case 2:
                    viewNewMassage(account);
                    break;
                case 3:
                    viewChatWithUser(account);
                    break;
                case 4:
                    sendNewMassage(account);
                    break;
                case 5:
                    sendNewMassageForUser(account);
                    break;
            }
        }
    }

    private void viewChat(){
        System.out.println("Общий чат:");
        ArrayList<Message> messages = messageService.viewMessageAll();
        for(Message message: messages){
            System.out.println(message.toString());
        }
    }

    private void viewNewMassage(Account account){
        String loginFromMessage = "";
        while (!loginFromMessage.equals("exit")){
            LinkedList<String> loginsFromNewMassage = account.getNewMessageNames();
            if(loginsFromNewMassage.size()<1){
                System.out.print("У вас нет новых сообщений!");
                return;
            }
            System.out.print("У Вас соббщения от ");
            for(String logins : loginsFromNewMassage){
                System.out.print(logins+" ");
            }
            System.out.print("\nВведите логин пользовотеля, сообщения которого вы хотите прочитать(для выхода набетирое 'exit'): ");
            loginFromMessage = in.next();
            if(loginFromMessage.equals("exit")){
                return;
            }
            boolean loginTrue = loginsFromNewMassage.remove(loginFromMessage);
            if(loginTrue) {
                ArrayList<Message> messages = messageService.viewMessageFromUser(account.getLogin(), loginFromMessage);
                for(Message message : messages){
                    System.out.print(message.toString()+"\n");
                }
            } else {
                System.out.println("Не верно введен логин!");
            }
        }
        Controller.save(account);
    }

    private void viewChatWithUser(Account account){
        System.out.print("ведите login пользователя: ");
        String loginFrom = in.next();
        AccountService accountService = new AccountService();
        Account accountFrom = accountService.viewAccount(loginFrom);
        if(accountFrom==null){
            System.out.print("Не верно введено имя пользователя!!!\n");
            return;
        }
        ArrayList<Message> messages = messageService.viewMessageFromUser(account.getLogin(),accountFrom.getLogin());
        if(messages.size()<1){
            System.out.print("У Вас нет чата с этим пользователем!\n");
        } else {
            for(Message message : messages){
                System.out.println(message.toString());
            }
        }
    }


    private void sendNewMassage(Account account){
        System.out.print("Введите сообщение: ");
        String mes = in.next();
        messageService.sendMessageForAll(account.getLogin(),mes);
    }

    private void sendNewMassageForUser(Account account){
        System.out.print("Введите логин пользователя: ");
        String loginFor = in.next();
        AccountService accountService = new AccountService();
        Account accountFor = accountService.viewAccount(loginFor);
        if(accountFor==null){
            System.out.print("Не верно введен логин!\n");
            return;
        }
        accountFor.addNewMessageName(account.getLogin());
        System.out.print("Введите сообщение: ");
        String mes = in.next();
        messageService.sendMessageForUser(account.getLogin(),accountFor.getLogin(),mes);
        Controller.save(accountFor);
    }
}
