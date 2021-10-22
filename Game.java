/**
 *  This class is the main class of the "Spooky Mansion" application. 
 *  "Spooky Mansion" is a text based adventure game.  Users 
 *  can walk through a mansion infested with ghosts and hopefully make it
 *  back out alive.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method. Outside of BlueJ, enter 'Java Game' into the command line.
 * 
 *  This main class creates and initializes all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Emma Grey
 * @version 2021.10.21
 */

public class Game 
{
    private Parser parser;
    private Player player;

    //text colors :)
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_GREEN = "\u001B[32m";
        
    /**
     * Create the game, initialize its internal map, and create a
     * player.
     */
    public Game() 
    {
        player = new Player(10);
        createRooms();
        parser = new Parser();
    }

    /**
     * To run program outside of BlueJ, main method is included.
     * It creates a new instance of the Game class and calls the
     * play method to start the game.
     * @param args user does not need to enter any arguments
     */
    public static void main(String[] args)
    {
        Game newGame = new Game();
        newGame.play();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room foyer, mainHall, diningRoom, kitchen, livingRoom, guestRoom, laundry, basementTop, basementBottom, cellar, emptyRoom, mainBottom, mainTop, library, lounge, patio, garden, landing, masterBedroom, study, bathroom, kidBedroom;

        Item key, chocolate, flashlight, candy, note, dagger, jacket, scarf, ring;
      
        // create the rooms
        foyer = new Room("in the foyer of the Spooky Mansion.");
        mainHall = new Room("in the main hall. You can tell these people were rich.");
        diningRoom = new Room("in the dining room. The table is set...");
        kitchen = new Room("in the kitchen. It smells like something is rotting.");
        livingRoom = new Room("in the living room. Why is the TV on?");
        guestRoom = new Room("in the guest room.");
        laundry = new Room("in the laundry room.");
        basementTop = new Room("at the top of a creepy staircase.\nDo you dare to go down?");
        basementBottom = new Room("in a dark, dusty basement.");
        cellar = new Room("in the cellar. Sadly, no vintage aged wines for you.");
        emptyRoom = new Room("in an empty, uncomfortably echoey, room.");
        mainBottom = new Room("at the bottom of a huge staircase.");
        mainTop = new Room("at the top of the huge staircase.");
        library = new Room("in the library. Did that rocking chair just move?");
        lounge = new Room("in the lounge. The chairs in here look comfy, but you resist the urge to sit.");
        patio = new Room("outside in an enclosed patio. It's actually kind of nice.");
        garden = new Room("outside in a small, enclosed garden. A garden of dead, black plants.");
        landing = new Room("in the upstairs landing. Did you just hear someone crying?");
        masterBedroom = new Room("in the master bedroom.");
        study = new Room("in the upstairs study.");
        bathroom = new Room("in the bathroom. The toilet water is black. Yuck.");
        kidBedroom = new Room("in a child's bedroom. Why is that doll looking at you?");
        
        //create items
        key = new Item("key","rusty key", 3, true);
        chocolate = new Item("chocolate","chocolate bar", 2, true);
        flashlight = new Item("flashlight","flashlight", 3, false);
        candy = new Item("candy","fruit-by-the-foot", 2, true);
        note = new Item("note","cryptic note that means nothing", 1, false);
        dagger = new Item("dagger","dagger", 4, true);
        jacket = new Item("jacket","jacket", 3, true);
        scarf = new Item("scarf","scarf", 2, true);
        ring = new Item("ring","diamond ring", 3, true);



        // initialize room exits
        foyer.setExit("north", mainHall);
        foyer.addItem(chocolate);
        foyer.addItem(candy);

        mainHall.setExit("north", mainBottom);
        mainHall.setExit("south", foyer);
        mainHall.setExit("west", library);
        mainHall.setExit("east", diningRoom);
        
        library.setExit("west", lounge);
        library.setExit("east", mainHall);
        library.addItem(key);

        lounge.setExit("north", garden);
        lounge.setExit("south", patio);
        lounge.setExit("east", library);

        garden.setExit("south", lounge);
        garden.addItem(dagger);

        patio.setExit("north", lounge);

        mainBottom.setExit("south", mainHall);
        mainBottom.setExit("east", kitchen);
        mainBottom.setExit("up", mainTop);

        diningRoom.setExit("north", kitchen);
        diningRoom.setExit("west", mainHall);
        diningRoom.addItem(jacket);
        
        kitchen.setExit("north", laundry);
        kitchen.setExit("south", diningRoom);
        kitchen.setExit("west", mainBottom);
        kitchen.setExit("east", livingRoom);

        laundry.setExit("south", kitchen);
        laundry.setExit("east", guestRoom);

        livingRoom.setExit("north", guestRoom);
        livingRoom.setExit("west", kitchen);

        guestRoom.setExit("south", livingRoom);
        guestRoom.setExit("west", laundry);
        guestRoom.setExit("east", basementTop);

        basementTop.setExit("west", guestRoom);
        basementTop.setExit("down", basementBottom);

        basementBottom.setExit("north", emptyRoom);
        basementBottom.setExit("west", cellar);
        basementBottom.setExit("up", basementTop);

        cellar.setExit("east", basementBottom);
        cellar.addItem(flashlight);

        emptyRoom.setExit("south", basementBottom);

        mainTop.setExit("north", landing);
        mainTop.setExit("down", mainBottom);

        landing.setExit("north", bathroom);
        landing.setExit("south", mainTop);
        landing.setExit("west", kidBedroom);
        landing.setExit("east", masterBedroom);

        kidBedroom.setExit("east", landing);

        bathroom.setExit("south", landing);
        bathroom.setExit("east", study);
        
        study.setExit("south", masterBedroom);
        study.setExit("west", bathroom);
        study.addItem(note);

        masterBedroom.setExit("north", study);
        masterBedroom.setExit("west", landing);
        masterBedroom.addItem(scarf);
        masterBedroom.addItem(ring);

        player.setRoom(foyer);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to " + TEXT_RED + "The Spooky Mansion" + TEXT_RESET);
        System.out.println("The Spooky Mansion is a simple text-based adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println("You approach an abandoned mansion and can't help but go inside.");
        System.out.println("Why'd you do that?");
        System.out.println();
        System.out.println();
        System.out.println(player.getRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println(TEXT_RED + "\nI don't know what you mean..." + TEXT_RESET);
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;

            case LOOK:
                look();
                break;

            case EAT:
                eat();
                break;
            
            case TAKE:
                take(command);
                break;
            
            case DROP:
                drop(command);
                break;
            
            case ITEMS:
                listItems();
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println();
        System.out.println("You are scared and don't know what to do.");
        System.out.println("You reach into your pocket and find a slip of paper.");
        System.out.println("Where did that come from?");
        System.out.println();
        System.out.println("It lists your options:");
        System.out.println(TEXT_GREEN + parser.showCommands() + TEXT_RESET);
    }

    /**
     * Print out long description: What room the user is currently
     * in and the available exits
     */
    private void look()
    {
        System.out.println("\nYou look up to see what's around you.");
        System.out.println(player.getRoom().getLongDescription());
    }

    /**
     * Executes eating command
     * 
     */
    private void eat()
    {
        System.out.println("\nYou eat a chocolate bar you found.");
        System.out.println("Does your health go up?");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println(TEXT_RED + "\nGo where?" + TEXT_RESET);
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println(TEXT_RED + "\nThere is no door!" + TEXT_RESET);
        }
        else {
            player.setRoom(nextRoom);
            System.out.println(player.getRoom().getLongDescription());
        }
    }

    /**
     * Try to pick up an item. If the Person can fit it in their inventory,
     * and the item is able to be picked up, take the item. Otherwise,
     * print an error message.
     * @param command
     */
    private void take(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to pick up
            System.out.println(TEXT_RED + "\nWhat item do you want to take?" + TEXT_RESET);
            return;
        }

        String itemName = command.getSecondWord();
        Item currentItem = player.getRoom().getItemFromName(itemName);
        if (player.pickUpItem(currentItem)) {
            System.out.println(TEXT_GREEN + "\nYou picked up an item." + TEXT_RESET);
        }
        
    }

    /**
     * Drop an item and leave it in the room the Person is currently in.
     * @param command
     */
    private void drop(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to pick up
            System.out.println(TEXT_RED + "\nWhat item do you want to drop?" + TEXT_RESET);
            return;
        }
        String itemName = command.getSecondWord();
        Item currentItem = player.getItemFromName(itemName);
        player.dropItem(currentItem);
        System.out.println(TEXT_GREEN + "\nYou dropped an item." + TEXT_RESET);
    }

    /**
     * print the player's inventory
     * @return String  player's inventory
     */
    private void listItems()
    {   
        if (player.getPlayerInventory() == "") {
            System.out.println(TEXT_GREEN + "\nYour inventory is now empty.\n" + TEXT_RESET);
        } else {
            System.out.println(TEXT_GREEN + "\nCurrent inventory: " + TEXT_RESET + player.getPlayerInventory() + "\n");
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println(TEXT_RED + "\nQuit what?" + TEXT_RESET);
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
