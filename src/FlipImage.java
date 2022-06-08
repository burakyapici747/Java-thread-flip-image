import java.awt.image.BufferedImage;

public class FlipImage {
    private String name;
    private int startX;
    private int endX;
    private int startY;
    private int endY;
    private BufferedImage resultImage;
    private BufferedImage originalImage;

    public FlipImage(String name, int startX, int endX, int startY, int endY, BufferedImage resultImage, BufferedImage originalImage){
        this.name = name;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.resultImage = resultImage;
        this.originalImage = originalImage;

    }

    public void run(){
        for(int y = this.startY; y < this.endY; y++){

            for(int x = this.startX; x < this.endX; x++){
                int currentRGB = originalImage.getRGB(x, y);

                resultImage.setRGB(this.endX - 1 - x, y, currentRGB);


            }
        }
        System.out.println(this.name + " bitti");
    }

}
