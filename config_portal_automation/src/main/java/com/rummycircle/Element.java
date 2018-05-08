package com.rummycircle;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.rummycircle.utils.exceptions.RCException;

public class Element extends AbstractBasePage {

	public Element(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	public void clickElement(String locator, final Properties locatorFile) {
		if (locator.contains(".id")) {
			if (cmd.isElementPresent(By.id(locatorFile.getProperty(locator)))) {
				cmd.click(By.id(locatorFile.getProperty(locator)), String
						.format("Click on the element with the locator %s",
								locator));

			} else {
				throw new RCException(String.format(
						"Element with the locator %s is not found", locator));
			}

		} else {
			if (cmd.isElementPresent(By.xpath(locatorFile.getProperty(locator)))) {
				cmd.click(By.xpath(locatorFile.getProperty(locator)), String
						.format("Click on the element with the locator %s",
								locator));

			} else {
				throw new RCException(String.format(
						"Element with the locator %s is not found", locator));
			}
		}
	}

	public void fillElement(String locator, String value,
			final Properties locatorFile) {
		if (locator.contains(".id")) {
			if (cmd.isElementPresent(By.id(locatorFile.getProperty(locator)))) {
				cmd.sendKeys(By.id(locatorFile.getProperty(locator)), value,
						String.format(
								"Click on the element with the locator %s",
								locator));

			} else {
				throw new RCException(String.format(
						"Element with the locator %s is not found", locator));
			}

		} else if (locator.contains(".name")) {
			if (cmd.isElementPresent(By.name(locatorFile.getProperty(locator)))) {
				cmd.sendKeys(By.name(locatorFile.getProperty(locator)), value,
						String.format(
								"Click on the element with the locator %s",
								locator));

			} else {
				throw new RCException(String.format(
						"Element with the locator %s is not found", locator));
			}

		} else {
				if (cmd.isElementPresent(By.xpath(locatorFile.getProperty(locator)))) {
				cmd.sendKeys(By.xpath(locatorFile.getProperty(locator)), value,
						String.format(
								"Click on the element with the locator %s",
								locator));

			} else {
				throw new RCException(String.format(
						"Element with the locator %s is not found", locator));
			}
		}
	}

	public String getTextOfElement(String locator, final Properties locatorFile) {
		if (locator.contains(".id")) {
			if (cmd.isElementPresent(By.id(locatorFile.getProperty(locator)))) {
				return cmd.getText(By.id(locatorFile.getProperty(locator)),
						String.format(
								"Getting text of element with lcoator %s",
								locator));
			} else {
				throw new RCException(String.format(
						"Element with the locator %s is not found", locator));
			}
		}else if(locator.contains(".xpath")){
			if (cmd.isElementPresent(By.xpath(locatorFile.getProperty(locator)))) {
				return cmd.getText(By.xpath(locatorFile.getProperty(locator)),
						String.format(
								"Getting text of element with lcoator %s",
								locator));
			} else {
				throw new RCException(String.format(
						"Element with the locator %s is not found", locator));
			}
			
		} else {
			if (cmd.isElementPresent(By.name(locatorFile.getProperty(locator)))) {
				return cmd.getText(By.name(locatorFile.getProperty(locator)),
						String.format(
								"Getting text of element with lcoator %s",
								locator));
			} else {
				throw new RCException(String.format(
						"Element with the locator %s is not found", locator));
			}
			
		}

	}

	public String getValueOfElement(String locator, final Properties locatorFile) {

		if (locator.contains("id")) {
			if (cmd.isElementPresent(By.id(locatorFile.getProperty(locator)))) {
				return cmd.getAttribute(
						By.id(locatorFile.getProperty(locator)), "value",
						String.format(
								"Getting text of element with lcoator %s",
								locator));
			} else {
				throw new RCException(String.format(
						"Element with the locator %s is not found", locator));
			}
		} else if(locator.contains(".xpath")) {
			if (cmd.isElementPresent(By.xpath(locatorFile.getProperty(locator)))) {
				return cmd.getAttribute(By.xpath(locatorFile
						.getProperty(locator)), "value", String.format(
						"Getting text of element with lcoator %s", locator));
			} else {
				throw new RCException(String.format(
						"Element with the locator %s is not found", locator));
			}
		}else{
			if (cmd.isElementPresent(By.name(locatorFile.getProperty(locator)))) {
				return cmd.getAttribute(By.name(locatorFile
						.getProperty(locator)), "value", String.format(
						"Getting text of element with lcoator %s", locator));
			} else {
				throw new RCException(String.format(
						"Element with the locator %s is not found", locator));
			}
		}

	}
}
