package com.ssafy.enjoytrip.config;

import com.ssafy.enjoytrip.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@RequiredArgsConstructor
@EnableAspectJAutoProxy	//aop autoproxy 설정
public class WebMvcConfig implements WebMvcConfigurer{

	//인터셉터 등록
	private  final LoginInterceptor interceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//해당 요청 인터셉트
		registry.addInterceptor(interceptor).addPathPatterns("/**/attraction/**")
				.addPathPatterns("/**/attraction/**")
				.addPathPatterns("/**/board/**");

		//제외 path
		//		.excludePathPatterns("/signUp", "/signIn");

		//해당 요청을 제외한 나머지 요청들 인터셉트
//		registry.addInterceptor(interceptor).excludePathPatterns("/**/auth/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/profileimg/**")
				.addResourceLocations("/resources/");
	}


}
