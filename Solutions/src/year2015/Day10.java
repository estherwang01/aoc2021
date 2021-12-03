package year2015;

public class Day10 {
    public static String processOnce(String input){
        String currentDigit = input.substring(0, 1);
        int count = 1;
        String result = "";
        for(int i = 1; i< input.length(); i++){
            String next = input.substring(i, i+1);
            if(next.equals(currentDigit)){
                count++;
            } else{
                result += count;
                result += currentDigit;
                currentDigit = next;
                count = 1;
            }
        }
        result += count + "" + currentDigit;
        return result;
    }
    public static void main(String[] args) {
        String input = "1113122113";
//        System.out.println(processOnce(input));
        for(int i =0; i<50; i++){
            input = processOnce(input);
        }
        System.out.println(input.length());
    }
}
