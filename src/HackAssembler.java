
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class HackAssembler {
    public static void main(String[] args) throws FileNotFoundException
    {
        System.out.println();

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
