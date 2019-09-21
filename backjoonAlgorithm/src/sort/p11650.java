package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// ÁÂÇ¥ Á¤·ÄÇÏ±â
public class p11650 {
	static List<Point> sort = new ArrayList<Point>();
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
	    try {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			if(n < 1 || n > 100000)
				System.exit(0);
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.valueOf(st.nextToken());
				int y = Integer.valueOf(st.nextToken());
				Point point = new Point(x, y);
				sort.add(point);
//				binaryInsertionSort(point);
			}
			Collections.sort(sort);
			
			sort.stream().forEach(point -> point.print());
		} 
	    catch (NumberFormatException e) {
	    	System.exit(0);
	    }
	    catch (IOException e) {
			System.exit(0);
		};
	}
	
	private static int getBinaryIndex(Point key) {
		int mid;
		int left = 0;
		int right = sort.size() - 1;
		
		while(left < right) {
			mid = (left + right) / 2;
			Point comparePoint = sort.get(mid);
			if(key.getX() > comparePoint.getX())
				left = mid + 1;
			else if(key.getX() == comparePoint.getX()) {
				while(mid <= right) {
					if((sort.get(mid + 1).getX() != key.getX()) 
					&& (sort.get(mid + 1).getY() < key.getY())) {
						mid++;
						continue;
					}
					break;
				}
				right = mid;
			}
			else
				right = mid;
		}
		return right;
	}
	// ÀÌÁø »ðÀÔ Å½»ö
	private static void binaryInsertionSort(Point key) {
		if(sort.isEmpty()) {
			sort.add(key);
			return;
		}
		
		sort.add(getBinaryIndex(key), key);
	}
}

class Point implements Comparable<Point>{
	private int x, y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	@Override
	public int compareTo(Point p) {
		if(this.x < p.getX()) {
			return -1;
		}
		else if(this.x == p.getX()) {
			if(this.y < p.getY())
				return -1;
			else
				return 1;
		}
		else
			return 1;
	}
	
	public void print() {
		//.append(" ").append(this.y)
		System.out.println(new StringBuilder().append(this.x).append(" ").append(this.y));
	}
}
