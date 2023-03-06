package com.epam.esm.exceptions.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

/**
 * Class to localize messages.
 */
@Component
public class Translator {

    private static ResourceBundleMessageSource messageSource;

    @Autowired
    Translator(ResourceBundleMessageSource messageSource) {
        Translator.messageSource = messageSource;
    }

    /**
     * Method for getting localized message from property file.
     *
     * @param msg code of message to get
     * @return localized message
     */
    public static String toLocale(String msg) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msg, null, locale);
    }

    public static String toLocale(String message, List<Object> arguments) {
        return messageSource.getMessage(message, arguments.toArray(), LocaleContextHolder.getLocale());
    }
}
