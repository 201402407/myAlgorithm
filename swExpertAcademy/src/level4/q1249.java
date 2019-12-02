package level4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 4���� - ���޷� ����
// DFS?
public class q1249 {
	static int[] moveX = {0, 0, -1, 1};	// ��, ��, ��, ��
	static int[] moveY = {-1, 1, 0, 0};	// ��, ��, ��, ��
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int c = Integer.valueOf(st.nextToken());	// �׽�Ʈ ���̽� ��ü ���
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= c; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			sb.append("#").append(i).append(" ");
			int[][] map = new int[n + 2][n + 2];	// 1���� n����
			int[][] distance = new int[n + 2][n + 2];	// 1���� n����
			for(int j = 1; j <= n; j++) {
				String str = new String(br.readLine());
				for(int k = 0; k < str.length(); k++) {
					map[j][k + 1] = str.charAt(k) - '0';	// �ƽ�Ű �ڵ��� char�̹Ƿ� �ƽ�Ű �ڵ� ���� 0�� ���� �� 
					distance[j][k + 1] = Integer.MAX_VALUE;
				}
			}
			int result = bfs(n, map, distance);
			sb.append(result).append("\n");
		}
		System.out.println(sb.toString());
		
	}
	
	static int bfs(int n, int[][] map, int[][] distance) {
		Point startPoint = new Point(1, 1, 0);	// ������
		PriorityQueue<Point> q = new PriorityQueue<Point>();
		q.offer(startPoint);
		while(!q.isEmpty()) {
			Point currentPoint = q.poll();
			int currentX = currentPoint.getX();
			int currentY = currentPoint.getY();
			int currentWeight = currentPoint.getWeight();
			if(currentX == n && currentY == n) {
				return currentWeight;
			}
			
			for(int i = 0; i < moveX.length; i++) {	// �����¿�
				int nextX = currentX + moveX[i];
				int nextY = currentY + moveY[i];
				int nextWeight = currentWeight + map[nextY][nextX];
				if(nextX <= 0 || nextX >= n + 1 || nextY <= 0 || nextY >= n + 1) {	// �� �����
					continue;
				}
				else {
					// �ִ� �Ÿ��� �̵��� ���� ���� �ش� ��ǥ�� �������� ���� ��쿡 ���Ű� ť�� �ֱ� ����.
					if(nextWeight < distance[nextY][nextX]) {
						Point nextPoint = new Point(nextX, nextY, nextWeight);
						distance[nextY][nextX] = nextWeight;
						q.offer(nextPoint);
					}
				}
			}
		}
		return distance[n][n];
		
	}
}

class Point implements Comparable<Point> {
	int x;
	int y;
	int weight;
	
	Point(int x, int y, int weight) {
		this.x = x;
		this.y = y;
		this.weight = weight;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int compareTo(Point o) {
		if(this.weight < o.weight) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
