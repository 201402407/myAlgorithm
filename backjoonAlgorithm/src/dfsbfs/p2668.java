package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ?ˆ«?ê³ ë¥´ê¸?
// DFS
public class p2668 {
	static int[] cards;
	static boolean[] checked;
	static boolean isCycle;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		
		// input ê°? ë°°ì—´?— ???¥ 
		cards = new int[n + 1];
		checked = new boolean[n + 1]; // ?•´?‹¹ ì¹´ë“œ ë½‘ì•˜?Š”ì§? ?—¬ë¶? ?™•?¸
		for(int i = 1; i <= n; i++) {
			cards[i] = Integer.valueOf(br.readLine());
		}
		
		// ?¸?±?Š¤ - ê°? ?´ ê°™ì? ?Œ ì°¾ê¸° -> ë¬´ì¡°ê±? ê°ê°?˜ ì§‘í•© ?Œ?— ?˜?–¥?„ ì£¼ì? ?•Šê¸? ?•Œë¬¸ì—.
		// ?‘ ê°’ì´ ê°™ìœ¼ë©? ?–´?–¤ ì¹´ë“œë¥? ë½‘ë“  ?´ ì¹´ë“œë¥? ë½‘ì•„?•¼ ìµœë? ê°œìˆ˜ê°? ì¦ê??•œ?‹¤.
		for(int i = 1; i <= n; i++) {
			if(i == cards[i]) {
				checked[i] = true;
				continue;
			}
			
			if(checked[i]) {
				continue;
			}
			
			isCycle = false;
			
			// ë°±íŠ¸?˜?‚¹ 
			checked[i] = true;
			getCycle(i, i, n);
			if(!isCycle) {
				checked[i] = false;	
			}
		}
		
		int count = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++) {
			if(checked[i]) {
				count++;
				sb.append(i).append("\n");
			}
		}
		
		System.out.println(count);
		System.out.println(sb.toString());
	}
	
	// ?‚¬?´?´ ì°¾ì•„?„œ ë¦¬ìŠ¤?Š¸?— ???¥?•˜ê¸? 
	// DFS
	static void getCycle(int index, int last, int n) {
		int ele = cards[index];
		if(!checked[ele]) {
			checked[ele] = true;
			getCycle(ele, last, n);
			
			if(!isCycle) {
				checked[ele] = false;	
			}
		}
		
		if(ele == last) {
			isCycle = true;
		}
	}
}