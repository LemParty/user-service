package com.lemparty;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

	@Value( "${mongo.url}" )
	private static String mongoURL;

	@Value( "${mongo.privatekey.name}" )
	private static String mongoPrivateKeyName;

	public static void main(String[] args) {
		if(mongoPrivateKeyName != null && !mongoPrivateKeyName.equals("")) {
			System.out.println("Using mongo Private Key: "+mongoPrivateKeyName);
			SSLContextHelper.setSslProperties(mongoPrivateKeyName);
		}

		SpringApplication.run(UserServiceApplication.class, args);
	}

}
