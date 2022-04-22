package huff;

import java.util.*;

public class main {
    public static void main(String [] args){
        Set<Character> uinqe= new LinkedHashSet<>();
        List<String> uni= new ArrayList<>();
        String code[];
        String s="ABACCAAAB";




        for(int i=0;i<s.length();i++)     //unique characters
            uinqe.add((s.charAt(i)));

        double freq[]=new double[uinqe.size()];
        String un[]=new String[uinqe.size()];

        Iterator iterator = uinqe.iterator();

        double counter=0;                      //get frequency
        int counter2=0;
        Object nextchar;
        char cha;
        while (iterator.hasNext()) {
            nextchar=iterator.next();
            //uni.add((String) nextchar);
            cha= (char) nextchar;
            un[counter2]= String.valueOf(cha);
            for(int i=0;i<s.length();i++){

                if(Objects.equals(s.charAt(i),nextchar ))
                    counter++;
            }
            freq[counter2]=counter/s.length();
            counter=0;
            counter2++;
        }



        huffman h=new huffman(s);
        System.out.println("Start Compression");
        code=h.compression(freq,un);

        //String compressed="00100011";
        String compressed="";
        /*for(int i=0;i<code.length;i++)
            compressed+=code[i];*
         */

        compressed=h.getCompressd();

//System.out.println(compressed);
        System.out.println("Start Decompression");
        h.decompress(code,compressed);

    }
}
