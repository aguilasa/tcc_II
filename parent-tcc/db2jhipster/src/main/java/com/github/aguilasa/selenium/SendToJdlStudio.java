package com.github.aguilasa.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SendToJdlStudio {

	private String filePath;
	private boolean isSending = false;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public boolean isSending() {
		return isSending;
	}

	public void setSending(boolean isSending) {
		this.isSending = isSending;
	}

	public void send() {
		try {
			if (!isSending) {
				isSending = true;
				WebDriver driver = new ChromeDriver();

				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.manage().window().maximize();

				driver.get("https://start.jhipster.tech/jdl-studio/");

				WebElement uploadbutton = driver.findElement(By.id("uploadbutton"));
				uploadbutton.click();

				WebElement uploadElement = driver.findElement(By.id("jdlFileInput"));
				uploadElement.sendKeys(getFilePath());

				WebElement uploadDialog = driver.findElement(By.id("upload-dialog"));
				List<WebElement> buttons = uploadDialog.findElements(By.tagName("button"));
				if (!buttons.isEmpty()) {
					buttons.get(0).click();
				}
			}
		} finally {
			isSending = false;
		}
	}

	static {
		System.setProperty("webdriver.chrome.driver", ".\\chromedriver_win32\\chromedriver.exe");
	}

}
