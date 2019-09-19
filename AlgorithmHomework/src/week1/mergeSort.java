// 201402407 ���ؿ�
package week1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// ������Ʈ �����ϱ�
// �ð� ���⵵
// Best : nlog_2n,  Average : nlog_2n,  Worst : nlog_2n
// Conquer : log_2n, Merge : n -> �׷��� nlog_2n �̴�.
public class mergeSort {
	static List<Integer> sort;
	static int callCount = 0;
	public static void main(String args[]) {
		try { 
			String fileSrc = new java.io.File("").getAbsolutePath();
			String inputFileSrc =  fileSrc + "/src/data02.txt";	// ��� ��� ����
			String outputFileSrc =  fileSrc + "/src/hw01_05_201402407_merge.txt";	// ��� ��� ����
			FileInputStream fileInputStream = new FileInputStream(inputFileSrc);
			
			byte[] buffer = new byte[fileInputStream.available()];	// ������ ��ü ũ�⸸ŭ ����Ʈ ���� ����
			while(fileInputStream.read(buffer) != -1) {}	// ���ۿ� �� ����
			StringTokenizer st = new StringTokenizer(new String(buffer), ","); // ��ū���� ���� �и�
	    	
			int countTokens = st.countTokens();	// ���� �ֱ�
	    	sort = new ArrayList<Integer>();
	    	
		    for(int i = 0; i < countTokens; i++) {
			    int number = Integer.parseInt(st.nextToken());
			    sort.add(number);
		    }
		    
		    List<Integer> result = mergeSort(sort);
		    
		    // ���Ͽ� ���� ���� ���� ����
		    File outputFile = new File(outputFileSrc);
		    FileWriter writer = new FileWriter(outputFile, false);
			// �ڹ� 8 Stream API ���
		    // ���Ͽ� ����
		    result.stream().forEach(element -> {
				try {
					writer.write(element + ",");
				} catch (IOException e) {
					System.out.println("���� �߻� !");
					System.exit(0);
				}
			});
		    writer.write(String.valueOf(callCount));
		    	
		    writer.flush();
			fileInputStream.close();
			writer.close();
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
			System.out.println("���� ���� ����.");
			System.exit(0);
		}
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
		callCount++;
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
