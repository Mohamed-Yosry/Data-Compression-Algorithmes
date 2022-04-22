import java.util.List;

public class Decompression {
    String s="";
    double floating;
    double lower,upper,code;

    Decompression(double floating){
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
    void decompress(List<Character> uniqe, double l, double u, double freq[], int num){

        l = setLower(l, u - l, freq[0]);
        u = setUpper(l, u - l, freq[freq.length-1]);
        code=setCode(floating,l,u);
        double l2,range;
        for(int i=0;i<num;i++){

            for(int x=1;x<freq.length;x++){
                if(code<=freq[x] && code>=freq[x-1]){
                    System.out.print(uniqe.get(x-1));
                    range=u-l;
                    l2=l;
                    l = setLower(l2, range, freq[x-1]);
                    u = setUpper(l2, range, freq[x]);
                    code=setCode(floating,l,u);

                    break;
                }

            }
        }

    }
}
