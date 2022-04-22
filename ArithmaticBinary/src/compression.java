import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class compression {
    String s,code="";
    double lower,upper,range,k=1;

    compression(String s){
        this.s=s;
    }

    double setLower(double l,double range,double lowRange){
        lower=l+range*lowRange;
        return lower;
    }
    double setUpper(double u,double range,double highRange){
        upper=u+range*highRange;
        return upper;
    }
    void setK(double freq[]){
        double n=1;
        for(int i=0;i<freq.length-1;i++){
            if(freq[i+1]-freq[i]<n)
                n=freq[i+1]-freq[i];
        }
        while(true){
            if(Math.pow(n, -1.0)>=Math.pow(2.0, k))
                k++;
            else
                break;
        }
        //System.out.println(k+"      kkkk");

    }
    String compress(double l, double u, double freq[], List<Character> uni){

        double out=0,l2;
        setK(freq);
        for(int i=1;i<s.length();i++){
            for(int x=0;x<uni.size();x++){
                if(Objects.equals(s.charAt(i-1), uni.get(x))) {
                    range = u - l;
                    l2 = l;
                    l = setLower(l2, range, freq[x]);
                    u = setUpper(l2, range, freq[x + 1]);
                    while(l<.5 && u<.5){//scalling 1
                        l=l*2;
                        u=u*2;
                        //System.out.print(0+"s1 ");
                        code+="0";
                        //////
                    }while(l>.5 && u>.5){ // scalling 2
                        l-=.5;
                        u-=.5;
                        l=l*2;
                        u=u*2;
                        //System.out.print(1+"s2 ");
                        code+="1";
                        /////////
                    }
                }

            }

        }
        //System.out.print(1+"k ");
        code+="1";
        for(int i=0;i<k-1;i++)
            code+="0";
        return code;
    }
}
