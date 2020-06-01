package anything;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// �������� ����
// �����佺ü�׽��� ü
public class p6588 {
	static boolean[] anatos;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		anatos = new boolean[1000001];
		
		// �����佺ü�׽� ���
		anatos[1] = true;
		for(int i = 2; i <= 1000; i++) {
			if(anatos[i]) {
				continue;
			}
			
			int ele = i;
			
			for(int j = ele * 2; j < anatos.length; j += ele) {
				if(!anatos[j]) { 
					anatos[j] = true;
				}
			}
		}
		
		int tc = Integer.parseInt(br.readLine());
		while(tc != 0) {
			boolean check = false;
			for(int i = tc - 3; i >= 3; i--) {
				if(!anatos[i] && !anatos[tc - i]) {
					sb.append(tc + " = ").append(i > tc - i ? tc - i : i)
					.append(" + ").append(i > tc - i ? i : tc - i).append("\n");
					check = true;
					break;
				}
			}
			if(!check) {
				sb.append("Goldbach's conjecture is wrong.").append("\n");
			}
			
			tc = Integer.parseInt(br.readLine());
		}
		
		System.out.println(sb.toString());
	}
}
