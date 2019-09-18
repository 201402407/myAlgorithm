// 201402407 ���ؿ�
package week1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;

// �������� �����ϱ�
public class InsertionSort {
	static int sort[];
	public static void main(String args[]) {
		try { 
			String inputFileSrc = new java.io.File("").getAbsolutePath() + "/src/input.txt";	// ��� ��� ����
			FileInputStream fileInputStream = new FileInputStream(inputFileSrc);
			
			byte[] buffer = new byte[fileInputStream.available()];	// ������ ��ü ũ�⸸ŭ ����Ʈ ���� ����
			while(fileInputStream.read(buffer) != -1) {}	// ���ۿ� �� ����
			StringTokenizer st = new StringTokenizer(new String(buffer)); // ��ū���� ���� �и�
	    	
			int countTokens = st.countTokens();	// ���� �ֱ�
	    	sort = new int[countTokens];
	    	
		    for(int i = 0; i < countTokens; i++) {
			    int number = Integer.parseInt(st.nextToken());
			    sort[i] = number;
		    }
		    
		    insertionSort();
			
		}
		catch(NumberFormatException e ) {
			System.out.println("���ڰ� �ƴ� ���ڸ� �Է¹޾ҽ��ϴ�.");
			System.exit(0);
		}
		catch(FileNotFoundException e) {
			System.out.println("�ش� ��ο� ��ǲ ������ �������� �ʽ��ϴ�.");
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void insertionSort() {
		
	}
}
