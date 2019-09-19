// 201402407 ���ؿ�
package week1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

// ���� ���� �����ϱ�
// �ð� ���⵵
// Best : n,  Average : n^2,  Worst : n^2
public class InsertionSort {
	static int sort[];
	public static void main(String args[]) {
		try { 
			String fileSrc = new java.io.File("").getAbsolutePath();
			String inputFileSrc =  fileSrc + "/src/data02.txt";	// ��� ��� ����
			String outputFileSrc =  fileSrc + "/src/hw01_05_201402407_insertion.txt";	// ��� ��� ����
			FileInputStream fileInputStream = new FileInputStream(inputFileSrc);
			
			byte[] buffer = new byte[fileInputStream.available()];	// ������ ��ü ũ�⸸ŭ ����Ʈ ���� ����
			while(fileInputStream.read(buffer) != -1) {}	// ���ۿ� �� ����
			StringTokenizer st = new StringTokenizer(new String(buffer), ","); // ��ū���� ���� �и�
	    	
			int countTokens = st.countTokens();	// ���� �ֱ�
	    	sort = new int[countTokens];
	    	
		    for(int i = 0; i < countTokens; i++) {
			    int number = Integer.parseInt(st.nextToken());
			    sort[i] = number;
		    }
		    insertionSort();
			
		    // ���Ͽ� ���� ���� ���� ����
		    File outputFile = new File(outputFileSrc);
		    FileWriter writer = new FileWriter(outputFile, false);
		    
		    int count = 0;
		    for(int result : sort) {
		    	count++;
		    	String elementStr = String.valueOf(result);
		    	if(count == sort.length) {
		    		writer.write(elementStr);
		    		break;
		    	}
		    	writer.write(elementStr + ",");
		    }
	    	
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
		for(int i = 2; i < size; i++) {
				int key = sort[i];
				int j = i - 1;
				while(j > 0 && sort[j] > key) {
					sort[j + 1] = sort[j];
					j--;
				}
				sort[j + 1] = key;
		}
	}
}
