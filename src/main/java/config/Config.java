package config;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class Config {
    public static ISettingsFile environment = new JsonSettingsFile("config.json");
}
