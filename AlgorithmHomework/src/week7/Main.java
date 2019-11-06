package week7;

//201402318 권순창 SLS
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
	  String fileSrc = new java.io.File("").getAbsolutePath();
	  String inputFileSrc =  fileSrc + "/src/data07.txt";	// 상대 경로 설정
      File file = new File(inputFileSrc); //arr1입력
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr); //파일 읽은 버퍼리더 생성
      StringTokenizer st = new StringTokenizer(br.readLine(), ","); //데이터 자를 스트링토크나이저
      int count = Integer.parseInt(st.nextToken())+1; //데이터 개수
      double arr[][] = new double[count][2]; //데이터 저장할 배열
      double E[][] = new double[count][count]; //SSE
      double a[][] = new double[count][count]; //a
      double b[][] = new double[count][count]; //b
      for (int i = 1; i < count; i++) { //배열에 데이터 삽입
          arr[i][0] = Double.parseDouble(st.nextToken());
          arr[i][1] = Double.parseDouble(st.nextToken());
      }
      int cost = Integer.parseInt(st.nextToken()); //cost값
      for(int j = 0; j < count; j++){
          for(int i = 0; i < j; i++){
              double sumxy = 0; //시그마xy
              double sumx = 0; //시그마x
              double sumx2 = 0; //시그마x^2
              double sumy = 0; //시그마y
              for(int k = i; k <= j; k++){ //i부터 j까지의 시그마값들 계산
                  sumx += arr[k][0];
                  sumy += arr[k][1];
                  sumxy += arr[k][0] * arr[k][1];
                  sumx2 += arr[k][0] * arr[k][0];
              }
              double n = j - i + 1; //i와 j사이 n값
              a[i][j] = (n * sumxy - sumx * sumy) / (n * sumx2 - sumx * sumx); //a 계산
              b[i][j] = (sumy - a[i][j] * sumx) / n; //b계산

              double SSE = 0;
              for(int k = i; k <= j; k++){
                  double y = a[i][j] * arr[k][0] + b[i][j]; //y = ax + b
                  SSE += (arr[k][1] - y) * (arr[k][1] - y); // 시그마(y-ax-b)^2
              }
              E[i][j] = SSE; //sum of squared error
          }
      }
      double OPT[] = new double[count]; //최소합을 구할 배열
      int div[] = new int[count]; //나누어진 구간을 구할 배열
      for(int j = 0; j < count; j++){
          if(j == 0){ //j = 0인경우 OPT = 0;
              OPT[j] = 0;
          }
          else{
              double min = Double.MAX_VALUE; //최소값
              for(int i = 0; i <= j; i++){ //Dynamic programming
                  double value;
                  if(i == 0){
                      value = E[i][j] + cost;
                  }
                  else{
                      value = OPT[i-1] + E[i][j] + cost;
                  }
                  if(value < min){ //최소합이 min값보다 작으면 min값에 삽입
                      min = value;
                      if (div[j] < i) { //최소합이 나오면 구간지정
                          div[j] = i;
                      }
                  }
              }
              OPT[j] = min; //최소합 삽입
          }
      }
      System.out.println("Cost of the optimal solution : " + String.format("%.6f",OPT[count-1])); //OPT출력
      System.out.println("An optimal solution:");
      int index = count-1;
      Stack stack = new Stack();
      while(index > 0) { //이어진 구간을 스택에 삽입후 출력
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