import config.Config;
import crud.AuthorTable;
import crud.TestTable;
import models.AuthorModel;
import models.TestModel;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import utils.FileUtils;
import utils.RandomUtils;
import java.util.List;

public class BaseTestKT2 {
    public static int author_id;
    public static List<TestModel> tests;
    public static String date;

    @BeforeTest
    public void beforeTest() {
        AuthorModel firstModel = AuthorTable.select(Config.environment.getValue("/authorName").toString());
        if(firstModel.getName() == null) {
            AuthorTable.add();
            AuthorModel model = AuthorTable.select(Config.environment.getValue("/authorName").toString());
            author_id = model.getId();
        } else {
            author_id = firstModel.getId();
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        int randomId = RandomUtils.generatorInt(10);
        tests = TestTable.select(FileUtils.readFile("src/main/resources/selectTest.sql")+"where id like"+"'%"+randomId+randomId+"%' limit "+Config.environment.getValue("/limitTests").toString());
        for(TestModel model: tests) {
            model.setAuthor_id(author_id);
            model.setProject_id((Integer) Config.environment.getValue("/projectId"));
        }
        for(TestModel model: tests) {
            TestTable.add(model, FileUtils.readFile("src/main/resources/testAdd.sql"));
        }
    }

    @AfterMethod
    public void afterMethod() {
        TestTable.delete(tests.size());
        List<TestModel> models = TestTable.select(String.format(FileUtils.readFile("src/main/resources/selectTest.sql") + "where end_time = '%s'", date));
        Assert.assertTrue(models.isEmpty(), "Tests were not delete");
    }
}
