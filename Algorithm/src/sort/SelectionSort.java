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
			String inputFileSrc =  fileSrc + "/src/sortData.txt";	// 상대 경로 설정
			String outputFileSrc =  fileSrc + "/src/SelectionResult.txt";	// 상대 경로 설정
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
		    
		    selectionSort();
		    
		    // 파일에 쓰기 위한 변수 생성
		    File outputFile = new File(outputFileSrc);
		    FileWriter writer = new FileWriter(outputFile, false);

		    // 파일에 쓰기
		    for(int element : sort) {
		    	try {
		    		writer.write(element + ",");
					System.out.print(element + " ");
		    	}
		    	catch (IOException e) {
		    		System.out.println("예외 발생 !");
					System.exit(0);
		    	}
		    	
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
			System.out.println("파일 쓰기 에러.");
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
