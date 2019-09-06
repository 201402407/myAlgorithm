package dfsnfs;

public class q1 {
    int targetNumber;
    int numbersSize;
    int[] numberArray;
    public int solution(int[] numbers, int target) {	
        int answer = 0;
        if(target < 1 || target > 1000) {
            System.exit(0);
        }
        numberArray = numbers;
        targetNumber = target;  // 전역 변수로 설정
        numbersSize = numbers.length;   // numbers 길이 전역 변수 설정
        if(numbersSize < 2 || numbersSize > 20) {
            System.exit(0);
        }

        answer = getMethodCount(numbers[0], 0, 0) + getMethodCount(-numbers[0], 0, 0);	// 굳이 전역변수 안하고 바로 값(numbers, target) 집어넣어도 됨.
        return answer;
    }

    private int getMethodCount(int currentNumber, int currentSum, int depth) {
        currentSum += currentNumber;
        if(depth + 1 >= numbersSize) {  // 최하층 노드인 경우
            if(currentSum == targetNumber) {
                return 1;
            }
            else
                return 0;
        }
        depth++;
        return getMethodCount(numberArray[depth], currentSum, depth) + getMethodCount(-numberArray[depth], currentSum, depth);
    }
}
