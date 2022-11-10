package com.itacademy.courses.Dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itacademy.courses.Model.Account;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AccountDao {
    private final ObjectMapper mapper = new ObjectMapper();
    private final String dirFile = "\\SocialNetwork\\src\\main\\java\\com.itacademy.courses\\Repository\\Account.json";

    public ArrayList<Account> readAccounts(){
        ArrayList<Account> accounts;  //если я не объявлю эту переменную, то я в случае пустого файла верну null, а так я верну пустой лист
        try {
            accounts = mapper.readValue(new File(dirFile), new TypeReference<ArrayList<Account>>(){});
        } catch (IOException e) {
            accounts = new ArrayList<>();
            e.printStackTrace();
        }
        return accounts;
    }

    public String writeAccount(ArrayList<Account> accounts){
        String message = "";
        try{
            mapper.writeValue(new File(dirFile),accounts);
            message = "OK";
        } catch (IOException e){
            e.printStackTrace();
        }
        return message;
    }

    public void renewAccounts(){ //to change json file after adding, deleting account fields
        ArrayList<Account> accounts;
        try {
            accounts = mapper.readValue(new File(dirFile), new TypeReference<ArrayList<Account>>(){});
            mapper.writeValue(new File(dirFile),accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
