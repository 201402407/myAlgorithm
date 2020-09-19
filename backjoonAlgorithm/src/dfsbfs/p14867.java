package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// ����
// BFS ?
public class p14867 {
	static int a, b;
	static Set<String> visitedSet;	// ������ �湮�ߴ��� Ȯ���ϱ� ���� HashSet. �˻��� ���� ������. 2�����迭�� ����ϱ⿡�� ������ �ʹ� Ŀ�� �ȵ�. 
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		a = Integer.valueOf(st.nextToken());
		b = Integer.valueOf(st.nextToken());
		int finishA = Integer.valueOf(st.nextToken());
		int finishB = Integer.valueOf(st.nextToken());
		visitedSet = new HashSet<String>();
		
		System.out.println(bfs(finishA, finishB));
	}
	
	static int bfs(int finishA, int finishB) {
		Queue<Point14867> q = new LinkedList<Point14867>();
		q.offer(new Point14867(0, 0));
		
		while(!q.isEmpty()) {
			Point14867 p = q.poll();
			int nowX = p.x;
			int nowY = p.y;
			if(nowX == finishA && nowY == finishB) {
				return p.count;
			}
			
			for(int i = 1; i <= 6; i++) {
				int x = nowX;
				int y = nowY;
				// �̹� ���ִµ�, Full �ϴ°Ŵ� ��ȿ����
				if(x == a && i == 1) {
					continue;
				}
				if(y == b && i == 2) {
					continue;
				}
				
				// �̹� ����ִµ�, Empty�� move �ϴ°Ŵ� ��ȿ����
				if(x == 0) {	
					if(i == 3 || i == 5) {
						continue;	
					}
				}
				if(y == 0) {
					if(i == 4 || i == 6) {
						continue;	
					}
				}
				
//				Point14867 nextP = changeWater(p.x, p.y, i);
				switch(i) {
				// 1, 2 : Fill
				case 1:	// x��ŭ ä��� 
					x = a;
					break;
				case 2:	// y��ŭ ä���
					y = b;
					break;
				// 3, 4 : Empty
				case 3:
					x = 0;
					break;
				case 4:
					y = 0;
					break;
				// 5, 6 : Move
				case 5:	// x -> y
					if(b - y < x) {
						x = x - (b - y);
						y = b;
					}
					else {
						y += x;
						x = 0;
					}
					break;
				case 6:	// y -> x
					if(a - x < y) {
						y = y - (a - x);
						x = a;
						
					}
					else {
						x += y;
						y = 0;
					}
					break;
				}
				
				if(isVisited(x, y)) {
					continue;
				}
				
//				nextP.setCount(p.count + 1);
				q.offer(new Point14867(x, y, p.count + 1));
			}
		}
		
		return -1;
	}
	
	// �湮�� ���� �ִ��� üũ. ������ �湮 HashSet�� �ְ� false.
	static boolean isVisited(int x, int y) {
		String point = x + "_" + y;
		if(visitedSet.contains(point)) {
			return true;
		}
		
		visitedSet.add(point);
		return false;
	}
	
	// �� 6������ ����� ���� x�� y������ ���� �� �ٲٱ� 
	static Point14867 changeWater(int x, int y, int menu) {
		switch(menu) {
		// 1, 2 : Fill
		case 1:	// x��ŭ ä��� 
			x = a;
			break;
		case 2:	// y��ŭ ä���
			y = b;
			break;
		// 3, 4 : Empty
		case 3:
			x = 0;
			break;
		case 4:
			y = 0;
			break;
		// 5, 6 : Move
		case 5:	// x -> y
			if(b - y < x) {
				x = x - (b - y);
				y = b;
			}
			else {
				y += x;
				x = 0;
			}
			break;
		case 6:	// y -> x
			if(a - x < y) {
				y = y - (a - x);
				x = a;
				
			}
			else {
				x += y;
				y = 0;
			}
			break;
		}
		
		return new Point14867(x, y);
	}
}

class Point14867 {
	int x;
	int y;
	int count = 0;
	
	Point14867(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	Point14867(int x, int y, int count) {
		this.x = x;
		this.y = y;
		this.count = count;
	}
	
	void setCount(int count) {
		this.count = count;
	}
}
