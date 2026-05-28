import java.time.LocalTime;

public class Message {
    String username;
    String texts;
    LocalTime times;

    public Message(String user, String text, LocalTime time){
        username = user;
        texts = text;
        times = time;
    }
}
