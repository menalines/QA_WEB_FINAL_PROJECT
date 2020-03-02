package Fixtures;

import Listeners.Listener;
import PageObjects.CartPage;
import PageObjects.HeadPage;
import PageObjects.UserPage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.open;

@ExtendWith(Listener.class)
@Execution(ExecutionMode.CONCURRENT)
public class BrowserFixture {
    protected String correctEmail = "test@mail.ru";
    protected String correctPassword = "test";
    protected String userPageUrl = "http://automationpractice.com/index.php?controller=my-account";
    protected HeadPage headPage = new HeadPage();
    protected UserPage userPage = new UserPage();
    protected CartPage cartPage = new CartPage();

    @BeforeEach
    public void beforeTest() {
        Configuration.baseUrl = userPageUrl;

        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

        open(userPageUrl);
    }

    @AfterEach
    public void postConditions(){
        clearBrowserCookies();
    }
}