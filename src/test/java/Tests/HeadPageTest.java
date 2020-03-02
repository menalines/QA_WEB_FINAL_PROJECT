package Tests;

import Fixtures.BrowserFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.CONCURRENT)
public class HeadPageTest extends BrowserFixture {

    @Test
    public void loginWithCorrectData() {
        headPage.goToLoginPage().loginWithCorrectData(correctEmail, correctPassword);
        assertEquals(userPageUrl, url());
    }
    @Test
    public void loginWithIncorrectData() {
        assertTrue(headPage.goToLoginPage().loginWithIncorrectData(correctEmail,"1"));
    }
    @Test
    public void womenButtonMenuIsDisplayedTest() {
        assertTrue(headPage.womenButtonMenuIsDisplayed());
    }

    @Test
    public void dressesButtonMenuIsDisplayedTest() {
        assertTrue(headPage.dressesButtonMenuIsDisplayed());
    }

    @Test
    public void summerDressUrlSameTest() {
        assertTrue(headPage.summerDressUrlSame());
    }
}
