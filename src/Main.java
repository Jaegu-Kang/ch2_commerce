import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Product 객체들을 생성합니다.
        Product pd1 = new Product("Galaxy S26", 1200000, "최신 안드로이드 스마트폰", 10);
        Product pd2 = new Product("iPhone 17", 1350000, "Apple의 최신 스마트폰", 10);
        Product pd3 = new Product("MacBook Pro", 2400000, "M5 칩셋이 탑재된 노트북", 10);
        Product pd4 = new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 10);

        // Product List 를 생성합니다.
        List<Product> products = new ArrayList<>();

        // List에 생성한 Product 등록합니다.
        products.add(pd1);
        products.add(pd2);
        products.add(pd3);
        products.add(pd4);

        // CommerceSystem 객체 생성 후 start 함수를 실행합니다.
        CommerceSystem system = new CommerceSystem(products);
        system.start();



    }
}
