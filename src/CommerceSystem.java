import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    // 속성
    private List<Category> categories ;
    private CartSystem cart;
    private Scanner scanner;

    // 생성자
    public CommerceSystem(List<Category> categories, Scanner scanner) {
        this.categories = categories;
        this.cart = new CartSystem();
        this.scanner = scanner;
    }
    // 기능
    // 외부에서 변수의 값을 읽을 수 있도록 하는 게터 매서드
    public List<Category> getCategories(){
        return this.categories;
    }
    // 외부에서 변수의 값을 수정할 수 있도록 하는 세터 메서드
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    // start 함수를 생성합니다.
    public void start() {



        // 반복문 시작
        while (true) {
            try {
                System.out.println("[  실시간 커머스 플랫폼  ]");
                // 리스트에 저장된 카테고리의 수만큼 반복하여 출력합니다
                for (int i = 0; i < categories.size(); i++) {
                    System.out.println((i + 1) + "." + categories.get(i).getCategoryName());
                }
                // 종료 안내메뉴를 출력합니다.
                System.out.println("0.종료             | 프로그램 종료");

                int cartMenuNum = categories.size() + 1;
                int cancelNum = categories.size() + 2;

                if (!cart.empty()) {
                    System.out.println("[ 주문 관리 ]");
                    System.out.println(cartMenuNum + ".장바구니 확인   | 장바구니를 확인 후 주문합니다.");
                    System.out.println(cancelNum + ".주문 취소      | 진행중인 주문을 취소합니다.");
                    System.out.println("아래 메뉴를 선택해주세요.");
                }
                System.out.print("원하는 메뉴 숫자를 입력하세요: ");

                // 사용자가 입력한 숫자를 읽어옵니다.
                int mainNum = scanner.nextInt();

                // 입력받은 숫자에 따라서 기능을 실행하기 위한 switch문
                // 확장성과 편의를 위해서 switch case가 맞지 않는것 같아서 if문으로 변경합니다.
                if (mainNum == 0) {
                    // 입력받은 숫자가 0일 때 프로그램을 종료합니다.
                    System.out.println("커머스 플랫폼을 종료합니다.");
                    return;
                    // inputNum이 0 보다 클때 실제 입력받은 값에 맞는 카테고리를 가져옵니다.
                } else if (mainNum > 0 && mainNum <= categories.size()) {
                    Category selectCategory = categories.get(mainNum - 1);

                    while (true) {
                        try {
                            // 입력받은 값에 맞는 카테고리를 출력합니다.
                            System.out.println("[ " + selectCategory.getCategoryName() + " 카테고리 ]");
                            // 카테고리 안에있는 상품의 갯수만큼 지정된 형식에 맞춰서 출력합니다.
                            // 직접 변수에 접근하지 않고 getProducts()를 사용합니다.
                            for (int i = 0; i < selectCategory.getProducts().size(); i++) {
                                Product p = selectCategory.getProducts().get(i);
                                System.out.printf("%d. %-15s | %,-10d원 | %-15s %n", (i + 1), p.getName(), p.getPrice(), p.getDescription());
                            }
                            System.out.println("0.  뒤로가기");

                            // 사용자가 입력한 숫자를 읽어옵니다.
                            int catNum = scanner.nextInt();
                            // 상세메뉴 조건문 시작
                            if (catNum == 0) {
                                //입력받은 값이 0일 경우 메인메뉴로 돌아갑니다.
                                System.out.println("메인 메뉴로 돌아갑니다.");
                                break;
                                // 입력받은 값이 0보다 크고 상품의 갯수보다 작거나 같은 정상적인 수를 입력 받았을때
                                // 직접 변수에 접근하지 않고 getProducts()를 사용합니다.
                            } else if (catNum > 0 && catNum <= selectCategory.getProducts().size()) {
                                Product p = selectCategory.getProducts().get(catNum - 1);
                                // (i + 1), p.name, p.price, p.description, p.stock 을 형식에 맞춰서 출력합니다.
                                System.out.printf("선택한상품: %-10s | %,-10d원 | %-10s | 재고: %d개%n", p.getName(), p.getPrice(), p.getDescription(), p.getStock());

                                handleAddProduct(scanner, p);
                                break;
                            } else {
                                // 상품 목록의 숫자가 아닌 잘못된 숫자를 입력받았을 때 에러 메세지를 출력합니다.
                                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                        }
                    }
                    } else if (!cart.empty() && mainNum == 4) {
                        handleOrderComplete(scanner);
                    } else if (!cart.empty() && mainNum == 5) {
                        cart.cancelOrder();
                } else {
                        // 메인메뉴의 숫자가 아닌 잘못된 수를 입력 받았을때 에러 메세지를 출력합니다.
                        throw new IllegalArgumentException("잘못 입력하셨습니다. 다시 입력해주세요.");
                    }

                } catch(InputMismatchException e){
                    System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private void handleAddProduct(Scanner scanner, Product p) {
        System.out.printf("%s | %,d원 | %s", p.getName(), p.getPrice(), p.getDescription());
        System.out.println("| 위 상품을 장바구니에 추가하시겠습니까?");
        System.out.print("1. 확인     2. 취소 :");
        int addProduct = scanner.nextInt();

        if (addProduct == 1) {
            int quantity = 1;
            int roadCart = cart.checkQuantity(p);
            if (roadCart + quantity > p.getStock()) {
                System.out.println("재고가 부족합니다.");
            } else {
                cart.addProduct(p, quantity);
            }
        } else if (addProduct == 2) {
            System.out.println("취소되었습니다.");
        } else {
            System.out.println("잘못 입력하셨습니다.");
        }
    }

    private void handleOrderComplete(Scanner scanner) {
        System.out.println("아래와 같이 주문 하시겠습니까?");
        cart.showCart();

        System.out.println("1. 주문 확정     2. 메인으로 돌아가기");
        int orderNum = scanner.nextInt();
        if (orderNum == 1) {
            cart.orderComplete();
        } else if (orderNum == 2) {
            System.out.println("메인 메뉴로 돌아갑니다.");
        } else {
            System.out.println("잘못 입력하셨습니다.");
        }
    }

}
