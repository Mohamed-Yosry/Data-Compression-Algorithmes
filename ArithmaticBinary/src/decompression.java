import java.util.List;
import java.util.Objects;

public class decompression {
    String s="",floating;
    double lower,upper,code,k=0,binaryCode;

    decompression(String floating){
        this.floating=floating;
    }

    double setLower(double l,double range,double lowRange){
        lower=l+range*lowRange;
        return lower;
    }
    double setUpper(double u,double range,double highRange){
        upper=u+range*highRange;
        return upper;
    }
    double setCode(double c,double l,double u){
        code=(c-l)/(u-l);
        return code;
    }
    void calcK(){
        for(int i=floating.length()-1;i>-1;i--){
            if(Objects.equals(floating.charAt(i), '1')) {
                k=floating.length()-i;
                break;
            }
        }
    }
    double convertBinary(int start){
        double num=0,counter=0;
        //System.out.println(start);
        for(int i=start;i<k+start;i++){
            //System.out.println(Math.pow(2.0, k-counter-1)+"   powwer    "+((int)floating.charAt(i)-48));
            num+= Math.pow(2.0, k-counter-1)*((int)floating.charAt(i)-48);
            counter++;
            //System.out.print(floating.charAt(i));
        }
        //System.out.println();
        //System.out.println(num/Math.pow(2.0, k)+"      binary");
        return num/Math.pow(2.0, k);
    }
    void decompress(List<Character> uniqe, double l, double u, double freq[], int num){
        calcK();
        binaryCode=convertBinary(0);
        //System.out.println(binaryCode+"    binary");
        l = setLower(l, u - l, freq[0]);
        u = setUpper(l, u - l, freq[freq.length-1]);
        code=setCode(binaryCode,l,u);
        //System.out.println(code+"    code");
        double l2,range;
        for(int i=0;i<num;i++){

            for(int x=0;x<freq.length-1;x++){
                //System.out.println("num     "+i+"     code:   "+code+"      binarycode:"+binaryCode);

                if(code<freq[x+1] && code>=freq[x]){
                    //System.out.println("num     "+i);

                    System.out.print(uniqe.get(x));    //printing the string
                    range=u-l;
                    l2=l;
                    l = setLower(l2, range, freq[x]);
                    u = setUpper(l2, range, freq[x+1]);
                    //System.out.println(l+"       lower");
                    //System.out.println(u+"       upeer");
                    while(l<.5 && u<.5){//scalling 1
                        l=l*2;
                        u=u*2;
                        s+="0";
                        //System.out.println("0");

                    }while(l>.5 && u>.5){ // scalling 2
                        l-=.5;
                        u-=.5;
                        l=l*2;
                        u=u*2;
                        s+="1";
                        //System.out.println("1");//+l+"    "+u);

                    }
                    /*if(s.length()>floating.length())
                        break;*/
                    if(s.length()>k)
                        break;
                    //if(s.length()==0)
                        binaryCode=convertBinary(s.length());
                    /*else
                        binaryCode=convertBinary(s.length()-1);*/

                    //System.out.println("    for code   "+binaryCode);
                    code=setCode(binaryCode,l,u);
                    //System.out.println(code+"    for code   "+binaryCode+"      l: "+l+"    u:"+u);
                    //System.out.println(code+"    for code   "+(binaryCode-l)+"   "+(u-l));

                    //s="";
                    break;
                }

            }
        }

    }
}
