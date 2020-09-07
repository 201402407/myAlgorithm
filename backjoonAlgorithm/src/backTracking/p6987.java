package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ������
// ��Ʈ��ŷ
// ���Ʈ ���� 
// �ð����⵵ : O(3^15) -> �� ��� �� �ִ� 3���� ����� ����. �� 15��⸦ �ϹǷ�.
public class p6987 {
	static final int MAX_TEAM_COUNT = 6;
	static int[][] matches;
	static boolean isEndGame = false;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int tc = 4;
		// �ִ� ��� ������ ����� �� ���ϱ�
		int size = 0;
		for(int i = 1; i < MAX_TEAM_COUNT; i++) {
			size += i;
		}
		
		// ��� ��ġ �� �� ���� ���� 
		matches = new int[size][2];
		int index = 0;
		for(int i = 0; i < MAX_TEAM_COUNT - 1; i++) {
			for(int j = i + 1; j < MAX_TEAM_COUNT; j++) {
				matches[index][0] = i;
				matches[index][1] = j;
				index++;
			}
		}
		
		while(tc --> 0) {
			st = new StringTokenizer(br.readLine());
			int[][] worldCup = new int[3][MAX_TEAM_COUNT]; // �ε����� �����°� ������? �� : ��/��/��, �� : A,B,C,D,E,F�� 
			boolean isPossible = true;
			
			// ��� ��� ��� �Է¹ޱ� 
			for(int i = 0; i < MAX_TEAM_COUNT; i++) {
				int win = Integer.valueOf(st.nextToken());
				int draw = Integer.valueOf(st.nextToken());
				int lose = Integer.valueOf(st.nextToken());
				
				worldCup[0][i] = win;
				worldCup[1][i] = draw;
				worldCup[2][i] = lose;
				
				// �� ���� 5���� ����ؾ� �Ѵ�.
				if(win + draw + lose != 5) {
					isPossible = false;
					break;
				}
			}
			
			// ��� ���� ��� ���� ���ǿ� ��ġ�ϴ� ��� ��� ��� �� ���� 
			if(isPossible) {
				backTracking(worldCup, 0, size);
				if(isEndGame) {
					sb.append(1);
				}
				else {
					sb.append(0);
				}
			}
			else {
				sb.append(0);
			}
			
			sb.append(" ");
			isEndGame = false;
		}
		
		System.out.print(sb.toString());
	}
	
	// ��Ʈ��ŷ �Լ� 
	static void backTracking(int[][] worldCup, int matchCount, int size) {
		if(isEndGame) {
			return;
		}
		
		// ��� ������ ������ �� �ִٸ� �� �������� �����ϴ�.
		if(matchCount == size) {
			isEndGame = true;
			return;
		}
		
		int myTeam = matches[matchCount][0];
		int enemyTeam = matches[matchCount][1];
		
		// �� -> ��
		if(worldCup[0][myTeam] > 0 && worldCup[2][enemyTeam] > 0) {
			worldCup[0][myTeam]--;
			worldCup[2][enemyTeam]--;
			backTracking(worldCup, matchCount + 1, size);
			worldCup[0][myTeam]++;
			worldCup[2][enemyTeam]++;
		}
		// �� -> ��
		if(worldCup[1][myTeam] > 0 && worldCup[1][enemyTeam] > 0) {
			worldCup[1][myTeam]--;
			worldCup[1][enemyTeam]--;
			backTracking(worldCup, matchCount + 1, size);
			worldCup[1][myTeam]++;
			worldCup[1][enemyTeam]++;
		}
		// �� -> ��
		if(worldCup[2][myTeam] > 0 && worldCup[0][enemyTeam] > 0) {
			worldCup[2][myTeam]--;
			worldCup[0][enemyTeam]--;
			backTracking(worldCup, matchCount + 1, size);
			worldCup[2][myTeam]++;
			worldCup[0][enemyTeam]++;
		}
	}
}
