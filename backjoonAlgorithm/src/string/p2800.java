package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

// ���ڿ� ó��
// ��ȣ ���� ����
// ��쿡 ���� ���� ��ȣ �����ϰ� ���� ��(��������) ���
public class p2800 {
	static List<String> resultList;
	static List<Couple> gualhoIndexList;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String math = st.nextToken();	// �׻� ��ȣ�� �ùٸ��� ���� �ִٰ� �Ѵ�.
		resultList = new ArrayList<String>();	// ��쿡 ���� ���ڿ��� ���� ArrayList
		gualhoIndexList = new ArrayList<Couple>();	// ��ȣ�� �ε��� ���� ���� ArrayList
		Stack<Integer> gualho = new Stack<Integer>();	// ��ȣ�� �ε����� ���� ����
		
		// ��ȣ�� �������� ã�� ����
		for(int i = 0; i < math.length(); i++) {
			char ch = math.charAt(i);
			if(ch == '(') {	// �ε��� ����ֱ�
				gualho.push(i);
			}
			if(ch == ')') {
				Couple couple = new Couple(gualho.pop(), i);
				gualhoIndexList.add(couple);
			}
		}
		recursive(new String(math), 0);	// ��� ȣ�� ����
		removeElement(math);
		Collections.sort(resultList);
		for(int i = 0; i < resultList.size(); i++) {
			System.out.println(resultList.get(i));
		}
	}
	
	// �ε����� �ϳ��� �����ϸ鼭  1)�ش� ��ȣ�� ����� ��� �� 2)�ش� ��ȣ�� ������ �ʴ� ��� �� ���� ��� ���� �����ϰ� ���ȣ��
	static void recursive(String element, int index) {
		if(index >= gualhoIndexList.size()) {
			return;
		}
		StringBuilder sb = new StringBuilder(element);
		// �ش� ��ȣ�� ������ �ʴ� ���
		String result = sb.toString().replaceAll("_", "");
		addListElement(result);
		recursive(new String(sb.toString()), index + 1);

		// �ش� ��ȣ�� ����� ���
		Couple point = gualhoIndexList.get(index);
		sb.setCharAt(point.start, '_');		// ��ȣ�� �ٸ� ���ڷ� ġȯ
		sb.setCharAt(point.end, '_');		// ��ȣ�� �ٸ� ���ڷ� ġȯ
		result = sb.toString().replaceAll("_", "");
		addListElement(result);
		recursive(new String(sb.toString()), index + 1);
	}
	
	// �ش� ���� ����
	static void removeElement(String ele) {
		for(int i = 0; i < resultList.size(); i++) {
			if(ele.equals(resultList.get(i))) {
				resultList.remove(i);
				break;
			}
		}
	}
	// ���� ���Ұ� ������ �߰����� �ʰ�, �ٸ� ������ ���� �߰�
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