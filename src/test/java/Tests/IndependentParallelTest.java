package Tests;

import Fixtures.BrowserFixture;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Execution(ExecutionMode.CONCURRENT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IndependentParallelTest extends BrowserFixture {

    @Test
    public void isContactUsEnabledTest() {
        $(byText("Contact us")).isEnabled();
    }

    @Test
    public void isLoginEnabledTest() {
        $(byClassName("login")).isEnabled();
    }
}
