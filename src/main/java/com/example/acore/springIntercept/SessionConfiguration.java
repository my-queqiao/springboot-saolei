package com.example.acore.springIntercept;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * springboot的拦截器配置
 * @author tom
 *
 */
@Configuration
public class SessionConfiguration implements WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new IntercptSessionCheck()).addPathPatterns("/**");
        String a = ";\r\n };";
        String c = "/** } // {";
        char b = '}';
        // }
        /**
         *	{ // {
         */
        // 还有注解 @Request("{}")
    }
    public static void main(String[] args) {
    	String a = ";\"\r\n  };";
    	System.out.println(a);
    	System.out.println("=========");
    	char[] charArray = a.toCharArray();
    	for (char c : charArray) {
			System.out.println(c);
		}
	}
}
