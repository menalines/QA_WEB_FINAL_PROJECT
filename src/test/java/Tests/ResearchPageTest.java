package Tests;

import Fixtures.BrowserFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.SAME_THREAD)
public class ResearchPageTest extends BrowserFixture {

    @Test
    public void searchForAvailableProducts() {
        assertTrue(headPage.searchAndFoundAnyProduct("Dress"));
    }

    @Test
    public void productNotFoundTest() {
        assertTrue(headPage.searchAndNotFoundProduct("Glasses"));
    }
}
