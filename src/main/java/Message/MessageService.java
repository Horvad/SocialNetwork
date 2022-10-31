package Message;

import java.util.LinkedList;

public class MessageService {
    private MessageDao messageDao = new MessageDao();
    public void sendMessage(String login, String message){
        Message obMessage = new Message(login,message);
        messageDao.writeMessages(obMessage);
    }

    public void viewMessages(){
        LinkedList<Message> messages = messageDao.readMessages();
        for(Message message: messages){
            System.out.println(message.toString());
        }
    }
}
