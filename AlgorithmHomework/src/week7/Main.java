package week7;

//201402318 �Ǽ�â SLS
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
	  String fileSrc = new java.io.File("").getAbsolutePath();
	  String inputFileSrc =  fileSrc + "/src/data07.txt";	// ��� ��� ����
      File file = new File(inputFileSrc); //arr1�Է�
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr); //���� ���� ���۸��� ����
      StringTokenizer st = new StringTokenizer(br.readLine(), ","); //������ �ڸ� ��Ʈ����ũ������
      int count = Integer.parseInt(st.nextToken())+1; //������ ����
      double arr[][] = new double[count][2]; //������ ������ �迭
      double E[][] = new double[count][count]; //SSE
      double a[][] = new double[count][count]; //a
      double b[][] = new double[count][count]; //b
      for (int i = 1; i < count; i++) { //�迭�� ������ ����
          arr[i][0] = Double.parseDouble(st.nextToken());
          arr[i][1] = Double.parseDouble(st.nextToken());
      }
      int cost = Integer.parseInt(st.nextToken()); //cost��
      for(int j = 0; j < count; j++){
          for(int i = 0; i < j; i++){
              double sumxy = 0; //�ñ׸�xy
              double sumx = 0; //�ñ׸�x
              double sumx2 = 0; //�ñ׸�x^2
              double sumy = 0; //�ñ׸�y
              for(int k = i; k <= j; k++){ //i���� j������ �ñ׸����� ���
                  sumx += arr[k][0];
                  sumy += arr[k][1];
                  sumxy += arr[k][0] * arr[k][1];
                  sumx2 += arr[k][0] * arr[k][0];
              }
              double n = j - i + 1; //i�� j���� n��
              a[i][j] = (n * sumxy - sumx * sumy) / (n * sumx2 - sumx * sumx); //a ���
              b[i][j] = (sumy - a[i][j] * sumx) / n; //b���

              double SSE = 0;
              for(int k = i; k <= j; k++){
                  double y = a[i][j] * arr[k][0] + b[i][j]; //y = ax + b
                  SSE += (arr[k][1] - y) * (arr[k][1] - y); // �ñ׸�(y-ax-b)^2
              }
              E[i][j] = SSE; //sum of squared error
          }
      }
      double OPT[] = new double[count]; //�ּ����� ���� �迭
      int div[] = new int[count]; //�������� ������ ���� �迭
      for(int j = 0; j < count; j++){
          if(j == 0){ //j = 0�ΰ�� OPT = 0;
              OPT[j] = 0;
          }
          else{
              double min = Double.MAX_VALUE; //�ּҰ�
              for(int i = 0; i <= j; i++){ //Dynamic programming
                  double value;
                  if(i == 0){
                      value = E[i][j] + cost;
                  }
                  else{
                      value = OPT[i-1] + E[i][j] + cost;
                  }
                  if(value < min){ //�ּ����� min������ ������ min���� ����
                      min = value;
                      if (div[j] < i) { //�ּ����� ������ ��������
                          div[j] = i;
                      }
                  }
              }
              OPT[j] = min; //�ּ��� ����
          }
      }
      System.out.println("Cost of the optimal solution : " + String.format("%.6f",OPT[count-1])); //OPT���
      System.out.println("An optimal solution:");
      int index = count-1;
      Stack stack = new Stack();
      while(index > 0) { //�̾��� ������ ���ÿ� ������ ���
          int next = div[index];
          stack.push("[Segment " + next + " - " + index +
                  "] : y = " + String.format("%.6f",a[next][index]) + " * x + " + String.format("%.6f",b[next][index])
                  + " // square error : " + String.format("%.6f",E[next][index]));
          index = next - 1;
      }
      while(!stack.isEmpty()) {
          System.out.println(stack.pop());
      }
  }
}