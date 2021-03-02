package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// µÚÁýÈù µ¡¼À
// ¹®ÀÚ¿­
public class p1357 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		
		String x = line.split(" ")[0];
		String y = line.split(" ")[1];
		int xLen = x.length();
		int yLen = y.length();
		
		// Rev(X)
		StringBuilder sbX = new StringBuilder();
		for(int i = xLen - 1; i >= 0; i--) {
			sbX.append(x.charAt(i));
		}
		// Rev(Y)
		StringBuilder sbY = new StringBuilder();
		for(int j = yLen - 1; j >= 0; j--) {
			sbY.append(y.charAt(j));
		}
		
		int revX = Integer.parseInt(sbX.toString());
		int revY = Integer.parseInt(sbY.toString());
		
		int revXY = revX + revY;
		
		// Rev(Rev(X) + Rev(Y))
		String resultStr = String.valueOf(revXY);
		String result = "";
		for(int j = resultStr.length() - 1; j >= 0; j--) {
			result += resultStr.charAt(j);
		}
		
		System.out.println(Integer.parseInt(result));
	}
}
