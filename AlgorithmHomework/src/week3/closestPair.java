package week3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// 201402407 이해원
// N개의 점이 주어졌을 때 두 점 사이가 가장 가까운 거리를 찾는 알고리즘.
// Closest Pair 알고리즘
public class closestPair {
	public static void main(String args[]) throws IOException {
		String fileSrc = new java.io.File("").getAbsolutePath();
		String inputFileSrc =  fileSrc + "/src/data03_closest.txt";	// 상대 경로 설정
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFileSrc));
			String str;
			StringTokenizer st;
			List<Point> list = new ArrayList<Point>();
			while((str = br.readLine()) != null) {
				st = new StringTokenizer(str, ",");
				double x = Double.valueOf(st.nextToken());
				double y = Double.valueOf(st.nextToken());
				Point point = new Point(x, y);
				list.add(point);
			}
			Collections.sort(list); // 오름차순 정렬
			double distance = merge(list);
			System.out.format("%.3f", distance);
		} catch (FileNotFoundException e) {
			System.exit(0);
		} catch (IOException e) {
			System.exit(0);
		} catch (NumberFormatException e) {
			System.exit(0);
		}
	}
	
	private static double merge(List<Point> list) {
		if(list.size() <= 3) {
			return bruteForce(list, 0, list.size());
		}
		
		int right = list.size();
		int mid = list.size() / 2;
		double leftDistance = merge(list.subList(0, mid));
		double rightDistance = merge(list.subList(mid, right));
		double minDistance = Math.min(leftDistance, rightDistance);
					
		// line은 왼쪽 리스트의 맨 오른쪽 끝 x좌표와 오른쪽 리스트의 맨 왼쪽 끝 x좌표의 절반에 해당하니까
		double line = (list.get(mid - 1).getX() + list.get(mid).getX()) / 2;
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
		// Y좌표 오름차순 정렬
		Collections.sort(window, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if(o1.getY() < o2.getY()) {
					return -1;
				}
				else {
					return 1;
				}
			}
		});
		
		// 여기서, 두 y값 차이가 minDistance 보다 크면 바로 종료시키기
		for(int i = 0; i < window.size() - 1; i++) {
			Point point1 = window.get(i);
			for(int j = i + 1; j < window.size(); j++) {
				Point point2 = window.get(j);
				if(point2.getY() - point1.getY() < minDistance) { // 최소 거리 이내인 y값 탐색
					minDistance = Math.min(minDistance, 
							getDistance(point1.getX(), point1.getY(), point2.getX(), point2.getY())
							);
				}
				else
					break;
			}
		}
		return minDistance;
	}
	
	// 두 점 사이의 거리 계산
	private static double getDistance(double x1, double y1, double x2, double y2) {
		double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
		return distance;
	}
	
	private static double bruteForce(List<Point> list, int start, int end) {
		double min = Double.MAX_VALUE;
		for(int i = start; i < end - 1; i++) {
			Point p1 = list.get(i);
			for(int j = i + 1; j < end; j++) {
				Point p2 = list.get(j);
				min = Math.min(min, getDistance(p1.getX(), p1.getY(), p2.getX(), p2.getY()));
			}
		}
		return min;
	}
}

class Point implements Comparable<Point> {
	private double x;
	private double y;
	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	// x 좌표 오름차순 정렬
	@Override
	public int compareTo(Point point) {
		// -1이면 위치 고정
		if(this.x > point.x) {
			return 1;
		}
		else if(this.x == point.x) {
			if(this.y > point.y) {
				return -1;
			}
			else
				return 1;
		}
		else {
			return -1;
		}
	}
	
	
	
}