package Account;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AccountDao {
    private ObjectMapper mapper = new ObjectMapper();

    public ArrayList<Account> readAccounts(){
        ArrayList<Account> accounts;
        try {
            accounts = mapper.readValue(new File("Account.json"), new TypeReference<ArrayList<Account>>(){});
        } catch (IOException e) {
            accounts = new ArrayList<>();
            e.printStackTrace();
        }
        return accounts;
    }

    public String writeAccount(ArrayList<Account> accounts){
        String message = "";
        try{
            mapper.writeValue(new File("Account.json"),accounts);
            message = "OK";
        } catch (IOException e){
            e.printStackTrace();
        }
        return message;
    }

    public void renewAccounts(){ //to change json file after adding, deleting account fields
        ArrayList<Account> accounts;
        try {
            accounts = mapper.readValue(new File("Account.json"), new TypeReference<ArrayList<Account>>(){});
            mapper.writeValue(new File("Account.json"),accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
