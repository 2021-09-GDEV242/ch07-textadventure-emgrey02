import java.util.ArrayList;
import java.util.Random;
/**
 * This RoomRandomizer class is able to return a random Room from the 
 * ArrayList of rooms when the TransporterRoom asks for one.
 *
 * @author Emma Grey
 * @version 11.4.2021
 */
public class RoomRandomizer
{
    private Random num;
    private ArrayList<Room> rooms = Game.getRooms();

    /**
     * Constructor for objects of class RoomRandomizer
     */
    public RoomRandomizer()
    {
        num = new Random();
    }
    
    /**
     * Get a random room from the ArrayList of Rooms
     * 
     * @return  a random Room object
     */
    public Room getRandomRoom()
    {
        int randomIndex = num.nextInt(rooms.size());
        return rooms.get(randomIndex);
    }
}
