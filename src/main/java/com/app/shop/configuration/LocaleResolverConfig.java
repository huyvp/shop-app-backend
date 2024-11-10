package com.app.shop.configuration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.List;
import java.util.Locale;

@Configuration

public class LocaleResolverConfig extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {
    private static final List<Locale> SUPPORTED_LOCALES = List.of(
            new Locale("en"),
            new Locale("vi")
    );

    @Override
    @NonNull
    public Locale resolveLocale(HttpServletRequest request) {
        String language = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
        if (StringUtils.hasLength(language)) {
            return Locale.lookup(Locale.LanguageRange.parse(language), SUPPORTED_LOCALES);
        }
        return Locale.getDefault();
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
