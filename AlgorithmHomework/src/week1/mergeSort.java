// 201402407 이해원
package week1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 머지소트 구현하기
public class mergeSort {
	static List<Integer> sort;
	
	public static void main(String args[]) {
		try { 
			String inputFileSrc = new java.io.File("").getAbsolutePath() + "/src/input.txt";	// 상대 경로 설정
			FileInputStream fileInputStream = new FileInputStream(inputFileSrc);
			
			byte[] buffer = new byte[fileInputStream.available()];	// 파일의 전체 크기만큼 바이트 버퍼 설정
			while(fileInputStream.read(buffer) != -1) {}	// 버퍼에 값 저장
			StringTokenizer st = new StringTokenizer(new String(buffer)); // 토큰으로 숫자 분리
	    	
			int countTokens = st.countTokens();	// 갯수 넣기
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
			System.out.println("숫자가 아닌 문자를 입력받았습니다.");
			System.exit(0);
		}
		catch(FileNotFoundException e) {
			System.out.println("해당 경로에 인풋 파일이 존재하지 않습니다.");
			System.exit(0);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 자바 8 Stream API 사용
	private static void print(List<Integer> list) {
		list.stream().forEach(element -> System.out.print(element + " "));
	}
	
	private static List<Integer> mergeSort(List<Integer> list) {
		int middle;
		// 리스트의 사이즈가 1보다 크면 conquer & merge 수행
		if(list.size() > 1) {
			middle = list.size() / 2;
			// 두 부분으로 나눠 conquer 진행한 뒤 두 부분을 merge 한다.
			return merge(mergeSort(list.subList(0, middle)), mergeSort(list.subList(middle, list.size())));	
		}
		else { // 사이즈가 1 이하면(즉, 원소가 하나만 있는 배열이라면) 해당 배열을 리턴하며 재귀 종료.
			return list;
		}
	}
	
	private static List<Integer> merge(List<Integer> left, List<Integer> right) {
		List<Integer> resultList = new ArrayList<>();
		int leftStart = 0;
		int rightStart = 0;	// 왼쪽, 오른쪽 리스트가 따로 있으므로
		int leftEnd = left.size();
		int rightEnd = right.size();
		
		// 왼쪽 또는 오른쪽 리스트 중 하나라도 다 돌 때 까지 계속 탐색해서 정렬
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
		
		// 왼쪽과 오른쪽 각각이 먼저 끝났을 때 다른 배열의 남은 원소들을 전부 추가하는 과정 진행
		if(leftStart >= leftEnd) {	// 왼쪽 먼저 끝나면
			for(; rightStart < rightEnd; rightStart++) 
				resultList.add(right.get(rightStart));
		}
		else if(rightStart >= rightEnd) {	// 오른쪽 먼저 끝나면
			for(; leftStart < leftEnd; leftStart++) 
				resultList.add(left.get(leftStart));
		}
		
		return resultList;
	}
}
