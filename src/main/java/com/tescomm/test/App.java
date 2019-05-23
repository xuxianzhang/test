package com.tescomm.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] argv) throws IOException {
		for(int i = 0;i<argv.length;i++) {
			System.out.println(argv[i]);
		}
		if(argv.length < 2) {
			System.out.println("wrong param!");
		}
		File input = new File(argv[0]);
		File output = new File(argv[1]);
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fos = new FileOutputStream(output);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int cnt = 0;
		long all = 0;
		long start = 0L;
		long finish = 0L;
		byte[] buf = new byte[102400];
		try {
			start = new Date().getTime();
			while((cnt = fis.read(buf)) != -1) {
				all+=cnt;
				fos.write(buf);
			}
			finish = new Date().getTime();
			System.out.println("Read complate! total buf : "+all+". total time : "+(finish - start)/1000.0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			fis.close();
			fos.close();
		}
		
	}
}
