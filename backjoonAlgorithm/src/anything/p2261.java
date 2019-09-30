package anything;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// 가장 가까운 두 점
// Closest Pair 알고리즘
public class p2261 {
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		try {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			if(n < 2 || n > 100000) {
				System.exit(0);
			}
			List<Point> list = new ArrayList<Point>();
			// 점들 리스트에 넣기
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.valueOf(st.nextToken());
				int y = Integer.valueOf(st.nextToken());
				if(Math.abs(x) > 10000 || Math.abs(y) > 10000) {
					System.exit(0);
				}
				Point point = new Point(x, y);
				list.add(point);
			}
			Collections.sort(list); // 오름차순 정렬
			int result = merge(list);
			System.out.println(result);
		}
		catch (NumberFormatException e) {
			System.exit(0);
		}
		catch (IOException e) {
			System.exit(0);
		}
	
	}

	private static int merge(List<Point> list) {
		if(list.size() == 2) {
			return getDistance(list.get(0), list.get(1));
		}
		if(list.size() == 3) {
			return bruteForce(list, 0, 3);
		}
		
		int right = list.size();
		int mid = list.size() / 2;
		// 좌, 우 리스트 병합
		int leftDistance = merge(list.subList(0, mid));
		int rightDistance = merge(list.subList(mid, right));
		int minDistance = Math.min(leftDistance, rightDistance);
		
		int line = (list.get(mid - 1).getX() + list.get(mid).getX()) / 2;
		
		List<Point> window = new ArrayList<Point>();
		// 왼쪽 리스트 탑-다운 탐색
		for(int i = mid - 1; i >= 0; i--) {
			Point p = list.get(i);
			if(line - p.getX() < minDistance) {
				window.add(p);
			}
			else
				break;
		}
		// 오른쪽 리스트 바텀-업 탐색
		for(int i = mid; i < right; i++) {
			Point p = list.get(i);
			if(p.getX() - line < minDistance) {
				window.add(p);
			}
			else
				break;
		}
		if(window.isEmpty()) {
			return minDistance;
		}
		
		// y좌표 오름차순 정렬
		Collections.sort(window, new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				if(p1.getY() < p2.getY()) {
					return -1;
				}
				else
					return 1;
			}
		});
		
		// 여기서, 두 y값 차이가 minDistance 보다 크면 바로 종료시키기
		for(int i = 0; i < window.size() - 1; i++) {
			Point point1 = window.get(i);
			for(int j = i + 1; j < window.size(); j++) {
				Point point2 = window.get(j);
				int distanceY = point2.getY() - point1.getY();
				if(distanceY * distanceY < minDistance) { // 최소 거리 이내인 y값 탐색
					minDistance = Math.min(minDistance, getDistance(point1, point2));
				}
				else
					break;
			}
		}
		return minDistance;
	}
	
	// 마지막 리턴 값은 제곱값이므로 거리도 제곱하면 루트가 사라진다.
	private static int getDistance(Point p1, Point p2) {
		return (int) (Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
	}
	
	private static int bruteForce(List<Point> list, int start, int end) {
		int min = Integer.MAX_VALUE;
		for(int i = start; i < end - 1; i++) {
			Point p1 = list.get(i);
			for(int j = i + 1; j < end; j++) {
				Point p2 = list.get(j);
				min = Math.min(min, getDistance(p1, p2));
			}
		}
		return min;
	}
}

class Point implements Comparable<Point> {
	private int x;
	private int y;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	// 오름차순으로 해야하니까
	@Override
	public int compareTo(Point o) {
		if(this.x > o.getX()) {
			return 1;
		}
		else if(this.x == o.getX()) {
			if(this.y < o.getY()) {
				return -1;
			}
			else
				return 1;
		}
		else
			return -1;
	}	
}