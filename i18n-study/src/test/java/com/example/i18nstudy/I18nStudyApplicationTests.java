package com.example.i18nstudy;


import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.i18nstudy.domain.MessageInfo;

@SpringBootTest
@RunWith(SpringRunner.class)
public class I18nStudyApplicationTests {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void initData() {
        List<MessageInfo> messageInfos = Arrays.asList(
                new MessageInfo("user.name", Locale.US.toString(), "yoyo-EN"),
                new MessageInfo("user.name1", Locale.US.toString(), "nrsc"),
                new MessageInfo("user.name2", Locale.US.toString(), "nrsc{0}-{1}"),
                new MessageInfo("user.name", Locale.CHINA.toString(), "章尔"),
                new MessageInfo("user.name1", Locale.CHINA.toString(), "章尔1"),
                new MessageInfo("user.name2", Locale.CHINA.toString(), "章尔{0}-{1}")
        );
        redisTemplate.opsForValue().set("userInfo", messageInfos);
    }


    @Test
    public void getData() {
        List<MessageInfo> messageInfos = Arrays.asList(
                new MessageInfo("user.name", Locale.US.toString(), "yoyo-EN"),
                new MessageInfo("user.name1", Locale.US.toString(), "nrsc"),
                new MessageInfo("user.name2", Locale.US.toString(), "nrsc{0}-{1}"),
                new MessageInfo("user.name", Locale.CHINA.toString(), "章尔"),
                new MessageInfo("user.name1", Locale.CHINA.toString(), "章尔1"),
                new MessageInfo("user.name2", Locale.CHINA.toString(), "章尔{0}-{1}")
        );
        Object userInfo = redisTemplate.opsForValue().get("userInfo");
        System.out.println(userInfo);
    }


}
