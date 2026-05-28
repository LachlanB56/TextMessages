import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import java.io.BufferedReader;

public class MessageTerminal {
    public static void main(String[] args) throws IOException {
        Scanner myscan = new Scanner(System.in);
        boolean open = true;

        while(open) {
            String username;
            username = checkentry();
            boolean entry = true;


            while(entry) {
                System.out.println("Press 1 to send a text, 2 to view all texts and 3 to exit");
                int i = myscan.nextInt();
                if (i == 1) {
                    sendtext(username);
                } else if (i == 2) {
                    readtext();
                } else if(i == 3){
                    entry = false;
                    open = exit();
                } else {
                    System.out.println("not a valid action try again");
                }
            }
        }
    }



    public static String checkentry() throws IOException {
        Scanner myscan = new Scanner(System.in);
        System.out.println("Welcome to the Chat terminal please press 1 to enter your user/password or 2 to create an account");
        boolean entry = true;
        String user = "n";
        while(entry) {
            int i = myscan.nextInt();
            if (i == 1) {
                user = login();
                entry = false;
            } else if (i == 2) {
                createaccount();
                entry = false;
            } else {
                System.out.println("Not a valid action please enter 1 to login or 2 to create account");
            }
        }
        return user;
    }


    public static void createaccount() throws IOException {
        File accounts = new File("Accounts.txt");
        Scanner myscan = new Scanner(System.in);

        System.out.println("Please enter a username");
        String username = myscan.nextLine();

        System.out.println("Please create a password");
        String password = myscan.nextLine();

        System.out.println("please confirm password");
        String confirmpassword = myscan.nextLine();

            while (!password.equals(confirmpassword)){
                System.out.println("passwords do not match please try again");

                System.out.println("Please create a password");
                password = myscan.nextLine();

                System.out.println("please confirm password");
                confirmpassword = myscan.nextLine();
        }
        try {
            FileWriter mywriter = new FileWriter("Accounts.txt", true);
            mywriter.write(username + "|" + password + System.lineSeparator());
            mywriter.close();
        }catch(IOException e){
            System.out.println("File not found");
        }
        System.out.println("Thank you, your username and password have been recorded!");
    }



    public static String login() throws FileNotFoundException {
        String Line;
        List<String> accounts = new ArrayList<>();
        Scanner myscan = new Scanner(System.in);
        System.out.println("Please enter your username");
        String username = myscan.nextLine();
        System.out.println("Please enter your password");
        String password = myscan.nextLine();

        try {
            BufferedReader myread = new BufferedReader(new FileReader("Accounts.txt"));

            while((Line = myread.readLine()) != null){
                accounts.add(Line);
            }
}
        catch(IOException e){
            System.out.println("Sorry File not found");
}

        while(!accounts.contains(username + "|" + password)){
        System.out.println("Username/password combination does not exist try again");
        System.out.println("Please enter your username");
        username = myscan.nextLine();

        System.out.println("Please enter your password");
        password = myscan.nextLine();
        }

        System.out.print("Welcome " + username + "!" + System.lineSeparator());
        return username;
    }



    public static void sendtext(String username) throws IOException {
        Scanner myscan = new Scanner(System.in);
        LocalTime now = LocalTime.now();

        System.out.println("What message would you like to send to the chat?");
        String text = myscan.nextLine();

        Message texts = new Message(text, username, now);

        try {
            FileWriter writer = new FileWriter("texts.txt", true);
            writer.write(texts.username + "|" + texts.texts + "[" + texts.times + "]" + System.lineSeparator());
            writer.close();
        }catch (IOException e){
            System.out.println("Error file not found");
        }

    }
    public static void readtext()throws IOException{
        List<String> texts = new ArrayList<>();
        String line = "n";
       try {
           BufferedReader myread = new BufferedReader(new FileReader("texts.txt"));
           while((line = myread.readLine()) != null){
               texts.add(line);
           }
           for (int i = 0; i < texts.size(); i++){
               System.out.println(texts.get(i));
           }
       }catch(IOException e){
           System.out.println("text file could not be found");
       }



    }
    public static boolean exit(){
         return false;
    }
}
