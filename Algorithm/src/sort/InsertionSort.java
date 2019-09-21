// 201402407 이해원
package sort;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.StringTokenizer;

// 삽입 정렬 구현하기
// 시간 복잡도
// Best : n,  Average : n^2,  Worst : n^2
public class InsertionSort {
	static int sort[];
	static long startTime;
	public static void main(String args[]) {
		try { 
			String fileSrc = new java.io.File("").getAbsolutePath();
			String inputFileSrc =  fileSrc + "/src/sortData.txt";	// 상대 경로 설정
			String outputFileSrc =  fileSrc + "/src/InsertionResult.txt";	// 상대 경로 설정
			FileInputStream fileInputStream = new FileInputStream(inputFileSrc);
			
//			byte[] buffer = new byte[fileInputStream.available()];	// 파일의 전체 크기만큼 바이트 버퍼 설정
//			while(fileInputStream.read(buffer) != -1) {}	// 버퍼에 값 저장
//			StringTokenizer st = new StringTokenizer(new String(buffer), ","); // 토큰으로 숫자 분리
//	    	
//			int countTokens = st.countTokens();	// 갯수 넣기
//	    	sort = new int[countTokens];
//	    	
//		    for(int i = 0; i < countTokens; i++) {
//			    int number = Integer.parseInt(st.nextToken());
//			    sort[i] = number;
//		    }
			int n = 300000;
			System.out.println("n이 " + String.valueOf(n) + " 일 때 ");
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
	            System.out.format("insertionSort 최종 실행 결과 시간 : %f sec %n", (System.nanoTime() - startTime) / 1000000000.0);
	        });
		    
		    Thread binaryInsertionThread = new Thread(() -> {
	            startTime = System.nanoTime();
	            binaryInsertionSort();
	            System.out.format("binaryInsertionSort 최종 실행 결과 시간 : %f sec %n", (System.nanoTime() - startTime) / 1000000000.0);
	            
	        });
		    
		    insertionThread.start();
		    binaryInsertionThread.start();
		    
//		    // 파일에 쓰기 위한 변수 생성
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
	
	// 이진 탐색 알고리즘 추가
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
		// 알고리즘 수업 PPT 수도코드
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
