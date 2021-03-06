import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments 
     */
    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();  
    }

    /**
     * Constructor that takes a file name and creates the picture 
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Constructor that takes a picture and creates a 
     * copy of that picture
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
            " height " + getHeight() 
            + " width " + getWidth();
        return output;

    }

    /** Method to set the blue to 0 */
    public void zeroBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(0);
            }
        }
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right */
    public void mirrorVertical()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    public void mirrorVerticalRightToLeft()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][width -1 - col];
                rightPixel = pixels[row][col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    public void mirrorHorizontal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int height = pixels.length;
        for (int col = 0; col < pixels[0].length; col++)
        {
            for (int row = 0; row < height / 2; row++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[height-1-row][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        } 
    }

    public void mirrorHorizontalBotToTop()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int height = pixels.length;
        for (int col = pixels[0].length-1; col >=0; col--)
        {
            for (int row = pixels.length-1; row > height / 2; row--)
            {
                bottomPixel = pixels[row][col];
                topPixel = pixels[height-1-row][col];
                topPixel.setColor(bottomPixel.getColor());
            }
        } 
    }

    /** Mirror just part of a picture of a temple */
    public void mirrorTemple()
    {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++)
            {

                leftPixel = pixels[row][col];      
                rightPixel = pixels[row]                       
                [mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
                count++;
            }
        }
        System.out.println("The value of count is: " + count);
    }

    public void mirrorArms()
    {
        int mirrorPoint = 207;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();
        for (int row = 159; row <mirrorPoint ; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 96; col < 296; col++)
            {

                leftPixel = pixels[row][col];      
                rightPixel = pixels[mirrorPoint -row + mirrorPoint]                       
                [col];
                rightPixel.setColor(leftPixel.getColor());
                count++;
            }
        }
    }

    /** copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic, 
    int startRow, int startCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow; 
        fromRow < fromPixels.length &&
        toRow < toPixels.length; 
        fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startCol; 
            fromCol < fromPixels[0].length &&
            toCol < toPixels[0].length;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }   
    }

    /** Method to create a collage of several pictures */
    public void createCollage(Picture pic)
    {
        Picture mJ = new Picture("mJ.jpg");
        pic.copy(mJ,0,0);
        Picture mJPurple = new Picture("mJ.jpg");
        mJPurple.purple();
        pic.copy(mJPurple,0,200);
        Picture mJgray = new Picture("mJ.jpg");
        mJgray.grayscale();
        pic.copy(mJgray,0,400);
        Picture mJhorizontal = new Picture("mJ.jpg");
        mJhorizontal.mirrorHorizontal();
        pic.copy(mJhorizontal,0,600);
        Picture mJyellow = new Picture("mJ.jpg");
        mJyellow.yellow();
        pic.copy(mJyellow,250,0);
        Picture mJNoBlue = new Picture("mJ.jpg");
        mJNoBlue.zeroBlue();
        pic.copy(mJNoBlue,250,200);
        Picture mJhorizontalBT = new Picture("mJ.jpg");
        mJhorizontalBT.mirrorHorizontalBotToTop();
        pic.copy(mJhorizontalBT,250 ,400);
        Picture mJvertical = new Picture("mJ.jpg");
        mJvertical.mirrorVertical();
        pic.copy(mJvertical,250,600);
        Picture mJvertical1 = new Picture("mJ.jpg");
        mJvertical1.mirrorVerticalRightToLeft();
        pic.copy(mJvertical1,500,0);
        Picture mJtransform1 = new Picture("mJ.jpg");
        mJtransform1.mirrorVertical();
        mJtransform1.purple();
        pic.copy(mJtransform1,500,200);
        Picture mJSepia = new Picture("mJ.jpg");
        mJSepia.grayscale();
        mJSepia.sepia();
        pic.copy(mJSepia,500,400);
        Picture mJtransform2 = new Picture("mJ.jpg");
        mJtransform2.mirrorHorizontal();
        mJtransform2.yellow();
        pic.copy(mJtransform2,500,600);
        pic.write("collage.jpg");
    }

    /** Method to show large changes in color 
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist)
    {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; 
            col < pixels[0].length-1; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col+1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor) > 
                edgeDist)
                    leftPixel.setColor(Color.BLACK);
                else
                    leftPixel.setColor(Color.WHITE);
            }
        }
    }

    public void keepOnlyBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setGreen(0);
                pixelObj.setRed(0);
            }
        }
    }

    public void negate()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                int currentRed = pixelObj.getRed();
                int currentBlue = pixelObj.getBlue();
                int currentGreen = pixelObj.getGreen();
                pixelObj.setGreen(255- currentGreen);
                pixelObj.setBlue(255- currentBlue);
                pixelObj.setRed(255- currentRed);
            }
        }
    }

    public void grayscale()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                int currentRed = pixelObj.getRed();
                int currentBlue = pixelObj.getBlue();
                int currentGreen = pixelObj.getGreen();
                int averageColor = currentRed + currentBlue + currentGreen /3;
                pixelObj.setGreen(averageColor);
                pixelObj.setBlue(averageColor);
                pixelObj.setRed(averageColor);
            }
        }
    }

    public void purple()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setGreen(0);
            }
        }
    }
     public void yellow()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setRed(0);
            }
        }
    }
    public void cropAndCopy( Picture sourcePicture, int startSourceRow, int endSourceRow, int startSourceCol, int endSourceCol,
    int startDestRow, int startDestCol )
    {

    }
    public void sepia()
    {
        
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                if(pixelObj.getRed()<60)
                {
                    pixelObj.setBlue(pixelObj.getBlue() *9/10);
                    pixelObj.setGreen(pixelObj.getGreen() *9/10);
                    pixelObj.setRed(pixelObj.getRed() *9/10);
                }
                if(pixelObj.getRed()>190)
                    {
                        pixelObj.setBlue(pixelObj.getBlue() *8/10);
                    }
                else{
                        pixelObj.setBlue(pixelObj.getBlue() *9/10);
                    }
                
            }
        }
    }
    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) 
    {
      Picture canvas = new Picture(750,800);
// 
//         mJ.explore();
//         mJ.grayscale();
//         mJ.explore();
     canvas.createCollage(canvas);
     canvas.explore();
    }

} // this } is the end of class Picture, put all new methods before this
