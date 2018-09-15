package springboot.myabtis.multidatasource.module.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class IndexControllerTest {

	@Test
	void test() {
		System.out.println("ok");
	}
	
	@Test
	void test2() {
		BigDecimal req = new BigDecimal("18.98");
		String result = req.multiply(new BigDecimal("0.99")).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
		System.out.println(result);
	}

}
