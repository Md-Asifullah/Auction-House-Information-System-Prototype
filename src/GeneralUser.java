public class GeneralUser extends User{

    private String type;

    public GeneralUser(int id, String firstName, String lastName, String username, String password) {
        super(id, firstName, lastName, username, password);
        setType("general_user");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
