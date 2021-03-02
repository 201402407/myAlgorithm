package anything;

//public class psspp1 {
//	public static void main(String args[]) {
//		String t = "34.5";
//		double doubleT = Double.parseDouble(t);
//		
//		// ����ó��
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
	 * (�� �׵θ� ����) �� ���ο� ���ԵǾ��ִ� ������ ���� ���ϱ�
	 * @param inner	: ���� ���� ������
	 * @param outer	: ���� �ܺ� ������
	 * @param points_x	: x��ǥ �迭 
	 * @param points_y	: y��ǥ �迭
	 * @return ����
	 */
    public int solution(int inner, int outer, int[] points_x, int[] points_y) {
    	// �� �������� ���������� �Ÿ��� ã�� �� �Ÿ��� ������ �Ǵ��ϱ�
    	// ���� ������ ���� < �Ÿ� < �ܺ� ������ ���� �� ���� ���� ���ϱ� ?
    	int len = points_x.length;	// x, y �迭�� ���̴� �����ϴٴ� ��������
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
    
	// A �迭 : �������� ���ĵ�
	// �ߺ� ���Ұ� �����ϴ� ���
	// Lower_bound ? Upper_bound ?
	// Lower_bound ����
	static int solution(int[] A, int X) {
	        int N = A.length;	// A �迭�� ����
	        if (N == 0) {	// A �迭�� ����ִٸ� (empty)
	            return -1;
	        }
	        int l = 0;	// start
	        int r = N - 1;	// end
	        // �̺�Ž��
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