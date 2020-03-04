package Fixtures;

import Listeners.Listener;
import PageObjects.CartPage;
import PageObjects.HeadPage;
import PageObjects.UserPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

@ExtendWith(Listener.class)
@Execution(ExecutionMode.SAME_THREAD)
public class BrowserFixture {
    protected String correctEmail = "test@mail.ru";
    protected String correctPassword = "test";
    static protected String userPageUrl = "http://automationpractice.com/index.php?controller=my-account";
    protected HeadPage headPage = new HeadPage();
    protected UserPage userPage = new UserPage();
    protected CartPage cartPage = new CartPage();

    @BeforeAll
    public static void beforeTest() {
//        Configuration.remote = "http://localhost:4444/wd/hub";
//        Configuration.browser = "chrome";
        Configuration.browserSize = "1980x1080";
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("enableVNC", true);
//        capabilities.setCapability("enableVideo", true);
//        Configuration.browserCapabilities = capabilities;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("80.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        try {
            RemoteWebDriver driver = new RemoteWebDriver(
                    URI.create("http://selenoid:4444/wd/hub").toURL(),
                    capabilities
            );

            Configuration.baseUrl = "http://automationpractice.com";
            sleep(10000);
            setWebDriver(driver);
            sleep(10000);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

//        ChromeOptions options = new ChromeOptions();
//        options.setCapability("enableVNC", true);
//        options.setCapability("enableVideo", true);
//
//        RemoteWebDriver webDriver = null;
//
//        try {
//            webDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), options);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//        Configuration.baseUrl = "http://automationpractice.com";
//        sleep(10000);
//        setWebDriver(webDriver);
//        sleep(10000);

        Selenide.open("/");
    }

    @AfterEach
    public void postConditions(){
        clearBrowserCookies();
    }
}