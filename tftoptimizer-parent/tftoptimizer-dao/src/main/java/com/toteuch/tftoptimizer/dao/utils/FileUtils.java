package com.toteuch.tftoptimizer.dao.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.toteuch.tftoptimizer.dao.TFTOptimizerAbstractDao;

public class FileUtils extends TFTOptimizerAbstractDao {

	private static final Logger LOG = LogManager.getLogger(FileUtils.class);

	private FileUtils() {
	}

	public static void writeToFolder(String path, Image img) {

		File file = new File(path);
		file.getParentFile().mkdirs();
		BufferedImage bi = toBufferedImage(img);
		try {
			file.createNewFile();
			ImageIO.write(bi, "png", file);
		} catch (IOException e) {
			LOG.error(String.format("Failed to write file %s on disk", path), e);
		}
	}

	public static Image getImage(String path) {
		File f = new File(path);
		if (f.exists()) {
			try {
				LOG.trace(String.format("Starting reading file on FS %s...", path));
				Image ret = ImageIO.read(f);
				LOG.trace(String.format("End of reading file on FS %s", path));
				return ret;

			} catch (IOException e) {
				LOG.error(String.format("Failed to read file %s on disk", path), e);
			}
		} else {
			LOG.info(String.format("File %s not found on disk. We'll try to get them on next time !", path));
		}
		return null;
	}

	public static void empty(String path) {
		File folder = new File(path);
		if (folder.exists()) {
			folder.delete();
		}
	}
}
