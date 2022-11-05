package Message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class MessageDao {
    private final ObjectMapper mapper = new ObjectMapper();

    public LinkedList<Message> readMessages(){
        LinkedList<Message> messages;
        try {
            messages = mapper.readValue(new File("Message.json"), new TypeReference<LinkedList<Message>>(){});
        } catch (IOException e) {
            messages = new LinkedList<>();
            e.printStackTrace();
        }
        return messages;
    }

    public String writeMessages(Message newMessage){
        String message = "";
        LinkedList<Message> messages = readMessages();
        messages.add(newMessage);
        try{
            mapper.writeValue(new File("Message.json"),messages);
            message = "OK";
        } catch (IOException e){
            e.printStackTrace();
        }
        return message;
    }

    public String viewMassages(String login){

        return "";
    }

    public boolean addMessageForUser(MessageUsers messageUsers, String directory, String user){
        String directoryForUser = "\\SocialNetwork\\Chats\\"+directory;
        if(createNewFileOrSearch(directoryForUser,user+".json")){
            LinkedList<MessageUsers> messages;
            String dir = directoryForUser+"\\"+user+".json";
            try {
                messages = mapper.readValue(new File(dir), new TypeReference<LinkedList<MessageUsers>>(){});
            }catch (IOException e){
                messages = new LinkedList<>();
                e.printStackTrace();
            }
            messages.add(messageUsers);
            try {
                mapper.writeValue(new File(dir),messageUsers);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return true;
    }

    private boolean createNewFileOrSearch(String directory, String fileName){
        boolean fileIsFound = true;
        File file = new File(directory,fileName){};
        try {
            if(!file.isDirectory()) {
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
