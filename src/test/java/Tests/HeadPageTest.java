package Tests;

import Fixtures.BrowserFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.SAME_THREAD)
public class HeadPageTest extends BrowserFixture {

    @Test
    public void loginWithCorrectData() {

        headPage.goToLoginPage().loginWithCorrectData(CORRECT_EMAIL, CORRECT_PASSWORD);
        assertEquals(userPageUrl, url());
    }

    @Test
    public void loginWithIncorrectLogin() {
        assertTrue(headPage.goToLoginPage().loginWithIncorrectData("incorrectData@gmail.com", CORRECT_PASSWORD));
    }

    @Test
    public void loginWithIncorrectPassword() {
        assertTrue(headPage.goToLoginPage().loginWithIncorrectData(CORRECT_EMAIL,"incorrectData"));
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
