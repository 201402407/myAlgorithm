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
			// �ϵ� ���� �ֱ�
			list.add(Integer.valueOf(st.nextToken()));
			--n;	// �ϳ� ����
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
			while(m --> 0) {	// ������̱� ������
				st = new StringTokenizer(br.readLine());
				int firstTeam = Integer.valueOf(st.nextToken());
				int secondTeam = Integer.valueOf(st.nextToken());
				// ������̱� ������ contains�� ���Ұ� �����ϴ��� ������ ��
				if(graph[firstTeam].contains((Integer) secondTeam)) {	
					graph[firstTeam].remove((Integer) secondTeam);	// �̰� �ǳ�? �ȴ�! -> �ݴ� �������� �����ϱ� ���� ���� ������ �׷������� ����� ���� �߰��Ѵ�.
					indegree[secondTeam]--;	// ���ἱ�� ������ ����
					graph[secondTeam].add(firstTeam);
					indegree[firstTeam]++;	
				}
				else {
					graph[secondTeam].remove((Integer) firstTeam);	// �̰� �ǳ�? �ȴ�! -> �ݴ� �������� �����ϱ� ���� ���� ������ �׷������� ����� ���� �߰��Ѵ�.
					indegree[firstTeam]--;	// ���ἱ�� ������ ����
					graph[firstTeam].add(secondTeam);
					indegree[secondTeam]++;
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
	static String topologicalSort(List<Integer>[] graph, int[] indegree) {
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i = 1; i < graph.length; i++) {
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
