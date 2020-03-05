package PageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class UserPage {

    private SelenideElement ordersButton = $(By.xpath("//a[@title='Orders']"));

    public boolean orderIsPresent(String orderName) {

        ordersButton.click();

        return $(By.xpath("//a[contains(text(),'" + orderName + "')]")).exists();
    }
}
