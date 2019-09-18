// 201402407 ���ؿ�
package week1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// ������Ʈ �����ϱ�
public class mergeSort {
	static List<Integer> sort;
	
	public static void main(String args[]) {
		try { 
			String inputFileSrc = new java.io.File("").getAbsolutePath() + "/src/input.txt";	// ��� ��� ����
			FileInputStream fileInputStream = new FileInputStream(inputFileSrc);
			
			byte[] buffer = new byte[fileInputStream.available()];	// ������ ��ü ũ�⸸ŭ ����Ʈ ���� ����
			while(fileInputStream.read(buffer) != -1) {}	// ���ۿ� �� ����
			StringTokenizer st = new StringTokenizer(new String(buffer)); // ��ū���� ���� �и�
	    	
			int countTokens = st.countTokens();	// ���� �ֱ�
	    	sort = new ArrayList<Integer>();
	    	
		    for(int i = 0; i < countTokens; i++) {
			    int number = Integer.parseInt(st.nextToken());
			    sort.add(number);
			    System.out.print(number + " ");
		    }
		    System.out.println();
		    List<Integer> result = mergeSort(sort);
			print(result);
		}
		catch(NumberFormatException e ) {
			System.out.println("���ڰ� �ƴ� ���ڸ� �Է¹޾ҽ��ϴ�.");
			System.exit(0);
		}
		catch(FileNotFoundException e) {
			System.out.println("�ش� ��ο� ��ǲ ������ �������� �ʽ��ϴ�.");
			System.exit(0);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// �ڹ� 8 Stream API ���
	private static void print(List<Integer> list) {
		list.stream().forEach(element -> System.out.print(element + " "));
	}
	
	private static List<Integer> mergeSort(List<Integer> list) {
		int middle;
		// ����Ʈ�� ����� 1���� ũ�� conquer & merge ����
		if(list.size() > 1) {
			middle = list.size() / 2;
			// �� �κ����� ���� conquer ������ �� �� �κ��� merge �Ѵ�.
			return merge(mergeSort(list.subList(0, middle)), mergeSort(list.subList(middle, list.size())));	
		}
		else { // ����� 1 ���ϸ�(��, ���Ұ� �ϳ��� �ִ� �迭�̶��) �ش� �迭�� �����ϸ� ��� ����.
			return list;
		}
	}
	
	private static List<Integer> merge(List<Integer> left, List<Integer> right) {
		List<Integer> resultList = new ArrayList<>();
		int leftStart = 0;
		int rightStart = 0;	// ����, ������ ����Ʈ�� ���� �����Ƿ�
		int leftEnd = left.size();
		int rightEnd = right.size();
		
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
		
		return resultList;
	}
}
