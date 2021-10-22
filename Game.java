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
        Room foyer, mainHall, diningRoom, kitchen, livingRoom, guestRoom, laundry, basementTop,
        basementBottom, cellar, emptyRoom, mainBottom, mainTop, library, lounge, patio, garden,
        landing, masterBedroom, study, bathroom, kidBedroom;

        Item key, chocolate, flashlight, candy, note, jacket, scarf, ring, necklace, doll;
      
        // create the rooms
        foyer = new Room("in the foyer of the Spooky Mansion.", true);
        mainHall = new Room("in the main hall. The chandelier above you is HUGE.", true);
        diningRoom = new Room("in the dining room. The table is set...", true);
        kitchen = new Room("in the kitchen. It smells like something is rotting.", true);
        livingRoom = new Room("in the living room. Why is the TV on?", true);
        guestRoom = new Room("in the guest room. The closet door is eerily open.", false);
        laundry = new Room("in the laundry room.", true);
        basementTop = new Room("at the top of a creepy staircase.\nDo you dare to go down?", true);
        basementBottom = new Room("in a dark, dusty basement.", true);
        cellar = new Room("in the cellar. Sadly, no vintage aged wines for you.", true);
        emptyRoom = new Room("in an empty, uncomfortably echoey room.", true);
        mainBottom = new Room("at the bottom of an unreasonably wide, grand staircase.", true);
        mainTop = new Room("at the top of the grand staircase.", true);
        library = new Room("in the library. Did that rocking chair just move?", true);
        lounge = new Room("in the lounge. The chairs in here look comfy, but you don't want to touch anything.", true);
        patio = new Room("outside on the patio. Does it really have to be raining today?.", true);
        garden = new Room("outside in the garden. Obviously everything is dead.", false);
        landing = new Room("on the upstairs landing. Did you just hear someone crying?", true);
        masterBedroom = new Room("in the master bedroom. Is that a jewelry box?", false);
        study = new Room("in the upstairs study. Dusty books are scattered everywhere.", true);
        bathroom = new Room("in the bathroom. The toilet water is black. Yuck.", true);
        kidBedroom = new Room("in a child's bedroom. Why does it feel like that doll is staring at you?", true);
        
        //create items
            //to unlock doors
        key = new Item("key","rusty key", 3, true);

            //unusable
        flashlight = new Item("flashlight","flashlight", 3, false);
        note = new Item("note","cryptic note that means nothing", 1, false);
        doll = new Item("doll", "creepy doll", 4, false);

            //to eat
        chocolate = new Item("chocolate","chocolate bar", 2, true);
        candy = new Item("candy","fruit-by-the-foot", 3, true);

            //to use
        jacket = new Item("jacket","jacket", 3, true);
        scarf = new Item("scarf","scarf", 2, true);

            //to trade
        ring = new Item("ring","diamond ring", 3, true);
        necklace = new Item("necklace", "pearl necklace", 2, true);
        

        // initialize room exits, items, and ghosts
        foyer.setExit("north", mainHall);

        mainHall.setExit("north", mainBottom);
        mainHall.setExit("south", foyer);
        mainHall.setExit("west", library);
        mainHall.setExit("east", diningRoom);
        Player ghost1 = new Player(5);
        ghost1.setRoom(mainHall);
        ghost1.addItem(chocolate);
        ghost1.addItem(candy);
        mainHall.addGhost(ghost1);
        
        library.setExit("west", lounge);
        library.setExit("east", mainHall);
        library.addItem(key);

        lounge.setExit("north", garden);
        lounge.setExit("south", patio);
        lounge.setExit("east", library);
        lounge.addItem(necklace);

        garden.setExit("south", lounge);
        Player ghost2 = new Player(2);
        ghost2.setRoom(garden);
        ghost2.addItem(candy);
        garden.addGhost(ghost2);

        patio.setExit("north", lounge);
        patio.addItem(key);

        mainBottom.setExit("south", mainHall);
        mainBottom.setExit("east", kitchen);
        mainBottom.setExit("up", mainTop);

        diningRoom.setExit("north", kitchen);
        diningRoom.setExit("west", mainHall);
        diningRoom.addItem(jacket);
        Player ghost3 = new Player(4);
        ghost2.setRoom(diningRoom);
        ghost2.addItem(note);
        ghost2.addItem(chocolate);
        diningRoom.addGhost(ghost3);
        
        kitchen.setExit("north", laundry);
        kitchen.setExit("south", diningRoom);
        kitchen.setExit("west", mainBottom);
        kitchen.setExit("east", livingRoom);
        foyer.addItem(chocolate);
        foyer.addItem(candy);

        laundry.setExit("south", kitchen);
        laundry.setExit("east", guestRoom);
        Player ghost4 = new Player(2);
        ghost4.setRoom(laundry);
        ghost4.addItem(candy);
        laundry.addGhost(ghost4);

        livingRoom.setExit("north", guestRoom);
        livingRoom.setExit("west", kitchen);
        livingRoom.addItem(scarf);

        guestRoom.setExit("south", livingRoom);
        guestRoom.setExit("west", laundry);
        guestRoom.setExit("east", basementTop);
        guestRoom.addItem(jacket);

        basementTop.setExit("west", guestRoom);
        basementTop.setExit("down", basementBottom);

        basementBottom.setExit("north", emptyRoom);
        basementBottom.setExit("west", cellar);
        basementBottom.setExit("up", basementTop);

        cellar.setExit("east", basementBottom);
        cellar.addItem(flashlight);
        Player ghost5 = new Player(3);
        ghost5.setRoom(cellar);
        ghost5.addItem(chocolate);
        cellar.addGhost(ghost5);

        emptyRoom.setExit("south", basementBottom);

        mainTop.setExit("north", landing);
        mainTop.setExit("down", mainBottom);

        landing.setExit("north", bathroom);
        landing.setExit("south", mainTop);
        landing.setExit("west", kidBedroom);
        landing.setExit("east", masterBedroom);

        kidBedroom.setExit("east", landing);
        kidBedroom.addItem(doll);
        kidBedroom.addItem(candy);
        Player ghost6 = new Player(3);
        ghost6.setRoom(kidBedroom);
        ghost6.addItem(candy);
        ghost6.addItem(note);
        kidBedroom.addGhost(ghost6);

        bathroom.setExit("south", landing);
        bathroom.setExit("east", study);
        bathroom.addItem(necklace);
        
        study.setExit("south", masterBedroom);
        study.setExit("west", bathroom);
        study.addItem(note);
        Player ghost7 = new Player(3);
        ghost6.setRoom(study);
        ghost6.addItem(chocolate);
        study.addGhost(ghost7);

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
        System.out.println("Welcome to The Spooky Mansion");
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
                System.out.println("\nI don't know what you mean...");
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
                eat(command);
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
            
            case USE:
                use(command);
                break;
            
            case HEALTH:
                printHealth();
                break;
            
            case TRADE:
                trade();
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
        System.out.println(parser.showCommands());
    }

    /**
     * Trade a jewelry item for an item in the ghost's inventory
     * @param
     */
    private void trade()
    {
        
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
    private void eat(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("\nWhat do you want to eat?");
            return;
        }
        String foodItem = command.getSecondWord();
        if (!foodItem.equals("chocolate") && !foodItem.equals("candy")) {
            System.out.println("...this isn't edible.");
            return;
        } else if (foodItem.equals("chocolate")) {
            System.out.println("\nYou eat a chocolate bar. Your health increases by 3 spoops.");
            player.addHealth(3);
        } else if (foodItem.equals("candy")) {
            System.out.println("You eat candy. Your health increases by 2 spoops.");
            player.addHealth(2);
        } 
        Item currentItem = player.getItemFromName(foodItem);
        player.removeItem(currentItem);
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("\nGo where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("\nThere is no door!");
        }
        if (!nextRoom.isOpen()) {
            System.out.println("\nThis door is locked. Use a key to unlock it.");
        }
        else {
            player.setRoom(nextRoom);
            System.out.println(player.getRoom().getLongDescription());
            if (nextRoom.checkForGhost()) {
                player.damage(3);
            }
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
            System.out.println("\nWhat item do you want to take?");
            return;
        }
        String itemName = command.getSecondWord();
        Item currentItem = player.getRoom().getItemFromName(itemName);
        if (player.pickUpItem(currentItem)) {
            System.out.println("\nYou picked up an item.");
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
            System.out.println("\nWhat item do you want to drop?");
            return;
        }
        String itemName = command.getSecondWord();
        Item currentItem = player.getItemFromName(itemName);
        player.dropItem(currentItem);
        System.out.println("\nYou dropped an item.");
    }

    /**
     * Use an item: unlock door with key.
     * @param 
     */
    private void use(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to use
            System.out.println("\nWhat item do you want to use?");
            return;
        }
        String itemName = command.getSecondWord();
        if (itemName == "key") {
            player.getRoom().unlock();
            System.out.println("\nYou unlocked the door. You wonder what could be on the other side.");
        } if (itemName == "jacket") {
            player.addHealth(3);
            System.out.println("\nYou put on the oversized jacket. It increases your health by 3 spoops.");
        } if (itemName == "scarf") {
            player.addHealth(2);
            System.out.println("\nYou wrap the scarf around you. It warms you up a bit and increases your health by 3 spoops.");
        } else {
            System.out.println("\nYou can't use this item.");
        }
    }

    /**
     * Print the player's health to the console.
     */
    private void printHealth()
    {
        System.out.println("Your current health (in spoops): " + player.getHealth());
    }

    /**
     * print the player's inventory
     * @return String  player's inventory
     */
    private void listItems()
    {   
        if (player.getPlayerInventory() == "") {
            System.out.println("\nYour inventory is now empty.\n");
        } else {
            System.out.println("\nCurrent inventory: " + player.getPlayerInventory() + "\n");
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
            System.out.println("\nQuit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
