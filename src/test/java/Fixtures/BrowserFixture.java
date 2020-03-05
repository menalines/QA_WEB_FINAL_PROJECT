package Fixtures;

import Listeners.Listener;
import PageObjects.CartPage;
import PageObjects.HeadPage;
import PageObjects.UserPage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.open;

@ExtendWith(Listener.class)
public class BrowserFixture {

    public final static String CORRECT_EMAIL = "testDemo@mail.ru";
    public final static String CORRECT_PASSWORD = "123456789";
    protected final static String PAGE_URL = "http://automationpractice.com/";

    protected HeadPage headPage = new HeadPage();
    protected UserPage userPage = new UserPage();
    protected CartPage cartPage = new CartPage();

    @BeforeEach
    public void beforeTest() {

        Configuration.baseUrl = PAGE_URL;
        Configuration.remote = "http://localhost:4444/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;

        open(PAGE_URL);
    }

    @AfterEach
    public void postConditions() {
        clearBrowserCookies();
    }
}
