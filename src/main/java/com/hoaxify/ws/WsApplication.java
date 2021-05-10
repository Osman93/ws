package com.hoaxify.ws;

import com.hoaxify.ws.user.User;
import com.hoaxify.ws.user.UserRepository;
import com.hoaxify.ws.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WsApplication{


	public static void main(String[] args) {
		SpringApplication.run(WsApplication.class, args);
	}

	@Bean
	CommandLineRunner initUsers(UserService userService){

		return (args) -> {
				System.out.println("Command Line runner");
				User user = new User();
				user.setUsername("Osman2");
				user.setPassword("123456");
				user.setDisplayName("Ossiss");
				userService.save(user);
		};
	}

}
