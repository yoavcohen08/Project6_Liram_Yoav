
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;


public class Parser 
{
    private File filesource;
    private File filetarget;
    private Scanner readsourcefile;
    private FileWriter writetagetbuffer;
    private String line;
    private int coutner;

    // Constructor
    public Parser(String inputFilePath) throws FileNotFoundException
    {
        this.filesource = new File(inputFilePath);
        this.readsourcefile = new Scanner((this.filesource));
        String name = filesource.getName();
        int index = name.lastIndexOf('.');
        name = name.substring(0, index) + ".hack";
        filetarget = new File(filesource.getParent(),name);
        this.line = "";
        this.coutner=0;
        try
        {
            filetarget.createNewFile();
            this.writetagetbuffer = new FileWriter(this.filetarget);
        }
        catch (IOException e) 
        {
            System.out.println("File cant be excuted");
        }
        
    }

    // Check if there are more lines to process
    public boolean hasMoreLines()
    {
        return this.readsourcefile.hasNextLine();
    }

    // Advance to the next line
    public void advance()
    {
    
        while (hasMoreLines()) 
        {
            line = this.readsourcefile.nextLine();
            line = line.replaceAll("\\s+", "");
            if (line.contains("//")) 
            {
                line = line.substring(0, line.indexOf("//"));
            }
            if (line.isEmpty()) 
            {
            this.coutner++;
            return;
            }
        }

    }

    // Return the type of the current command: A_COMMAND, C_COMMAND, or L_COMMAND
    public instructioEnum instructionType() 
    {
        if (this.line.contains("@")) 
        {
          return instructioEnum.A_instruction;
        }
        if (this.line.contains("(")) 
        {
          return instructioEnum.L_instruction;
        }
        return instructioEnum.C_instruction;

    }

    // Return the symbol of an A_COMMAND or L_COMMAND (e.g., "@value" or "(LABEL)")
    public String symbol(){
        if (this.instructionType()==instructioEnum.A_instruction) 
        {
            return line.substring(line.indexOf("@")+1);
        }
        else if (this.instructionType()==instructioEnum.L_instruction) 
        {
            return line.substring(line.indexOf("(")+1,line.indexOf(")"));
        }
        return "";
    }

    // Return the dest field of a C_COMMAND
    public String dest(){
        if (this.instructionType()==instructioEnum.C_instruction && line.contains("="))  
        {
            return line.substring(0,line.indexOf('='));
        }
        return "null";
    }

    // Return the comp field of a C_COMMAND
    public String comp(){
        if (this.instructionType()==instructioEnum.C_instruction  ) 
        {
            return line.substring(line.indexOf('=')+1);
        }
        return "";
    }

    // Return the jump field of a C_COMMAND
    public String jump()
    {
        if (this.instructionType()==instructioEnum.C_instruction && line.contains(";")) 
        {
             line.substring(line.indexOf(';')+1);
            if (line.indexOf(";")>-1)
            {
                line.substring(line.indexOf(';')+1,line.indexOf(";"));
            }
            return line.substring(line.indexOf(';')+1);
        }
        return "null";
    }
     enum instructioEnum
     {
        A_instruction,
        L_instruction,
        C_instruction;
     }
     public FileWriter getFileWriter(){return this.writetagetbuffer;}
     public String getline(){return this.line;}
     public int getcounter(){return this.coutner;}
     public void close() throws IOException{this.writetagetbuffer.close();}
}
