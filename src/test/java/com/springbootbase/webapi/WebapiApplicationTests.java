package com.springbootbase.webapi;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = WebapiApplicationTests.class)
class WebapiApplicationTests {

	@BeforeAll
	public static void before() {
		System.setProperty("jasypt.encryptor.password", "secretkey");
	}

	@Test
	void contextLoads() {
	}

}
