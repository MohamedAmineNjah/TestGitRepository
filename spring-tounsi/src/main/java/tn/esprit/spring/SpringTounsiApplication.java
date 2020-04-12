package tn.esprit.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@SpringBootApplication
public class SpringTounsiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTounsiApplication.class, args);
	}

}
