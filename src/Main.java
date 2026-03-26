import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Product pd1 = new Product("Galaxy S26", 1200000, "최신 안드로이드 스마트폰", 10);
        Product pd2 = new Product("iPhone 17", 1350000, "Apple의 최신 스마트폰", 10);
        Product pd3 = new Product("MacBook Pro", 2400000, "M5 칩셋이 탑재된 노트북", 10);
        Product pd4 = new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 10);

        List<Product> products = new ArrayList<>();

        products.add(pd1);
        products.add(pd2);
        products.add(pd3);
        products.add(pd4);


        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("[  실시간 커머스 플랫폼  ]");
            for (int i = 0; i < products.size(); i++) {
                Product sc = products.get(i);
                System.out.printf("%d. %-15s | %,-10d원 | %-15s %n", (i + 1), sc.name, sc.price, sc.description);
            }
            System.out.println("0. 종료             | 프로그램 종료");
            System.out.print("원하는 메뉴 숫자를 입력하세요: ");
            int inputNum = scanner.nextInt();


            switch (inputNum){
                case 0:
                    System.out.println("커머스 플랫폼을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못 입력하셨습니다 다시 입력해주세요");
                    break;
            }

        }
    }
}
