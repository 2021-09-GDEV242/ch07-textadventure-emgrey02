
/**
 * This TransporterRoom class is a subclass of the Room class. It creates a
 * room that, when exited, transports the player to a random room in the mansion.
 *
 * @author Emma Grey
 * @version 11.4.2021
 */
public class TransporterRoom extends Room
{
    private RoomRandomizer randomizer;
    /**
     * Constructor for objects of class TransporterRoom
     */
    public TransporterRoom(String description, boolean isOpen)
    {
        super(description,isOpen);
        randomizer = new RoomRandomizer();
    }

    /**
     * Return a random room, independent of the direction
     *
     * @param  direction Ignored
     * @return    a random room.
     */
    public Room getExit(String direction)
    {
        return findRandomRoom();    
    }
    
    /**
     * Choose a random room.
     * 
     * @return A random room.
     */
    private Room findRandomRoom()
    {
        return randomizer.getRandomRoom();
    }
}
