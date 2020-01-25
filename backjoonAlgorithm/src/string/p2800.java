package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

// 문자열 처리
// 괄호 제거 문제
// 경우에 수에 따라 괄호 제거하고 사전 순(오름차순) 출력
public class p2800 {
	static List<String> resultList;
	static List<Couple> gualhoIndexList;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String math = st.nextToken();	// 항상 괄호는 올바르게 쳐져 있다고 한다.
		resultList = new ArrayList<String>();	// 경우에 따른 문자열을 담은 ArrayList
		gualhoIndexList = new ArrayList<Couple>();	// 괄호의 인덱스 쌍을 담은 ArrayList
		Stack<Integer> gualho = new Stack<Integer>();	// 괄호의 인덱스를 담은 스텍
		
		// 괄호의 순서쌍을 찾는 문제
		for(int i = 0; i < math.length(); i++) {
			char ch = math.charAt(i);
			if(ch == '(') {	// 인덱스 집어넣기
				gualho.push(i);
			}
			if(ch == ')') {
				Couple couple = new Couple(gualho.pop(), i);
				gualhoIndexList.add(couple);
			}
		}
		recursive(new String(math), 0);	// 재귀 호출 시작
		removeElement(math);
		Collections.sort(resultList);
		for(int i = 0; i < resultList.size(); i++) {
			System.out.println(resultList.get(i));
		}
	}
	
	// 인덱스가 하나씩 증가하면서  1)해당 괄호를 지우는 경우 와 2)해당 괄호를 지우지 않는 경우 에 대한 결과 값을 저장하고 재귀호출
	static void recursive(String element, int index) {
		if(index >= gualhoIndexList.size()) {
			return;
		}
		StringBuilder sb = new StringBuilder(element);
		// 해당 괄호를 지우지 않는 경우
		String result = sb.toString().replaceAll("_", "");
		addListElement(result);
		recursive(new String(sb.toString()), index + 1);

		// 해당 괄호를 지우는 경우
		Couple point = gualhoIndexList.get(index);
		sb.setCharAt(point.start, '_');		// 괄호를 다른 문자로 치환
		sb.setCharAt(point.end, '_');		// 괄호를 다른 문자로 치환
		result = sb.toString().replaceAll("_", "");
		addListElement(result);
		recursive(new String(sb.toString()), index + 1);
	}
	
	// 해당 원소 제거
	static void removeElement(String ele) {
		for(int i = 0; i < resultList.size(); i++) {
			if(ele.equals(resultList.get(i))) {
				resultList.remove(i);
				break;
			}
		}
	}
	// 같은 원소가 있으면 추가하지 않고, 다른 원소일 때만 추가
	static void addListElement(String ele) {
		boolean check = true;
		for(int i = 0; i < resultList.size(); i++) {
			if(ele.equals(resultList.get(i))) {
				check = false;
				break;
			}
		}
		if(check) {
			resultList.add(ele);
		}
	}
}

class Couple {
	int start;
	int end;
	Couple(int start, int end) {
		this.start = start;
		this.end = end;
	}
}