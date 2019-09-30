package week3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 201402407 ���ؿ�
// counting Inversions
// Merge and conquer ��� ���
// ArrayList ���
public class countingInversions {
	public static void main(String args[]) {
		String fileSrc = new java.io.File("").getAbsolutePath();
		String inputFileSrc =  fileSrc + "/src/data03_inversion.txt";	// ��� ��� ����
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(inputFileSrc);
			byte[] buffer = new byte[fileInputStream.available()];	// ������ ��ü ũ�⸸ŭ ����Ʈ ���� ����
			while(fileInputStream.read(buffer) != -1) {}	// ���ۿ� �� ����
			StringTokenizer st = new StringTokenizer(new String(buffer), ","); // ��ū���� ���� �и�
			
			int countTokens = st.countTokens();	// ���� �ֱ�
			List<Integer> list = new ArrayList<Integer>();
			for(int i = 0; i < countTokens; i++) {
				int number = Integer.parseInt(st.nextToken());
				list.add(number);
			}
		
			SortedList result = inversions(list);
			System.out.println(result.getCount());
			
		} catch (FileNotFoundException e) {
			System.exit(0);
		} catch (IOException e) {
			System.exit(0);
		}
	}
	
	private static SortedList inversions(List<Integer> list) {
		if(list.size() == 1)
			return new SortedList(0, list);
	
		int middle = list.size() / 2;
		SortedList sortedLeft = inversions(list.subList(0, middle));
		SortedList sortedRight = inversions(list.subList(middle, list.size()));
		SortedList mergedList = merge(sortedLeft.getSorted(), sortedRight.getSorted());
		
		return new SortedList(sortedLeft.getCount() + sortedRight.getCount() + mergedList.getCount(), mergedList.getSorted());
		
		
	}
	
	private static SortedList merge(List<Integer> left, List<Integer> right) {
		List<Integer> resultList = new ArrayList<>();
		int leftStart = 0;
		int rightStart = 0;	// ����, ������ ����Ʈ�� ���� �����Ƿ�
		int leftEnd = left.size();
		int rightEnd = right.size();
		int count = 0;
		
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
		return new SortedList(count, resultList);
	}
}

class SortedList {
	private int count;
	private List<Integer> sorted = new ArrayList<>();
	
	SortedList() {
		
	}

	SortedList(int count, List<Integer> sorted) {
		this.count = count;
		this.sorted = sorted;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Integer> getSorted() {
		return sorted;
	}

	public void setSorted(List<Integer> sorted) {
		this.sorted = sorted;
	}
}
