import java.util.List;
import java.util.Objects;

public class Compression {
    String s;
    double lower,upper,range;

    Compression(String s){
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
    double compress(double l, double u, double freq[], List<Character> uni){

        double out=0,l2;
        for(int i=1;i<s.length();i++){
            for(int x=0;x<uni.size();x++){
                if(Objects.equals(s.charAt(i-1), uni.get(x))) {
                    range = u - l;
                    l2 = l;
                    l = setLower(l2, range, freq[x]);
                    u = setUpper(l2, range, freq[x + 1]);
                    out = (u + l) / 2;
                }

            }


        }

        System.out.println(out);
        return out;
    }
}
