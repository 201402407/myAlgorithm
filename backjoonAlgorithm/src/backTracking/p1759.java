package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

// 암호 만들기
// 조합(백트래킹) 이용
public class p1759 {
	static ArrayList<String> moeumList;
	static ArrayList<String> jaeumList;
	static ArrayList<String> moeumCombinationList;
	static ArrayList<String> jaeumCombinationList;
	static ArrayList<String> resultList;
	static StringBuilder sb = new StringBuilder();
	static int jaeumLen, jaeumPos;
	static int moeumLen, moeumPos;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int l = Integer.valueOf(st.nextToken());
		int c = Integer.valueOf(st.nextToken());
		
		moeumList = new ArrayList<String>();
		jaeumList = new ArrayList<String>();
		moeumCombinationList = new ArrayList<String>();
		jaeumCombinationList = new ArrayList<String>();
		resultList = new ArrayList<String>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < c; i++) {
			String alphabet = st.nextToken();
			if(checkMoeum(alphabet)) {
				moeumList.add(alphabet);
			}
			else {
				jaeumList.add(alphabet);
			}
		}
		
		jaeumLen = jaeumList.size();
		moeumLen = moeumList.size();
		jaeumPos = l - 1;
		moeumPos = l - 2;
		
		for(int i = 1; i <= moeumPos; i++) {
			boolean[] moeumVisited = new boolean[moeumLen];
			combination(moeumVisited, 0, moeumLen, i, true);
			
			int j = l - i;
			boolean[] JaeumVisited = new boolean[jaeumLen];
			combination(JaeumVisited, 0, jaeumLen, j, false);
			
			addResultList();
			
			moeumCombinationList.clear();
			jaeumCombinationList.clear();
		}
		
		Collections.sort(resultList);
		for(int i = 0; i < resultList.size(); i++) {
			System.out.println(resultList.get(i));
		}
	}
	
	private static boolean checkMoeum(String alphabet) {
		switch(alphabet) {
		case "a":
		case "e":
		case "i":
		case "o":
		case "u":
			return true;
		default:
			return false;
		}
	}
	
	static void addResultList() {
		for(int i = 0; i < moeumCombinationList.size(); i++) {
			String mo = moeumCombinationList.get(i);
			for(int j = 0; j < jaeumCombinationList.size(); j++) {
				String ja = jaeumCombinationList.get(j);
				String moja = mo + ja;
				char[] mojaAlphabet = moja.toCharArray();
				Arrays.sort(mojaAlphabet);	// 알파벳이 증가하는 순서를 위한 정렬
				resultList.add(String.valueOf(mojaAlphabet));
			}
		}
	}
	
	// 백트래킹 사용
	// 사용 예시 : combination(arr, visited, 0, n, r)
	static void combination(boolean[] visited, int start, int n, int r, boolean isMoeum) {
	    if(r == 0) {
	    	StringBuilder sb = new StringBuilder();
	    	for(int i = 0; i < n; i++) {
	    		if(visited[i]) {
	    			if(isMoeum) 
	    				sb.append(moeumList.get(i));
	    	    	else 
	    	    		sb.append(jaeumList.get(i));
	    		}
	    	}
	    	if(isMoeum) 
	    		moeumCombinationList.add(sb.toString());
	    	else 
	    		jaeumCombinationList.add(sb.toString());
	        return;
	    } 
	    else {
	        for(int i = start; i < n; i++) {
	            visited[i] = true;
	            combination(visited, i + 1, n, r - 1, isMoeum);
	            visited[i] = false;
	        }
	    }
	}
}
