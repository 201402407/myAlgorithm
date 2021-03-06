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
	    	int n = Integer.parseInt(st.nextToken());	// 첫 번째 줄
	    	if(n < 1 || n > 500000) {
	    		System.out.println("상근이 카드 개수가 맞지 않습니다.");
		    	System.exit(0);
	    	}
	    	deck = new int[n];
	    	
	    	st = new StringTokenizer(br.readLine(), " ");	// 두 번째 줄
	    	int countTokens = st.countTokens();
	    	if(countTokens != n) {
	    		System.out.println("카드 개수가 맞지 않습니다.");
		    	System.exit(0);
	    	}
		    for(int i = 0; i < countTokens; i++) {
			    int number = Integer.parseInt(st.nextToken());
			    if(number < -10000000 || number > 10000000) {
			    	System.out.println("n의 범위가 맞지 않습니다.");
			    	System.exit(0);
			    }
			    deck[i] = number;
		    }
		    Arrays.sort(deck);
		    
		    st = new StringTokenizer(br.readLine());	// 세 번째 줄
		    int m = Integer.parseInt(st.nextToken());
	    	if(m < 1 || m > 500000) {
	    		System.out.println("내 카드 개수가 맞지 않습니다.");
		    	System.exit(0);
	    	}
	    	
	    	st = new StringTokenizer(br.readLine(), " ");	// 네 번째 줄
	    	countTokens = st.countTokens();
	    	if(countTokens != m) {
	    		System.out.println("카드 개수가 맞지 않습니다.");
		    	System.exit(0);
	    	}
		    for(int i = 0; i < countTokens; i++) {
			    int number = Integer.parseInt(st.nextToken());
			    if(number < -10000000 || number > 10000000) {
			    	System.out.println("n의 범위가 맞지 않습니다.");
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
	    	System.out.println("수가 아닌 값이 들어왔습니다.");
	    	System.exit(0);
	    } catch (IOException e) {
	    	System.out.println("수가 아닌 값이 들어왔습니다.");
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
