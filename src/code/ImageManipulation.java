package code;

import image.APImage;
import image.Pixel;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
        /* APImage picture = new APImage("cyberpunk2077.jpg");
        picture.draw();
        // testing if my challenges work
        grayScale("cyberpunk2077.jpg");
        blackAndWhite("cyberpunk2077.jpg");
        edgeDetection("cyberpunk2077.jpg",20);
        reflectImage("cyberpunk2077.jpg");
        rotateImage("cyberpunk2077.jpg");*/

    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        APImage picture = new APImage(pathOfFile);
        for (int i=0; i<picture.getWidth(); i++) {
            for (int k=0; k<picture.getHeight(); k++) {
                Pixel pixel = picture.getPixel(i,k); // get the pixel
                // find the average of red, green and blue components
                int average = getAverageColour(pixel);
                pixel.setRed(average);
                pixel.setBlue(average);
                pixel.setGreen(average);
            }
        }
        picture.draw();
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        return ((pixel.getRed()+pixel.getBlue()+pixel.getGreen())/3);
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        APImage picture = new APImage(pathOfFile);
        APImage output = new APImage(pathOfFile); // to preserve the og image
        for (int i=0; i<picture.getWidth(); i++) {
            for (int k=0; k<picture.getHeight(); k++) {
                Pixel pixel = picture.getPixel(i,k); // get the pixel
                // find the average of red, green and blue components
                int average = getAverageColour(pixel);
                if (average<128) {
                    average = 0;  //set to black
                }
                else {
                    average = 255; //set to white
                }
                pixel.setGreen(average);
                pixel.setBlue(average);
                pixel.setRed(average);
            }
        }
        picture.draw();
    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String pathToFile, int threshold) {
        APImage picture = new APImage(pathToFile);
        for (int i=0; i<picture.getHeight()-1; i++) {
            for (int k=1; k<picture.getWidth(); k++) {
                Pixel pixel = picture.getPixel(k,i);
                // compare pixel and the pixel to the left
                int setColor = 255; // if not either circumstance below, set pixel to white
                if (Math.abs(getAverageColour(pixel)-getAverageColour(picture.getPixel(k-1,i)))>threshold) {
                    setColor=0; // set to black
                }
                else if (Math.abs(getAverageColour(pixel)-getAverageColour(picture.getPixel(k,i+1)))>threshold) {
                    setColor=0; //set to black
                }
                pixel.setRed(setColor);
                pixel.setBlue(setColor);
                pixel.setGreen(setColor);
            }
        }
        picture.draw();
    }

    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {
        APImage image = new APImage(pathToFile);
        for (int i=0; i<image.getHeight(); i++) {
            for (int k=0; k<image.getWidth()/2; k++) {
                Pixel pixel = image.getPixel(k,i);
                Pixel pixel2 = image.getPixel(image.getWidth()-1-k,i);
                image.setPixel(image.getWidth()-1-k,i,pixel);
                image.setPixel(k,i,pixel2);
            }
        }
        image.draw();
    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String pathToFile) {
        APImage ogImage = new APImage(pathToFile);
        APImage image = new APImage(ogImage.getHeight(), ogImage.getWidth()); // width becomes height, height becomes width
        for (int i=0; i<ogImage.getWidth(); i++) {
            for (int k=0; k<ogImage.getHeight(); k++) {
                Pixel pixel=ogImage.getPixel(i,k);
                image.setPixel(ogImage.getHeight()-1-k,i,pixel);
            }
        }
        image.draw();
    }

}
