import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class quantization {
    int [][]image,arr;
    int width,height,spliting=1;
    List <label> labels=new ArrayList<>();
    List <codebook> codebooks=new ArrayList<>();
    List <codebook> old;
    List <codebook> avg;

    quantization(int [][]imag,int width,int height){
        image=imag;
        arr=new int[8][8];
        this.width=width;
        this.height=height;
    }

    void setLabels(int sw,int sh){
        int w=0,h=0;
        arr=new int[2][2];
        label l=new label();
        for(int i=0;i<2;i++){
            for(int x=0;x<2;x++){
                arr[i][x]=image[i+sw][x+sh];
            }
        }
        l.setArr(arr);
        labels.add(l);
    }

    void split(int index){

    }

    void setCodebooks(){
        codebook c=new codebook();
        double [][]codebookArray=new double[2][2];
        for(int i=0;i<2;i++)
            for (int x = 0; x < 2; x++)
                codebookArray[i][x]=0;

        for(int j=0;j<labels.size();j++){
            arr=new int[2][2];

            arr=labels.get(j).getArr();

            for(int i=0;i<2;i++) {
                for (int x = 0; x < 2; x++) {
                    codebookArray[i][x]+=arr[i][x];
                }
            }
        }
        c.setArr(codebookArray);
        if(spliting==1) {
            for (int i = 0; i < 2; i++)
                for (int x = 0; x < 2; x++)
                    c.setVectors(i, x);
        }

        codebooks.add(c);
    }


    void setnewCodebooks(int index){
        codebook c=new codebook();
        double [][]codebookArray=new double[2][2];
        for(int i=0;i<2;i++)
            for (int x = 0; x < 2; x++)
                codebookArray[i][x]=0;


            List<Integer>la=new ArrayList<>();
        la=codebooks.get(index).getLabel();
        for(int j=0;j<la.size();j++){
            arr=new int[2][2];

            arr=labels.get(la.get(j)).getArr();

            for(int i=0;i<2;i++) {
                for (int x = 0; x < 2; x++) {
                    codebookArray[i][x]+=arr[i][x];
                }
            }
        }
        c.setArr(codebookArray);
        if(spliting==1) {
            for (int i = 0; i < 2; i++)
                for (int x = 0; x < 2; x++)
                    c.setVectors(i, x);
        }

        codebooks.remove(index);
        codebooks.add(c);
    }

    void splitcodebook(int index){

        //for(int i=0;i<codebooks.size();i++){
            double [][]arr=new double[2][2];
            double [][]arr2=new double[2][2];
            codebook code=new codebook();
            arr=codebooks.get(index).getArr();
            for(int x=0;x<2;x++){
                for (int y=0;y<2;y++){
                    arr[x][y]=Math.floor(arr[x][y]);
                }
            }
            codebooks.get(index).setArr(arr);
            //codebooks.get(index).print();
            for(int x=0;x<2;x++){
                for (int y=0;y<2;y++){
                    arr2[x][y]=arr[x][y]+1;
                }
            }
            code.setArr(arr2);



            //code.print();

            codebooks.add(code);
        /*System.out.println("code");
            codebooks.get(0).print();
        System.out.println("");
            codebooks.get(1).print();
        System.out.println("");
            //codebooks.get(1).print();
            //codebooks.get(index+1).print();*/
            spliting*=2;

        //}
    }

    double claculateLabel(int Codebookindex,int labelIndex){
        double value=0;
        double [][]code=new double[2][2];
        int [][]labelarr=new int[2][2];

        code=codebooks.get(Codebookindex).getArr();
        labelarr=labels.get(labelIndex).getArr();
        for(int i=0;i<2;i++){
            for (int x=0;x<2;x++){
                value+=((code[i][x]-labelarr[i][x])*(code[i][x]-labelarr[i][x]));
            }
        }

        //System.out.println(value+"   vlaue     "+labelIndex+"    "+Codebookindex);
        return value;
    }

    void compress(){

        for(int i=0;i<height;){
            for(int x=0;x<width;){
                setLabels(x, i);
                x+=2;
            }
            i+=2;
        }


        System.out.println(labels.size());


        setCodebooks();
        codebooks.get(0).setAVG(labels.size());

        System.out.println();

        int counter=0;
        boolean cond=true;
        while(codebooks!=avg){//for(int i=0;i<codebooks.;i++) {
            double [][]arr=new double[2][2];
            if(codebooks.size()<4) {
                if(counter==0){
                    splitcodebook(0);
                    counter++;
                }
                else
                    for(int x=0;x<2;x++)
                        splitcodebook(x);
            }


            for(int x=0;x<labels.size();x++){
                int lowestcodebookIndex=0;
                double lowestValue=0,value=0;
                for(int y=0;y<codebooks.size();y++){
                    value=claculateLabel(y,x);
                    if(value<=lowestValue || y==0){
                        lowestValue=value;
                        lowestcodebookIndex=y;
                    }
                }
                codebooks.get(lowestcodebookIndex).setLabel(x);
            }


            System.out.println(codebooks.size()+"   sssss");
            for(int x=0;x<codebooks.size();x++) {
                codebooks.get(x).setAVG(codebooks.get(x).getLabel().size());

                if(codebooks==avg) {
                    cond=false;
                    break;
                }
                avg=new ArrayList<>(codebooks);
                /*codebooks.get(x).clearLabel();
                setnewCodebooks(x);*/
            }

        }

        //codebooks.get(0).printList();


        //System.out.println(codebooks.size());
        //codebooks.get(1).printList();

        /*for(int i=0;i<4;i++){
            System.out.println("index: "+i+"        "+codebooks.get(i).getLabel().size());
            codebooks.get(i).printList();
        }*/



    }

}
