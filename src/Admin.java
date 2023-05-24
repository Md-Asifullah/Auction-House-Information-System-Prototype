import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Admin extends User{
    private String type;
    public Admin(int id, String firstName, String lastName, String username, String password) {
        super(id, firstName, lastName, username, password);
        setType("admin");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addNewLot(ArrayList<Lot> lots){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the details to create a new lot.");
        System.out.println("Who is the seller of the lot?");
        String seller = Main.getStringfromUser();
        System.out.println("Please provide a description of the lot.");
        String description = Main.getStringfromUser();
        System.out.println("Please enter type of the lot. Is it a Jewellery, Fine china or Paintings?");
        String type = Main.getStringfromUser();
        System.out.println("Please enter reserve price of the lot.");
        Double reservePrice = Main.getDoublefromUser();

        Lot newLot = new Lot(seller,description,type,reservePrice);
        lots.add(newLot);

        System.out.println("New lot has been successfully added.");
    }

    Auction auction01 = new Auction(1, new Date(2021, 0, 14),"am","current");

    public void addNewAuction(ArrayList<Auction> auctions){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the details to create a new auction.");
        System.out.println("Please enter auction number");
        int auctionNumber = Main.getIntegerfromUser();
        int date, month, year;
        System.out.println("Please enter date in number");
        date = Main.getIntegerfromUser();
        System.out.println("Please enter month in number");
        month = Main.getIntegerfromUser();
        System.out.println("Please enter year in number");
        year = Main.getIntegerfromUser();
        scanner.nextLine();
        System.out.println("Please enter if the slot is am or pm");
        String slot = Main.getStringfromUser();
        System.out.println("Please enter state of the auction");
        String state = Main.getStringfromUser();
        Date enteredDate = new Date(year, month, date);
        Auction newAuction = new Auction(auctionNumber, enteredDate, slot, state);
        auctions.add(newAuction);

        System.out.println("New Auction has been successfully added.");
    }

    public void assignLotToAuction(Lot lot, int auctionNumber){
        lot.setAllocatedAuction(auctionNumber);
        lot.setState("assigned");
    }
}
