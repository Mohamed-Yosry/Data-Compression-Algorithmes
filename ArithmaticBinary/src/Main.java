import java.util.*;

public class Main {
    public static void main(String [] args){
        Set<Character> uinqe= new LinkedHashSet<>();
        List<Character> uni= new ArrayList<>();
        String s="ACBACC";


        for(int i=0;i<s.length();i++)     //unique characters
            uinqe.add(s.charAt(i));

        double freq[]=new double[uinqe.size()+1];

        Iterator iterator = uinqe.iterator();

        double counter=0;                      //get frequency
        int counter2=1;
        Object nextchar;
        while (iterator.hasNext()) {
            nextchar=iterator.next();
            uni.add((Character) nextchar);
            for(int i=0;i<s.length();i++){

                if(Objects.equals(s.charAt(i),nextchar ))
                    counter++;
            }
            freq[counter2]=counter/s.length();
            counter=0;
            counter2++;
        }
        freq[0]=0;


        for(int i=1;i<uinqe.size()+1;i++){
            freq[i]=freq[i]+freq[i-1];
        }



        System.out.println("Commpresion:");
        compression c=new compression(s);
        String code=c.compress(freq[0],freq[freq.length-1],freq,uni);
        System.out.println(code);

        /*freq[1]=0.8;
        freq[2]=0.82;
        freq[3]=1;
        code="110001100000";*/

        System.out.println("Deommpresion:  "+ s.length());
        decompression d=new decompression(code);
        d.decompress(uni,freq[0],freq[freq.length-1],freq,s.length());


    }
}
