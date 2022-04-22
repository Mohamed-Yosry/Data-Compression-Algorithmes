package LZ;

import java.util.ArrayList;
import java.util.List;

public class LZ77 {
    String text,line,decompressedText="";
    int len=0,iteration=0;  boolean c=false;
    int currentPosition=0;
    List<Integer> positionTag=new ArrayList<>();
    List<Integer> lengthTag=new ArrayList<>();
    List<Character> nextCharTag=new ArrayList<>();
    public void setString(String s){//}BufferedReader file){
        /*try {
            line = file.readLine();
            while (line != null) {
                text = line;
                file.close();
            }
        }catch (Exception e){

        }*/
        text=s;
    }
    public void comperssionHelper(int maxLoopI){
        int prevIteration=iteration;
        for(int i=0;i<maxLoopI;i++){
            len=maxLoopI-i;
            iteration=prevIteration;
            for(int x=0;x<=i;x++){
                if(text.substring(currentPosition,currentPosition+maxLoopI-i)
                        .equals(text.substring(iteration, iteration+len))){

                    c=true;
                    return ;
                }
                iteration++;
            }
        }
    }
    public void compression(){
        System.out.println("Compression:");
        while (currentPosition<text.length()) {
            if (currentPosition == 0) {
                positionTag.add(0);
                lengthTag.add(0);
                nextCharTag.add(text.charAt(0));
                currentPosition++;
            } else {
                int maxLoopI;
                if(currentPosition<20) {
                    iteration = 0;
                    maxLoopI=currentPosition;
                }
                else if(currentPosition+20>=text.length()){
                    maxLoopI=text.length()-currentPosition;
                    System.out.println(maxLoopI);
                    iteration=currentPosition-maxLoopI;
                }
                else{
                    iteration=currentPosition-20;
                    maxLoopI=20;
                }
                comperssionHelper(maxLoopI);
                if(!c){
                    positionTag.add(0);
                    lengthTag.add(0);
                    nextCharTag.add(text.charAt(currentPosition));
                    currentPosition++;
                    c=false;
                }
                else{

                    lengthTag.add(len);
                    if(currentPosition+len+1<text.length()) {
                        positionTag.add(currentPosition-iteration);
                        nextCharTag.add(text.charAt(currentPosition + len));

                    }
                    else {
                        positionTag.add(currentPosition-iteration);
                        nextCharTag.add(text.charAt(text.length()-1));
                    }

                    currentPosition+=len+1;
                }
            }
        }

    }

    public void decompression(List<Integer> position,List<Integer> length,List<Character> nextChar){
        for(int i=0;i<nextChar.size();i++){
            if(position.get(i)==0){
                decompressedText+=nextChar.get(i);
            }
            else if(position.get(i)>0){
                int start=decompressedText.length()-position.get(i);
                int end=start+length.get(i);
                decompressedText+=decompressedText.substring(start,end);
                decompressedText+=nextChar.get(i);
            }
        }
        System.out.println("Decompression: "+decompressedText);
    }

    public void print(){
        for(int i=0;i<positionTag.size();i++) {
            System.out.println(positionTag.get(i) +"  "+ lengthTag.get(i) +"    "+ nextCharTag.get(i));
        }
    }
}
