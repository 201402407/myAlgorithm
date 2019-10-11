package week5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class QuickSort {
	public static void main(String args[]) {
		String fileSrc = new java.io.File("").getAbsolutePath();
		String inputFileSrc =  fileSrc + "/src/data05.txt";	// ��� ��� ����
		String quickOutputFileSrc =  fileSrc + "/src/hw03_05_201402407_quick.txt";	// ��� ��� ����
		String randomOutputFileSrc =  fileSrc + "/src/hw03_05_201402407_quickRandom.txt";	// ��� ��� ����
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFileSrc));
			String str;
			StringTokenizer st;
			List<Integer> list = new ArrayList<Integer>();
			while((str = br.readLine()) != null) {
				st = new StringTokenizer(str, ",");
				int length = st.countTokens();
				for(int i = 0; i < length; i++) {
					int num = Integer.valueOf(st.nextToken());
					list.add(num);
				}
			}
			List<Integer> randomList = new ArrayList<Integer>();
			randomList.addAll(list);	// Deep copy
			
			// �⺻ ����Ʈ
			quickSort(list, 0, list.size() - 1);
			StringBuilder sb = new StringBuilder();
			for(int num : list) {
				sb.append(num).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);	// ���� ��ǥ ����
			System.out.println(sb.toString());
			
			// ���� ����Ʈ ���� ����
		    File outputFile = new File(quickOutputFileSrc);
		    FileWriter writer = new FileWriter(outputFile, false);
		    writer.write(sb.toString());
		    writer.flush();
		    writer.close();
		    sb.setLength(0); // �ʱ�ȭ
		    
		    // ���� ����Ʈ
		    quickSort_withRandom(randomList, 0, randomList.size() - 1);
		    sb = new StringBuilder();
		    for(int num : randomList) {
		    	sb.append(num).append(",");
		    }
		    sb.deleteCharAt(sb.length() - 1);
		    
		    // ���� ����Ʈ ���� ����
		    outputFile = new File(randomOutputFileSrc);
		    writer = new FileWriter(outputFile, false);
		    writer.write(sb.toString());
		    writer.flush();
		    writer.close();
		    sb.setLength(0); // �ʱ�ȭ
		    
		} catch (FileNotFoundException e) {
			System.exit(0);
		} catch (IOException e) {
			System.exit(0);
		} catch (NumberFormatException e) {
			System.exit(0);
		}
	}
	
	private static void quickSort(List<Integer> list, int start, int end) {
		if(start < end) {
			int mid = partition(list, start, end);
			quickSort(list, start, mid - 1);
			quickSort(list, mid + 1, end);
		}
	}
	
	private static int partition(List<Integer> list, int start, int end) {
		int pivot = list.get(end);
		int startIndex = start - 1;
		for(int endIndex = start; endIndex < end; endIndex++) {
			if(list.get(endIndex) <= pivot) {
				startIndex++;
				Collections.swap(list, startIndex, endIndex);
			}
		}
		startIndex++;
		Collections.swap(list, startIndex, end);
		return startIndex;
	}
	
	private static void quickSort_withRandom(List<Integer> list, int start, int end) {
		if(start < end) {
			int mid = randomizedPartition(list, start, end);
			quickSort(list, start, mid - 1);
			quickSort(list, mid + 1, end);
		}
	}
	
	// �������� �ǹ��� ����.
	private static int randomizedPartition(List<Integer> list, int start, int end) {
		int pivotIndex = (int)(Math.random() * end) + start; // (Math.Random() * �ִ�) + �ּڰ� : �ּڰ� ~ �ִ� ����
		Collections.swap(list, pivotIndex, end);
		return partition(list, start, end);
	}
}
