// 201402407 ���ؿ�
package sort;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.StringTokenizer;

// ���� ���� �����ϱ�
// �ð� ���⵵
// Best : n,  Average : n^2,  Worst : n^2
public class InsertionSort {
	static int sort[];
	static long startTime;
	public static void main(String args[]) {
		try { 
			String fileSrc = new java.io.File("").getAbsolutePath();
			String inputFileSrc =  fileSrc + "/src/sortData.txt";	// ��� ��� ����
			String outputFileSrc =  fileSrc + "/src/InsertionResult.txt";	// ��� ��� ����
			FileInputStream fileInputStream = new FileInputStream(inputFileSrc);
			
//			byte[] buffer = new byte[fileInputStream.available()];	// ������ ��ü ũ�⸸ŭ ����Ʈ ���� ����
//			while(fileInputStream.read(buffer) != -1) {}	// ���ۿ� �� ����
//			StringTokenizer st = new StringTokenizer(new String(buffer), ","); // ��ū���� ���� �и�
//	    	
//			int countTokens = st.countTokens();	// ���� �ֱ�
//	    	sort = new int[countTokens];
//	    	
//		    for(int i = 0; i < countTokens; i++) {
//			    int number = Integer.parseInt(st.nextToken());
//			    sort[i] = number;
//		    }
			int n = 300000;
			System.out.println("n�� " + String.valueOf(n) + " �� �� ");
			int start = 0;
			sort = new int[n];
			for(int i = n - 1; i >= 0; i--) {
				sort[start] = i;
				start++;
			}
//			
//			for(int i = 0; i < n; i++) {
//				sort[start] = i;
//				start++;
//			}
			
		    Thread insertionThread = new Thread(() -> {
	            startTime = System.nanoTime();
	            insertionSort();
	            System.out.format("insertionSort ���� ���� ��� �ð� : %f sec %n", (System.nanoTime() - startTime) / 1000000000.0);
	        });
		    
		    Thread binaryInsertionThread = new Thread(() -> {
	            startTime = System.nanoTime();
	            binaryInsertionSort();
	            System.out.format("binaryInsertionSort ���� ���� ��� �ð� : %f sec %n", (System.nanoTime() - startTime) / 1000000000.0);
	            
	        });
		    
		    insertionThread.start();
		    binaryInsertionThread.start();
		    
//		    // ���Ͽ� ���� ���� ���� ����
//		    File outputFile = new File(outputFileSrc);
//		    FileWriter writer = new FileWriter(outputFile, false);
//		    
//		    int count = 0;
//		    for(int result : sort) {
//		    	count++;
//		    	String elementStr = String.valueOf(result);
//		    	if(count == sort.length) {
//		    		writer.write(elementStr);
//		    		break;
//		    	}
//		    	writer.write(elementStr + ",");
//		    }
//	    	
//	    	writer.flush();
//			fileInputStream.close();
//			writer.close();
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
			System.out.println("���� ���� �� ���� �߻�.");
			System.exit(0);
		}
	}
	
	private static void insertionSort() {
		/*
		 * ���� �����ؼ� �Ѱ�
		for(int i = 1; i < sort.length; i++) {
			int temp = sort[i];
			for(int j = i - 1; j > 0; j--) {
				int preValue = sort[j];
				if(preValue <= temp) {
					break;
				}
				// swap
				sort[j + 1] = preValue;
				sort[j] = temp;
			}
		}
		*/
		
		// �˰��� ���� PPT �����ڵ�
		int size = sort.length;
		for(int i = 1; i < size; i++) {
				int key = sort[i];
				int j = i - 1;
				while(j >= 0 && sort[j] > key) {
					sort[j + 1] = sort[j];
					j--;
				}
				sort[j + 1] = key;
		}
	}
	
	// ���� Ž�� �˰��� �߰�
	private static int binarySearch(int left, int right, int key) {
		int mid;
		while(left < right) {
			mid = (left + right) / 2;
			if(key >= sort[mid])
				left = mid + 1;
			else
				right = mid;
		}
		return right;
	}
	
	private static void binaryInsertionSort() {	
		// �˰��� ���� PPT �����ڵ�
		int size = sort.length;
		for(int i = 1; i < size; i++) {
				int key = sort[i];
				int changeIndex = binarySearch(0, i, key);
				if(i - 1 >= changeIndex)
					System.arraycopy(sort, changeIndex, sort, changeIndex + 1, i - changeIndex);
				sort[changeIndex] = key;
		}
	}
}
