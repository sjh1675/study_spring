package net.koreate.board.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 참조로 인해 매개변수로 메소드를 실행 시키면 새로운 객체(빈, 즉 싱글톤이 아닌)가 들어가서 싱글톤 보장이 안되는데, 이미 빈으로 등록되어 있다면 새로운 객체를 생성하는게 아닌 등록된 빈을 준다
@EnableWebMvc // annotation-driven 역할을 해준다 : 컨트롤러가 어떤 요청을 처리하고 있는지 전송방식과 정보를 핸들러에 등록
@ComponentScan(basePackages= {"net.koreate.board.controller", "net.koreate.board.service"})
public class ServletContextConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/*").addResourceLocations("/resources/");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	/*
	 * @Bean // 메소드 이름이 빈 이름이 된다 public ViewResolver customViewResolver() {
	 * InternalResourceViewResolver bean = new InternalResourceViewResolver();
	 * bean.setViewClass(JstlView.class); bean.setPrefix("/WEB-INF/views/");
	 * bean.setSuffix(".jsp"); return bean; }
	 */
	
}