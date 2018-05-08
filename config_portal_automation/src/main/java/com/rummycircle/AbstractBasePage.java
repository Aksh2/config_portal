package com.rummycircle;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.rummycircle.ui.WebDriverCommandHelper;
import com.rummycircle.utils.testutils.PropertyReader;

public abstract class AbstractBasePage {
	
	protected WebDriver driver = null;
	protected WebDriverCommandHelper cmd=null;
	
	protected static long SHORT_WAIT = 3000l; //3 secs
	protected static long MEDIUM_WAIT = 5000l; //5 secs
	protected static long LONG_WAIT = 10000l; // 10 secs
	protected static long TAKE_A_BREAK = 20000l; // 20 secs
	protected static long OPERATION_TIMEOUT=60000l; //60 secs
	
	public AbstractBasePage(WebDriver driver){
		this.driver=driver;
		cmd = new WebDriverCommandHelper(driver);
	}
	
	/**
	 * read xpath file -> /src/test/resources/xpaths/<filename.properties> 
	 * Sample -PropertyReader.loadLocatorProperties("samplexpath.properties");
	 * 
	 * @param fileName
	 * @return
	 */
	
	
	protected Properties loadLocators(String fileName){
		return PropertyReader.loadLocatorProperties(fileName);
	}
	
	/**
	 * read xpath file -> /src/test/resources/messages/<language>/<filename.properties> 
	 * Sample -PropertyReader.loadMessageProperties("samplemessage.properties", "en");
	 * 
	 * @param fileName
	 * @param language
	 * @return
	 */
	
	protected Properties loadMessages(String fileName, String language){
		return PropertyReader.loadMessageProperties(fileName, language);
	}
	
	
	/**
	 * read xpath file -> /src/test/resources/<filename> 
	 * Sample -PropertyReader.loadCustomProperties("custom.properties");
	 * 
	 * @param fileName
	 * @param language
	 * @return
	 */
	
	protected Properties loadCustomFile(String filename){
		return PropertyReader.loadCustomProperties(filename);
	}
}
