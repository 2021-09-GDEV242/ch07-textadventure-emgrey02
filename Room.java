import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class Room - a room in the mansion.
 *
 * This class is part of The Spooky Mansion application. 
 * The Spooky Mansion is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room. A room can hold any amount
 * of items to be picked up by the player, a room can have a ghost
 * in it, and it can be locked.
 * 
 * 
 * @author  Emma Grey
 * @version 2021.10.22
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;  // stores exits of this room.
    private ArrayList<Item> items;      // stores items in this room
    private ArrayList<Player> ghost;   //stores ghost in room
    private boolean open;
    private RoomRandomizer randomizer;

    /**
     * Create a room by providing a description and
     * whether the room is unlocked.
     * @param description The room's description.
     * @param isOpen  boolean, true if the room is unlocked
     */
    public Room(String description, boolean isOpen) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<Item>();
        ghost = new ArrayList<Player>();
        open = isOpen;
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Add an item to the room's inventory.
     * @param roomItem  Item object in the room
     */
    public void addItem(Item roomItem)
    {
        items.add(roomItem);
    }

    /**
     * Remove an item from the room's inventory.
     * @param roomItem Item object in the room
     */
    public void removeItem(Item roomItem)
    {
        items.remove(roomItem);
    }

    /**
     * See if the room is open/unlocked.
     * @return boolean
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Unlock the room.
     */
    public void unlock() {
        open = true;
    }

    /**
     * Add ghost to room.
     * @param ghost a Player object representing a ghost
     */
    public void addGhost(Player ghostPlayer)
    {
        ghost.add(ghostPlayer);
    }

    /**
     * Remove ghost from room after successful trade.
     * @param ghost a Player object representing a ghost
     */
    public void removeGhost(Player ghostPlayer)
    {
        ghost.remove(ghostPlayer);
    }

    /**
     * Check room for ghosts.
     * @return boolean 
     */
    public boolean checkForGhost()
    {
        if (ghost.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Return ghost object that is in the room.
     * @return Player object representing a ghost
     */
    public Player getGhost()
    {
        return ghost.get(0);
    }

    /**
     * Return an item object given the item's name, if it
     * exists in the room. Otherwise it
     * returns null.
     * @param itemName name of the item
     * @return corresponding item object in the room
     */
    public Item getItemFromRoom(String itemName)
    {
        for (Item item : items) {
            if (itemName.equals(item.getName())) {
                return item;
            }
        }
        return null;
    }

    /**
     * Return short description of the room.
     * @return The short description of the room.
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a long description of the room.
     *     You are in the kitchen.
     *     items in the room...
     *     ghosts in the room...
     *     How many ghosts left in the mansion...
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {   
        String longDescription = "\nYou are " + description + "\n";

        if (items.size() > 0) {
            if (items.size() > 1) {
                longDescription += "\nYou see some items on the ground:";
            } else {
                longDescription += "\nYou see something on the ground:";
            }
            for (Item item : items) {
                longDescription += "\n" + item.getItemDetails();
            }
            longDescription += "\n";
        }
        if (checkForGhost()) {
            System.out.println("\nBOO!!!\n");
            System.out.println("You are spooked by a ghost and lost 3 spoops of health.");
            System.out.println("You can trade jewelry for any item the ghost has, and it will leave. \nOtherwise, it will scare you again.");
            System.out.println("The ghost has this item to trade: " + getGhost().getPlayerInventory());
        }

        return longDescription + "\n" + getExitString();
    
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

