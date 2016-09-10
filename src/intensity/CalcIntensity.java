package intensity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.*;

public class CalcIntensity {

	public static void main(String[] args) {

		FileReader fileReader;
		try {
			fileReader = new FileReader(new File("data/config.txt"));
			BufferedReader br = new BufferedReader(fileReader);

			String fileName = null;
			while ((fileName = br.readLine()) != null) {
				// reading lines until the end of the file
				File imgFile = new File(fileName);
				int width = 0;
				int height = 0;
				try {
					BufferedImage bimg = ImageIO.read(imgFile);
					//BufferedImage grayImg = toGray(bimg);
					//saveImageToFile(grayImg, fileName + "-gray.jpg");
					width = bimg.getWidth();
					height = bimg.getHeight();
					long intensitySum = 0;
					for (int i = 0; i < width; i++) {
						for (int j = 0; j < height; j++) {
							int grayPoint = getGray(bimg.getRGB(i, j));
							intensitySum += grayPoint;
						}
					}
					System.out.println("Results for image: " + fileName);
					System.out.println("Width: " + width);
					System.out.println("Height: " + height);
					System.out.println("Total pixels: " + width * height);
					System.out.println("Intensity sum: " + intensitySum);
					System.out.println("Intensity average: " + intensitySum / (width * height));
					
				} catch (IOException e) {
					System.out.println("There was a problem reading the image.");
					e.printStackTrace();
				}
				System.out.println("**************************************");
			}
			System.out.println("**************THE-END*****************");
			
		} catch (FileNotFoundException e1) {
			System.out.println("Configuration file not found. "
					+ "Create a file with the name \"config.txt\" under the \"data\" folder. " +e1.getMessage());
		} catch (IOException e) {
			System.out.println("Problems reading the configuration file \"config.txt\". "
					+ "It should contain the images location for analysing. ");
		}
	}

	static int getGray(int rgb){
		int r = (rgb >> 16) & 0xFF;
		int g = (rgb >> 8) & 0xFF;
		int b = (rgb & 0xFF);
		int gray = (r + g + b) / 3;
		return gray;
	}
	
	static BufferedImage toGray(BufferedImage origPic) {
	    BufferedImage pic = new BufferedImage(origPic.getWidth(), origPic.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
	    Graphics g = pic.getGraphics();
	    g.drawImage(origPic, 0, 0, null);
	    g.dispose();
	    return pic;
	}
	
	static void saveImageToFile(BufferedImage pic, String fileName) throws IOException{
		File outputfile = new File(fileName);
		ImageIO.write(pic, "jpg", outputfile);
	}
	
}
