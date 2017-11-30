package top.linjt.java_learning.IO.inputStreamTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import top.linjt.java_learning_util.exception.MyException;

public class InputStreamProcessingTemplate_Interface {

	public void process(String fileName,InputStreamProcessor processor) throws MyException{
		 IOException processException = null;
	        InputStream input = null;
	        try{
	            input = new FileInputStream(fileName);

	            processor.process(input);
	        } catch (IOException e) {
	            processException = e;
	        } finally {
	           if(input != null){
	              try {
	                 input.close();
	              } catch(IOException e){
	                 if(processException != null){
	                    throw new MyException(
	                      "Error message..." +
	                      fileName,processException);
	                 } else {
	                    throw new MyException("Error closing InputStream for file " +
		                        fileName,e);
	                 }
	              }
	           }
	           if(processException != null){
	              throw new MyException( "Error processing InputStream for file " +
		                    fileName,processException)
	               ;
	           }
	        }
	}
}


