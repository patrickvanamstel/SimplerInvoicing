package nl.kaninefatendreef.si.server.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class ApplicationInitializer implements WebApplicationInitializer {

	
	@Override
    public void onStartup(ServletContext servletContext) throws ServletException {

		
		AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();

		annotationConfigWebApplicationContext.register(SpringConfig.class);
        Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(annotationConfigWebApplicationContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        
        servletContext.addListener(new com.sun.xml.ws.transport.http.servlet.WSServletContextListener());

	}

	
}