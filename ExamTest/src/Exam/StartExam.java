package Exam;

import Exam.Service.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class StartExam {
    public static void main(String[] args) {


        System.out.println("풀고싶은 문제를 골라주세요.");
        System.out.println("1. 요구사항 확인");
        System.out.println("2. 화면 설계");
        System.out.println("3. 데이터 입출력 구현");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        Service service = new Service(choice);
    }
}
