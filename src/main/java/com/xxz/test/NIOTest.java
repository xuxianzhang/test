package com.xxz.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

import org.junit.Test;

public class NIOTest {

	// @Test
	public void cacheAllocateTest() {
		try {
			int capacity = 100;
			CharBuffer buffer = CharBuffer.allocate(capacity);
			buffer.put("hello world!");
			System.out.println(buffer.position());
			System.out.println(buffer.limit());

			buffer.flip();
			System.out.println(buffer.position());
			System.out.println(buffer.limit());
			char[] out = new char[buffer.limit()];
			buffer.get(out);
			System.out.println(out);
			System.out.println(buffer.position());
			System.out.println(buffer.limit());
			buffer.rewind();
			buffer.get(out);
			System.out.println(out);
			System.out.println(buffer.position());
			System.out.println(buffer.limit());

			System.out.println(buffer.mark());
			buffer.flip();
			buffer.mark();
			buffer.put("new world!");
			buffer.reset();
			buffer.put("new world!");

			buffer.flip();
			System.out.println(buffer.position());
			System.out.println(buffer.limit());
			buffer.get(out);
			System.out.println(out);
			System.out.println(buffer.position());
			System.out.println(buffer.limit());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// @Test
	public void directMemTest() {
		FileChannel channel = null;
		FileChannel channelOut = null;
		MappedByteBuffer mappedBuffer = null;
		try {
			channel = FileChannel.open(Paths.get("C:\\Users\\xuxia\\Desktop\\script.txt"), StandardOpenOption.WRITE,
					StandardOpenOption.READ);
			channelOut = FileChannel.open(Paths.get("C:\\Users\\xuxia\\Desktop\\script11.txt"),
					StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.READ.CREATE);
			mappedBuffer = channel.map(MapMode.READ_WRITE, 0, channel.size() + 1000);

			System.out.println(channel.size());
			byte[] outByte = new byte[(int) channel.size()];
			mappedBuffer.get(outByte);
			String outString = new String(outByte, "GB2312");
			System.out.println(outString);
			int position = mappedBuffer.position();
			mappedBuffer.flip();
			String str = "this is test buffer!";

			mappedBuffer.put(str.getBytes(), position, str.length());

			channel.transferTo(0, mappedBuffer.position(), channelOut);

			mappedBuffer = channelOut.map(MapMode.READ_WRITE, 0, channel.size());
			mappedBuffer.get(outByte);
			System.out.println(new String(outByte, "GB2312"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (channel != null) {
				try {
					channel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (channelOut != null) {
				try {
					channelOut.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	public void socketTest() {
		SocketChannel open = null;
		FileChannel channelOut = null;
		int wirteSize = 0;
		try {
			open = SocketChannel.open(new InetSocketAddress("127.0.0.1", 1080));
			channelOut = FileChannel.open(Paths.get("C:\\Users\\xuxia\\Desktop\\socket.txt"), StandardOpenOption.CREATE,
					StandardOpenOption.WRITE, StandardOpenOption.READ);
			ByteBuffer info = ByteBuffer.allocate(1024);
			MappedByteBuffer write = channelOut.map(MapMode.READ_WRITE, 0, 102400) ;
			byte[] out = new byte[1024];
			write.flip();
			while(wirteSize <= 102400 ) {
				
				wirteSize += open.read(info);
				info.get(out);
				System.out.println(new String(out,StandardCharsets.UTF_8));
				write.put(info);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (open != null) {
				try {
					open.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(channelOut!=null) {
				try {
					channelOut.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
