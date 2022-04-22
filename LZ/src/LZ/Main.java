package LZ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Integer> positionTag=new ArrayList<>();
        List<Integer> lengthTag=new ArrayList<>();
        List<Character> nextCharTag=new ArrayList<>();
        List<Integer> positionTag2=new ArrayList<>();
        List<Character> nextCharTag2=new ArrayList<>();

        int n1,n2;
        Scanner scan=new Scanner(System.in);
        int p,l;
        String c;

        System.out.println("LZ78:");

        LZ78 o = new LZ78();
        o.setText("ABAABABAABABBBBBBBBBBA");
        o.compression();
        o.print();
        n1=scan.nextInt();
        for(int i=0;i<n1;i++){
            p=scan.nextInt();
            c=scan.next();
            positionTag2.add(p);
            nextCharTag2.add(c.charAt(0));
        }
        o.decompression(positionTag2,nextCharTag2);


        System.out.println("\n\n\n");


      /*  System.out.println("LZ77:");
        LZ77 o2=new LZ77();
        o2.setString("ababbbbbbbbbbbbbbbbbaaaaababbaaaaaaaaaaaaaaaaabaaaaa");
        o2.setString("ababbbbbbbbbbbbbbbbbaaaaababbaaaaaaaaaaaaaaaaabaaaaa");
        o2.compression();
        o2.print();
        n2=scan.nextInt();
        for(int i=0;i<n2;i++){
            p=scan.nextInt();
            l=scan.nextInt();
            c=scan.next();
            positionTag.add(p);
            lengthTag.add(l);
            nextCharTag.add(c.charAt(0));
        }
        o2.decompression(positionTag,lengthTag,nextCharTag);*/
    }
}
