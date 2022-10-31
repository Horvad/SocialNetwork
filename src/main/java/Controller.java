//Form of communication using
//Т.к классы Controller будут скорее всего передаланы под Post и Get запросы, я решил не заморачиваться по поводу красоты кода.
import Account.Account;
import Account.AccountService;
import Message.MessageService;

import java.util.Scanner;

//в контроллере будет бесконечный цикл для добаления и удаления пользователей,
//авторизация, а также вывод доски для чата
public class Controller {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args){
        while (true){
            int selection = -1;
            System.out.print("Выберите действие:\n1.Создание нового аккаунта\n2.Авторизация\n" +
                    "3.Удаление аккаунта.\n0.Завершение программы\nВведите значение: ");
            selection = in.nextInt();
            if(selection==0){
                break;
            }
            String login = "";
            String password = "";
            if(selection<0||selection>3){
                System.out.println("\nНе верный вввод данных!!!\n");
            } else {
                System.out.print("Введите логин: ");
                login = in.next();
                System.out.print("Введите пароль: ");
                password = in.next();
            }
            ////1. Create new account, 2. Authorization(After authorization, the form for editing an account and sending
            // messages). Delete account. O. Close program
            switch (selection){
                case 1:
                    createNewAccount(login,password);
                    break;
                case 2:
                    authorization(login,password);
                    break;
                case 3:
                    deleteAccount(login,password);
            }
        }
    }

    //
    public static void createNewAccount(String login, String password){
        AccountService accountService = new AccountService();
        String newAccount = accountService.createNewAccount(login,password);
        if(newAccount.equals("OK")){
            System.out.println("\n\n\nАккаунт успешно добален\n");
        } else {
            System.out.println("\n\n\n"+newAccount);
        }
    }


    //Authorization and create form edit account, send message
    public static void authorization(String login, String password){
        AccountService accountService = new AccountService();
        Account account = accountService.loginAccount(login,password);
        if(account!=null){
            System.out.print("\n\n\nАвторизация прошла успешно!\n");
            authorizationCase(account);
        } else {
            System.out.print("\n\n\nНе верный логин или пароль!\n");
        }
    }


    public static void deleteAccount(String login, String password){
        AccountService accountService = new AccountService();
        String deleteAccount = accountService.deleteAccount(login,password);
        System.out.print("\n\n\n"+deleteAccount+"\n");
    }

    //Form authorization and send message
    public static void authorizationCase(Account account){
        while (true) {
            int selection = -1;
            System.out.print("Выберите действие:\n1.Просмотр информации о аккаунте\n2.Редактирование информации о профиле акккаунта\n" +
                    "3.Просмотр чата.\n4.Отправиль сообжение в чат.\n0.Возврат в предыдущее меню\nСделайте выбор: ");
            selection = in.nextInt();
            if(selection==0){
                System.out.print("\n\n\n");
                break;
            }
            if(selection<0||selection>4){
                System.out.println("\nНе верный вввод данных!!!\n");
                continue;
            }
            ControllerEditAccount controllerEditAccount = new ControllerEditAccount();
            MessageService messageService = new MessageService();
            //1. View account information. 2. Edit Account(Create new ControllerEditAccount). 3. View chat(Create
            // controller View Message). 4. Send message(Create controller View Message)
            switch (selection){
                case 1:
                    controllerEditAccount.viewAccount(account);
                    break;
                case 2:
                    Account editAccount;
                    editAccount = controllerEditAccount.editAccount(account);
                    while (true){
                        System.out.print("Сохранить (y/n):");
                        char selectionSave = (in.next()).charAt(0);
                        if(selectionSave=='y'){
                            account.clone(editAccount);
                            AccountService accountServiceDao = new AccountService();
                            accountServiceDao.save(account);
                            break;
                        }
                        if(selectionSave=='n'){
                            break;
                        } else {
                            System.out.print("Не верный выбор!!!\n");
                        }
                    }
                    break;
                case 3:
                    messageService.viewMessages();
                    break;
                case 4:
                    while (true){
                        System.out.print("Для выхода наберите 'exit': ");
                        String message = in.next();
                        if(message.equals("exit")){
                            break;
                        } else {
                            messageService.sendMessage(account.getLogin(),message);
                        }
                    }
                    break;
            }
        }
    }
}
