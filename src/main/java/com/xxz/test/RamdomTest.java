package com.xxz.test;

import java.util.Random;

import org.junit.Test;

public class RamdomTest {
	@Test
	public void random() {
		Random ra = new Random();
		for(int i = 0;i <10;i++) {
			System.out.println("==============================");
			System.out.println("随机整数（0-100）："+ra.nextInt(101));
			System.out.println("随机整数："+ra.nextInt());
			System.out.println("随机长整型："+ra.nextLong());
			System.out.println("随机双精度："+ra.nextDouble());
			System.out.println("随机布尔："+ra.nextBoolean());
		}
	}
}
