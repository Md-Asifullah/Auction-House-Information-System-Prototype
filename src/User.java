import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public User(int id, String firstName, String lastName, String username, String password){
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setPassword(password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String logIn(Admin admin, ArrayList<GeneralUser> users, String [] loggedInUserName){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your username");
        String username = scanner.nextLine();
        // Basic data validation where username cannot be empty
        while(username.equals("")){
            System.out.println("Please enter a valid username. It cannot be empty.");
            username = scanner.nextLine();
        }

        System.out.println("Please enter your password");
        String password = scanner.nextLine();
        // Basic data validation where password cannot be empty
        while(password.equals("")){
            System.out.println("Please enter a valid password. It cannot be empty.");
            password = scanner.nextLine();
        }

        // Authenticate

        if(admin.getUsername().equals(username) && admin.getPassword().equals(password)){
            System.out.println("Welcome " + admin.getUsername());
            loggedInUserName[0] = "admin";
            return "admin";
        }

        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)){
                System.out.println("Welcome " + users.get(i).getUsername());
                loggedInUserName[0] = users.get(i).getUsername();
                return "general_user";
            }
        }

        System.out.println("Authentication failed");
        System.out.println("Enter yes to attempt login again. Enter no to exit");
        String operation = scanner.nextLine().toLowerCase();
        while(!(operation.equals("yes") || operation.equals("no"))){
            System.out.println("Please enter valid operation. Enter yes or no");
            operation = scanner.nextLine().toLowerCase();
        }
        if(operation.equals("no")){
            return "unassigned";
        }
        if(operation.equals("yes")){
            String logInReturn = logIn(admin, users, loggedInUserName);
            return logInReturn;
        }

        return "unassigned";
    }
}
