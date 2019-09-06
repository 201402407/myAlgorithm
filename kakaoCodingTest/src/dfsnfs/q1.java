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
        targetNumber = target;  // ���� ������ ����
        numbersSize = numbers.length;   // numbers ���� ���� ���� ����
        if(numbersSize < 2 || numbersSize > 20) {
            System.exit(0);
        }

        answer = getMethodCount(numbers[0], 0, 0) + getMethodCount(-numbers[0], 0, 0);	// ���� �������� ���ϰ� �ٷ� ��(numbers, target) ����־ ��.
        return answer;
    }

    private int getMethodCount(int currentNumber, int currentSum, int depth) {
        currentSum += currentNumber;
        if(depth + 1 >= numbersSize) {  // ������ ����� ���
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
