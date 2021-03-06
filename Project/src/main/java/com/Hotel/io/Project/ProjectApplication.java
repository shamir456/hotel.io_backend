package com.Hotel.io.Project;

import com.Hotel.io.Project.config.SecurityUtility;
import com.Hotel.io.Project.domain.User;
import com.Hotel.io.Project.domain.security.Role;
import com.Hotel.io.Project.domain.security.UserRole;
import com.Hotel.io.Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableJpaAuditing
@EnableTransactionManagement
public class ProjectApplication implements CommandLineRunner {

	@Value("${server.port:8443}") 
	private int serverPort;

	@Autowired
	private UserService userService;

	public static void main(String[] args)
	{
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{

		User user1 = new User();
		user1.setLastname("John");
		user1.setFirstname("Adams");
		user1.setUsername("Shameer");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("jp"));
		user1.setEmail("k164030@nu.edu.pk");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1 = new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1, role1));

		userService.createUser(user1, userRoles);

		userRoles.clear();

		User user2 = new User();
		user2.setFirstname("Admin");
		user2.setLastname("Admin");
		user2.setUsername("admin");
		user2.setPassword(SecurityUtility.passwordEncoder().encode("pp"));
		user2.setEmail("Admin@gmail.com");
		Role role2 = new Role();
		role2.setRoleId(0);
		role2.setName("ROLE_ADMIN");
		userRoles.add(new UserRole(user2, role2));

		userService.createUser(user2, userRoles);
	}



	// @Bean
	// public ServletWebServerFactory servletContainer() {
	// 	TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
	// 		@Override
	// 		protected void postProcessContext(Context context) {
	// 			SecurityConstraint securityConstraint = new SecurityConstraint();
	// 			securityConstraint.setUserConstraint("CONFIDENTIAL");
	// 			SecurityCollection collection = new SecurityCollection();
	// 			collection.addPattern("/*");
	// 			securityConstraint.addCollection(collection);
	// 			context.addConstraint(securityConstraint);
	// 		}
	// 	};

	// 	tomcat.addConnectorCustomizers(new TomcatConnectorCustomizer() {
			
	// 		@Override
	// 		public void customize(Connector connector) {
	// 			((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);				
	// 		}
	// 	});

	// 	tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
	// 	return tomcat;
	// }

	// private Connector initiateHttpConnector() {
	// 	Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	// 	connector.setScheme("http");
	// 	connector.setPort(8080);
	// 	connector.setSecure(false);
	// 	connector.setRedirectPort(serverPort);

	// 	//Tomcat maxSwallowSize sets to 2MB by default.
	//     //To set the maxSwallowSize property of Tomcat https://tomcat.apache.org/tomcat-8.0-doc/config/http.html
	//     //http://stackoverflow.com/questions/35748022/multipart-file-maximum-size-exception-spring-boot-embbeded-tomcat
	    
	// 	((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(11534336);
	// 	return connector;
	// }
}
