package Tests;

import Fixtures.BrowserFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.CONCURRENT)
public class ResearchPageTest extends BrowserFixture {

    @Test
    public void findProductsTest() {
        assertTrue(headPage.searchAndFoundAnyProduct("Dress"));
    }

    @Test
    public void notFoundProductsTest() {
        assertTrue(headPage.searchAndNotFoundProduct("Glasses"));
    }
}
