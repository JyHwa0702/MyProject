package hello.hellospring;

import java.util.Scanner;

public class fasdf {

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("4정수의 최댓값을 구하고싶으시면 1을");
        System.out.println("3정수의 최솟값을 구하고싶으시면 2를");
        System.out.println("4정수의 최솟값을 구하고싶으시면 3을 누르세요");

        int x1 = stdIn.nextInt();
        while (x1<5) {
            switch (x1) {
                case 1:
                    System.out.println("정수 4개를 입력하세요");
                    int a = stdIn.nextInt();
                    int b = stdIn.nextInt();
                    int c1 = stdIn.nextInt();
                    int d = stdIn.nextInt();
                    System.out.println("최댓값은" + MaxPro.max4(a, b, c1, d) + " 입니다.");
                    System.out.println("처음으로 돌아가려면 4, 끝내시려면 5를 누르세요");
                    x1 = stdIn.nextInt();

                case 2:
                    System.out.println("정수 3개를 입력하세요 ");
                    int a1 = stdIn.nextInt();
                    int b1 = stdIn.nextInt();
                    int c11 = stdIn.nextInt();
                    System.out.println("정수 3개의 최솟값은" + MaxPro.min3(a1, b1, c11) + " 입니다.");

                case 3:
                    System.out.println("정수 4개를 입력하세요 ");
                    int a2 = stdIn.nextInt();
                    int b3 = stdIn.nextInt();
                    int c14 = stdIn.nextInt();
                    int d5 = stdIn.nextInt();
                    System.out.println("정수 4개의 최솟값은" + MaxPro.min4(a2, b3, c14, d5) + " 입니다.");

                    break;
            }
        }
    }

}
