package Exam.Service;

import Exam.Problem.Third;
import Exam.Problem.First;
import Exam.Problem.Second;

import java.util.*;

public class Service {
    Scanner sc = new Scanner(System.in);
    public Service (int choice){

        if(choice == 1){
            Map<String, String> exam = First.getExam();
            startTest(exam);
        } else if (choice == 2) {
            Map<String, String> exam = Second.getExam();
            startTest(exam);
        } else if (choice == 3) {
            Map<String, String> exam = Third.getExam();
            startTest(exam);
        } else{
            System.out.println("잘못된 입력입니다.");
        }
    }

    void startTest(Map<String, String> exam) {
        Set<String> problemSet = exam.keySet(); //문제들의 집합을 구한다.

        //랜덤한 문제를 선택하기 위해 Random 객체를 생성.
        Random random = new Random();

        //랜덤한 문제를 출력합니다.
        for (int i=0; i<10; i++){
            String randomWord = (String) problemSet.toArray()[random.nextInt(problemSet.size())];
            System.out.println(randomWord);
            String answer = sc.nextLine();
            if(answer.equals(exam.get(randomWord))){
                System.out.println("정답입니다.");
            }else{
            System.out.println("정답은 이거입니다.");
            System.out.println("정답 : "+exam.get(randomWord));;
            }
        }
    }
}
