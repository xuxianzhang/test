package com.xxz.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class ReslectTest {
	@Test
	public void getClassTest() {
		StringBuffer sb = new StringBuffer();
		try {
			Class<?> clz = Class.forName("java.lang.StringBuffer");
			Method[] methods = clz.getMethods();
			for(Method m:methods) {
				System.out.println(m);
			}
			
			Object str = clz.newInstance();
			
			Constructor constructor = clz.getConstructor();
			Object str1 = constructor.newInstance();
			
			Method append = clz.getMethod("append", String.class);
			System.out.println(append.getName());
			append.invoke(sb, "sb append this words!");
			System.out.println(sb.toString());
			
			if(str1 instanceof StringBuffer) {
				System.out.println("str1 is a Instance of StringBuffer!");
			}
			
			if(str instanceof StringBuffer) {
				System.out.println("str is a Instance of StringBuffer!");
			}
			
			if(StringBuffer.class.isInstance(sb)) {
				System.out.println("sb is a Instance of StringBuffer!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
