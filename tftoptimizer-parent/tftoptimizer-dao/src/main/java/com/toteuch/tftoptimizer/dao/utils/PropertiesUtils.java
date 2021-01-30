package com.toteuch.tftoptimizer.dao.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertiesUtils {

	private static final String ROOT_FOLDER = "tftoptimizer.dao.rootfolder";
	private static final String ROOT_FOLDER_DEFAUT = System.getProperty("user.home") + "\\AppData\\Local\\TFTOptimizer\\";

	private static final Logger LOG = LogManager.getLogger(PropertiesUtils.class);

	private static Properties prop = new Properties();

	private PropertiesUtils() {
	}

	public static String getRootFolder() {
		return prop.getProperty(ROOT_FOLDER, ROOT_FOLDER_DEFAUT);
	}

	public static void readProperties(String filename) {
		try {
			prop.load(ClassLoader.getSystemResourceAsStream(filename));
		} catch (IOException e) {
			LOG.error(String.format("Couldn't load properties file %s", filename));
		}
	}

}
