package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		// This test ensures the Spring context loads successfully.
		// No assertions are needed as any failure will throw an exception.
		assert true;
	}

	@Test
	void mainMethodRuns() {
		// This test ensures the main method runs without exceptions.
		DemoApplication.main(new String[]{});
		assert true;
	}

}
