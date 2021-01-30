package com.toteuch.tftoptimizer.dao.parser;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.toteuch.tftoptimizer.dao.utils.FileUtils;

public class HTMLParser {
	private static final Logger LOG = LogManager.getLogger(HTMLParser.class);

	protected static String getUrlContents(String theUrl) {
		LOG.debug(String.format("Getting url contents from %s", theUrl));
		StringBuilder content = new StringBuilder();
		// many of these calls can throw exceptions, so i've just
		// wrapped them all in one try/catch statement.
		try {
			// create a url object
			URL url = new URL(theUrl);
			// create a urlconnection object
			URLConnection urlConnection = url.openConnection();
			// wrap the urlconnection in a bufferedreader
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;
			// read from the urlconnection via the bufferedreader
			LOG.trace(String.format("Reading lines from output..."));
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOG.trace(String.format("End of getting url contents"));
		return content.toString();
	}

	protected static Image getImage(String sUrl, String rootFolder, String filename) {
		String ext = StringUtils.substring(sUrl, sUrl.lastIndexOf("."), sUrl.length());
		String fullFilename = String.format("%s%s%s", rootFolder, filename, ext);
		Image imgFromFS = FileUtils.getImage(fullFilename);
		if (imgFromFS != null) {
			return imgFromFS;
		}
		LOG.debug(String.format("Getting image from %s", sUrl));
		int attempt = 1;
		Image image = null;
		URL url = null;
		while (image == null && attempt <= 3) {
			try {
				url = new URL("https:" + sUrl);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			LOG.trace(String.format("URL object's url : %s", url));
			try {
				if (url != null) {
					LOG.trace(String.format("Reading image, attempt #%d...", attempt));
					image = ImageIO.read(url);
					LOG.trace("End of read");
				}
			} catch (IOException e) {
				LOG.error(e);
			}
			attempt++;
		}
		if (image == null) {
			LOG.error(String.format("Image %s can't be read", url));
		} else {
			LOG.trace(String.format("Writing file %s on disk...", fullFilename));
			FileUtils.writeToFolder(fullFilename, image);
			LOG.info(String.format("File %s writed on disk", fullFilename));
		}
		LOG.trace("End of reading image");
		return image;
	}

	public void emptyCache(String rootFolder) {
		FileUtils.empty(rootFolder);
	}
}
