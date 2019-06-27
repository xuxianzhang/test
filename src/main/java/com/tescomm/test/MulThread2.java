package com.tescomm.test;

public class MulThread2 {
	private int i = 200;

	public int getI() {
		return i;
	}

	public synchronized void method1() {
		System.out.println("method1: *10");
		i *= 10;
		System.out.println("i=" + i);
	}

	public synchronized void method2() {
		System.out.println("method2: *20");
		i *= 20;
		System.out.println("i=" + i);
	}

	public synchronized void method3() {
		System.out.println("method3: +30");
		i += 30;
		System.out.println("i=" + i);
	}

	public synchronized void method4() {
		System.out.println("method4: +40");
		i += 40;
		System.out.println("i=" + i);
	}

	public static void main(String[] args) {
		final MulThread2 test = new MulThread2();
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				test.method1();
			}
		});

		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				test.method2();
			}
		});

		Thread thread3 = new Thread(new Runnable() {
			public void run() {
				test.method3();
			}
		});

		Thread thread4 = new Thread(new Runnable() {
			public void run() {
				test.method4();
			}
		});

		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
	}
}
