package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// ��� ������
// NFS ��� ��� -> ������.
public class p2579 {
	static int[] scores;
	static int[] result;
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = null; 
	    try {
	    	st = new StringTokenizer(br.readLine());
	    	int n = Integer.parseInt(st.nextToken());
		    if(n < 0 || n > 300) {
		    	System.out.println("n�� ������ ���� �ʽ��ϴ�.");
		    	System.exit(0);
		    }
		    scores = new int[n];
		    result = new int[n];
		    
		    for(int i = 0; i < n; i++) {
		    	st = new StringTokenizer(br.readLine());
			    int score = Integer.parseInt(st.nextToken());
			    if(score < 0 || score > 10000) {
			    	System.out.println("������ ������ ���� �ʽ��ϴ�.");
			    	System.exit(0);
			    }
			    scores[i] = score;
			    result[i] = 0;
		    }
		    int result = bfs(0);
		    System.out.println(result);
		    
	    }
	    catch(IOException e) {
	    	System.exit(0);
	    }
	    catch(NumberFormatException e) {
	    	System.out.println("���� �ƴ� ���� ���Խ��ϴ�.");
	    	System.exit(0);
	    }
	}
	
	private static int bfs(int start) {
		Queue<Walk> queue = new LinkedList<Walk>();
		if(scores.length == 1)
			return scores[0];
		
		queue.add(new Walk(start, scores[0], 0));	// ���������� �� ĭ �ö� ���
		queue.add(new Walk(start + 1, scores[1], 0));	// ���������� �� ĭ �ö� ���
		int finish = scores.length - 1;
		while(!queue.isEmpty()) {
			Walk walking = queue.poll();
			if(walking.getNowStair() == finish) {
				if(result[finish] < walking.getSumScore())
					result[finish] = walking.getSumScore();
			}
			
			if(walking.getNowStair() + 1 <= finish 
					&& walking.getContinueWalkCount() == 0) {	// �� ĭ �ö󰡴� ���
				int nextStair = walking.getNowStair() + 1;
				int nextSumScore = walking.getSumScore() + scores[nextStair];
				Walk nextWalk = new Walk(nextStair, nextSumScore, walking.getContinueWalkCount() + 1);
				queue.add(nextWalk);
			}
			if(walking.getNowStair() + 2 <= finish) {	// �� ĭ �ö󰡴� ���
				int nextStair = walking.getNowStair() + 2;
				int nextSumScore = walking.getSumScore() + scores[nextStair];
				if(result[nextStair] <= nextSumScore) {	// ���ݰ��� ���� �� result ������ ������ ť �߰����� �ʰ� ������
					Walk nextWalk = new Walk(nextStair, nextSumScore, 0);	// ���ӵ� ��� ������ �ʱ�ȭ
					result[nextStair] = nextSumScore;
					queue.add(nextWalk);	
				}
			}
		}
		return result[finish];
	}
}

class Walk {
	private int nowStair;
	private int sumScore;
	private int continueWalkCount;
	
	Walk(int nowStair, int sumScore, int continueWalkCount) {
		this.nowStair = nowStair;
		this.sumScore = sumScore;
		this.continueWalkCount = continueWalkCount;
	}
	
	public int getSumScore() {
		return sumScore;
	}
	public int getNowStair() {
		return nowStair;
	}
	public int getContinueWalkCount() {
		return continueWalkCount;
	}
	
	
}
