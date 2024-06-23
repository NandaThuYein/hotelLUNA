package hotelLUNA.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"hotelLUNA"})
@PropertySource("classpath:email.properties")
public class Luna_dispatcher implements WebMvcConfigurer{
	
	@Autowired
	private Environment env;
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		
		return viewResolver;
	}
	
	  @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/toreachresourcefolder/**").addResourceLocations("/resources/");
	    }
	
	  @Bean
	  public Connection con() {
			String driver="com.mysql.cj.jdbc.Driver";
			String url="jdbc:mysql://localhost:3306/hotel_management_system";
			 String user="root";
			 String pass="136968";
			 Connection con =null;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url,user,pass);
				System.out.println("database is connecting......");
			} catch (ClassNotFoundException e) {
			System.out.println("connection error");
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return con;
		}
	
	@Bean
	public JavaMailSender getmailsender() {
		JavaMailSenderImpl jms = new JavaMailSenderImpl();
		jms.setHost(env.getProperty("email.host"));
		jms.setUsername(env.getProperty("email.username"));
		jms.setPassword(env.getProperty("email.password"));
		jms.setPort(getStringToInteger("email.port"));
		jms.setJavaMailProperties(mailproperties());
		
		return jms;
	}

	private Properties mailproperties() {
		Properties proper = new Properties();
		proper.put("mail.smtp.starttls.enable", true);
		proper.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		return proper;
	}
	  
	int getStringToInteger(String num) {
		String pro = env.getProperty(num);
		return Integer.parseInt(pro);
	}   
	  
}
