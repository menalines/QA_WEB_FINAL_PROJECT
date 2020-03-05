package Listeners;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Listener implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
