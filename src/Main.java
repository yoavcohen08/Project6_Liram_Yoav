import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException
    {
       File filesource = new File("C:\\Users\\liram\\Desktop\\file\\Project6_Liram_Yoav\\Pong.asm");
       if (!filesource.exists()) 
       {
            throw new FileNotFoundException("File Not Found"); 
       }
       Scanner readsourcefile = new Scanner((filesource));
        String name = filesource.getName();
        int index = name.lastIndexOf('.');
        name = name.substring(0, index) + ".hack";
       File filetarget = new File(filesource.getParent(),name);
       Parser parser = new Parser(filesource);
       SymbolTable table = new SymbolTable();
       while (parser.hasMoreLines()) 
       {
            parser.advance();
           if (parser.instructionType() == instructioEnum.L_instruction) 
           {
                if (!table.contains(parser.symbol())) 
                {
                    table.addLinstruction(parser.symbol(), parser.getcounter());
                }
           }         
       }
       
        parser = new Parser(filesource);
        try
        {
            filetarget.createNewFile();
            try(FileWriter writetagetbuffer = new FileWriter(filetarget);)
            {
                
                while (parser.hasMoreLines()) 
                {
                    String curline = "";
                    parser.advance();
                    switch (parser.instructionType()) 
                    {
                        case A_instruction :
                        {
                        
                            try 
                            {
                                int at = Integer.parseInt(parser.symbol());
                                curline = intToBinary(at);
                                break;
                            } 
                            catch (Exception e) 
                            {
                                if (table.contains(parser.symbol())) 
                                {
                                    int at = table.getAddress(parser.symbol());
                                    curline = intToBinary(at);
                                }
                                else
                                {
                                    table.addVaribale(parser.symbol());
                                    int at = table.getAddress(parser.symbol());
                                    curline = intToBinary(at);
                                }
                                break;
                        } 

                        }               
                        case C_instruction:
                        {
                            curline ="111"+ Code.comp(parser.comp())+Code.dest(parser.dest())+Code.jump(parser.jump());
                        }
                        case L_instruction:
                        {
                            break;
                        }
                    }
                    if (!curline.isEmpty()) 
                    {
                        writetagetbuffer.append(curline + "\n");
                    }
                    
                }
            }    
        }
        catch (IOException e) 
        {
            System.out.println("File cant be excuted");
            
        }
        finally
        {
            readsourcefile.close();
        }
        
    }
    public static String intToBinary(int x)
    {
        int [] array = new int[16];
        int id=0;
        String binary = "";
        while (x>0) 
        {
            array[id++] = x%2;
            x = x/2;
        }
        for(int i =15;i>=0;i--)
        {
            binary+=array[i];
        }
        return binary;
    }
}