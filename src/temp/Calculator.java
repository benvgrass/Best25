package temp;

import java.io.*;

public class Calculator {
	private static final String dataPath = "data" + File.separator + "jays.csv";
	
	public static void main(String[] args) throws IOException {
		File f = new File(dataPath);
		BufferedReader scanner = new BufferedReader(new FileReader(f));
		String total = "";
		String data;
		while((data = scanner.readLine()) != null) {
			total += data;
		}
		System.out.print(total.length());
		scanner.close();
	}
}
