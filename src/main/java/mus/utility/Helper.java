package mus.utility;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Helper {
	public static byte[] convertCameraImage(){
		try {
			return convertImage(HendlStallUtility.getSystemTempDir().replace("\\",  "/")+"/onvif.jpg");
		} catch (IOException e) {
			System.err.println("error converting image");
			e.printStackTrace();
		}
		return null;
	}
	public static byte[] convertImage(String path) throws IOException{
		File imgPath = new File(path);
		BufferedImage bufferedImage = ImageIO.read(imgPath);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "jpg", baos);
		byte[] bytes = baos.toByteArray();
		return bytes;
	}
}
