/**
 * Class Item - an item in The Spooky Mansion game.
 * 
 * This class is part of The Spooky Mansion application. 
 * The Spooky Mansion is a very simple, text based adventure game.
 * 
 * Each item has a (String) description, a (integer) weight, a (String) name,
 * and a boolean for whether the item can be picked up. 
 * 
 * @author Emma Grey
 * @version 2021.10.22
 */

public class Item 
{
    private String description;
    private int weight;
    private String name;
    private boolean canBePickedUp;
    /**
     * Create an item object, after supplying it with a description
     * and a weight. 
     * @param description  what the item is
     * @param weight  how much the item weighs
     */
    public Item(String itemName, String itemDescription, int itemWeight, boolean pickedUp)
    {
        description = itemDescription;
        weight = itemWeight;
        name = itemName;
        canBePickedUp = pickedUp;
    }

    /**
     * Return whether item can be picked up
     * @return boolean
     */
    public boolean isTakeable()
    {
        return canBePickedUp;
    }

    /**
     * Get and return the description of the current item.
     * @return String  description of the item
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Get and return the weight of the current item.
     * @return int  weight of the item
     */
    public int getWeight()
    {
        return weight;
    }

    /**
     * Get and return the name of the current item.
     * @return String  name of the item.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Return a string with all the details of the item.
     * @return String  details about the item
     */
    public String getItemDetails()
    {
        String combine = "A " + description + " that weighs " + weight + " spoops." + 
        "  (Item name: " + name + ")";
        return combine;
    }
}
