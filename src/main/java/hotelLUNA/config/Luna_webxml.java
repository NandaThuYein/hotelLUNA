package hotelLUNA.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Luna_webxml extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		@SuppressWarnings("rawtypes")
		Class [] configfieles = {Luna_dispatcher.class};
		return configfieles;
	}

	@Override
	protected String[] getServletMappings() {
		String [] mappings =  {"/"};
		return mappings;
	}

}
