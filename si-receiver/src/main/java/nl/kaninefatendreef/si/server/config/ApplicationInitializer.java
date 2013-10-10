package nl.kaninefatendreef.si.server.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationInitializer implements WebApplicationInitializer {

	Logger _logger = LoggerFactory.getLogger(ApplicationInitializer.class); 
	
	@Override
    public void onStartup(ServletContext servletContext) throws ServletException {


		AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
		
		String customConfiguration = servletContext.getInitParameter("nl.kaninefatendreef.si.config.custom");
		
		if (customConfiguration != null && !customConfiguration.equals("")){
			try {
				@SuppressWarnings("rawtypes")
				Class customConfigurationClass = Class.forName(customConfiguration);
				annotationConfigWebApplicationContext.register(SpringConfig.class , customConfigurationClass);
			} catch (ClassNotFoundException e) {
				_logger.error("Class with name " + customConfiguration + " not found in the environment with property nl.kaninefatendreef.si.config.custom", e);
			}
		}else{

			annotationConfigWebApplicationContext.register(SpringConfig.class);
		}

		/*
		 * Spring security filter mapping
		 */
		servletContext.addListener(new ContextLoaderListener(annotationConfigWebApplicationContext));		
		FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
		filterRegistration.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
		
		/*
		 * Spring components mapping
		 */
		Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(annotationConfigWebApplicationContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        
        /*
         * Sun soap mapping
         */
        servletContext.addListener(new com.sun.xml.ws.transport.http.servlet.WSServletContextListener());

	}
	
}