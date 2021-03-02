package anything;

//public class psspp1 {
//	public static void main(String args[]) {
//		String t = "34.5";
//		double doubleT = Double.parseDouble(t);
//		
//		// 예외처리
//		if(doubleT < 34.0 || doubleT > 42.0) {
//			System.out.println("out of range!");
//		}
//		
//		System.out.println(getBodyState(doubleT));
//	}
//	
//	static String getBodyState(double doubleT) {
//		if(doubleT < 35.0) {
//			return "hypothermia";
//		}
//		if(doubleT >= 35.0 && doubleT <= 37.5) {
//			return "normal";
//		}
//		if(doubleT > 37.5 && doubleT <= 40.0) {
//			return "fever";
//		}
//		if(doubleT > 40.0) {
//			return "hyperpyrexia";
//		}
//		
//		return "nothing";
//	}
//}

public class psspp1 {
	public static void main(String args[]) {
//		int[] A = {-200 };
//		int X = -200;
//		System.out.println(solution(A, X));
		
		
	}
	
	/**
	 * (원 테두리 제외) 원 내부에 포함되어있는 점들의 개수 구하기
	 * @param inner	: 원의 내부 반지름
	 * @param outer	: 원의 외부 반지름
	 * @param points_x	: x좌표 배열 
	 * @param points_y	: y좌표 배열
	 * @return 개수
	 */
    public int solution(int inner, int outer, int[] points_x, int[] points_y) {
    	// 각 지점에서 원점까지의 거리를 찾아 그 거리를 가지고 판단하기
    	// 내부 반지름 길이 < 거리 < 외부 반지름 길이 인 점의 개수 구하기 ?
    	int len = points_x.length;	// x, y 배열의 길이는 동일하다는 전제조건
    	for(int i = 0; i < len; i++) {
    		int x = points_x[i];
    		int y = points_y[i];
    		
    		double distance = getDistance(x, y);
    		System.out.println(distance);
    	}
    }
    
    static double getDistance(int x, int y) {
    	return Math.sqrt((x * x) + (y * y));
    }
    
	// A 배열 : 오름차순 정렬됨
	// 중복 원소가 존재하는 경우
	// Lower_bound ? Upper_bound ?
	// Lower_bound 진행
	static int solution(int[] A, int X) {
	        int N = A.length;	// A 배열의 길이
	        if (N == 0) {	// A 배열이 비어있다면 (empty)
	            return -1;
	        }
	        int l = 0;	// start
	        int r = N - 1;	// end
	        // 이분탐색
	        while (l < r) {
	            int m = (l + r) / 2;
	            if (A[m] >= X) {
	                r = m;
	            } else {
	                l = m + 1;
	            }
	        }
	        if (A[l] == X) {
	            return l;
	        }
	        return -1;
	    }
}