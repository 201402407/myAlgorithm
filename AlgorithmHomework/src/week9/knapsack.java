package week9;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

// 201402407 이해원

/*
 * 0-1 knapsack 알고리즘
 * W 만큼의 무게만 담을 수 있는 배낭이 있을 때
 * n개의 물건(item)들이 있고, 물건마다 번호(i)를 붙여넣었다. 
 * 각각의 물건들은 Wi만큼의 무게와 Vi만의 가치를 가진다.
 * 이 때, 배낭 안에 담은 물건들이 지니는 가치의 총 합이 가장 높게 하기 위해 담는 물건들.
 * 동적 프로그래밍으로 해결해야 함
 */
public class knapsack {
	static int bagWeight;
	static int[][] items;
	static int[][] opt;
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("배낭의 사이즈를 입력하세요(0 ~ 50) : ");
		bagWeight = sc.nextInt();
		if(bagWeight < 0 || bagWeight > 50) {
			System.out.println("0 ~ 50 사이의 수를 입력하지 않으셨습니다. 다시 시작하세요.");
			System.exit(0);
		}
		
		String fileSrc = new java.io.File("").getAbsolutePath();
		String inputFileSrc =  fileSrc + "/src/data09_knapsack.txt";	// 상대 경로 설정
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFileSrc));
			StringTokenizer st;
			String str;
			
			items = new int[6][2];	// 아이템 개수가 5라고 이미 주어졌기 때문에 5를 바로 넣고 시작한다.
			while((str = br.readLine()) != null) {	// 여기선 item들의 인덱스가 1 ~ 5 이다.
				st = new StringTokenizer(str, ",");
				int index = Integer.valueOf(st.nextToken());
				int value = Integer.valueOf(st.nextToken());
				int weight = Integer.valueOf(st.nextToken());
				items[index][0] = value;
				items[index][1] = weight;
			}
			opt = new int[items.length][bagWeight + 1];
			
			getOPT();
			printOPT();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 동적 프로그래밍
	// Bottom-up 방식
	// items의 0번 째 인덱스 : Value,  1번 째 인덱스 : Weight
	static void getOPT() {
		for(int i = 1; i < items.length; i++) {	// 0 ~ n + 1
			for(int w = 0; w <= bagWeight; w++) {	// 0 ~ weight + 1
				if(items[i][1] > w) {
					opt[i][w] = opt[i - 1][w];
				}
				else {
					// max{OPT(i - 1, w) , Vi + OPT(i - 1, w - Wi)}
					opt[i][w] = Math.max(opt[i - 1][w], opt[i - 1][w - items[i][1]] + items[i][0]);
				}
			}
		}
	}
	
	// OPT 배열 값 + max와 item 출력
	// i번째 인덱스에서 최댓값이 나오면 인덱스 i인 item은 무조건 있다는건가
	static void printOPT() {
		StringBuilder sb = new StringBuilder();
		int max = 0;
		for(int i = 0; i < opt.length; i++) {
			for(int j = 0; j < opt[0].length; j++) { // bagWeight가 0이어도 opt의 배열 크기는 1이 되므로 opt[0] 가능
				sb.append(opt[i][j]).append("\t");
				max = Math.max(max, opt[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		System.out.println("max : " + max);
		sb.setLength(0); // StringBuilder 초기화
		sb.append("item : ");
		for(int k = opt.length - 1; k >= 1; k--) {
			if(bagWeight >= 0 && opt[k][bagWeight] != opt[k - 1][bagWeight]){
                sb.append(k).append(" ");
                bagWeight = bagWeight - items[k][1]; // 해당 아이템을 넣었으니까 그만큼 가방의 무게 줄이기
			}
		}
		System.out.println(sb.toString());
	}
}
