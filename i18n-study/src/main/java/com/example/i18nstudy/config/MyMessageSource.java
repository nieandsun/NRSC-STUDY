package com.example.i18nstudy.config;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.i18nstudy.domain.MessageInfo;

/**
 * Created on 2022-09-09
 * Description:
 */
@Component("myMessageSource")
public class MyMessageSource extends AbstractMessageSource {

    private final Map<String, MessageFormat> cachedMessageFormats = new HashMap<>();

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        Map<String, String> map = getMessagesMap();
        return map.get(code + '_' + locale.toString());
    }

    private Map<String, String> getMessagesMap() {
        Object userInfoList = redisTemplate.opsForValue().get("userInfo");
        List<MessageInfo> messageInfoList = (List<MessageInfo>) userInfoList;
        Map<String, String> map = new HashMap<>();
        for (MessageInfo messageInfo : messageInfoList) {
            String key = messageInfo.getCode() + '_' + messageInfo.getLocale();
            map.computeIfAbsent(key, k -> messageInfo.getMessage());
        }
        return map;
    }

    @Override
    @Nullable
    protected MessageFormat resolveCode(String code, Locale locale) {
        String key = code + '_' + locale.toString();
        String msg = getMessagesMap().get(key);
        if (msg == null) {
            return null;
        }
        synchronized (this.cachedMessageFormats) {
            MessageFormat messageFormat = this.cachedMessageFormats.get(key);
            if (messageFormat == null) {
                messageFormat = createMessageFormat(msg, locale);
                this.cachedMessageFormats.put(key, messageFormat);
            }
            return messageFormat;
        }
    }
}
