public class Lot {
    static int counter = 1;
    private int lotNumber;
    private String seller;
    private String buyer;
    private String description;
    private String type;
    private double reservePrice;
    private int allocatedAuction;
    private double hammerPrice;
    private String state;
    private double tax;
    private double amountSellerReceive;

    public Lot(String seller, String description, String type, double reservePrice){
        setLotNumber(counter);
        counter++;
        setSeller(seller);
        setDescription(description);
        setType(type);
        setReservePrice(reservePrice);
        setState("unassigned");
        setTax(0);
        setBuyer("No buyer yet");
        setAllocatedAuction(0);
    }

    public int getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(int lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(double reservePrice) {
        this.reservePrice = reservePrice;
    }

    public int getAllocatedAuction() {
        return allocatedAuction;
    }

    public void setAllocatedAuction(int allocatedAuction) {
        this.allocatedAuction = allocatedAuction;
    }

    public double getHammerPrice() {
        return hammerPrice;
    }

    public void setHammerPrice(double hammerPrice) {
        this.hammerPrice = hammerPrice;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getAmountSellerReceive() {
        return amountSellerReceive;
    }

    public void setAmountSellerReceive(double amountSellerReceive) {
        this.amountSellerReceive = amountSellerReceive;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    private double commission;

    public void allocateToAuction(int auctionId){
        setAllocatedAuction(auctionId);
        setState("assigned");
    }

    public void bid(double biddingPrice, String [] buyer, String itemDescription){
        if(enterHammerPrice(biddingPrice)){
            setHammerPrice(biddingPrice);
            setBuyer(buyer[0]);
            setState("sold");
            calculateAmount();
            System.out.println("Congratulations! You have successfully purchased the " + itemDescription + " at auction.");
        }else{
            System.out.println("Bidding price is less than hammer price. So, item is not sold.");
        }
    }

    private boolean enterHammerPrice(double hammerPrice){
        if(hammerPrice >= getReservePrice()){
            return true;
        }
        return false;
    }

    private void calculateAmount(){
        setCommission(.10 * getHammerPrice());
        if(getType().equals("Paintings")){
            setTax(.20 * getHammerPrice());
        }
        setAmountSellerReceive(getHammerPrice() - (getCommission() + getTax()));
    }

    public String listLot(){
        String output = "ID: " + getLotNumber() + " - ";
        output += "Type: " + getType() + " - ";
        output += "Description: " + getDescription() + " - ";
        output += "State: " + getState() + " - ";
        if(getAllocatedAuction() == 0){
            output += "Allocated Auction: None - ";
        }else{
            output += "Allocated Auction: " + getAllocatedAuction() + " - ";
        }
        output += "Reserve Price: " + getReservePrice() + " - ";
        output += "Hammer Price: " + getHammerPrice();
        return output;
    }

    public void printDetails(){
        System.out.println("---------- Details of Lot " + getLotNumber() + " ----------");
        System.out.println("Seller: " + getSeller());
        if(getState().equals("sold")) {
            System.out.println("Buyer: " + getBuyer());
        }
        System.out.println("Description: " + getDescription());
        System.out.println("Type: " + getType());
        System.out.println("Reserve Price: $" + getReservePrice());
        System.out.println("Allocated Auction: " + getAllocatedAuction());
        System.out.println("State: " + getState());
        if(getState().equals("sold")){
            System.out.println("Hammer Price: $" + getHammerPrice());
            System.out.println("Tax: $" + getTax());
            System.out.println("Commission: $" + getCommission());
            System.out.println("Amount seller receive: $" + getAmountSellerReceive());
        }
        System.out.println("--------------------------------------");
    }

}
