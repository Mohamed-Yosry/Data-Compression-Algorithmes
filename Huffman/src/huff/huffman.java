package huff;


public class huffman {
    private String text,compressd="";
    private String left,right,unique[],code[],leftCode="00",rightCode="10";
    private double leftP,rightP;
    private double frequency[];
    public huffman(String t){
        text=t;
    }
    String[] compression(double freq[], String uni[]){
        code=new String[freq.length];
        unique=new String[freq.length];
        frequency=new double[freq.length];
        for(int i=0;i<freq.length;i++){
            frequency[i]=freq[i];
            unique[i]=uni[i];
        }

        sort(frequency, unique);


        for(int i=freq.length-1;i>1;i--) {
            sort(freq, uni);   //sort the 2 array
            freq[i-1]+=freq[i];
            uni[i-1]+=uni[i];
            freq[i]=0;
        }
        left=uni[0];
        leftP=freq[0];
        right=uni[1];
        rightP=freq[1];

        for(int i=0;i<frequency.length;i++){
            if(left.contains(unique[i])){
                code[i]=leftCode;
                if(leftCode.charAt(leftCode.length()-1)=='0')
                    leftCode=leftCode.replace(leftCode.charAt(leftCode.length()-1),'1');
                else
                    leftCode+="0";
                leftP-=frequency[i];
            }else if(right.contains(unique[i])){
                code[i]=rightCode;
                if(rightCode.charAt(rightCode.length()-1)=='0')
                    rightCode=rightCode.replace(rightCode.charAt(rightCode.length() - 1), '1');
                else
                    rightCode+="0";
                rightP-=frequency[i];
            }
        }

        /*for(int i=0;i<frequency.length;i++)
            System.out.println(unique[i]+"    "+frequency[i]+"      "+code[i]);*/


        char t,t2;
        for(int i=0;i<text.length();i++){
            t=text.charAt(i);
            for(int x=0;x<unique.length;x++){
                t2=unique[x].charAt(0);
                if(t2==t){
                    System.out.print(code[x]);
                    compressd+=code[x];
                    break;
                }
            }
        }
        System.out.println();

        return code;
    }

    String getCompressd(){return compressd;}
    void decompress(String arrCode[],String comp){
        for(int i=0;i<comp.length();i++){
            for(int x=0;x<arrCode.length;x++){
                //System.out.println(comp.substring(i,i+arrCode[x].length())+"  sunb");
                if(i+arrCode[x].length()<=comp.length()) {
                    if (arrCode[x].equals(comp.substring(i, i + arrCode[x].length()))) {
                        System.out.print(unique[x]);
                        i += arrCode[x].length() - 1;
                    }
                }
            }
        }
    }

    void sort(double arr[],String uni[])
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            double key = arr[i];
            String k=uni[i];
            int j = i - 1;


            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                uni[j + 1] = uni[j];
                j = j - 1;
            }
            arr[j + 1] = key;
            uni[j + 1] = k;
        }
    }

}
