package com.example.i18nstudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class I18nDemoController {
    @Autowired
    //@Qualifier("reloadableResourceBundleMessageSource")
    @Qualifier("staticMessageSource")
    private MessageSource messageSource;

    @GetMapping("/hello")
    public String hello() {
        String defaultM = messageSource
                .getMessage("user.name", null, LocaleContextHolder.getLocale());
        String message1 = messageSource
                .getMessage("user.name1", null, LocaleContextHolder.getLocale());
        String message2 = messageSource
                .getMessage("user.name2", new String[]{"WW", "MM"}, LocaleContextHolder.getLocale());
        String message3 = messageSource
                .getMessage("user.nameXX", null, "defaultName", LocaleContextHolder.getLocale());

        return defaultM + "<->" + message1 + "--" + message2 + "##" + message3;
    }
}
