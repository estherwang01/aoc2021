public class InputParser {
    public static String[] parseStringInput(String input){
        return input.split("\\s+");
    }

    public static int[] parseIntInput(String input){
        String[] s = parseStringInput(input);
        int[] b = new int[s.length];
        for(int i = 0; i<s.length; i++){
            b[i] = Integer.parseInt(s[i]);
        }
        return b;
    }
}
