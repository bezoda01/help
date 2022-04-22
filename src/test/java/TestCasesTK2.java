import crud.TestTable;
import models.TestModel;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.DateUtils;
import utils.FileUtils;
import java.util.List;

public class TestCasesTK2 extends BaseTestKT2{

    @Test
    public void test() {
        List<TestModel> beforeUpdate = TestTable.select(FileUtils.readFile("src/main/resources/selectTest.sql")+"order by id desc limit "+tests.size());
        date = DateUtils.getDate();
        List<TestModel> afterUpdate = TestTable.update(date, tests.size());
        SoftAssert soft = new SoftAssert();
        for(TestModel model: afterUpdate) {
            soft.assertTrue(model.status_id == 1, "Tests were not finish");
        }
        for(int i = 0;i < tests.size();i++){
            soft.assertFalse(beforeUpdate.get(i).end_time.equals(afterUpdate.get(i).end_time), "Tests were not update");
        }
        soft.assertAll();
    }
}
