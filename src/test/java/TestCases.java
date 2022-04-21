import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCases extends BaseTest{
    @Test
    public void testCase1() {
        Assert.assertTrue(true);
    }

    @Test
    public void testCase2() {
        Assert.assertTrue(true);
    }


    @Test
    public void testCase3() {
        Assert.assertTrue(false);
    }
}
