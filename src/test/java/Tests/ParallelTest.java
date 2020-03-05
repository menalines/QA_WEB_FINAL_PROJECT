package Tests;

import Fixtures.BrowserFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Execution(ExecutionMode.CONCURRENT)
public class ParallelTest extends BrowserFixture {

    @Test
    public void isContactUsEnabledTest() {
        $(byText("Contact us")).isEnabled();
    }

    @Test
    public void isLoginEnabledTest() {
        $(byClassName("login")).isEnabled();
    }
}
