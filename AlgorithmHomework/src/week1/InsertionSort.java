// 201402407 이해원
package week1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

// 삽입 정렬 구현하기
// 시간 복잡도
// Best : n,  Average : n^2,  Worst : n^2
public class InsertionSort {
	static int sort[];
	public static void main(String args[]) {
		try { 
			String fileSrc = new java.io.File("").getAbsolutePath();
			String inputFileSrc =  fileSrc + "/src/data02.txt";	// 상대 경로 설정
			String outputFileSrc =  fileSrc + "/src/hw01_05_201402407_insertion.txt";	// 상대 경로 설정
			FileInputStream fileInputStream = new FileInputStream(inputFileSrc);
			
			byte[] buffer = new byte[fileInputStream.available()];	// 파일의 전체 크기만큼 바이트 버퍼 설정
			while(fileInputStream.read(buffer) != -1) {}	// 버퍼에 값 저장
			StringTokenizer st = new StringTokenizer(new String(buffer), ","); // 토큰으로 숫자 분리
	    	
			int countTokens = st.countTokens();	// 갯수 넣기
	    	sort = new int[countTokens];
	    	
		    for(int i = 0; i < countTokens; i++) {
			    int number = Integer.parseInt(st.nextToken());
			    sort[i] = number;
		    }
		    insertionSort();
			
		    // 파일에 쓰기 위한 변수 생성
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
			System.out.println("숫자가 아닌 문자를 입력받았습니다.");
			System.exit(0);
		}
		catch(FileNotFoundException e) {
			System.out.println("해당 경로에 인풋 파일이 존재하지 않습니다.");
			System.exit(0);
		} 
		catch (IOException e) {
			System.out.println("파일 저장 중 오류 발생.");
			System.exit(0);
		}
	}
	
	private static void insertionSort() {
		/*
		 * 내가 생각해서 한거
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
		
		// 알고리즘 수업 PPT 수도코드
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
