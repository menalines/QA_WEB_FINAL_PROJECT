package Tests;

import Fixtures.BrowserFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.SAME_THREAD)
public class HeadPageTest extends BrowserFixture {

    @Test
    public void womenButtonMenuIsDisplayedTest() {
        assertTrue(headPage.womenButtonMenuIsDisplayed());
    }

    @Test
    public void dressesButtonMenuIsDisplayedTest() {
        assertTrue(headPage.dressesButtonMenuIsDisplayed());
    }

    @Test
    public void summerDressUrlSameTest() {
        assertTrue(headPage.summerDressUrlSame());
    }
}
