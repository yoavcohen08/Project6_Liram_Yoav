import java.util.HashMap;

public class Code {

    private static HashMap<String, String> MapDest;
    private static HashMap<String, String> MapComp;
    private static HashMap<String, String> MapJump;

    static {
        MapDest = new HashMap<>();
        MapComp = new HashMap<>();
        MapJump = new HashMap<>();
        initializeMapDest();
        initializeMapComp();
        initializeMapJump();

    }

    // Translate a dest into its binary representation
    public static String dest(String str) {
        if (str == null) {
            return "000";
        }
        return MapDest.get(str);
    }

    // Translate a comp into its binary representation
    public static String comp(String str) {
        if (str == null) {
            return "0000000";
        }
        return MapComp.get(str);
    }

    // Translate a jump into its binary representation
    public static String jump(String str) {
        if (str == null) {
            return "000";
        }
        return MapJump.get(str);
    }

    // Initialize dest
    private static void initializeMapDest() {
        MapDest.put("null", "000");
        MapDest.put("M", "001");
        MapDest.put("D", "010");
        MapDest.put("DM", "011");
        MapDest.put("A", "100");
        MapDest.put("AM", "101");
        MapDest.put("AD", "110");
        MapDest.put("ADM", "111");
    }

    // Initialize comp
    private static void initializeMapComp() {
        MapComp.put("0", "0101010");
        MapComp.put("1", "0111111");
        MapComp.put("-1", "0111010");
        MapComp.put("D", "0001100");
        MapComp.put("A", "0110000");
        MapComp.put("M", "1110000");
        MapComp.put("!D", "0001101");
        MapComp.put("!A", "0110001");
        MapComp.put("!M", "1110001");
        MapComp.put("-D", "0001111");
        MapComp.put("-A", "0110011");
        MapComp.put("-M", "1110011");
        MapComp.put("D+1", "0011111");
        MapComp.put("A+1", "0110111");
        MapComp.put("M+1", "1110111");
        MapComp.put("D-1", "0001110");
        MapComp.put("A-1", "0110010");
        MapComp.put("M-1", "1110010");
        MapComp.put("D+A", "0000010");
        MapComp.put("D+M", "1000010");
        MapComp.put("D-A", "0010011");
        MapComp.put("D-M", "1010011");
        MapComp.put("A-D", "0000111");
        MapComp.put("M-D", "1000111");
        MapComp.put("D&A", "0000000");
        MapComp.put("D&M", "1000000");
        MapComp.put("D|A", "0010101");
        MapComp.put("D|M", "1010101");
    }

    // Initialize jump
    private static void initializeMapJump() {
        MapJump.put("null", "000");
        MapJump.put("JGT", "001");
        MapJump.put("JEQ", "010");
        MapJump.put("JGE", "011");
        MapJump.put("JLT", "100");
        MapJump.put("JNE", "101");
        MapJump.put("JLE", "110");
        MapJump.put("JMP", "111");
    }

    public static void main(String[] args) {
        System.out.println("Dest 'D': " + dest("D")); // Expected: 010
        System.out.println("Dest 'ADM': " + dest("ADM")); // Expected: 111
        System.out.println("Dest 'XYZ' (invalid): " + dest("XYZ")); // Expected: 000

        System.out.println("Comp 'D+A': " + comp("D+A")); // Expected: 0000010
        System.out.println("Comp '!M': " + comp("!M")); // Expected: 1110001
        System.out.println("Comp 'XYZ' (invalid): " + comp("XYZ")); // Expected: 0000000

        System.out.println("Jump 'JGT': " + jump("JGT")); // Expected: 001
        System.out.println("Jump 'JLE': " + jump("JLE")); // Expected: 110
        System.out.println("Jump 'XYZ' (invalid): " + jump("XYZ")); // Expected: 000
    }
}