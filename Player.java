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
     * Add an item into the Player's inventory to begin with. 
     * Used mainly for ghosts.
     * @param item  item to put in player's inventory
     */
    public void addItem(Item item)
    {
      inventory.add(item);
    }

    /**
     * Add health to a player
     * @param spoops  amount of health that player gains
     */
    public void addHealth(int spoops)
    {
      health += spoops;
      if (health > 10) {
        health = 10;
      }
    }

    /**
     * Remove health from a player
     * @param spoops  amount of health player looses
     */
    public void damage(int spoops)
    {
      health -= spoops;
      if (health <= 0) {

      }
    }

    /**
     * Get the room the player is currently in.
     */
    public Room getRoom()
    {
        return currentRoom;
    }

    /**
     * Get the current health of the player.
     * @return health  integer of spoops of health
     */
    public int getHealth()
    {
      return health;
    }

    /**
     * Return the item object that corresponds to an item's name, if
     * it exists in the player's inventory. Otherwise, it returns
     * null.
     * @param itemName  name of item to return
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
     * the item puts the inventory over its max weight, or it can't
     * be picked up, then the item isn't
     * picked up. Returns a boolean if successful or not.
     * @param currentItem  item that player wants to pick up
     * @return boolean
     */
    public boolean pickUpItem(Item currentItem)
    {
        if (currentItem.isTakeable()) {
          if (getCurrentWeight() + currentItem.getWeight() <= maxWeight) {
              inventory.add(currentItem);
              currentRoom.removeItem(currentItem);
              return true;
          } else {
              System.out.println("This item is too heavy.\n");
          }
        } else {
          System.out.println("You don't need this item.\n");
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
      for (Item item : inventory) {
        if (item == currentItem) {
          inventory.remove(currentItem);
          currentRoom.addItem(currentItem);
        }
      }
    }

    /**
     * Remove an item (after eating or using).
     * @param currentItem
     */
    public void removeItem(Item currentItem)
    {
      inventory.remove(currentItem);
    }

    /**
     * Get the current weight of the player's inventory.
     * @return int  current weight of player's inventory
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
          stringInventory += item.getName() + "(" + item.getWeight() + ") ";
      }
      return stringInventory;
    }

}
