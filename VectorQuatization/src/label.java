public class label {
    int [][]arr=new int[2][2];
    void setArr(int a[][]){arr=a;}
    int[][] getArr(){return arr;}
    void print(){
        for(int i=0;i<2;i++) {
            for (int x = 0; x < 2; x++)
                System.out.print(arr[i][x] + "  ");
            System.out.println();
        }
    }
}
