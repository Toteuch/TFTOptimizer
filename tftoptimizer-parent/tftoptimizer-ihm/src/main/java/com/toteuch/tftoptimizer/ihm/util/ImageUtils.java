package com.toteuch.tftoptimizer.ihm.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ImageUtils {
	public static ImageIcon getScaledImageIcon(Image srcImg, int s) {
		BufferedImage resizedImg = new BufferedImage(s, s, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, s, s, null);
		g2.dispose();
		return new ImageIcon(resizedImg);
	}

	public static ImageIcon getScaledImageIconFromClassLoader(String fp, int s) {
		Image img = new ImageIcon(ClassLoader.getSystemResource(fp)).getImage();
		return getScaledImageIcon(img, s);
	}

	public static ImageIcon BlackAndWhiteScaledImageIconFromClassLoader(String fp, int s) {
		BufferedImage resizedImg = new BufferedImage(s, s, BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D graphic = resizedImg.createGraphics();
		graphic.drawImage(getScaledImageIconFromClassLoader(fp, s).getImage(), 0, 0, Color.WHITE, null);
		graphic.dispose();
		return new ImageIcon(resizedImg);
	}
}
