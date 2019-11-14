package week7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 201402407 ���ؿ�
 * 7���� ����
 * Segmented Least Squares
 * �ּ� ������
 * ������ �ٻ簪�� ������ �������� �ּҰ� �ǰ� ��
 * �������� ������ �ִ��� �� �ٻ��ϴ� ������ ã�� ��
 * ������ ���� ���� �� ����.
 */
public class SLS {
	static double[][] point; // x, y �� ������ �迭
//	static double[][] sse;
	static double[][] a;
	static double[][] b;
	static double[] opt;
	static double[][] error; // e�� ��
	static int[] division; // �������� ������ ���� �迭
	static int cost;
	public static void main(String args[]) {
		String fileSrc = new java.io.File("").getAbsolutePath();
		String inputFileSrc =  fileSrc + "/src/data07.txt";	// ��� ��� ����
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFileSrc));
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str, ",");
			int n = Integer.valueOf(st.nextToken());
			n++;
			
			// �ε��� : 0 ~ n - 1����.
			point = new double[n][2];
			opt = new double[n];
			error = new double[n][n];
			a = new double[n][n];
			b = new double[n][n];
			division = new int[n];
			for(int i = 1; i < n; i++) {
				point[i][0] = Double.valueOf(st.nextToken());
				point[i][1] = Double.valueOf(st.nextToken());
			}
			cost = Integer.valueOf(st.nextToken());
			SLS(n);
		}
		catch(IOException e) {
			System.exit(0);
		}
		catch (NumberFormatException e) {
			System.out.println("�ѹ����˿���");
			System.out.println(e);
			System.exit(0);
		}
	}
	
	static void SLS(int n) {
		for(int j = 0; j < n; j++) {
			for(int i = 0; i < j; i++) {
				double xySum = 0; // �ñ׸� xy
				double xSum = 0; // �ñ׸� x
				double x2Sum = 0; // �ñ׸� x^2
				double ySum = 0; // �ñ׸� y
					
				// �ñ׸� ���
				// i���� j���� ��� �ñ׸� ������ ���
                for(int k = i; k <= j; k++) { 
                    xSum += point[k][0];
                    ySum += point[k][1];
                    xySum += point[k][0] * point[k][1];
                    x2Sum += point[k][0] * point[k][0];
                }
                double distance = j + 1 - i;
                a[i][j] = (distance * xySum - xSum * ySum) / (distance * x2Sum - xSum * xSum); // a ���
                b[i][j] = (ySum - a[i][j] * xSum) / distance; // b ���

                double SSE = 0;
                for(int k = i; k <= j; k++) {
                    double y = a[i][j] * point[k][0] + b[i][j]; // y = ax + b
                    SSE += Math.pow((point[k][1] - y), 2); // (y - ax - b)^2 �ñ׸� ���
                }
                error[i][j] = SSE; //sum of squared error
			}
		}
		
        for(int k = 0; k < n; k++){
            if(k == 0){ //j = 0�ΰ�� OPT = 0;
                opt[k] = 0;
            }
            else {
        		double min = Double.MAX_VALUE;
                for(int i = 0; i <= k; i++) { 
                    double value = 0;
                    if(i == 0) 
                        value = error[i][k] + cost;
                    else 
                        value = error[i][k] + opt[i - 1] + cost;
                    
                    // �ּ����� min������ ������ min���� ����
                    if(value < min){
                    	if (division[k] < i) { // �ּ����� ������ ��������
                        	division[k] = i;
                    	}
                    	min = value;
                    }
                }
                opt[k] = min;
            }
        }
		resultPrint(n);
	}
	
	private static void resultPrint(int n) {

        System.out.println("Cost of the optimal solution : " + String.format("%.6f", opt[n - 1])); 		// ��ü ������ OPT ��.
        System.out.println();
        System.out.println("An optimal solution :");
        int index = n - 1;
        
        Stack<Integer> stack = new Stack<Integer>();
        int asd = stack.pop();
        Queue<Integer> q = new LinkedList<Integer>();
        ArrayList<Long> tempList = new ArrayList<Long>();
        
        Collections.sort(tempList);
        int asff = stack.lastElement();
        StringBuilder sb = new StringBuilder();
        while(index > 0) { 
            int next = division[index];
            sb.append("[Segment \t").append(next).append(" - ").append(index).append("] : y = ").append(String.format("%.6f", a[next][index]))
            .append(" * x + ").append(String.format("%.6f",b[next][index])).append(" // square error : ").append(String.format("%.6f", error[next][index])).append("\n");
            index = next - 1;
        }
        System.out.println(sb.toString());
	}
}
