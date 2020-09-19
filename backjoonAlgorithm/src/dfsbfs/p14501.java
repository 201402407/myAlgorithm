package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 퇴사
// DFS
public class p14501 {
	static int[] weight, day;
	static int[] result;
	static int money = 0; 
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		weight = new int[n + 1];
		day = new int[n + 1];
		result = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int days = Integer.valueOf(st.nextToken());
			int weights = Integer.valueOf(st.nextToken());
			day[i] = days;
			weight[i] = weights;
		}
		
		for(int i = 1; i <= day[1] && i <= n; i++) { // 무조건 1일 차 부터 시작하는 것이 아닌 1일 차에서 걸리는 것부터 시작 
			dfs(n, new Consult(i, 0));
		}
		System.out.println(money);
	}
	
	static void dfs(int n, Consult consult) {
		int nowDay = consult.getNowDay();
		int nowWeight = consult.getWeight();
		if(nowDay + day[nowDay] > n + 1) { // 이 날 상담을 받으면 중간에 퇴사해야 하는 경우
			money = Math.max(money, nowWeight);
			return;
		}
		int nextDay = nowDay + day[nowDay];
		int nextWeight = nowWeight + weight[nowDay];
		if(nextDay == n + 1) {
			money = Math.max(money, nextWeight);
			return;
		}
		for(int i = nextDay; i <= n; i++) {
			dfs(n, new Consult(i, nextWeight));
		}
	}
}

class Consult {
	private int nowDay;
	private int weight;
	
	Consult(int nowDay, int weight) {
		this.nowDay = nowDay;
		this.weight = weight;
	}

	public int getNowDay() {
		return nowDay;
	}

	public void setNowDay(int nowDay) {
		this.nowDay = nowDay;
	}

	public int getWeight() {
		
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
