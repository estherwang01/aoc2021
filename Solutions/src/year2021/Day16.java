package year2021;
import java.util.ArrayList;
import java.util.HashMap;

public class Day16 {
    static String input = "020D78804D397973DB5B934D9280CC9F43080286957D9F60923592619D3230047C0109763976295356007365B37539ADE687F333EA8469200B666F5DC84E80232FC2C91B8490041332EB4006C4759775933530052C0119FAA7CB6ED57B9BBFBDC153004B0024299B490E537AFE3DA069EC507800370980F96F924A4F1E0495F691259198031C95AEF587B85B254F49C27AA2640082490F4B0F9802B2CFDA0094D5FB5D626E32B16D300565398DC6AFF600A080371BA12C1900042A37C398490F67BDDB131802928F5A009080351DA1FC441006A3C46C82020084FC1BE07CEA298029A008CCF08E5ED4689FD73BAA4510C009981C20056E2E4FAACA36000A10600D45A8750CC8010989716A299002171E634439200B47001009C749C7591BD7D0431002A4A73029866200F1277D7D8570043123A976AD72FFBD9CC80501A00AE677F5A43D8DB54D5FDECB7C8DEB0C77F8683005FC0109FCE7C89252E72693370545007A29C5B832E017CFF3E6B262126E7298FA1CC4A072E0054F5FBECC06671FE7D2C802359B56A0040245924585400F40313580B9B10031C00A500354009100300081D50028C00C1002C005BA300204008200FB50033F70028001FE60053A7E93957E1D09940209B7195A56BCC75AE7F18D46E273882402CCD006A600084C1D8ED0E8401D8A90BE12CCF2F4C4ADA602013BC401B8C11360880021B1361E4511007609C7B8CA8002DC32200F3AC01698EE2FF8A2C95B42F2DBAEB48A401BC5802737F8460C537F8460CF3D953100625C5A7D766E9CB7A39D8820082F29A9C9C244D6529C589F8C693EA5CD0218043382126492AD732924022CE006AE200DC248471D00010986D17A3547F200CA340149EDC4F67B71399BAEF2A64024B78028200FC778311CC40188AF0DA194CF743CC014E4D5A5AFBB4A4F30C9AC435004E662BB3EF0";
    static HashMap<String, String> convert = new HashMap<>();
    static String[] hex = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
    static String[] bin = new String[]{"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
    static int gIndex = 0; //global index into the input
    static String binInput = "";
    static int count;

    public static void main(String[] args) {
        for(int i =0; i<hex.length; i++){
            convert.put(hex[i], bin[i]);
        }
        for(int i =0; i<input.length(); i++){
            String hex = input.substring(i, i+1);
            binInput += convert.get(hex);
        }
        System.out.println(Long.valueOf(parsePacket(),2)); //part 2
        System.out.println(count); //part 1
    }

    public static String parsePacket() {
        int version = Integer.parseInt(binInput.substring(gIndex, gIndex+3), 2);
        count += version;
        int id = Integer.parseInt(binInput.substring(gIndex+3, gIndex+6),2);
        gIndex +=6;
        if(id == 4){
            return parseLiteral();
        }
        else{
            String lengthTypeID = binInput.substring(gIndex, gIndex+1);
            gIndex++;
            return parseOperator(lengthTypeID, id);
        }
    }

    public static String parseLiteral(){
        String nextChunk = binInput.substring(gIndex, gIndex + 5);
        String acc = "";
        while(nextChunk.substring(0,1).equals("1")){
            acc += nextChunk.substring(1);
            gIndex +=5;
            nextChunk = binInput.substring(gIndex, gIndex+5);
        }
        acc += nextChunk.substring(1);
        gIndex +=5;
        return acc;
    }

    public static String parseOperator(String ltid, int id) {
        Operator op = new Operator(id);
        if(ltid.equals("0")){ //next 15 bits is total length in bits of subpackets
            int bitlength = Integer.parseInt(binInput.substring(gIndex, gIndex+15), 2);
            gIndex += 15;
            int end = gIndex + bitlength;
            while(gIndex < end){
                op.input.add(parsePacket());
            }

        }else{ //next 11 bits = number of subpackets contained in this packet
            int numPackets = Integer.parseInt(binInput.substring(gIndex, gIndex + 11), 2);
            gIndex += 11;
            for(int i =0; i<numPackets; i++){
                op.input.add(parsePacket());
            }
        }
        return Long.toBinaryString(op.eval());
    }

    static class Operator{
        ArrayList<String> input = new ArrayList<>();
        int operator;
        public Operator(int op){
            operator = op;
        }
        public Long eval() {
            switch (operator){
                case 0:
                    return input.stream().mapToLong(n -> Long.valueOf(n, 2)).sum();
                case 1:
                    return input.stream().mapToLong(n -> Long.valueOf(n, 2)).reduce(1, (a,b) -> a*b);
                case 2:
                    return input.stream().mapToLong(n -> Long.valueOf(n, 2)).min().orElse(-1);
                case 3:
                    return input.stream().mapToLong(n -> Long.valueOf(n, 2)).max().orElse(-1);
                case 5:
                    return ((Long.valueOf(input.get(0), 2)) > (Long.valueOf(input.get(1), 2)) ? (long) 1 : (long)0);
                case 6:
                    return ((Long.valueOf(input.get(0), 2)) < (Long.valueOf(input.get(1), 2)) ? (long) 1 : (long)0);
                case 7:
                    return (long) (input.get(0).equals(input.get(1)) ? 1: 0);
            }
            return (long) 0;
        }
    }
}
