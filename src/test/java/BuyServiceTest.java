import com.bk.bm.domain.Buy;
import com.bk.bm.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

/**
 * Created by choi on 2017. 10. 7. PM 6:58.
 */
public class BuyServiceTest extends AbstractTestableContext {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BookService<Buy> bookService;

    @Before
    public void setUp() {

    }

    @Test
    public void createBuy() {
        Buy buy = new Buy(1, "123123", "13123", "테스트책", 10000, 15000, "상태 등...");
        Buy book = bookService.createBook(1, buy);
        logger.debug("BUY BOOK instance : "+book);
        assertTrue(book instanceof Buy);
    }

    @Test
    public void getBuy() {
        Buy buy = bookService.getBook(1);
        logger.debug("BUY instance : "+String.valueOf(buy));
        logger.debug("BUY buy_id : "+String.valueOf(buy.getBuy_id()));
        logger.debug("BUY title : "+String.valueOf(buy.getTitle()));
        logger.debug("BUY created_at : "+String.valueOf(buy.getCreated_at()));
        assertTrue(buy instanceof Buy);
    }

}
