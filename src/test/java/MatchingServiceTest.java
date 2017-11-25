import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by choi on 2017. 10. 11. AM 1:43.
 */
public class MatchingServiceTest extends AbstractTestableContext {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Before
    public void setUp() {

    }

    @Test
    public void requestRestTemplateSpeed() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
        int threadPoolCount = Runtime.getRuntime().availableProcessors() * 2 + 1;
        logger.debug("스레드 풀 개수 : " + threadPoolCount);
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolCount);

//        ExecutorService executorService = Executors.newCachedThreadPool();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            HttpEntity request = new HttpEntity(headers);
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    restTemplate.exchange("https://www.daum.net", HttpMethod.GET, request, String.class);
                }
            });
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
            int poolSize = threadPoolExecutor.getPoolSize();
            logger.debug("풀 갯수 : "+poolSize);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {}
        long end = System.currentTimeMillis();
        logger.debug("수행 시간 : " + (end - start) / 1000.0);
    }
}
