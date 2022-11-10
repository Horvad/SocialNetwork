package com.itacademy.courses.Interfaces;

import com.itacademy.courses.Model.Message;
import java.util.ArrayList;

public interface MessageServiceImpl {
    public boolean sendMessageForUser(String login, String forUser, String message);
    public boolean sendMessageForAll(String user, String massage);
    public ArrayList<Message> viewMessageFromUser(String login, String fromUser);
}
