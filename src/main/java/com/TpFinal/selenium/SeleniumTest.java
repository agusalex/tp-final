package com.TpFinal.selenium;

import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created by Max on 10/2/2017.
 */
public class SeleniumTest {


    FirefoxDriver driver;

    @Test
    public void testCrearPersona() {
        driver.get("http://inmobi.ddns.net/#!personas");
        assertTrue(driver.getTitle().equals("TpFinal"));
        driver.findElement(By.xpath("//div[@class='v-expand']/div/div/div[3]/div/div[5]/div")).click();
        driver.findElement(By.xpath("//div[@id='ROOT-2521314']/div/div[2]/div/div[2]/div/div/div/div[1]/div/div[3]/div/div[3]/div")).click();
        driver.findElement(By.xpath("//div[@id='ROOT-2521314']/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div[3]/div/table/tbody/tr[2]/td[3]/div/div[3]/div")).click();

    }



    public void testWithoutTestbench() {
        driver.get("http://inmobi.ddns.net/#!personas");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final WebElement button = findButtonByCaption("Iniciar Sesión");
        /*button.click();
        final WebElement button2 = findButtonByCaption("Nuevo");
        button2.click();
        driver.manage().timeouts()
                .implicitlyWait(2, TimeUnit.SECONDS);

        final List<WebElement> elements =
                driver.findElements(By.id("Nombre"));
        if (elements.isEmpty()) {
            throw new RuntimeException("No Label found");
        }
        driver.manage().timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS);
        final String value = elements.get(0).getText();
        Assert.assertEquals("Clicked", value);*/
    }

    public WebElement findButtonByCaption(String caption) {
        final List<WebElement> buttons =
                driver.findElements(By.className("v-button"));
        for (final WebElement button : buttons) {
            if (button.getText().equals(caption)) {
                return button;
            }
        }
        return null;
    }


    @Before
    public void setUp() throws Exception {
        if(SystemUtils.IS_OS_LINUX){
            System.setProperty("webdriver.gecko.driver","GeckoDriver"+ File.separator+"geckodriver");
            System.out.println("OS: LINUX");
        }
        if(SystemUtils.IS_OS_WINDOWS) {
            String systemArchitecture = System.getProperty("sun.arch.data.model");
            if(systemArchitecture.equals("64")) {
                System.setProperty("webdriver.gecko.driver", "GeckoDriver" + File.separator + "geckodriver.exe");



                System.out.println("OS: Windows 64-bit");
            }
            else {
                System.setProperty("webdriver.gecko.driver", "GeckoDriver-32-bits" + File.separator + "geckodriver.exe");
                System.out.println("OS: Windows 32-bit");
            }
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }



    @After
    public void tearDown() {
        driver.quit();
    }

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }


}