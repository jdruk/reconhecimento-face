package estudo;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import java.util.Arrays;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.imgproc.Imgproc;

public class App {

	public static void main(String[] args) {
	
		nu.pattern.OpenCV.loadShared();
		System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);
	
		
		BufferedImage fistImage = Carregar.abrir("image.jpg");
		BufferedImage secondImage = Carregar.abrir("image1.jpg");
		
		new App().compare_image(fistImage, secondImage);
		
	}

	public void compare_image(BufferedImage fistImage,BufferedImage secondImage){
		Mat firstImageMat = convertToMat(fistImage);
		Mat secondeImageMat = convertToMat(secondImage);
		
		Mat imageOneHistogram = new Mat();
		Mat imagemTwoHistogram = new Mat();
		
		MatOfFloat ranges = new MatOfFloat(0f,256f);
		MatOfInt histSize = new MatOfInt(25);
		
		Imgproc.calcHist(Arrays.asList(firstImageMat), new MatOfInt(0), 
				new Mat(), imageOneHistogram, histSize, ranges);
		Imgproc.calcHist(Arrays.asList(secondeImageMat), new MatOfInt(0),
				new Mat(), imagemTwoHistogram, histSize, ranges);
	    
		double res = Imgproc.compareHist(imageOneHistogram, imagemTwoHistogram, Imgproc.CV_COMP_CORREL);
		Double similiratyPorcentage = new Double(res*100);
		
		System.out.println("As imagens são cerga de " + similiratyPorcentage + " % iguais");
	}
	private Mat convertToMat(BufferedImage img){
		byte[] data = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
		Mat mat = new Mat(img.getHeight(),img.getWidth(),CvType.CV_8UC3);
		mat.put(0,0,data);
		
		Mat mat1 = new Mat(img.getHeight(),img.getWidth(),CvType.CV_8UC3);
		Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2HSV);
		
		return mat1;
	}
}
