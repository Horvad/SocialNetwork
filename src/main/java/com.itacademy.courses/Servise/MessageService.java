package com.itacademy.courses.Servise;


import com.itacademy.courses.Dao.MessageDao;
import com.itacademy.courses.Interfaces.MessageServiceImpl;
import com.itacademy.courses.Model.Message;

import java.util.ArrayList;

public class MessageService implements MessageServiceImpl {
    private MessageDao messageDao = new MessageDao();

    @Override
    public boolean sendMessageForAll(String user, String message) {
        Message sendMessage = new Message(user, "", message);
        boolean send = messageDao.writeMessageForUser(sendMessage, "", "Message");
        return send;
    }

    @Override
    public boolean sendMessageForUser(String login, String forUser, String message) {
        Message sendMessage = new Message(login, forUser, message);
        boolean send = messageDao.writeMessageForUser(sendMessage, forUser, login);
        messageDao.writeMessageForUser(sendMessage, login, forUser);
        return send;
    }

    @Override
    public ArrayList<Message> viewMessageFromUser(String login, String fromUser) {
        ArrayList<Message> messages = messageDao.readMessages(login, fromUser);
        return messages;
    }

    public ArrayList<Message> viewMessageAll(){
        ArrayList<Message> messages = messageDao.readMessages("","Message");
        return messages;
    }

}