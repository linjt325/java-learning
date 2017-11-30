package top.linjt.java_learning.IO.other;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

public class StreamTokenizerT {

	/**
	 * 将字符串分词解析
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		StreamTokenizer tokenizer = new StreamTokenizer(new StringReader("Mary had 1 little lamb..."));

		while(tokenizer.nextToken() != StreamTokenizer.TT_EOF){

		    if(tokenizer.ttype == StreamTokenizer.TT_WORD) {

		        System.out.println(tokenizer.sval);
		    } else if(tokenizer.ttype == StreamTokenizer.TT_NUMBER) {

		        System.out.println(tokenizer.nval);

		    } else if(tokenizer.ttype == StreamTokenizer.TT_EOL) {

		        System.out.println();

		    }
		}
	}
}
