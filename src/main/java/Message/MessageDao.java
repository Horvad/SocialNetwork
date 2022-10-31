package Message;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class MessageDao {
    private ObjectMapper mapper = new ObjectMapper();

    public LinkedList<Message> readMessages(){
        LinkedList<Message> messages = new LinkedList<Message>();
        try {
            messages = mapper.readValue(new File("Message.json"), new TypeReference<LinkedList<Message>>(){});
        } catch (IOException e) {
            messages = new LinkedList<Message>();
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
}
