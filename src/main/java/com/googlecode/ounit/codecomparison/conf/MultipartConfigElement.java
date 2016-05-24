package com.googlecode.ounit.codecomparison.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class MultipartConfigElement {
	
	@Bean
	public CommonsMultipartResolver multipartResolver(){
	    CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
	    commonsMultipartResolver.setDefaultEncoding("utf-8");
	    commonsMultipartResolver.setMaxUploadSize(65536); // maximum code length
	    return commonsMultipartResolver;
	}
}
