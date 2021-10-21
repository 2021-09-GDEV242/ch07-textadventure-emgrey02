/**
 * This class holds information about an item in The Spooky Mansion game. 
 * Each item has a (String) description and a (integer) weight. 
 */

public class Item 
{
	private String itemDescription;
	private int itemWeight;

	/**
	 * Create an item object, after supplying it with a description
	 * and a weight. 
	 * @param description  what the item is
	 * @param weight  how much the item weighs
	 */
	public Item(String description, int weight)
	{
		itemDescription = description;
		itemWeight = weight;
	}

	/**
	 * Get and return the description of the current item.
	 * @return String  description of the item
	 */
	public String getDescription()
	{
		return itemDescription;
	}

	/**
	 * Get and return the weight of the current item.
	 * @return int  weight of the item
	 */
	public int getWeight()
	{
		return itemWeight;
	}

	/**
	 * Return a string with all the details of the item.
	 * @return String  details about the item
	 */
	public String getItemDetails()
	{
		String combine = "This item is a " + itemDescription + ".\n It weighs " + itemWeight + " spooks.";
		return combine;
	}
}
