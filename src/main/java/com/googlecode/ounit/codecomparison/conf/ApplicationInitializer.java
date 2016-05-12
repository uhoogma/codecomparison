package com.googlecode.ounit.codecomparison.conf;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.googlecode.ounit.codecomparison.dao.SetUpDao;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		new SetUpDao().createSchema();

		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringConfig.class);
		rootContext.register(JpaConfig.class);

		DispatcherServlet servlet = new DispatcherServlet(rootContext);

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", servlet);
		dispatcher.setLoadOnStartup(0);
		dispatcher.addMapping("/");

		// UTF-8 Encoding
		FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
		fr.setInitParameter("encoding", "UTF-8");
		fr.setInitParameter("forceEncoding", "true");
		fr.addMappingForUrlPatterns(null, true, "/*");
	}
}
