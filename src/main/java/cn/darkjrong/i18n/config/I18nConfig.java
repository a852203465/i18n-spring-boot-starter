package cn.darkjrong.i18n.config;

import cn.darkjrong.spring.boot.autoconfigure.I18nProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * i18n Web 配置
 *
 * @author Rong.Jia
 * @date 2025/03/27
 */
@Configuration
public class I18nConfig implements WebMvcConfigurer {

	@Autowired
	private I18nProperties i18nProperties;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor(i18nProperties);
		registry.addInterceptor(interceptor)
				.addPathPatterns(i18nProperties.getInterceptor().getPatterns());
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
		messageBundle.setBasename(i18nProperties.getBasename());
		messageBundle.setDefaultEncoding(i18nProperties.getDefaultEncoding());
		messageBundle.setConcurrentRefresh(i18nProperties.isConcurrentRefresh());
		messageBundle.setUseCodeAsDefaultMessage(i18nProperties.isUseCodeAsDefaultMessage());
		messageBundle.setCacheSeconds(i18nProperties.getCacheSeconds());
		return messageBundle;
	}

	@Bean
	public MessageSourceAccessor getDefaultMessageSourceAccessor(MessageSource messageSource) {
		return new MessageSourceAccessor(messageSource);
	}

//	@Bean(name = "localeResolver")
//	public LocaleResolver localeResolver() {
//		LangHeaderLocaleResolver resolver = new LangHeaderLocaleResolver();
//		resolver.setSupportedLocales(i18nProperties.getSupportedLocales().stream()
//				.map(LocaleType::getValue)
//				.collect(Collectors.toList()));
//		resolver.setDefaultLocale(LocaleType.getValue((i18nProperties.getDefaultLocale())));
//		resolver.setHeaderName(i18nProperties.getInterceptor().getName());
//		return resolver;
//	}

}
