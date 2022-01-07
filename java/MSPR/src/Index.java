import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Index {
	
	public static void main(String[] args) throws IOException {
		
		String html = "<div><h1>hello</h1></div>";
		
		File fa= new File("C:\\Users\\Mael\\Documents\\Document Mael");
		
		
			BufferedWriter bw = new BufferedWriter(new FileWriter(fa));
			bw.write(html);
			bw.close();
		
	}
}
