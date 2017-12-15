package top.linjt.java_learning.NIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class Main {
	
	static int length =0x8000000;//128Mb
	public static void main(String[] args) throws IOException  {
		RandomAccessFile randFile = new RandomAccessFile("a.txt", "rw");
//		创建channel
		FileChannel inChannel=randFile.getChannel();
		
		MappedByteBuffer mbb= inChannel.map(MapMode.READ_WRITE, 0, length);
		for(int i=0;i<length;i++){
			mbb.put((byte) 'x');
		}
		
		mbb.flip();
//		inChannel.write(mbb);
		while (mbb.hasRemaining()){
//			System.out.println((char)mbb.get());
		}
//		mbb.clear();
		inChannel.close();
		randFile.close();
		
	}
}
