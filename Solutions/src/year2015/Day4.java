package year2015;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class Day4 {
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String input = "yzbqklnj";

        String current = "";
        int index = 0;
        while(!check(current)){
            String c = input + index;
            current = md5(c);
            index ++;
        }
        System.out.println(index-1);
    }
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String md5(String s) throws NoSuchAlgorithmException {
        byte[] b = s.getBytes(StandardCharsets.UTF_8);
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] md5 = md.digest(b);
        return bytesToHex(md5);
    }
    public static boolean check(String s){
        return s.length() >= 6 && s.substring(0, 6).equals("000000");
    }
}
