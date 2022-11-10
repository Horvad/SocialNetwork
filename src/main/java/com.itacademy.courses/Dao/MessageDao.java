package com.itacademy.courses.Dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itacademy.courses.Model.Message;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MessageDao {
    private final ObjectMapper mapper = new ObjectMapper();
    private final String directory = "\\SocialNetwork\\src\\main\\java\\com.itacademy.courses\\Repository\\";

    public ArrayList<Message> readMessages(String user, String fromUser){
        ArrayList<Message> messages = null; //если я не объявлю эту переменную, то я в случае пустого файла верну null, а так я верну пустой лист
        String directoryForUser = this.directory+user;
        createNewFileOrSearch(directoryForUser,fromUser+".json");
        String dirFile = directoryForUser+"\\"+fromUser+".json";
        try {
            messages = mapper.readValue(new File(dirFile), new TypeReference<ArrayList<Message>>(){});
        } catch (IOException e) {
            messages = new ArrayList<>();
            e.printStackTrace();
        }
        return messages;
    }

    public boolean writeMessageForUser(Message messageUsers, String user, String fromUser){
        ArrayList<Message> messages = readMessages(user,fromUser);
        messages.add(messageUsers);
        String dir = this.directory+"\\"+user+"\\"+fromUser+".json";
        try {
            mapper.writeValue(new File(dir),messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean createNewFileOrSearch(String directory, String fileName){
        boolean fileIsFound = true;
        File file = new File(directory,fileName){};
        try {
            if(!file.exists()) {
                File fileDir = new File(directory);
                fileIsFound = fileDir.mkdir();
            }
            if(!file.isFile()){
                fileIsFound = file.createNewFile();
            }
        } catch (IOException e){
            fileIsFound = false;
            e.printStackTrace();
        }
        return fileIsFound;
    }
}
