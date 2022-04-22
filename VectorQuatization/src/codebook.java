import java.util.ArrayList;
import java.util.List;

public class codebook {
    double [][]arr=new double[2][2];
    List <Integer> vectorsWidth=new ArrayList<>();
    List <Integer> vectorsHeight=new ArrayList<>();
    List <Integer> labelsList=new ArrayList<>();

    public void setArr(double[][] arr) {
        this.arr = arr;
    }

    public double[][] getArr() {
        return arr;
    }

    public void setVectors(int w,int h) {
        this.vectorsWidth.add(w);
        this.vectorsHeight.add(h);
    }

    public List<Integer> getVectorsWidth() {
        return vectorsWidth;
    }

    public List<Integer> getVectorsHeight() {
        return vectorsHeight;
    }

    public void setLabel(int l) {
        this.labelsList.add(l);

    }

    public List<Integer> getLabel() {
        return labelsList;
    }

    void printList(){
        for(int i=0;i<100;i++)
            System.out.print(labelsList.get(i)+"  ");
        System.out.println(labelsList.size());
    }

    void clearLabel(){labelsList.clear();}
    public void setAVG(int l){
        for(int i=0;i<2;i++)
            for (int x = 0; x < 2; x++)
                arr[i][x]=arr[i][x]/l;
    }

    void print(){
        for(int i=0;i<2;i++) {
            for (int x = 0; x < 2; x++)
                System.out.print(arr[i][x] + "  ");
            System.out.println();
        }
    }
}
