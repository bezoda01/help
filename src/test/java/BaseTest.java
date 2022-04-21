import config.Config;
import crud.AuthorTable;
import crud.TestTable;
import models.AuthorModel;
import models.TestModel;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import utils.*;

public class BaseTest {

    @AfterMethod
    public void afterTest(ITestResult result) {
        AuthorModel firstModel = AuthorTable.checkIsAuthorExist(String.format(FileUtils.readFile("src/main/resources/getAuthor.sql"), Config.environment.getValue("/authorName").toString()));
        if(firstModel.getName() == null) {
            AuthorModel authorModel = new AuthorModel(
                    Config.environment.getValue("/authorName").toString(),
                    Config.environment.getValue("/authorLogin").toString(),
                    Config.environment.getValue("/authorEmail").toString());
            AuthorTable.add(authorModel, FileUtils.readFile("src/main/resources/AddAuthor.sql"));
        }

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
                firstModel.getId());
        TestTable.add(testModel, FileUtils.readFile("src/main/resources/testAdd.sql"));

        BrowserUtils.quit();
    }
}
