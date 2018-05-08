package com.rummycircle.instantwithdrawal;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.rummycircle.AbstractBasePage;
import com.rummycircle.utils.reporting.extent.Logger;

public class PaginationPage extends AbstractBasePage {
	Properties locators = null;
	Properties cuLocators = null;
	Logger log = null;

	public PaginationPage(WebDriver driver) {
		super(driver);
		locators = loadLocators("instant_withdrawal.properties");
		cuLocators = loadCustomFile("custom.properties");
		log = Logger.getLogger();
	}

	public int getPageNumbers() {
		log.info("Getting the no of active pages");
		if (cmd.waitAndCheckPresent(5, By.xpath(locators.getProperty("iw.get_all_active_pages.xpath"))))
			return cmd.findElements(By.xpath(locators.getProperty("iw.get_all_active_pages.xpath"))).size();
		else
			return 0;
	}

	public void navigateToPage(int pageNo) {
		log.info("Clicking on pageNo: " + pageNo);
		if (cmd.waitAndCheckPresent(5, By.xpath(String.format(locators.getProperty("iw.go_to_page.xpath"), pageNo))))
			cmd.click(By.xpath(String.format(locators.getProperty("iw.go_to_page.xpath"), pageNo)),
					"Clicking on page no: " + pageNo);
	}

	public void navigatetoFirstPage() {
		log.info("Clicking on first page link");
		String page = cuLocators.getProperty("firstPage.title");
		if (cmd.waitAndCheckPresent(5, By.xpath(String.format(locators.getProperty("iw.go_to_page.xpath"), page))))
			cmd.click(By.xpath(String.format(locators.getProperty("iw.go_to_navigator_page.xpath"), page)),
					"Clicking on page no: " + page);
	}

	public void navigatetoLastPage() {
		log.info("Clicking on last page link");
		String page = cuLocators.getProperty("lastPage.title");
		if (cmd.waitAndCheckPresent(5, By.xpath(String.format(locators.getProperty("iw.go_to_page.xpath"), page))))
			cmd.click(By.xpath(String.format(locators.getProperty("iw.go_to_navigator_page.xpath"), page)),
					"Clicking on page no: " + page);
	}

	public void navigatetoNextPage() {
		log.info("Clicking on next page link");
		String page = cuLocators.getProperty("nextPage.title");
		if (cmd.waitAndCheckPresent(5, By.xpath(String.format(locators.getProperty("iw.go_to_page.xpath"), page))))
			cmd.click(By.xpath(String.format(locators.getProperty("iw.go_to_navigator_page.xpath"), page)),
					"Clicking on page no: " + page);
	}

	public void navigatetoPreviousPage() {
		log.info("Clicking on previous page link");
		String page = cuLocators.getProperty("prevPage.title");
		if (cmd.waitAndCheckPresent(5, By.xpath(String.format(locators.getProperty("iw.go_to_page.xpath"), page))))
			cmd.click(By.xpath(String.format(locators.getProperty("iw.go_to_navigator_page.xpath"), page)),
					"Clicking on page no: " + page);
	}

}