package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 최종 순위 문제
// 위상 정렬 사용
// 예외 처리 하기. 1) 확실한 순위를 찾을 수 없을 때(= 위상 경로가 여러 개 존재할 때) , 2) 데이터에 일관성이 없을 때(= 사이클이 존재하는 경우)
public class p3665 {
	static List<Integer> ranking = new ArrayList<Integer>();
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int tc = Integer.valueOf(st.nextToken());
		while(tc --> 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			st = new StringTokenizer(br.readLine());
			List<Integer> list = new ArrayList<Integer>();
			List<Integer>[] graph = new ArrayList[n + 1];
			int[] indegree = new int[n + 1];
			for(int i = 1; i <= n; i++) {
				graph[i] = new ArrayList<Integer>();
			}
			// 일등 먼저 넣기
			list.add(Integer.valueOf(st.nextToken()));
			--n;	// 하나 감소
			while(n --> 0) {
				int team = Integer.valueOf(st.nextToken());
				indegree[team] = list.size();
				for(int i = 0; i < list.size(); i++) {
					graph[list.get(i)].add(team);
				}
				list.add(team);
			}
			
			st = new StringTokenizer(br.readLine());
			int m = Integer.valueOf(st.nextToken());
			while(m --> 0) {	// 상대적이기 떄문에
				st = new StringTokenizer(br.readLine());
				int firstTeam = Integer.valueOf(st.nextToken());
				int secondTeam = Integer.valueOf(st.nextToken());
				// 상대적이기 때문에 contains로 원소가 존재하는지 따져야 함
				if(graph[firstTeam].contains((Integer) secondTeam)) {	
					graph[firstTeam].remove((Integer) secondTeam);	// 이게 되나? 된다! -> 반대 방향으로 수정하기 위해 기존 방향을 그래프에서 지우고 새로 추가한다.
					indegree[secondTeam]--;	// 연결선의 개수도 수정
					graph[secondTeam].add(firstTeam);
					indegree[firstTeam]++;	
				}
				else {
					graph[secondTeam].remove((Integer) firstTeam);	// 이게 되나? 된다! -> 반대 방향으로 수정하기 위해 기존 방향을 그래프에서 지우고 새로 추가한다.
					indegree[firstTeam]--;	// 연결선의 개수도 수정
					graph[firstTeam].add(secondTeam);
					indegree[secondTeam]++;
				}	
			}
			
			String result = topologicalSort(graph, indegree);
			if(result.equals("SUCCESS")) {	// 성공적으로 순위를 만든 경우
				for(int i = 0; i < ranking.size() - 1; i++) {
					sb.append(ranking.get(i)).append(" ");
				}
				sb.append(ranking.get(ranking.size() - 1));
			}
			else {	// IMPOSSIBLE or ? 인 경우
				sb.append(result);
			}
			sb.append("\n");
			ranking.clear();	// 다음 테스트케이스를 위한 초기화
		}
		System.out.println(sb.toString());
	}
	
	// return : "?", "IMPOSSIBLE", "SUCCESS"
	static String topologicalSort(List<Integer>[] graph, int[] indegree) {
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i = 1; i < graph.length; i++) {
			if(indegree[i] == 0) {
				queue.add(i);
			}
		}
		
		// 위상 정렬 시작(1 ~ n까지 하나씩만 뽑아 한 개의 순위만 확정시키기 때문에 for문 사용.)
		for(int i = 1; i < graph.length; i++) {
			if(queue.isEmpty()) {	// 사이클이 존재하는 조건. indegree가 0이 하나도 없는 경우
				return "IMPOSSIBLE";
			}
			if(queue.size() >= 2) {	// 두 개의 순위가 존재하는 조건
				return "?";
			}
			int team = queue.poll();
			ranking.add(team);
			for(int ele : graph[team]) {
				indegree[ele]--;
				if(indegree[ele] == 0) {
					queue.add(ele);
				}
			}
		}
		return "SUCCESS";
	}
}
