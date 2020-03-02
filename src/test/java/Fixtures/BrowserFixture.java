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

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setAcceptInsecureCerts(false);
        capabilities.setCapability("enableVNC", false);
        capabilities.setCapability("enableVideo", false);

        Configuration.browserCapabilities = capabilities;
//        Configuration.remote = "http://localhost:4444/wd/hub";

        open(userPageUrl);
    }

    @AfterEach
    public void postConditions(){
        clearBrowserCookies();
    }
}