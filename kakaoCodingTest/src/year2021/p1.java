package year2021;

import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class p1 {
	
	static HashMap<String, Integer> userIdMap;	// (유저 ID, 인덱스)
	static HashSet<Integer>[] reportList;	// (인덱스 - input ID, value - 신고한 ID 인덱스)
	static int[] reportCountArray; // (신고당한ID index, 신고당한 횟수)
	public static void main(String args[]) {
		String[] id_list = {"con", "ryan"};
		String[] report = { "ryan con", "ryan con", "ryan con", "ryan con" };
		int k = 3;
		
		int idListLen = id_list.length;
		userIdMap = new HashMap<String, Integer>();
		reportCountArray = new int[idListLen];
		reportList = new HashSet[idListLen];
		// 초기화
		for(int i = 0; i < idListLen; i++) {
			reportList[i] = new HashSet<Integer>();
			userIdMap.put(id_list[i], i);
		}
		
		// 신고 내용 for문 순회
		StringTokenizer st;
		for(String reportContent : report) {
			st = new StringTokenizer(reportContent);
			String userId = st.nextToken();
			String reportedId = st.nextToken();
			
			int index = userIdMap.get(userId);
			int reportedIdIndex = userIdMap.get(reportedId);
			if(!reportList[index].contains(reportedIdIndex)) {
				reportCountArray[reportedIdIndex]++;
				reportList[index].add(reportedIdIndex);	// 신고목록 추가
			}
			// 신고횟수 추가
			
		}
		
		// 
		// 신고 횟수 파악해서 정지 ID 판별
		int[] receivedMailCount = new int[idListLen];
		for(int i = 0; i < idListLen; i++) {
			if(reportCountArray[i] >= k) {	// 정지 ID : i
				for(int y = 0; y < idListLen; y++) {
					HashSet<Integer> set = reportList[y];
					for(int reportIdIndex : set) {
						if(reportIdIndex == i) {
							receivedMailCount[y]++;	// i를 신고했던 아이디 y의 메일받는 갯수 증가
							break;
						}
					}
				}
			}
		}
		
		for(int ele : receivedMailCount) {
			System.out.print(ele + " ");
		}
	}
}
