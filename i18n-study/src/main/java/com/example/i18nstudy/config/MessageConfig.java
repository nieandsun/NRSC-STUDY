package com.example.i18nstudy.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.util.ResourceUtils;

import java.util.Locale;

@Configuration
public class MessageConfig {

    @Bean("reloadableResourceBundleMessageSource")
    public MessageSource initReloadableResourceBundleMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        //指定读取国际化配置文件的basename
        messageSource.setBasename(ResourceUtils.CLASSPATH_URL_PREFIX + "i18n/messages");
        //指定编码
        messageSource.setDefaultEncoding("UTF-8");
        //指定缓存时间
        messageSource.setCacheSeconds(60);
        return messageSource;
    }

    /****
     * code和msg可以来自于数据库，或者其他任何文件系统
     * @return
     */
    @Bean("staticMessageSource")
    public MessageSource initStaticMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();

        messageSource.addMessage("user.name", Locale.US, "yoyo-EN");
        messageSource.addMessage("user.name1", Locale.US, "nrsc");
        messageSource.addMessage("user.name2", Locale.US, "nrsc{0}-{1}");

        messageSource.addMessage("user.name", Locale.CHINA, "章尔");
        messageSource.addMessage("user.name1", Locale.CHINA, "章尔1");
        messageSource.addMessage("user.name2", Locale.CHINA, "章尔{0}-{1}");
        return messageSource;
    }
}
