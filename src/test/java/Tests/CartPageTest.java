package Tests;

import Fixtures.BrowserFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.SAME_THREAD)
public class CartPageTest extends BrowserFixture {

    @Test
    public void addProductInCartTest() {
        assertEquals(headPage.addAllDressesToCart(), headPage.goToShoppingCartPage().getNumberOfProductsInCart());
    }

    @Test
    public void addZeroQuantityOfProductsTest() {

        headPage.addFirstProductInCartWithSetQuantity("0");

        assertEquals(0, headPage.goToShoppingCartPage().getNumberOfProductsInCart());
    }

    @Test
    public void correctAmountInCartTest() {

        headPage.addAllDressesToCart();

        double allProdPrice = headPage.goToShoppingCartPage().getAllProductsPrice();
        double totalProduct = cartPage.getTotalProductsPrice();

        assertEquals(allProdPrice, totalProduct);
    }

    @Test
    public void correctAmountInCartWithSetQuantityProductTest() {

        headPage.addAllDressesToCart();

        double allProdPrice = headPage.goToShoppingCartPage().setProductQuantity("5").getAllProductsPrice();
        double totalProduct = cartPage.getTotalProductsPrice();

        assertEquals(allProdPrice, totalProduct);
    }

    @Test
    public void deleteAllProductTest() {

        headPage.addAllDressesToCart();
        cartPage = headPage.goToShoppingCartPage().deleteAllProducts();

        assertEquals(0, cartPage.getNumberOfProductsInCart());
    }

    @Test
    public void createAndCheckOrderTest() {

        headPage.addAllDressesToCart();
        headPage.goToShoppingCartPage();

        String order = cartPage.getOrder();

        assertTrue(userPage.orderIsPresent(order));
    }
}
