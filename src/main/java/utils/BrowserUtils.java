package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;

public class BrowserUtils {
    public static Browser getBrowser() {
        return AqualityServices.getBrowser();
    }

    public static void quit() {
        getBrowser().getDriver().quit();
    }

    public static String getBrowserName() {
        return getBrowser().getBrowserName().name();
    }
}
