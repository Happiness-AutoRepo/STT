package com.happiness.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThymeleafdemoApplicationTests {

	@Test
	public void contextLoads1() {
		System.out.println("Conducting Unit Tests");
	}

	@Test
	public void contextLoads2() {
		System.out.println("Conducting Database Tests");
	}

}

