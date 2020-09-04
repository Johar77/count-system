package com.johar.countingservice.infrastructure.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * @ClassName: CountTimeGeneratorTest
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-04 08:29
 * @Since: 1.0.0
 */
public class CountTimeGeneratorTest {

    @Test
    public void currentTimeTest() throws InterruptedException {
        Date time1 = CountTimeGenerator.currentTime();
        Thread.sleep(1000 * 1);
        Date time2 = CountTimeGenerator.currentTime();

        Assert.isTrue(time1.equals(time2), "time1 not equal time2");

        Thread.sleep(1000 * 30);
        Date time3 = CountTimeGenerator.currentTime();
        Assert.isTrue(time1.equals(time3), "time1 not equal time3");

        Thread.sleep(1000 * 10);
        Date time4 = CountTimeGenerator.currentTime();
        Assert.isTrue(time1.equals(time4), "time1 not equal time4");

        Thread.sleep(1000 * 30);
        Date timeDiff = CountTimeGenerator.currentTime();

        Assert.isTrue(time1.before(timeDiff), "time1 equal timeDiff");
    }
}
