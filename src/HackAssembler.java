public class HackAssembler {
    public static void main(String[] args) 
    {
        SymbolTable st =new SymbolTable();
        st.addEntry("num");
        st.addEntry("sum");
        st.addEntry("sum1");
       System.out.println( st.getAddress("R17"));

    }
}
