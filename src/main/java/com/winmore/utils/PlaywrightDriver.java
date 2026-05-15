package com.winmore.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitUntilState;

public class PlaywrightDriver {
	
	public static PlaywrightDriver playwrightDriver;
	private Playwright playwright;
	public Browser browser;
	public Page page;
	public static Properties objectRepository = new Properties();
	public static Properties config = new Properties();
	private static FileInputStream fis;

	private static ThreadLocal<Playwright> pw = new ThreadLocal<>();
	private static ThreadLocal<Browser> br = new ThreadLocal<>();
	private static ThreadLocal<Page> pg = new ThreadLocal<>();

	public static Playwright getPlaywright() {

		return pw.get();
	}

	public static Browser getBrowser() {

		return br.get();
	}

	public static Page getPage() {

		return pg.get();
	}

	private PlaywrightDriver() {

		try {
			fis = new FileInputStream("./src/main/resources/objectRepository.properties");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			objectRepository.load(fis);

		} catch (IOException e) {

			e.printStackTrace();
		}

		try {
			fis = new FileInputStream("./src/main/resources/config.properties");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			config.load(fis);

		} catch (IOException e) {

			e.printStackTrace();
		}

		playwright = Playwright.create();
		pw.set(playwright);

		if (config.getProperty("browser").equals("chrome")) {

			// browser = getPlaywright().chromium()
			// .launch(new
			// BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));

			browser = getPlaywright().chromium()
					.launch(new BrowserType.LaunchOptions()
							.setChannel("chrome")
							.setHeadless(false)
							.setArgs(Arrays.asList("--start-maximized")));

		} else if (config.getProperty("browser").equals("firefox")) {

			browser = getPlaywright().firefox()
					.launch(new BrowserType.LaunchOptions().setChannel("firefox").setHeadless(false));

		}

		// br.set(browser);
		// page = getBrowser().newPage();
		// pg.set(page);

		br.set(browser);

		// disable default viewport so maximized window works
		page = getBrowser().newPage(new Browser.NewPageOptions().setViewportSize(null));
		pg.set(page);
	}

	public static void setupDriver() {

		playwrightDriver = new PlaywrightDriver();
	}

	public static void openPage(String url) {

		//getPage().navigate(url);
		getPage().navigate(
		        url,
		        new Page.NavigateOptions()
		            .setWaitUntil(WaitUntilState.DOMCONTENTLOADED) // don't block on full load
		            .setTimeout(60000) // 60s timeout
		    );
	}

	public static void closeBrowser() {

		getBrowser().close();
		getPage().close();
	}

	public static void quitPlaywright() {

		if (getPage() != null) {

			getPlaywright().close();

		}

	}

}