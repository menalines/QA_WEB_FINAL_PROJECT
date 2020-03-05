package PageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static Fixtures.BrowserFixture.CORRECT_EMAIL;
import static Fixtures.BrowserFixture.CORRECT_PASSWORD;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {

    public double getAllProductsPrice() {

        double allProdPrice = 0;

        ElementsCollection priceElements = $$("td.cart_total>span");

        for (SelenideElement element : priceElements) {
            String price = element.getText().substring(1);
            allProdPrice += Double.parseDouble(price);
        }

        return allProdPrice;
    }

    public double getTotalProductsPrice() {

        double totalProduct;

        totalProduct = Double.parseDouble($(By.xpath("//td[@id='total_product']"))
                .getText()
                .substring(1));

        return totalProduct;
    }

    public int getNumberOfProductsInCart() {
        return $$("table.table>tbody>tr").size();
    }

    public CartPage setProductQuantity(String value) {

        $("input.cart_quantity_input").clear();
        $("input.cart_quantity_input").setValue(value);

        return this;
    }

    public CartPage deleteAllProducts() {

        ElementsCollection allProducts = $$("td.cart_delete>div>a");

        for (SelenideElement element : allProducts) {
            element.click();
            element.should(hidden);
        }

        return this;
    }

    public String getOrder() {

        HeadPage loginPage = new HeadPage();

        $("p.clearfix>a.button").click();

        loginPage.loginWithCorrectData(CORRECT_EMAIL, CORRECT_PASSWORD);

        $(By.name("processAddress")).click();
        $(By.id("cgv")).click();
        $(By.name("processCarrier")).click();
        $(By.className("bankwire")).click();
        $(By.xpath("//span[text()='I confirm my order']")).click();

        String orderName = getOrderNameFromText();

        $(By.className("account")).click();

        return orderName;
    }

    private String getOrderNameFromText() {

        String part = $("div.box").getAttribute("innerHTML").split("<br>")[5];

        return part.split(" ")[9];
    }
}
