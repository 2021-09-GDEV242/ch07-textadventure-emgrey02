/**
 * This class holds information about an item in The Spooky Mansion game. 
 * Each item has a (String) description and a (integer) weight. 
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
		String combine = "A " + Game.TEXT_RED + description + Game.TEXT_RESET + " that weighs " + Game.TEXT_RED + weight + " spoops." + Game.TEXT_RESET + "  (Item name: " + Game.TEXT_GREEN + name + Game.TEXT_RESET + ")";
		return combine;
	}
}
