import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedImage image = ImageIO.read(new File("E:\\1.jpg"));
        int width = image.getWidth();
        int height = image.getHeight();
        quantization q;

        int [][]arr;
        arr = new int[width][height];

        for(int i = 0; i < width; i++)
            for(int j = 0; j < height; j++){
                int rgb=image.getRGB(i, j);
                int r=(rgb>>16)&0xff;
                arr[i][j] = r;

            }

        /*for(int i = 0; i < 16; i++) {
            for (int j = 0; j < 8; j++)
                System.out.print(arr[i][j] + "  ");
            System.out.println();
        }*/



        System.out.println();

        q=new quantization(arr,width,height);
        q.compress();

    }
}
