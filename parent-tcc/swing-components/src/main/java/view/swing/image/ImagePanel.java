package view.swing.image;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 2757296189155345318L;

	private BufferedImage image;
	private String imagePath;

	public ImagePanel() {

	}

	public ImagePanel(String imagePath) {
		setImagePath(imagePath);
	}

	public void setImagePath(String path) {
		if (hasChanged(path)) {
			try {
				imagePath = path;
				createImage();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean hasChanged(String path) {
		return !StringUtils.equalsIgnoreCase(imagePath, path);
	}

	public String getImagePath() {
		return imagePath;
	}

	private void createImage() throws IOException {
		if (StringUtils.isNotEmpty(imagePath)) {
			image = ImageIO.read(new File(imagePath));
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image, 0, 0, this);
		}
	}

}
