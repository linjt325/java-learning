package top.linjt.java_learning;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import top.linjt.java_learning.IO.inputStreamTemplate.InputStreamProcessingTemplate;
import top.linjt.java_learning_util.exception.MyException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    public static void main(String[] args) throws MyException  {
		
    	new InputStreamProcessingTemplate() {
			
			@Override
			public void doProcess(InputStream input) throws IOException {
				byte[] bytes=new byte[1024];
				
				int data=input.read(bytes);
				while(data!=-1){
					for(int i=0;i<bytes.length;i++){
						System.out.print((char)bytes[i]);
					}
					data=input.read(bytes);
				}
			}
		}.process("D:\\xxx.txt");
				
		
		
	}
}
