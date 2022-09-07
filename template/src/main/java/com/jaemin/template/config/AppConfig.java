package com.jaemin.template.config;

import java.time.Duration;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.jaemin.template.interceptor.TestInterceptor;
import com.jaemin.template.resolver.TestArgumentResolver;
import com.jaemin.template.typeconverter.TestConverter;
import com.jaemin.template.typeconverter.TestFormatter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableAsync
@EnableWebMvc
@EnableScheduling
@EnableAspectJAutoProxy
@ComponentScan( basePackages = { "com.jaemin.template" }, 
				excludeFilters = @ComponentScan.Filter(
								type = FilterType.ANNOTATION,
								classes = Configuration.class
								)
			)
public class AppConfig implements WebMvcConfigurer {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new TestConverter());
		registry.addFormatter(new TestFormatter());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TestInterceptor())
		.order(1)
		.addPathPatterns("/**");
		//.excludePathPatterns("/css/**","/*.ico", "/error", "/error-page/**");
	}

	
	@Override 
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/")
				.setCacheControl(CacheControl.maxAge(Duration.ofDays(365)));;
		}
	

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		 resolvers.add(new TestArgumentResolver());
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.enableContentNegotiation(new MappingJackson2JsonView());
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
}
