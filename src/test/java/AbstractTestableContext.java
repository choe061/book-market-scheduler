import com.bk.bm.config.DatabaseConfig;
import com.bk.bm.config.MybatisConfig;
import com.bk.bm.service.BuyServiceImpl;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by choi on 2017. 10. 5. PM 8:00.
 */
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {DatabaseConfig.class, MybatisConfig.class, BuyServiceImpl.class})
public abstract class AbstractTestableContext {
}
