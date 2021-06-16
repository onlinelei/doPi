package junit.springTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class) // 使用 Spring 运行器运行进行测试
@ContextConfiguration(locations = "classpath:appContext.xml") // 加载配置文件
@Transactional(transactionManager = "transactionManager_brandstart")
public class BaseSpringJunit4ClassTest {

    @Test
    public void test() {
    }
}
