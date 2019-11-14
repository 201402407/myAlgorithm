package week9;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

// 201402407 ���ؿ�

/*
 * 0-1 knapsack �˰���
 * W ��ŭ�� ���Ը� ���� �� �ִ� �賶�� ���� ��
 * n���� ����(item)���� �ְ�, ���Ǹ��� ��ȣ(i)�� �ٿ��־���. 
 * ������ ���ǵ��� Wi��ŭ�� ���Կ� Vi���� ��ġ�� ������.
 * �� ��, �賶 �ȿ� ���� ���ǵ��� ���ϴ� ��ġ�� �� ���� ���� ���� �ϱ� ���� ��� ���ǵ�.
 * ���� ���α׷������� �ذ��ؾ� ��
 */
public class knapsack {
	static int bagWeight;
	static int[][] items;
	static int[][] opt;
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("�賶�� ����� �Է��ϼ���(0 ~ 50) : ");
		bagWeight = sc.nextInt();
		if(bagWeight < 0 || bagWeight > 50) {
			System.out.println("0 ~ 50 ������ ���� �Է����� �����̽��ϴ�. �ٽ� �����ϼ���.");
			System.exit(0);
		}
		
		String fileSrc = new java.io.File("").getAbsolutePath();
		String inputFileSrc =  fileSrc + "/src/data09_knapsack.txt";	// ��� ��� ����
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFileSrc));
			StringTokenizer st;
			String str;
			
			items = new int[6][2];	// ������ ������ 5��� �̹� �־����� ������ 5�� �ٷ� �ְ� �����Ѵ�.
			while((str = br.readLine()) != null) {	// ���⼱ item���� �ε����� 1 ~ 5 �̴�.
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
	
	// ���� ���α׷���
	// Bottom-up ���
	// items�� 0�� ° �ε��� : Value,  1�� ° �ε��� : Weight
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
	
	// OPT �迭 �� + max�� item ���
	// i��° �ε������� �ִ��� ������ �ε��� i�� item�� ������ �ִٴ°ǰ�
	static void printOPT() {
		StringBuilder sb = new StringBuilder();
		int max = 0;
		for(int i = 0; i < opt.length; i++) {
			for(int j = 0; j < opt[0].length; j++) { // bagWeight�� 0�̾ opt�� �迭 ũ��� 1�� �ǹǷ� opt[0] ����
				sb.append(opt[i][j]).append("\t");
				max = Math.max(max, opt[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		System.out.println("max : " + max);
		sb.setLength(0); // StringBuilder �ʱ�ȭ
		sb.append("item : ");
		for(int k = opt.length - 1; k >= 1; k--) {
			if(bagWeight >= 0 && opt[k][bagWeight] != opt[k - 1][bagWeight]){
                sb.append(k).append(" ");
                bagWeight = bagWeight - items[k][1]; // �ش� �������� �־����ϱ� �׸�ŭ ������ ���� ���̱�
			}
		}
		System.out.println(sb.toString());
	}
}
