package dfsbfs;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 채점번호 : 10396292
// ID : thkang010
// Point 객체 사용. 애초에 count를 같이 넣고 진행했고, 처음부터 방문한 다음 진행을 하였다.
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
			
			// 정답 위치가 되면 카운트를 바로 리턴시킨다.
			if(temp_n == K) { int ans= temp_cnt;
			System.out.println(ans); break;}
			// 정답 위치가 아닌 경우 큐에 넣고 다음 위치를 true로 만들어버렸다.
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
		// 버퍼 리더로 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		
		Finding(N, cnt);
	}
}