package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class p1003 {
	static List<Point> result = new ArrayList<Point>();
	static Point[] points;
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int n = Integer.parseInt(br.readLine());
			points = new Point[n + 1];
			for(int i = 0; i < n; i++) {
				int num = Integer.parseInt(br.readLine());
				if(num < 0 || num > 40)
					System.exit(0);
				result.add(fibonacci(num));
			}
			
			result.stream().forEach(element -> element.print());
		} catch (NumberFormatException e) {
			System.exit(0);
		} catch (IOException e) {
			System.exit(0);
		}
	}
	
	private static Point fibonacci(int n) {
		if(points[n] != null) {
			return points[n];
		}
		else {
			if(n == 0) {
				points[n] = new Point(1, 0);
			}
			else if(n == 1) {
				points[n] = new Point(0, 1);
			}
			else {
				Point p1 = fibonacci(n - 1);
				Point p2 = fibonacci(n - 2);
				int zero = p1.getZeroCount() + p2.getZeroCount();
				int one = p1.getOneCount() + p2.getOneCount();
				points[n] = new Point(zero, one);
					
			}
			return points[n];
		}
	}
}

class Point {
	private int zeroCount;
	private int oneCount;
	
	Point(int zero, int one) {
		this.zeroCount = zero;
		this.oneCount = one;
	}
	
	public int getZeroCount() {
		return zeroCount;
	}
	public int getOneCount() {
		return oneCount;
	}
	
	public void print() {
		System.out.println(new StringBuilder().append(this.zeroCount).append(" ").append(this.oneCount));
	}
}
