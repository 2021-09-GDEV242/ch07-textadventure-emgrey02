/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * @author  Emma Grey
 * @version 2021.10.22
 */
public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.
    GO("go"), QUIT("quit"), HELP("help"), LOOK("look"), EAT("eat"), TAKE("take"), DROP("drop"), ITEMS("items"), USE("use"), HEALTH("health"), TRADE("trade"), GHOSTS("ghosts"), UNKNOWN("?");
    
    // The command string.
    private String commandString;
    
    /**
     * Initialize with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}
