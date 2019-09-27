package anything;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// Counting Inversions
public class p10090 {
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			if(n < 2 || n > 1000000) {
				System.exit(0);
			}
			List<Integer> numList = new ArrayList<Integer>();
			st = new StringTokenizer(br.readLine());
			int tokenSize = st.countTokens();
			for(int i = 0; i < tokenSize; i++) {
				int num = Integer.valueOf(st.nextToken());
				numList.add(num);
			}
			
			Inversion result = startInversion(numList);
			System.out.println(result.getCount());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Inversion startInversion(List<Integer> list) {
		if(list.size() == 1) {
			return new Inversion(0, list);
		}
		int listSize = list.size();
		int middle = listSize / 2;
		Inversion leftInversion = startInversion(list.subList(0, middle));
		Inversion rightInversion = startInversion(list.subList(middle, listSize));
		Inversion mergeInversion = merge(leftInversion.getList(), rightInversion.getList());
		
		return new Inversion(leftInversion.getCount() + rightInversion.getCount() + mergeInversion.getCount(),
				mergeInversion.getList());
	}
	
	private static Inversion merge(List<Integer> left, List<Integer> right) {
		List<Integer> resultList = new ArrayList<>();
		int leftStart = 0;
		int rightStart = 0;	// ����, ������ ����Ʈ�� ���� �����Ƿ�
		int leftEnd = left.size();
		int rightEnd = right.size();
		long count = 0;
		
		// ���� �Ǵ� ������ ����Ʈ �� �ϳ��� �� �� �� ���� ��� Ž���ؼ� ����
		while(leftStart < leftEnd && rightStart < rightEnd) {
			int leftNum = left.get(leftStart);
			int rightNum = right.get(rightStart);
			if(leftNum <= rightNum) {
				resultList.add(leftNum);
				leftStart++;
			}
			else {
				resultList.add(rightNum);
				count = count + (leftEnd - leftStart);	// ���� ����Ʈ�� leftstart���� ���� ������ŭ ���ϱ�
				rightStart++;
			}
		}
		
		// ���ʰ� ������ ������ ���� ������ �� �ٸ� �迭�� ���� ���ҵ��� ���� �߰��ϴ� ���� ����
		if(leftStart >= leftEnd) {	// ���� ���� ������
			for(; rightStart < rightEnd; rightStart++) 
				resultList.add(right.get(rightStart));
		}
		else if(rightStart >= rightEnd) {	// ������ ���� ������
			for(; leftStart < leftEnd; leftStart++) 
				resultList.add(left.get(leftStart));
		}
		return new Inversion(count, resultList);
	}
}

class Inversion {
	private long count;
	private List<Integer> list = new ArrayList<Integer>();
	
	Inversion(long count, List<Integer> list) {
		this.count = count;
		this.list = list;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}
}