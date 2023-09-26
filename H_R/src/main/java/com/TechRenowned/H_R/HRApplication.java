package com.TechRenowned.H_R;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class HRApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(HRApplication.class, args);
		//Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechRenowned", "postgres", "admin");
		//System.out.println(conn);
	}

}
