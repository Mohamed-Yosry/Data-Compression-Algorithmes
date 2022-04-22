package LZ;

import java.util.ArrayList;
import java.util.List;

public class LZ78 {
    private String text;
    private  int currentPosition=0,prevIndex=0,maxLength=1;
    private boolean cond=false,preCond=false;
    private List<String> dictionary=new ArrayList<String>();
    private List<Integer> index=new ArrayList<Integer>();
    private List<Character> nextChar=new ArrayList<Character>();
    public void setText(String s){
        text=s;
    }
    public String getText(){
        return text;
    }
    public void compressionHelper(){
        cond=false;
        preCond=false;
        for(int i=1;i<=text.length() && currentPosition+i<=text.length();i++){
            for(int x=0;x<dictionary.size() ;x++){
                if(text.substring(currentPosition,currentPosition+i)
                        .equals(dictionary.get(x))){
                    cond=true;
                    maxLength=currentPosition+i;
                    prevIndex=x;
                }
            }
            if(cond ) {
                preCond=cond;
                cond=false;
            }
            else if(!cond && preCond ) {
                break;
            }
        }
    }

    public void compression() {
        dictionary.add("  ");
        while (currentPosition < text.length()) {
            compressionHelper();
            if(!cond && preCond){
                if(maxLength+1>text.length()) {
                    dictionary.add(text.substring(currentPosition, text.length()));
                    nextChar.add('\0');
                }
                else {
                    dictionary.add(text.substring(currentPosition, maxLength + 1));
                    nextChar.add(text.charAt(maxLength));
                }
                index.add(prevIndex);
                currentPosition=maxLength+1;
            }else if(!cond && !preCond){
                dictionary.add(text.substring(currentPosition,currentPosition+1));
                index.add(0);
                nextChar.add(text.charAt(currentPosition));
                currentPosition++;
            }
        }
    }

    public void decompression(List<Integer> position,List<Character> nextCharacter){
        String decompres="";
        for(int i=0;i<nextCharacter.size();i++){
            if (position.get(i) != 0) {
                decompres += dictionary.get(position.get(i));
            }
            decompres+=nextCharacter.get(i);
        }
        if(decompres.charAt(decompres.length()-1)=='\\')
            decompres=decompres.substring(0,decompres.length()-1);
        System.out.println("Decompression: "+decompres);
    }
    public void print(){
        System.out.println("Compressed:");
        for(int i=0;i<index.size();i++) {
            System.out.println(index.get(i) +"    "+ nextChar.get(i));
        }
        System.out.println("\nDictionary:");
        for(int i=0;i<dictionary.size();i++) {
            System.out.println(dictionary.get(i));
        }
    }
}

