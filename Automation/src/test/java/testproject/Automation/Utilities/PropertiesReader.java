package Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    public static Logger logger = LogManager.getLogger(org.testng.TestRunner.class.getName());
    private static String screenshotsPath ="";
    private String userName = "";
    private String passWord = "";
    private String browser ="";
    private String url ="";
    private String filerId="";
    private String searchType="";
    private String topMenu="";
    private String listMenu="";
    private String filerType="";
    private boolean useAssert = true;
    public static int maxWaitTime = 150;

    public static final String error ="//*[contains(@class,'error')]";
    public static final String errorDetail = "//span[contains(@class,'ui-message-error-detail')]";
    public static String errormessage = null;

    public String getFilerType() {
        return filerType;
    }
    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getBrowser() {
        return browser;
    }

    public String getUrl() {
        return url;
    }

    public String getFilerId() {
        return filerId;
    }

    public String getSearchType() {
        return searchType;
    }

    public String getTopMenu() {
        return topMenu;
    }

    public String getListMenu() {
        return listMenu;
    }
    public static String getScreenshotsPath() {
        return screenshotsPath;
    }

    public PropertiesReader() {
        FileInputStream fis;
        try {
            fis = new FileInputStream("Reader.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Unable to read Reader.properties file!");
            return;
        }
        Properties p = new Properties();
        try {
            p.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to read Reader.properties file!");
            return;
        }
        userName = p.getProperty("userName");
        passWord = p.getProperty("passWord");
        browser = p.getProperty("browser");
        url = p.getProperty("url");
        filerId=p.getProperty("filer.id");
        searchType = p.getProperty("searchTypeRadioButton");
        topMenu =p.getProperty("topMenu");
        listMenu =p.getProperty("listMenu");
        filerType=p.getProperty("filer.type");
        screenshotsPath=p.getProperty("screenshotsPath");
        try {
            useAssert = Boolean.parseBoolean(p.getProperty("use_assert"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can't read useAssert property");
        }
    }
}
