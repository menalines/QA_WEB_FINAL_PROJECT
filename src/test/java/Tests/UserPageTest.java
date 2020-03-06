package Tests;

import Fixtures.BrowserFixture;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.SAME_THREAD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserPageTest extends BrowserFixture {

    private static final String USER_PAGE_URL = "http://automationpractice.com/index.php?controller=my-account";

    @Test
    public void loginWithCorrectDataTest() {

        headPage.goToLoginPage().loginWithCorrectData(CORRECT_EMAIL, CORRECT_PASSWORD);
        assertEquals(USER_PAGE_URL, url());
    }

    @Test
    public void loginWithIncorrectLoginTest() {
        assertTrue(headPage.goToLoginPage().loginWithIncorrectData("incorrectData@gmail.com", CORRECT_PASSWORD));
    }

    @Test
    public void loginWithIncorrectPasswordTest() {
        assertTrue(headPage.goToLoginPage().loginWithIncorrectData(CORRECT_EMAIL,"incorrectData"));
    }
}
