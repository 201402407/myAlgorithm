package week3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 201402407 이해원
// counting Inversions
// Merge and conquer 방식 사용
// ArrayList 사용
public class countingInversions {
	public static void main(String args[]) {
		String fileSrc = new java.io.File("").getAbsolutePath();
		String inputFileSrc =  fileSrc + "/src/data03_inversion.txt";	// 상대 경로 설정
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(inputFileSrc);
			byte[] buffer = new byte[fileInputStream.available()];	// 파일의 전체 크기만큼 바이트 버퍼 설정
			while(fileInputStream.read(buffer) != -1) {}	// 버퍼에 값 저장
			StringTokenizer st = new StringTokenizer(new String(buffer), ","); // 토큰으로 숫자 분리
			
			int countTokens = st.countTokens();	// 갯수 넣기
			List<Integer> list = new ArrayList<Integer>();
			for(int i = 0; i < countTokens; i++) {
				int number = Integer.parseInt(st.nextToken());
				list.add(number);
			}
		
			SortedList result = inversions(list);
			System.out.println(result.getCount());
			
		} catch (FileNotFoundException e) {
			System.exit(0);
		} catch (IOException e) {
			System.exit(0);
		}
	}
	
	private static SortedList inversions(List<Integer> list) {
		if(list.size() == 1)
			return new SortedList(0, list);
	
		int middle = list.size() / 2;
		SortedList sortedLeft = inversions(list.subList(0, middle));
		SortedList sortedRight = inversions(list.subList(middle, list.size()));
		SortedList mergedList = merge(sortedLeft.getSorted(), sortedRight.getSorted());
		
		return new SortedList(sortedLeft.getCount() + sortedRight.getCount() + mergedList.getCount(), mergedList.getSorted());
		
		
	}
	
	private static SortedList merge(List<Integer> left, List<Integer> right) {
		List<Integer> resultList = new ArrayList<>();
		int leftStart = 0;
		int rightStart = 0;	// 왼쪽, 오른쪽 리스트가 따로 있으므로
		int leftEnd = left.size();
		int rightEnd = right.size();
		int count = 0;
		
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
				count = count + (leftEnd - leftStart);	// 왼쪽 리스트의 leftstart부터 남은 갯수만큼 더하기
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
		return new SortedList(count, resultList);
	}
}

class SortedList {
	private int count;
	private List<Integer> sorted = new ArrayList<>();
	
	SortedList() {
		
	}

	SortedList(int count, List<Integer> sorted) {
		this.count = count;
		this.sorted = sorted;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Integer> getSorted() {
		return sorted;
	}

	public void setSorted(List<Integer> sorted) {
		this.sorted = sorted;
	}
}
