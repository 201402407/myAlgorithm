package dfsbfs;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// ä����ȣ : 10396292
// ID : thkang010
// Point ��ü ���. ���ʿ� count�� ���� �ְ� �����߰�, ó������ �湮�� ���� ������ �Ͽ���.
public class p1697_bestAnswer {
	private static int N, K, cnt;
	private static boolean[] visited = new boolean[100001];
	
	public static void Finding(int n, int cnt) {
		
		Queue<Point> q = new LinkedList<>();
		
		q.add(new Point(n, cnt));
		visited[n] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			int temp_n = p.x;
			int temp_cnt = p.y;
			
			// ���� ��ġ�� �Ǹ� ī��Ʈ�� �ٷ� ���Ͻ�Ų��.
			if(temp_n == K) { int ans= temp_cnt;
			System.out.println(ans); break;}
			// ���� ��ġ�� �ƴ� ��� ť�� �ְ� ���� ��ġ�� true�� �������ȴ�.
			if(0 <= temp_n-1 && !visited[temp_n-1]) {
				q.add(new Point(temp_n-1, temp_cnt+1)); visited[temp_n-1] = true;
			}
			
			if(temp_n+1 <= 100000 && !visited[temp_n+1]) {
				q.add(new Point(temp_n+1, temp_cnt+1)); visited[temp_n+1] = true;
			}
			
			if(temp_n*2 <= 100000 && !visited[temp_n*2]) {
				q.add(new Point(temp_n*2, temp_cnt+1)); visited[temp_n*2] = true;
			}
		}
		
	}
	
	public static void main(String[] args)throws IOException {
		// ���� ������ �ޱ�
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		
		Finding(N, cnt);
	}
}