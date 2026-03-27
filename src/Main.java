import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Category 객체들을 생성합니다("전자제품, 의류, 식품)
        Category electronics = new Category("전자제품");
        Category cloth = new Category("의류");
        Category food = new Category("식품");

        // 상품의 상세 정보를 담은 Product 객체들을 생성합니다.
        Product pd1 = new Product("Galaxy S26", 1200000, "최신 안드로이드 스마트폰", 10);
        Product pd2 = new Product("iPhone 17", 1350000, "Apple의 최신 스마트폰", 10);
        Product pd3 = new Product("MacBook Pro", 2400000, "M5 칩셋이 탑재된 노트북", 10);
        Product pd4 = new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 10);

        // Product List 를 생성합니다.
        // 리스트를 카테고리용으로 변경,생성합니다.
        List<Category> categories = new ArrayList<>();

        // List에 생성한 Product 등록합니다.
        // 전자제품 카테고리 안에 Product들을 등록합니다.
        // 직접 변수에 접근하지 않고 getProducts()를 사용합니다.
        electronics.getProducts().add(pd1);
        electronics.getProducts().add(pd2);
        electronics.getProducts().add(pd3);
        electronics.getProducts().add(pd4);

        // 카테고리 리스트에 각 카테고리들을 추가합니다.
        categories.add(electronics);
        categories.add(cloth);
        categories.add(food);

        // CommerceSystem 객체 생성 후 start 함수를 실행합니다.
        // 카테고리 리스트를 전달해서 객체를 생성합니다.
        CommerceSystem system = new CommerceSystem(categories);
        system.start();



    }
}
