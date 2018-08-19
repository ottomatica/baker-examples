package edu.ncsu.csc.selenium;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class BrowserHandler {

	static private boolean Mac() {
		return OS.contains("Mac OS X");
	}

	static private boolean Linux() {
		return OS.contains("Linux");
	}

	static private boolean Windows() {
		return OS.contains("Windows");
	}

	private ChromeDriver driver;
	
	public ChromeDriver getDriver() {
		return driver;
	}

	static private BrowserHandler instance = new BrowserHandler();

	static private String OS; 

	private BrowserHandler() {
		
		OS = System.getProperty("os.name");

		ChromeDriverManager.getInstance().setup();
		final ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1200x600");
		options.addArguments("blink-settings=imagesEnabled=false");

		if (Linux()) {
			options.setBinary("/usr/bin/google-chrome");
		} else if (Windows()) {
			options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		} else if (Mac()) {
			options.setBinary("/Applications/Google Chrome/Contents/MacOS/Google Chrome");
		}

		driver = new ChromeDriver(options);

	}

	static public BrowserHandler getInstance() {
		return instance;
	}

}
