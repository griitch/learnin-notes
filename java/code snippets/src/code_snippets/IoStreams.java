package code_snippets;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/*
 * 
 *  hiererchy of streams : https://www.tutorialspoint.com/java/images/file_io.jpg  
 *  
 * io stream : input source or output dest, can be a disk file, another prog, network,  memory array..
 * 
 * streams support different kinds of data, kayn stream t bytes, t primitive data types, objects ...,
 *  some streams just pass data, others manipulate and transform it 
 *  
 *  The xmpls will be mainly file streams
 */

@SuppressWarnings("unused")
public class IoStreams {
/*
 * Byte streams: i/o on bytes, all byte stream classes are descendants of ['Output','Input']Stream classes
 * used for low level streams of raw bytes such as image data
 * xmpl 1 : using FileInputStream to stream a file one byte at a time
 */
	public static void copyBytesFromFile(String file) throws IOException {
		
		// for some reason it works with absolute paths only
		FileInputStream in = null;
		
		try {
			in = new FileInputStream(file);
			int n;
			
			while( (n = in.read()) != -1 ) {
				System.out.println(n); // you'll see nothing but numbers in the console
				// if i had a output stream such as (foo = new OutPutstream) 
				// foo.write(n);
			}
			
			
		} finally {
			if(in != null) {
				// alwazys gotta close streams, (checking if null bcz maybe the open failed mn lwl )
				in.close();
			}
		}	
	}
	
	//---------------------------------------------------------------------------------
	
	/*
	 * Byte streams should only be used for low level io stuff, character streams are more convenient 
	 * all Characters streams are descendant of Reader and Writer classes
	 * xmpl 2 : same as xmpl1 but with a char stream instead 
	 * 
	 */
	
public static void copyCharsFromFile(String file) throws IOException {
		
		// for some reason it works with absolute paths only
		Reader in = null;
		
		try {
			in = new FileReader(file);
			int n;
			
			while( (n = in.read()) != -1 ) {
				System.out.println(n); // numbers in the console too unless casted to char
				// what even is the difference then : //
				//  -> char streams deal with 16 bits unicode, bytestreams deal with 8 bit bytes
			}
			
			
		} finally {
			if(in != null) {
				// alwazys gotta close streams, (checking if null bcz maybe the open failed mn lwl )
				in.close();
			}
		}	
	}

	/* 
	 * Buffered streams : calls to the underlying os are expensive so we try to minimize them, so we use a buffer instead
	 * and only make system calls when the buffer is full (output streams)  /empty (input)
	 *
	 * there are 4 buffered stream classes : 2 for char streams, 2 for byte streams, and ( one for input one for output ) 
	 * 
	 * We turn an unbuffered stream into a buffered one by feeding it into a buffered stream class constructoh
	 * inputStream = new BufferedReader(new FileReader("foobaz.txt"));
	 * 
	 * we can write out a buffer at critical points, this op is called flushing, and is donee either manually with flush() method
	 * or automatically ( PrintWriter.println() flushes the buffer ) 
	*/



	/* scanning and formatting : translating to and from human readable data
	 * scanning : turn input into token associated with bits of data -> Scanner api
	 * formatting : the opposite : assmble data into a nicely formatted form
	 * 
	 * xmpl 1 : scanning 
	 */

	public void scannerExample() throws IOException { // file not found exception to be precise
		
		Scanner s = null;
        try {
            s = new Scanner(new BufferedReader(new FileReader("D:\\\\dev\\\\java\\\\code snippets\\\\src\\\\code_snippets\\\\concurrency.java")));
            // a scanner wraps around an input stream
            /**
             * scanners by default treat input tokens as strings, but it can also support all primitive types except char
             * and other thangs like BigInteger with the Scanner.next[type]
             * xmpl : BigInteger foo = s.nextBigInteger(); int baz = s.nextInt()
             */
            	
            while (s.hasNext()) {
                System.out.println(s.next());
                
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
	}
	

	// for other thangs (data streams and object streams check the official documentation )
	
	/*
	 *  data streams : concerned with primitive types (boolean byte char short int long flaot double )
	 *  
	 *  object streams : i/o of objects that implement serializable interface
	 * 
	 * */
	
	
	public static void main(String[] args) throws IOException {
		
		
		
		Path p1 = Paths.get("DummyClas.java");
		Path p2 = p1.toAbsolutePath().normalize(); 
		System.out.println(p2.toString());
		boolean isRegularExec = Files.isRegularFile(p2) & Files.isReadable(p2) & Files.isExecutable(p2);
		boolean isSameFile = Files.isSameFile(p1, p2);
		 
		
		
		
		// The Files methods work on instances of Path objects.
		
		
		
		 //copyBytesFromFile("D:\\dev\\java\\code snippets\\src\\code_snippets\\concurrency.java");
		 //copyCharsFromFile("D:\\dev\\java\\code snippets\\src\\code_snippets\\concurrency.java");
	}
	

	
	
}
