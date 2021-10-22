import java.util.ArrayList;

/**
 * This class creates a Player, which stores the current room the
 * player is in and an inventory with a max capacity
 */
public class Player
{
    private Room currentRoom;
    private ArrayList<Item> inventory;
    private int maxWeight;
    private int health;

    /**
     * Create a player and set the max weight (in spoops) of their
     * inventory.
     * @param weight
     */
    public Player(int weight)
    {
        maxWeight = weight;
        health = 10;
        inventory = new ArrayList<Item>();
    }

    /**
     * Set the room the player is currently in.
     * @param room  room player is in
     */
    public void setRoom(Room room)
    {
        currentRoom = room;
    }

    /**
     * Get the room the player is currently in.
     */
    public Room getRoom()
    {
        return currentRoom;
    }

    /**
     * Get the item object from the item name
     * @param itemName
     * @return Item  corresponding item object in player's inventory
     */
    public Item getItemFromName(String itemName)
    {
        for (Item item : inventory) {
            if (itemName.equals(item.getName())) {
                return item;
            }
        }
        return null;
    }

    /**
     * Pick up an item and store it in the inventory. If adding
     * the item puts the inventory over its max weight, the item isn't
     * picked up. Returns boolean if successful or not.
     * @param currentItem  item that player wants to pick up
     * @return boolean
     */
    public boolean pickUpItem(Item currentItem)
    {
        if (getCurrentWeight() + currentItem.getWeight() <= maxWeight) {
            inventory.add(currentItem);
            currentRoom.removeItem(currentItem);
            return true;
        } else {
            System.out.println(Game.TEXT_RED + "This item is too heavy.\n" + Game.TEXT_RESET);
        }
        return false;
    }

    /**
     * Drop an item and remove it from the inventory. The item is
     * placed in the room the player is currently in.
     * @param currentItem  item that the player wants to drop
     */
    public void dropItem(Item currentItem)
    {
      
      inventory.remove(currentItem);
      currentRoom.addItem(currentItem);
    }

    public int getMaxWeight()
    {
      return maxWeight;
    }

    /**
     * Get the current total weight of the player's inventory.
     */
    private int getCurrentWeight()
    {
        int totalWeight = 0;
        for (Item item : inventory) {
            totalWeight += item.getWeight(); 
        }
        return totalWeight;
    }

    /**
     * Return contents of player inventory in a String
     * @return String  player inventory 
     */
    public String getPlayerInventory()
    {
      String stringInventory = "";
      for (Item item : inventory) {
          stringInventory += item.getName() + Game.TEXT_RED + "(" + item.getWeight() + ") " + Game.TEXT_RESET;
      }
      return stringInventory;
    }
}
