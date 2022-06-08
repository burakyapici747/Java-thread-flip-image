import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args){
        File file = new File("car.jpg");

        try{
            long startTime = System.currentTimeMillis();
            BufferedImage bImage = ImageIO.read(file);
            BufferedImage resultImage = ImageIO.read(file);


            int thread1StartX = 0, thread1EndX = bImage.getWidth();
            int thread1StartY = 0, thread1EndY = bImage.getHeight() / 4;

            int thread2StartX = 0, thread2EndX = bImage.getWidth();
            int thread2StartY = bImage.getHeight() / 4 + 1, thread2EndY = (bImage.getHeight() / 4) * 2;

            int thread3StartX = 0, thread3EndX = bImage.getWidth();
            int thread3StartY = (bImage.getHeight() / 4) * 2 + 1, thread3EndY = (bImage.getHeight() / 4) * 3;

            int thread4StartX = 0, thread4EndX = bImage.getWidth();
            int thread4StartY = (bImage.getHeight() / 4) * 3 + 1, thread4EndY = bImage.getHeight();


            MyThread thread_1 = new MyThread("Thread 1", thread1StartX, thread1EndX, thread1StartY, thread1EndY, resultImage, bImage);
            thread_1.start();
            MyThread thread_2 = new MyThread("Thread 2", thread2StartX, thread2EndX, thread2StartY, thread2EndY,resultImage, bImage);
            thread_2.start();
            MyThread thread_3 = new MyThread("Thread 3", thread3StartX, thread3EndX, thread3StartY, thread3EndY, resultImage, bImage);
            thread_3.start();
            MyThread thread_4 = new MyThread("Thread 4", thread4StartX, thread4EndX, thread4StartY, thread4EndY, resultImage, bImage);
            thread_4.start();

            try{
                thread_1.join();
                thread_2.join();
                thread_3.join();
                thread_4.join();
            }catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            ImageIO.write(resultImage, "jpg", new File("newImage.jpg"));
            long elapsedTime = System.currentTimeMillis() - startTime;

            System.out.println("Geçen zaman => " + elapsedTime);
            long startTimeNormal = System.currentTimeMillis();

            BufferedImage bImageNormal = ImageIO.read(file);
            BufferedImage resultImageNormal = ImageIO.read(file);

            FlipImage flipImageNormal = new FlipImage("Normal Flip Image", 0, 3840, 0, 2160, bImageNormal, resultImageNormal);
            flipImageNormal.run();
            long elapsedTimeNormal = System.currentTimeMillis() - startTimeNormal;
            System.out.println("Geçen zaman => " + elapsedTimeNormal);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }




    }
}
