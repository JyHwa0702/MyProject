package hello.hellospring;

import java.util.Scanner;

class MenuViewer{
    public static Scanner sc = new Scanner(System.in);

    public static void showMenu() {
        System.out.println("메뉴 등록"); //정보 저장
        System.out.println("메뉴 리스트"); //데이터 출력
        System.out.println("데이터 검색"); //이름 기준으로 데이터 찾아서 정보 출력
        System.out.println("데이터 수정"); //이름 기준으로 데이터 찾아서 정보 수정
        System.out.println("데이터 삭제"); //이름 기준으로 데이터 찾아서 삭제
    }
}

class Coffee{
    private String title; //제품명
    private int price; //가격
    private boolean isIce; //HOT/ICE
    private String remark; //부가설명

    public Coffee(String title, int price, boolean isIce, String remark) {
        this.title = title;
        this.price = price;
        this.isIce = isIce;
        this.remark = remark;
    }

    public String getTitle() {
        return title;
    }

//   public int getPrice() {
//      return price;
//   }

    void showDrinkInfo() {
        System.out.printf("%7s\t %7d\t %7s\t %7s \n", title, price, isIce ? "ICE" : "HOT", remark);;
    }
}

class ProductManager{
    Coffee[] productList = new Coffee[100];
    int curCut = 0;


    public Coffee readData() {
        System.out.println("제품명: ");
        String title = MenuViewer.sc.next();
        System.out.println("가격: ");
        int price = MenuViewer.sc.nextInt();
        return new Coffee(title, price);
    }

    public void showData() {
        System.out.println("――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        System.out.println("\"%7s\\t %7d\\t %7s\\t %7s \\n\", title, price, isIce ? \"ICE\" : \"HOT\", remark");
        for (int i = 0; i < curCut; i++) {
            productList[i].showDrinkInfo();
        }
        System.out.println("――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
    }
}


public class KioskVer03 {
    public static void main(String[] args) {
        Coffee coffee1 = new Coffee("아메리카노", 2500, true, "한국 전통 커피");
        Coffee coffee2 = new Coffee("바닐라라떼", 3500, false, "달달한 맛을 원한다면");

        coffee1.showDrinkInfo();
        coffee2.showDrinkInfo();
    }



}