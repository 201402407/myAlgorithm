package sort;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class SelectionSort {
	static int[] sort;
	public static void main(String args[]) {
		try { 
			String fileSrc = new java.io.File("").getAbsolutePath();
			String inputFileSrc =  fileSrc + "/src/sortData.txt";	// ��� ��� ����
			String outputFileSrc =  fileSrc + "/src/SelectionResult.txt";	// ��� ��� ����
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
		    
		    selectionSort();
		    
		    // ���Ͽ� ���� ���� ���� ����
		    File outputFile = new File(outputFileSrc);
		    FileWriter writer = new FileWriter(outputFile, false);

		    // ���Ͽ� ����
		    for(int element : sort) {
		    	try {
		    		writer.write(element + ",");
					System.out.print(element + " ");
		    	}
		    	catch (IOException e) {
		    		System.out.println("���� �߻� !");
					System.exit(0);
		    	}
		    	
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
			System.out.println("���� ���� ����.");
			System.exit(0);
		}
	}
	
	private static void selectionSort() {
		int n = sort.length;
		for(int i = 0; i < n - 1; i++) {
			int key = sort[i];
			int changeIndex = i;
			for(int j = i + 1; j < n; j++) {
				if(key > sort[j]) {
					key = sort[j];
					changeIndex = j;
				}
			}
			sort[changeIndex] = sort[i];
			sort[i] = key;
		}
	}
}
