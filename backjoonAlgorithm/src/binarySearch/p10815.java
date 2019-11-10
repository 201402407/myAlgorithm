package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p10815 {
	static int[] deck;
	
	public static void main(String args[]) {
	    try {
	    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    StringTokenizer st = new StringTokenizer(br.readLine()); 
	    	int n = Integer.parseInt(st.nextToken());	// ù ��° ��
	    	if(n < 1 || n > 500000) {
	    		System.out.println("����� ī�� ������ ���� �ʽ��ϴ�.");
		    	System.exit(0);
	    	}
	    	deck = new int[n];
	    	
	    	st = new StringTokenizer(br.readLine(), " ");	// �� ��° ��
	    	int countTokens = st.countTokens();
	    	if(countTokens != n) {
	    		System.out.println("ī�� ������ ���� �ʽ��ϴ�.");
		    	System.exit(0);
	    	}
		    for(int i = 0; i < countTokens; i++) {
			    int number = Integer.parseInt(st.nextToken());
			    if(number < -10000000 || number > 10000000) {
			    	System.out.println("n�� ������ ���� �ʽ��ϴ�.");
			    	System.exit(0);
			    }
			    deck[i] = number;
		    }
		    Arrays.sort(deck);
		    
		    st = new StringTokenizer(br.readLine());	// �� ��° ��
		    int m = Integer.parseInt(st.nextToken());
	    	if(m < 1 || m > 500000) {
	    		System.out.println("�� ī�� ������ ���� �ʽ��ϴ�.");
		    	System.exit(0);
	    	}
	    	
	    	st = new StringTokenizer(br.readLine(), " ");	// �� ��° ��
	    	countTokens = st.countTokens();
	    	if(countTokens != m) {
	    		System.out.println("ī�� ������ ���� �ʽ��ϴ�.");
		    	System.exit(0);
	    	}
		    for(int i = 0; i < countTokens; i++) {
			    int number = Integer.parseInt(st.nextToken());
			    if(number < -10000000 || number > 10000000) {
			    	System.out.println("n�� ������ ���� �ʽ��ϴ�.");
			    	System.exit(0);
			    }
			    if(isHaveCard(number)) {
			    	System.out.print("1 ");
			    }
			    else {
			    	System.out.print("0 ");
			    }
		    }
		    
		    
	    }
	    catch(NumberFormatException e) {
	    	System.out.println("���� �ƴ� ���� ���Խ��ϴ�.");
	    	System.exit(0);
	    } catch (IOException e) {
	    	System.out.println("���� �ƴ� ���� ���Խ��ϴ�.");
	    	System.exit(0);
		}
	}
	
	private static boolean isHaveCard(int card) {
		int left = 0;
		int mid = 0;
		int right = deck.length - 1; 
		while(left <= right) {
			mid = (left + right) / 2;
			if(deck[mid] < card) {
				left = mid + 1;
			}
			else if(deck[mid] > card) {
				right = mid - 1;
			}
			else
				return true;
		}
		return false;
	}
}
