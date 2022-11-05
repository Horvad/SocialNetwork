import Message.MessageDao;
import Message.MessageUsers;

public class TEST {
    public static void ttt(){
        MessageDao messageDao = new MessageDao();
        MessageUsers messageUsers = new MessageUsers("111","222","444444");
        messageDao.addMessageForUser(messageUsers,"222","111");
        messageDao.addMessageForUser(messageUsers,"111","222");
    }
}
