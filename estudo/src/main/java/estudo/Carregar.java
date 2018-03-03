package estudo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Carregar {
	public static BufferedImage abrir(String diretorio) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(diretorio));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return image;
	}
}
