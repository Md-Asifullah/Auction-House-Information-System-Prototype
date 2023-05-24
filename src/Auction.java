import java.util.Date;

public class Auction {
    static int counter = 1;
    private int id;
    private int auctionNumber;
    private Date date;
    private String slot;
    private String state;

    public Auction(int auctionNumber, Date date, String slot, String state){
        setId(counter);
        setAuctionNumber(auctionNumber);
        setDate(date);
        setSlot(slot);
        setState(state);
        counter++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuctionNumber() {
        return auctionNumber;
    }

    public void setAuctionNumber(int auctionNumber) {
        this.auctionNumber = auctionNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String intMonthToString(int i){
        String output;
        switch(i) {
            case 0:
                output = "Jan";
                break;
            case 1:
                output = "Feb";
                break;
            case 2:
                output = "Mar";
                break;
            case 3:
                output = "Apr";
                break;
            case 4:
                output = "May";
                break;
            case 5:
                output = "Jun";
                break;
            case 6:
                output = "Jul";
                break;
            case 7:
                output = "Aug";
                break;
            case 8:
                output = "Sep";
                break;
            case 9:
                output = "Oct";
                break;
            case 10:
                output = "Nov";
                break;
            default:
                output = "Dec";
        }
        return output;
    }

    public String listAuction(){
        String output = "ID: " + getAuctionNumber() + " - ";
        output += "Date: " + getDate().getDate() + "-" + this.intMonthToString((getDate().getMonth())) + "-" + getDate().getYear() + " - ";
        output += "Slot: " + getSlot() + " - ";
        output += "State: " + getState();
        return output;
    }

    public void printAllDetails(){

        System.out.println("---------- Details of Auction " + getAuctionNumber() + " ----------");
        System.out.println("Date: " + getDate().getDate() + "-" + this.intMonthToString((getDate().getMonth())) + "-" + getDate().getYear());
        System.out.println("Slot: " + getSlot());
        System.out.println("State: " + getState());

        System.out.println("--------------------------------------");
    }
}
