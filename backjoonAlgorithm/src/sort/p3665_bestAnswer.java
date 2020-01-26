package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// ���� ���� ����
// ���� ���� ���
// ���� ó�� �ϱ�. 1) Ȯ���� ������ ã�� �� ���� ��(= ���� ��ΰ� ���� �� ������ ��) , 2) �����Ϳ� �ϰ����� ���� ��(= ����Ŭ�� �����ϴ� ���)
public class p3665_bestAnswer {
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
			int[][] graph = new int[n + 1][n + 1];	// ArrayList ��� 2���� �迭�� �׷��� ��Ÿ����.  from , to
			int[] indegree = new int[n + 1];
			while(n --> 0) {
				int team = Integer.valueOf(st.nextToken());
				list.add(team);
			}
			for(int i = 0; i < list.size() - 1; i++) {
				int from = list.get(i);
				for(int j = i + 1; j < list.size(); j++) {
					int to = list.get(j);
					graph[from][to] = 1;
					indegree[to]++;
				}
			}
			
			st = new StringTokenizer(br.readLine());
			int m = Integer.valueOf(st.nextToken());
			while(m --> 0) {	// ������̱� ������
				st = new StringTokenizer(br.readLine());
				int firstTeam = Integer.valueOf(st.nextToken());
				int secondTeam = Integer.valueOf(st.nextToken());
				// ������̱� ������ �� �� �׷��� �� ���Ұ� �����ϴ��� ������ ��
				if(graph[firstTeam][secondTeam] == 1) {
					graph[firstTeam][secondTeam] = 0;
					graph[secondTeam][firstTeam] = 1;
					indegree[firstTeam]++;
					indegree[secondTeam]--;
				}
				else {
					graph[secondTeam][firstTeam] = 0;
					graph[firstTeam][secondTeam] = 1;
					indegree[secondTeam]++;
					indegree[firstTeam]--;
				}
			}
			
			String result = topologicalSort(graph, indegree);
			if(result.equals("SUCCESS")) {	// ���������� ������ ���� ���
				for(int i = 0; i < ranking.size() - 1; i++) {
					sb.append(ranking.get(i)).append(" ");
				}
				sb.append(ranking.get(ranking.size() - 1));
			}
			else {	// IMPOSSIBLE or ? �� ���
				sb.append(result);
			}
			sb.append("\n");
			ranking.clear();	// ���� �׽�Ʈ���̽��� ���� �ʱ�ȭ
		}
		System.out.println(sb.toString());
	}
	
	// return : "?", "IMPOSSIBLE", "SUCCESS"
	static String topologicalSort(int[][] graph, int[] indegree) {
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i = 1; i < indegree.length; i++) {
			if(indegree[i] == 0) {
				queue.add(i);
			}
		}
		// ���� ���� ����(1 ~ n���� �ϳ����� �̾� �� ���� ������ Ȯ����Ű�� ������ for�� ���.)
		for(int i = 1; i < graph.length; i++) {
			if(queue.isEmpty()) {	// ����Ŭ�� �����ϴ� ����. indegree�� 0�� �ϳ��� ���� ���
				return "IMPOSSIBLE";
			}
			if(queue.size() >= 2) {	// �� ���� ������ �����ϴ� ����
				return "?";
			}
			int team = queue.poll();
			ranking.add(team);
			for(int j = 1; j < graph[team].length; j++) {
				if(graph[team][j] == 1) {
					indegree[j]--;
					if(indegree[j] == 0) {
						queue.add(j);
					}
				}
			}
		}
		return "SUCCESS";
	}
}
