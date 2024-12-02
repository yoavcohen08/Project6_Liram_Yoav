public class Parser {

    // Constructor
    public Parser(String inputFilePath){

    }

    // Check if there are more lines to process
    public boolean hasMoreLines(){
        return false;
    }

    // Advance to the next line
    public void advance(){

    }

    // Return the type of the current command: A_COMMAND, C_COMMAND, or L_COMMAND
    public CommandType instructionType() {
        return null;
    }

    // Return the symbol of an A_COMMAND or L_COMMAND (e.g., "@value" or "(LABEL)")
    public String symbol(){
        return null;
    }

    // Return the dest field of a C_COMMAND
    public String dest(){
        return null;
    }

    // Return the comp field of a C_COMMAND
    public String comp(){
        return null;
    }

    // Return the jump field of a C_COMMAND
    public String jump()
    {
        return null;
    }
}
