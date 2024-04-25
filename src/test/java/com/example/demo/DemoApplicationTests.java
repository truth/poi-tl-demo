package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {

	}
	@Test
	void testFeatures(){
		String feature = "A=4×3㎡";
		String numString = feature.replaceAll("[^0-9.×]", "");
		String[] nums = numString.split("×");
		BigDecimal num1 = new BigDecimal(nums[0]);
		BigDecimal num2 = new BigDecimal(nums[1]);
		System.out.println(num1+","+num2);
	}
}
