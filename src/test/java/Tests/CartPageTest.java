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
        int allAddedDresses;
        allAddedDresses = headPage.addAllDressesToCart();
        assertEquals(allAddedDresses, headPage.goToShoppingCartPage().getNumberOfProductsInCart());
    }

    @Test
    public void addZeroQuantityOfProductsTest() {
        headPage.addFirstProductInCartWithSetQuantity("0");
        assertEquals(0, headPage.goToShoppingCartPage().getNumberOfProductsInCart());
    }

    @Test
    public void correctAmountInCartTest() {
        double allProdPrice, totalProduct;
        headPage.addAllDressesToCart();
        allProdPrice = headPage.goToShoppingCartPage().getAllProductsPrice();
        totalProduct = cartPage.getTotalProductsPrice();
        assertEquals(allProdPrice, totalProduct);
    }

    @Test
    public void correctAmountInCartWithSetQuantityProductTest() {
        double allProdPrice, totalProduct;
        headPage.addAllDressesToCart();
        allProdPrice = headPage.goToShoppingCartPage().setProductQuantity("5").getAllProductsPrice();
        totalProduct = cartPage.getTotalProductsPrice();
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
        String orderName;
        headPage.addAllDressesToCart();
        headPage.goToShoppingCartPage();
        orderName = cartPage.createOrderAndGetOrderName();
        assertTrue(userPage.orderIsPresent(orderName));
    }
}
