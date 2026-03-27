import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    // Product List 를 생성합니다.
    List<Product> products;

    // CommerceSystem 생성자를 지정합니다.
    public CommerceSystem(List<Product> products) {
        this.products = products;
    }
    // start 함수를 생성합니다.
    public void start() {

        // Scanner 객체를 생성합니다.
        Scanner scanner = new Scanner(System.in);

        // 반복문 시작
        while (true) {
            System.out.println("[  실시간 커머스 플랫폼  ]");
            // List에 저장된 product의 갯수 만큼 반복하여 출력합니다.
            for (int i = 0; i < products.size(); i++) {
                Product sc = products.get(i);
                // (i + 1), sc.name, sc.price, sc.description 를 형식에 맞춰서 출력합니다.
                System.out.printf("%d. %-15s | %,-10d원 | %-15s %n", (i + 1), sc.name, sc.price, sc.description);
            }
            System.out.println("0. 종료             | 프로그램 종료");
            System.out.print("원하는 메뉴 숫자를 입력하세요: ");

            // 사용자가 입력한 숫자를 읽어옵니다.
            int inputNum = scanner.nextInt();

            // 입력한 숫자에 따라서 기능을 실행하기 위한 switch문
            switch (inputNum){
                // 입력받은 숫자가 0일 때 프로그램을 종료합니다.
                case 0:
                    System.out.println("커머스 플랫폼을 종료합니다.");
                    return;
                // 다른 숫자를 입력받았을 때 에러 메세지를 출력합니다.
                default:
                    System.out.println("잘못 입력하셨습니다 다시 입력해주세요");
                    break;
            }

        }
    }

}
