package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// N - Queen 문제
// 백트래킹
public class p9663 {
	static int[] moveX = { -1, 0, 1}; // 위를 보는 방향 3가지(왼,위,오)
	static int[] moveY = { -1, -1, -1};
	static int count = 0;
	static int temp = 0;
	static boolean[][] queen;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		queen = new boolean[n][n];
		
		backTracking(0, n);
		
		System.out.println(count);
	}
	
	static void backTracking(int startY, int n) {
		if(startY == n) {	// 끝까지 도달한 경우
	    	count++;
	        return;
	    } 
	    else {
	    	for(int x = 0; x < n; x++) {
	    		if(checkQueen(x, startY, n)) {
    				continue;
    			}
	    		
    			queen[startY][x] = true;
    			backTracking(startY + 1, n);
    			queen[startY][x] = false;
	    	}
	    }
	}
	
	// k : 움직이는 방향
	// 퀸이 해당 위치에 놓여져 있는지 체크하는 함수(T : 퀸 존재, F : 퀸 존재 X)
	static boolean checkQueen(int x, int y, int n) {
		// 위로
        for(int i = y; i >= 0; i--) {
            if(queen[i][x])
            	return true;
        }
        // 왼쪽 위로 대각선
        int tx = x - 1;
        int ty = y - 1;
        while(tx >= 0 && ty >= 0) {
            if(queen[ty--][tx--])
            	return true;
        }
        // 오른쪽 위로 대각선
        tx = x + 1;
        ty = y - 1;
        while(ty >= 0 && tx < n) {
            if(queen[ty--][tx++])
            	return true;
        }
		return false;
	}
}
