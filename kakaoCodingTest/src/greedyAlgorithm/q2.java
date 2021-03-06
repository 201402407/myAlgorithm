package greedyAlgorithm;

public class q2 {
	public int solution(String name) {
        int length = name.length();
        int answer = 0;
        // 각 알파벳 별 최소 조작 수 구하기
        for(int i = 0; i < length; i++) {
            if(name.charAt(i) == 'A') {
                continue;
            }
            answer += getAlphabetCount(name.charAt(i));    
        }
    
        int forwardMoveCount = 0;
        int backMoveCount = 0;
        int aCount = 0;
        int aStartIndex = 0;
        // 전진 이동 수 구하기
        for(int count = 1; count < length; count++) {
            if(name.charAt(count) == 'A') {
                if(aCount == 0) {
                    aStartIndex = count - 1;
                }
                aCount++;
            }
            else {
                if(aCount + 1 > length / 2) {   // U턴 조건
                    forwardMoveCount += aStartIndex + getBackMoveCount(length, name, aStartIndex);
                    break;
                }
                else {
                    forwardMoveCount++;
                    forwardMoveCount += aCount;
                    aCount = 0;          
                }
            }
        }
        aCount = 0;
        aStartIndex = 0;
        
        // 후진 이동 수 구하기
        for(int count = length - 1; count > 0; count--) {
            if(name.charAt(count) == 'A') {
                if(aCount == 0) {
                    aStartIndex = count;
                }
                aCount++;
            }
            else {
                if(aCount + 1 > length / 2) {   // U턴 조건
                    backMoveCount += (length - aStartIndex) + getForwardMoveCount(length, name, aStartIndex) + 1;
                    break;
                }
                else {
                    backMoveCount++;
                    backMoveCount += aCount;
                    aCount = 0; 
                }         
            }
        }
        // 두 값 비교
        if(forwardMoveCount >= backMoveCount)
            answer += backMoveCount;
        else
            answer += forwardMoveCount;
        
        return answer;
	}
	
	// 알파벳 별 조이스틱 사용 횟수 리턴
	private int getAlphabetCount(char alphabet) {
        char a = 'A';
        int count = alphabet - a;
        return count > 13 ? 26 - count : count; 
    }
    
	// U턴 시 앞방향 횟수 구하기
    private int getForwardMoveCount(int length, String name, int maxIndex) {
        int aCount = 0;
        int forwardMoveCount = 0;
        // 전진 이동 수 구하기
        for(int count = 0; count < maxIndex; count++) {
            if(name.charAt(count) == 'A') {
                aCount++;
            }
            else {
                forwardMoveCount++;
                forwardMoveCount += aCount;
                aCount = 0;                
            }
        }
        return forwardMoveCount;
    }
    
    // U턴 시 뒷방향 횟수 구하기
    private int getBackMoveCount(int length, String name, int minIndex) {
        int aCount = 0;
        int backMoveCount = 0;
        for(int count = length - 1; count > minIndex; count--) {
            if(name.charAt(count) == 'A') {
                aCount++;
            }
            else {
                backMoveCount++;
                backMoveCount += aCount;
                aCount = 0;                
            }
        }
        return backMoveCount;
    }
}
