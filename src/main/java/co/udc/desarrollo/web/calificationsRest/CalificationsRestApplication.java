package co.udc.desarrollo.web.calificationsRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalificationsRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalificationsRestApplication.class, args);
		System.out.println( "CalificationsRestApplication started in " + args[0]);
	}

}
