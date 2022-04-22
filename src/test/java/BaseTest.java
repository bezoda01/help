import config.Config;
import crud.AuthorTable;
import crud.TestTable;
import models.AuthorModel;
import models.TestModel;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import utils.*;

public class BaseTest {
    public static int author_id = 0;

    @BeforeTest
    public void beforeTest() {
        AuthorModel firstModel = AuthorTable.select(Config.environment.getValue("/authorName").toString());
        System.out.println(firstModel.getId());
        if(firstModel.getId() == 0) {
            AuthorTable.add();
            AuthorModel model = AuthorTable.select(Config.environment.getValue("/authorName").toString());
            author_id = model.getId();
        } else {
            author_id = firstModel.getId();
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        TestModel testModel = new TestModel(
                result.getName(),
                result.getStatus(),
                result.getMethod().getMethodName(),
                (Integer) Config.environment.getValue("/projectId"),
                RandomUtils.generatorInt(20),
                DateUtils.getFormatDate(result.getStartMillis()),
                DateUtils.getFormatDate(result.getEndMillis()),
                HostUtils.getHostName(),
                BrowserUtils.getBrowserName(),
                author_id);
        TestTable.add(testModel, FileUtils.readFile("src/main/resources/testAdd.sql"));
        Assert.assertFalse(TestTable.select(FileUtils.readFile("src/main/resources/selectTest.sql")+"where start_time = '"+DateUtils.getFormatDate(result.getStartMillis())+"'").isEmpty(), "Test was not add");
        BrowserUtils.quit();
    }
}
