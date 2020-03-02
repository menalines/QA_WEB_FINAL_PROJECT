package PageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.url;

public class HeadPage {

    private SelenideElement womenButton = $(By.xpath("//a[text()='Women']"));
    private SelenideElement dressesButton = $(By.xpath("//div/ul/li/a[text()='Dresses']"));
    private SelenideElement womenButtonMenu = $(By.xpath("//a[text()='Women']/following-sibling::*"));
    private SelenideElement dressesButtonMenu = $(By.xpath("//div/ul/li/a[text() = 'Dresses']/following-sibling::*"));
    private SelenideElement searchField = $(By.id("search_query_top"));
    private SelenideElement searchButton = $(By.name("submit_search"));
    private SelenideElement shoppingCartButton = $("div.shopping_cart>a");
    private SelenideElement firstProductInPage = $("ul#homefeatured>li>div");
    private SelenideElement addToCartButton = $("p.buttons_bottom_block>button.exclusive");
    private SelenideElement singInButton = $(By.className("login"));

    SelenideElement userEmail = $(By.id("email"));
    SelenideElement userPassword = $(By.id("passwd"));
    SelenideElement signInButton = $(By.id("SubmitLogin"));
    SelenideElement invalidPasswordAlert = $(By.xpath("//li [text()='Invalid password.']"));

    private HeadPage searchProduct(String query) {
        searchField.setValue(query);
        searchButton.click();

        return this;
    }

    public HeadPage goToLoginPage() {
        singInButton.click();

        return new HeadPage();
    }

    public boolean womenButtonMenuIsDisplayed() {
        womenButton.hover();

        return womenButtonMenu.isDisplayed();
    }

    public boolean dressesButtonMenuIsDisplayed() {
        dressesButton.hover();

        return dressesButtonMenu.isDisplayed();
    }

    public boolean searchAndNotFoundProduct(String query) {
        searchProduct(query);

        return $(By.className("alert-warning")).should(exist).exists();
    }

    public boolean searchAndFoundAnyProduct(String query) {
        searchProduct(query);

        return $("ul.product_list>li").should(exist).exists();
    }

    public boolean summerDressUrlSame() {
        String url1, url2;
        womenButton.hover();
        $(By.xpath("//li[@class='sfHover']//a[contains(text(),'Summer Dresses')]")).click();
        url1 = url();
        womenButton.click();
        $("div.block_content>ul.tree>li.last>a").click();
        $(By.xpath("//div[@class='block_content']//ul//a[contains(text(),'Summer Dresses')]")).click();
        url2 = url();

        return url1.equals(url2);
    }

    public int addAllDressesToCart() {
        womenButton.click();
        $("div.block_content>ul.tree>li.last>a").click();
        $(By.xpath("//div[@class='block_content']//ul//a[contains(text(),'Summer Dresses')]")).click();

        ElementsCollection allProducts = $$("ul.product_list>li>div>div.right-block");
        for(SelenideElement element : allProducts) {
            element.hover();
            element.$(byText("Add to cart")).click();
            $("div.clearfix>div.layer_cart_cart>div.button-container>span>span").click();
        }
        return allProducts.size();
    }

    public CartPage goToShoppingCartPage() {
        shoppingCartButton.click();

        return new CartPage();
    }

    public HeadPage addFirstProductInCartWithSetQuantity(String value) {
        firstProductInPage.click();
        $(By.xpath("//input[@id='quantity_wanted']")).should(exist).clear();
        $(By.xpath("//input[@id='quantity_wanted']")).setValue(value);
        addToCartButton.click();
        if($("a.fancybox-item.fancybox-close").should(exist).isDisplayed()) {
            $("a.fancybox-item.fancybox-close").click();
        }
        else {
            $("span.continue>span").click();
        }
        return this;
    }


    public UserPage loginWithCorrectData(String email, String password) {
        userEmail.setValue(email);
        userPassword.setValue(password);
        signInButton.click();

        return new UserPage();
    }

    public boolean loginWithIncorrectData(String email, String password) {
        userEmail.setValue(email);
        userPassword.setValue(password);
        signInButton.click();

        return invalidPasswordAlert.exists();
    }
}

