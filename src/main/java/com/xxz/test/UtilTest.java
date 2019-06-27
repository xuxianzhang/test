package com.xxz.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.plaf.synth.SynthColorChooserUI;

import org.junit.Test;

public class UtilTest {

	

	// @Test
	public void arrayTest() {
		Random ra = new Random();
		Integer[] intArray = new Integer[100];
		for (int i = 0; i < intArray.length; i++) {
			intArray[i] = ra.nextInt(101);
		}
		System.out.println(Arrays.toString(intArray));
		class com implements Comparator<Integer> {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o1;
			}

		}

		Arrays.sort(intArray);

		int index = Arrays.binarySearch(intArray, 0, 99, 40);
		while (index > 0 && index != 99) {
			System.out.println("has" + index);
			System.out.println(intArray[index]);
			index = Arrays.binarySearch(intArray, index + 1, 99, 40);
		}
		System.out.println(Arrays.toString(intArray));

		List<Integer> stringAl = Arrays.asList(intArray);
		ArrayList<Integer> stringA2 = new ArrayList<Integer>();
		Collections.synchronizedList(stringA2);
		System.out.println(stringAl.toString());
	}

	//@Test
	public void vectorTest() {
		Integer time = 0;
		int capacity = 100;
		Random ra = new Random();
		Vector vector = new Vector();
//		Integer[] intArray = new Integer[capacity];
//		for(int i = 0;i<intArray.length;i++) {
//			intArray[i]= ra.nextInt(101);
//		}
		// Arrays.asList(intArray)

		System.out.println(vector.toString());

		class vectorEdit implements Runnable {
			public void run() {
//				synchronized (vector) {
//					System.out.println("name:" + Thread.currentThread().getName() + "vector size: " + vector.size()
//							+ vector.toString());
//					Integer value = ra.nextInt(101);
//					vector.add(value);
//
//					System.out.println("name:" + Thread.currentThread().getName() + "vector size: " + vector.size()
//							+ vector.toString());
//
//				}
				synchronized (vector) {
				System.out.println("name:" + Thread.currentThread().getName() + "vector size: " + vector.size()
						+ vector.toString());
				}
				Integer value = ra.nextInt(101);
				vector.add(value);
				synchronized (vector) {
				System.out.println("name:" + Thread.currentThread().getName() + "vector size: " + vector.size()
						+ vector.toString());
				}
				// System.out.println("thread name : "+Thread.currentThread().getName());
			}
		}
		;

		for (int i = 0; i < 20; i++) {
			Thread thread = new Thread(new vectorEdit());
			thread.setName(String.valueOf(i));
			thread.start();
		}
		try {
			Thread.currentThread().sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(vector.toString());
		System.out.println("vector size: " + vector.size());

	}
	
	 Integer index = 0;
	//@Test
	public void synchronizedTest() throws InterruptedException {
		
		class demo implements Runnable{

			@Override
			public void run() {
				synchronized (index) {
				for(int i = 0;i<1000;i++) {
						index=index+1;
					}
				}
				System.out.println("结果："+index);
			}
			
		}
		
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(new demo());
			thread.setName(String.valueOf(i));
			thread.start();
		}
		Thread.currentThread().sleep(1000);
		System.out.println("结果："+index);
	}
	//@Test
	public void listTest() {
		Random ra = new Random();
		Integer[] intArray = new Integer[10];
		for (int i = 0; i < intArray.length; i++) {
			intArray[i] = ra.nextInt(101);
		}
		List<Integer> arrayList = Arrays.asList(intArray); 
		ArrayList<Integer> alist = new ArrayList<Integer>(arrayList);
		Iterator it = arrayList.iterator();
		System.out.println(arrayList.toString());
		System.out.println("========================> Iterator next:");
		while(it.hasNext()) {
//			Integer i = (Integer)it.next();
			System.out.println(it.next().toString());
		}
		
		System.out.println("========================> ListIterator next:");
		ListIterator lit = arrayList.listIterator();
		while(lit.hasNext()) {
			System.out.println(lit.next().toString());
		}
		System.out.println("========================> ListIterator previous:");
		while(lit.hasPrevious()) {
			System.out.println(lit.previous().toString());
		}
	}
	//@Test
	public void setTest() {
		System.out.println("===========Hash Set none sort");
		StringBuilder build = new StringBuilder();
		build.append("[");
		HashSet<Integer> set = new HashSet<Integer>();
		Random ra = new Random();
		for (int i = 0; i < 1000; i++) {
			Integer value = ra.nextInt(101);
			if(!set.contains(value)) {
				build.append(value);
				if(i!=999) {
					build.append(",");
				}
			}
			set.add(value) ;
		}
		build.append("]");
		
		System.out.println(set.toString());
		System.out.println(build.toString());
		
		build.delete(0, build.length());
		
		System.out.println("===========Tree Set nature sort");
		TreeSet<String> treeSet = new TreeSet<String>();
		for (int i = 0; i < 1000; i++) {
			Integer value = ra.nextInt(101);
			if(!treeSet.contains(String.valueOf(value.hashCode()))) {
				build.append(value.hashCode());
				if(i!=999) {
					build.append(",");
				}
			}
			treeSet.add(String.valueOf(value.hashCode()));
		}
		System.out.println(treeSet.toString());
		System.out.println(build.toString());
		
		build.delete(0, build.length());
		System.out.println("===========Linked Hash Set insert sort");
		LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<Integer>();
		for (int i = 0; i < 1000; i++) {
			Integer value = ra.nextInt(101);
			if(!linkedHashSet.contains(value)) {
				build.append(value);
				if(i!=999) {
					build.append(",");
				}
			}
			linkedHashSet.add(value) ;
		}
		System.out.println(linkedHashSet.toString());
		System.out.println(build.toString());
	}
	@Test
	public void mapTest() {
		HashMap<String,String> hashMap = new HashMap<String,String>();
		LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<String,String>();
		Hashtable<String,String> hashTable = new Hashtable<String,String>();
		StringBuilder builder = new StringBuilder();
		
		Random ra = new Random();
		builder.append("[");
		for(int i = 0;i<50;i++) {
			String key = String.valueOf(ra.nextInt(1000));
			String value = String.valueOf(ra.nextInt(1000));
			builder.append(key+"="+value);
			if(i!=49) {
				builder.append(",");
			}
			hashTable.put(key, value);
		}
		builder.append("]");
		Iterator<Entry<String, String>> it = hashTable.entrySet().iterator();
		System.out.println(builder.toString());
		System.out.println(hashTable.toString());
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
		System.out.println("====================");
		Enumeration<String> enu = hashTable.keys();
		while(enu.hasMoreElements()) {
			String key = enu.nextElement();
			 System.out.println( key+"="+hashTable.get(key));
		}
	}
}
