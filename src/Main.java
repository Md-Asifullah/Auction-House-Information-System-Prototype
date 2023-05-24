import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    // Fix error on line 346

    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Auction> auctions = new ArrayList<Auction>();
    public static ArrayList<Lot> lots = new ArrayList<Lot>();
    public static Admin admin;
    public static ArrayList<GeneralUser> users = new ArrayList<GeneralUser>();

    public static String loggedInUserType = "unassigned";
    public static String [] loggedInUserName = {"username"};

    public static void main(String[] args) {


        System.out.println("Welcome to the Auction House Information System");

        initializeProgram();

        // System.out.println("loggedInUserType: " + loggedInUserType);
        // System.out.println("TEST 01: " + loggedInUserName[0]);

        // General User Functionalities

        boolean keepProgramActive = true;

        while(keepProgramActive){
            System.out.println("Do you want log in? Type 'yes' to continue. Hit return to exit program");
            String userInput = scanner.nextLine().toLowerCase();
            if(userInput.equals("yes")){
                loggedInUserType = User.logIn(admin, users, loggedInUserName);
                if(loggedInUserType.equals("general_user")){
                    showMenuForGeneralUser();
                }
                // Admin Functionalities
                if(loggedInUserType.equals("admin")){
                    showMenuForAdmin();
                }
            }else{
                keepProgramActive = false;
            }
        }

    }

    public static void showMenuForGeneralUser(){
        System.out.println("Please select an operation");
        System.out.println("1: View all lots");
        System.out.println("2: Print details of a specific lot");
        System.out.println("3: View all Auctions");
        System.out.println("4: Print details of a specific auction");
        System.out.println("5: Print allocated lots under a current auction");
        System.out.println("6: Bid");
        System.out.println("0: Log out program");
        switch(selectOption()){
            case 0:
                System.out.println("Program ended. You have been logged out.");
                break;
            case 1:
                listAllLots();
                haltAndShowGeneralUserMenu();
                break;
            case 2:
                printLotDetails();
                haltAndShowGeneralUserMenu();
                break;
            case 3:
                listAllAuctions();
                haltAndShowGeneralUserMenu();
                break;
            case 4:
                printAuctionDetails();
                haltAndShowGeneralUserMenu();
                break;
            case 5:
                printLotsUnderCurrentAuction();
                haltAndShowGeneralUserMenu();
                break;
            case 6:
                bid();
                haltAndShowGeneralUserMenu();
                break;
            default:
                System.out.println("Invalid operation selected. Please enter operation from 0 to 6.");
                System.out.println("Press return to continue");
                scanner.nextLine();
                showMenuForGeneralUser();
        }
    }

    public static void showMenuForAdmin(){
        System.out.println("Please select an operation");
        System.out.println("1: View all lots");
        System.out.println("2: Print details of a specific lot");
        System.out.println("3: Add a new lot");
        System.out.println("4: View all auctions");
        System.out.println("5: Print details of a specific auction");
        System.out.println("6: Add a new auction");
        System.out.println("7: Assign a lot to an auction");
        System.out.println("8: Print allocated lots under a current auction");
        System.out.println("9: Delete a lot");
        System.out.println("10: Delete an auction");
        System.out.println("11: End an auction");
        System.out.println("0: Log out");
        switch(selectOption()){
            case 0:
                System.out.println("Program ended. You have been logged out.");
                break;
            case 1:
                listAllLots();
                haltAndShowAdminMenu();
                break;
            case 2:
                printLotDetails();
                haltAndShowAdminMenu();
                break;
            case 3:
                admin.addNewLot(lots);
                haltAndShowAdminMenu();
                break;
            case 4:
                listAllAuctions();
                haltAndShowAdminMenu();
                break;
            case 5:
                printAuctionDetails();
                haltAndShowAdminMenu();
                break;
            case 6:
                admin.addNewAuction(auctions);
                haltAndShowAdminMenu();
                break;
            case 7:
                assignLotToAuction();
                haltAndShowAdminMenu();
                break;
            case 8:
                printLotsUnderCurrentAuction();
                haltAndShowAdminMenu();
                break;
            case 9:
                deleteLot();
                haltAndShowAdminMenu();
                break;
            case 10:
                deleteAuction();
                haltAndShowAdminMenu();
                break;
            case 11:
                endAuction();
                haltAndShowAdminMenu();
                break;
            default:
                System.out.println("Invalid operation selected. Please enter operation from 0 to 11.");
                System.out.println("Press return to continue");
                scanner.nextLine();
                showMenuForAdmin();
        }
    }

    public static void bid(){
        System.out.println("Below are the auctions which are active at the moment.");
        boolean auctionNotFound = true;
        for (int i = 0; i < auctions.size(); i++) {
            if(auctions.get(i).getState().equals("current")){
                auctionNotFound = false;
                System.out.println(auctions.get(i).listAuction());
            }
        }
        if(auctionNotFound){
            System.out.println("No active auctions found. Please try again later");
            return;
        }
        System.out.println("Please enter which auction you want to enter");
        printLotsUnderCurrentAuction();
        System.out.println("Do you want to bid for a lot? If so then type 'yes' or else hit return");
        String operation = scanner.nextLine().toLowerCase();
        if(operation.equals("yes")){
            enterHammerPrice();
        }
    }

    public static void enterHammerPrice(){
        System.out.println("Please enter lot number.");
        int lotNumber = getIntegerfromUser();
        boolean notFound = true;
        for (Lot selectedLot : lots) {
            if ((selectedLot.getLotNumber() == lotNumber) && (selectedLot.getState().equals("assigned"))) {
                notFound = false;
                double reservePrice = selectedLot.getReservePrice();
                String itemDescription = selectedLot.getDescription();
                System.out.println("Please enter your hammer price. The reserve price of the item is $" + reservePrice);
                double biddingPrice = getDoublefromUser();
                selectedLot.bid(biddingPrice, loggedInUserName, itemDescription);
            }
        }
        if(notFound){
            System.out.println("Your requested lot number cannot be found. Please check whether you entered a valid lot number or if it is assigned under any current auction.");
        }
    }
    public static void endAuction(){
        System.out.println("Please enter auction number that you want to end");
        int auctionNumber = getIntegerfromUser();
        boolean notFound = true;
        for (int i = 0; i < auctions.size(); i++) {
            if(auctions.get(i).getAuctionNumber() == auctionNumber){
                auctions.get(i).setState("past");
                notFound = false;
                System.out.println("You have ended Auction " + auctionNumber + " successfully.");
            }
        }
        if(notFound){
            System.out.println("The auction number you entered was not found.");
        }
    }

    public static void deleteAuction(){
        System.out.println("Please enter auction number that you want to delete");
        int auctionNumber = getIntegerfromUser();
        int lotDeleted = 0;
        boolean notFound = true;
        for (int i = 0; i < auctions.size(); i++) {
            if(auctions.get(i).getAuctionNumber() == auctionNumber){
                int auction = auctions.get(i).getAuctionNumber();
                auctions.remove(i);
                notFound = false;
                for (int j = 0; j < lots.size(); j++) {
                    if(lots.get(i).getAllocatedAuction() == auctionNumber){
                        lotDeleted++;
                        lots.remove(i);
                        notFound = false;

                    }
                }
                if(lotDeleted > 0){
                    System.out.println("Auction " + auction + " has been deleted successfully along with  " + lotDeleted + " lots allocated to it.");
                }else{
                    System.out.println("Auction " + auction + " has been deleted successfully.");
                }

            }
        }
        if(notFound){
            System.out.println("The auction number you entered was not found.");
        }
    }
    public static void deleteLot(){
        System.out.println("Please enter lot number that you want to delete");
        int lotNumber = getIntegerfromUser();
        boolean notFound = true;
        for (int i = 0; i < lots.size(); i++) {
            if(lots.get(i).getLotNumber() == lotNumber){
                String description = lots.get(i).getDescription();
                lots.remove(i);
                notFound = false;
                System.out.println("The lot " + description + " has been deleted successfully.");
            }
        }
        if(notFound){
            System.out.println("The lot number you entered was not found.");
        }
    }

    public static void printLotsUnderCurrentAuction(){
        System.out.println("Please enter auction number");
        int auctionNumber = getIntegerfromUser();
        boolean notFound = true;
        for (int i = 0; i < auctions.size(); i++) {
            if((auctions.get(i).getAuctionNumber() == auctionNumber) &&  (auctions.get(i).getState().equals("past"))){
                System.out.println("The auction has ended.");
                return;
            }
        }
        for (int i = 0; i < lots.size(); i++) {
            if((lots.get(i).getAllocatedAuction() == auctionNumber) && (lots.get(i).getState() == "assigned")){
                System.out.println(lots.get(i).listLot());
                notFound = false;
            }
        }
        if(notFound){
            System.out.println("No lots are found under the entered auction number");
        }
    }
    public static void assignLotToAuction(){
        System.out.println("Please enter lot number");
        int lotNumber = getIntegerfromUser();
        System.out.println("Please enter auction number");
        int auctionNumber = getIntegerfromUser();
        boolean notFound = true;
        Auction auction = auctions.get(1);
        for (int i = 0; i < auctions.size(); i++) {
            if(auctions.get(i).getId() == auctionNumber){
                notFound = false;
                auction = auctions.get(i);
            }
        }
        Lot lot = lots.get(1);
        for (int i = 0; i < lots.size(); i++) {
            if(lots.get(i).getLotNumber() == lotNumber){
                notFound = false;
                lot = lots.get(i);
            }
        }

        if(notFound){
            System.out.println("Your requested lot or auction number does not exist.");
        }else{
            admin.assignLotToAuction(lot, auctionNumber);
            System.out.println("Lot " + lot.getDescription() + " has been allocated to auction " + auction.getAuctionNumber() + " successfully.");
        }
    }

    public static void printAuctionDetails(){
        System.out.println("Please enter Auction number.");
        int auctionNumber = getIntegerfromUser();
        boolean notFound = true;
        for (int i = 0; i < auctions.size(); i++) {
            if(auctions.get(i).getId() == auctionNumber){
                notFound = false;
                auctions.get(i).printAllDetails();
            }
        }
        if(notFound){
            System.out.println("Your requested auction number cannot be found. Please check whether you entered a valid auction number.");
        }
    }

    public static void listAllAuctions(){
        for (int i = 0; i < auctions.size(); i++) {
            System.out.println(auctions.get(i).listAuction());
        }
    }

    public static void printLotDetails(){
        System.out.println("Please enter lot number.");
        //------------------------ Add method that only accept integer -----------------------------
        int lotNumber = getIntegerfromUser();
        boolean notFound = true;
        for (int i = 0; i < lots.size(); i++) {
            if(lots.get(i).getLotNumber() == lotNumber){
                notFound = false;
                lots.get(i).printDetails();
            }
        }
        if(notFound){
            System.out.println("Your requested lot number cannot be found. Please check whether you entered a valid lot number.");
        }
    }

    public static void haltAndShowGeneralUserMenu(){
        System.out.println();
        System.out.println("Press return to continue");
        scanner.nextLine();
        showMenuForGeneralUser();
    }

    public static void haltAndShowAdminMenu(){
        System.out.println();
        System.out.println("Press return to continue");
        scanner.nextLine();
        showMenuForAdmin();
    }
    public static void listAllLots(){
        for (int i = 0; i < lots.size(); i++) {
            System.out.println(lots.get(i).listLot());
        }
    }

    public static int selectOption(){
        String userInput = scanner.nextLine();
        int option = -1;
        try{
            option = Integer.parseInt(userInput);
        }catch (Exception e){
            System.out.println("Your input is not a valid Integer. Please select an operation again.");
            if(loggedInUserType.equals("general_user")){
                showMenuForGeneralUser();
            }else{
                showMenuForAdmin();
            }
        }
        return option;
    }

    public static void initializeProgram(){
        initializeAuctions();
        initializelots();
        initializeAdmin();
        initializeGeneralUser();
    }

    public static void initializeAdmin(){
        admin = new Admin(1,"Md","Asifullah","admin","root");
    }

    public static void initializeGeneralUser(){

        GeneralUser user1 = new GeneralUser(1,"John","Kamalesvaran","john","john1234");
        GeneralUser user2 = new GeneralUser(2,"Jane","Sellan","jane","jane789");
        GeneralUser user3 = new GeneralUser(2,"Tay","Jye Choi","tay","jyechoi5");
        users.add(user1);
        users.add(user2);
        users.add(user3);
    }


    public static void initializeAuctions(){
        Auction auction01 = new Auction(1, new Date(2021, 0, 14),"am","current");
        Auction auction02 = new Auction(2, new Date(2021, 7, 26),"am","current");
        Auction auction03 = new Auction(3, new Date(2021, 11, 14),"pm","current");

        auctions.add(auction01);
        auctions.add(auction02);
        auctions.add(auction03);
    }

    public static void initializelots(){
        // Initializing Lots
        Lot lot01 = new Lot("Kamaal bin Zufar","Victorian silver necklace","Jewellery",40.0);
        Lot lot02 = new Lot("Ramalaan bin Mahmood","Royal Doulton mendicant","Fine china",120.0);
        Lot lot03 = new Lot("Abdut Tawwab bin Zaidaan","Gold bracelet","Jewellery",90.0);
        Lot lot04 = new Lot("Hilmi bin Baheej","Royal Doulton Jester","Fine china",180.0);
        Lot lot05 = new Lot("Adham bin Taahir","Modem Landscape","Paintings",1200.0);
        Lot lot06 = new Lot("Mulia bin Yaacob","Victorian watercolour","Paintings",800.0);

        // Allocating test lot data to auctions
        lot01.setAllocatedAuction(1);
        lot02.setAllocatedAuction(3);
        lot03.setAllocatedAuction(2);
        lot01.setState("assigned");
        lot02.setState("assigned");
        lot03.setState("assigned");

        // Adding lots to array list
        lots.add(lot01);
        lots.add(lot02);
        lots.add(lot03);
        lots.add(lot04);
        lots.add(lot05);
        lots.add(lot06);
    }

    public static int getIntegerfromUser(){
        boolean integerNotReceived = true;
        int value = -1;
        while(integerNotReceived){
            String userInput = scanner.nextLine();
            try{
                value = Integer.parseInt(userInput);
                integerNotReceived = false;
            }catch (Exception e){
                System.out.println("Your input is not a valid Integer. You must input an integer to perform this operation.");
            }
        }
        return value;
    }

    public static Double getDoublefromUser(){
        boolean DoubleNotReceived = true;
        double value = -1.0;
        while(DoubleNotReceived){
            String userInput = scanner.nextLine();
            try{
                value = Double.parseDouble(userInput);
                DoubleNotReceived = false;
            }catch (Exception e){
                System.out.println("Your input is not a valid decimal. You must enter a valid decimal  for the current operation.");
            }
        }
        return value;
    }

    public static String getStringfromUser(){
        boolean stringIsEmpty = true;
        String value = " ";
        while(stringIsEmpty){
            String userInput = scanner.nextLine();
            if(userInput.equals("")){
                System.out.println("Your input cannot be empty. Please enter a value");
            }else{
                value = userInput;
                stringIsEmpty = false;
            }
        }
        return value;
    }
}