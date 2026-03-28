package com.akps.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.akps.demo.services.StudentService;
import com.akps.demo.services.impl.StudentServiceImpl;

@Configuration
@ComponentScan(basePackages={"com.akps.demo.service"})
@EnableCaching
public class AppConfig 
{
	@Bean
	public StudentService studentService()
	{ 
		return new StudentServiceImpl();
	}
	
	@Bean
	public CacheManager cacheManager() 
	{
		CacheManager cacheManager = new ConcurrentMapCacheManager(); 
		return cacheManager;
	}

}
