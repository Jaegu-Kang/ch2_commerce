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
            // 사용자가 잘못된 입력을 했을 경우 예외 처리를 위해 try catch 를 사용합니다.
            try {
                System.out.println("[  실시간 커머스 플랫폼  ]");
                // 리스트에 저장된 카테고리의 수만큼 반복하여 출력합니다
                for (int i = 0; i < categories.size(); i++) {
                    System.out.println((i + 1) + "." + categories.get(i).getCategoryName());
                }
                // 종료 안내메뉴를 출력합니다.
                System.out.println("0.종료             | 프로그램 종료");
                // 동적인 주문관리 메뉴 생성을 위한 번호를 생성합니다.
                int cartMenuNum = categories.size() + 1;
                int cancelNum = categories.size() + 2;
                // 만약 장바구니가 비어있지 않다면 주문관리 메뉴를 출력합니다.
                if (!cart.empty()) {
                    System.out.println("[ 주문 관리 ]");
                    System.out.println(cartMenuNum + ".장바구니 확인   | 장바구니를 확인 후 주문합니다.");
                    System.out.println(cancelNum + ".주문 취소      | 진행중인 주문을 취소합니다.");
                    System.out.println("아래 메뉴를 선택해주세요.");
                }
                System.out.print("원하는 메뉴 숫자를 입력하세요: ");

                // 사용자가 입력한 숫자를 읽어옵니다.
                int mainNum = scanner.nextInt();

                // 확장성과 편의를 위해서 switch case가 맞지 않는것 같아서 if문으로 변경합니다.
                if (mainNum == 0) {
                    // 입력받은 숫자가 0일 때 프로그램을 종료합니다.
                    System.out.println("커머스 플랫폼을 종료합니다.");
                    return;
                    // mainNum이 0 보다 크고 카테고리 범위 내 일때 실제 입력받은 값에 맞는 카테고리를 가져옵니다.
                } else if (mainNum > 0 && mainNum <= categories.size()) {
                    Category selectCategory = categories.get(mainNum - 1);
                    // 서브메뉴 반복문 시작
                    while (true) {
                        // // 사용자가 잘못된 입력을 했을 경우 예외 처리를 위해 try catch 를 사용합니다.
                        try {
                            // 입력받은 값에 맞는 카테고리를 출력합니다.
                            System.out.println("[ " + selectCategory.getCategoryName() + " 카테고리 ]");
                            // 카테고리 안에있는 상품의 갯수만큼 지정된 형식에 맞춰서 출력합니다.
                            for (int i = 0; i < selectCategory.getProducts().size(); i++) {
                                Product p = selectCategory.getProducts().get(i);
                                System.out.printf("%d. %-15s | %,-10d원 | %-15s %n", (i + 1), p.getName(), p.getPrice(), p.getDescription());
                            }
                            System.out.println("0.  뒤로가기");

                            // 사용자가 입력한 숫자를 읽어옵니다.
                            int subNum = scanner.nextInt();
                            // 상세메뉴 조건문 시작
                            if (subNum == 0) {
                                //입력받은 값이 0일 경우 메인메뉴로 돌아갑니다.
                                System.out.println("메인 메뉴로 돌아갑니다.");
                                break;
                                // 입력받은 값이 0보다 크고 상품의 갯수보다 작거나 같은 정상적인 수를 입력 받았을때
                            } else if (subNum > 0 && subNum <= selectCategory.getProducts().size()) {
                                Product p = selectCategory.getProducts().get(subNum - 1);
                                // (i + 1), p.name, p.price, p.description, p.stock 을 형식에 맞춰서 출력하면서
                                System.out.printf("선택한상품: %-10s | %,-10d원 | %-10s | 재고: %d개%n", p.getName(), p.getPrice(), p.getDescription(), p.getStock());
                                // 장바구니 추가기능을 핸들링하는 메서드를 호출합니다.
                                handleAddProduct(scanner, p);
                                break;
                            } else {
                                // 상품 목록의 숫자가 아닌 잘못된 숫자를 입력받았을 때 에러 메세지를 출력합니다.
                                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                            }
                            // 사용자가 문자를 입력했을 때 메세지를 출력합니다.
                        } catch (InputMismatchException e) {
                            System.out.println("잘못 입력하셨습니다. 숫자를 입력해주세요.");
                        }
                    } // 장바구니가 비어있지않고 사용자가 4를 입력했을때
                    } else if (!cart.empty() && mainNum == 4) {
                    // 주문 결제 기능을 핸들링하는 메서드를 호출합니다.
                        handleOrderComplete(scanner);
                      // 장바구니가 비어있지 않고 사용자가 5를 입력했을때
                    } else if (!cart.empty() && mainNum == 5) {
                        // 주문취소하는 기능을 핸들링하는 메서드를 호출합니다.
                        cart.cancelOrder();
                } else {
                        // 현재 메뉴가 아닌 잘못된 수를 입력 받았을때 에러 메세지를 출력합니다.
                        throw new IllegalArgumentException("잘못 입력하셨습니다. 다시 입력해주세요.");
                    }
                // 사용자가 문자를 입력했을 때 메세지를 출력합니다.
                } catch(InputMismatchException e){
                    System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                // 현재 메뉴가 아닌 잘못된 수를 입력 받았을때 에러 메세지를 출력합니다.
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    // CartSystem의 장바구니 추가 기능을 핸들링하는 메서드 입니다.
    private void handleAddProduct(Scanner scanner, Product p) {
        // 상품을 선택하면 해당 정보를 출력 한 뒤 추가 여부를 물어본 후
        System.out.printf("%s | %,d원 | %s", p.getName(), p.getPrice(), p.getDescription());
        System.out.println("| 위 상품을 장바구니에 추가하시겠습니까?");
        System.out.print("1. 확인     2. 취소 :");
        int addProduct = scanner.nextInt();
        // 사용자의 입력이 1일때
        if (addProduct == 1) {
            int quantity = 1;
            // 장바구니의 현재 수량을 가져옵니다.
            int roadCart = cart.checkQuantity(p);
            // 현재 수량과 추가할 수량의 합이 재고보다 큰지 판별합니다.
            if (roadCart + quantity > p.getStock()) {
                // 크다면 재고가 부족합니다 출력
                System.out.println("재고가 부족합니다.");
                // 아니라면 장바구니에 추가합니다.
            } else {
                cart.addProduct(p, quantity);
            }
        } else if (addProduct == 2) {
            System.out.println("취소되었습니다.");
        } else {
            System.out.println("잘못 입력하셨습니다.");
        }
    }
    // CartSystem의 주문 기능을 핸들링하는 메서드 입니다.
    private void handleOrderComplete(Scanner scanner) {
        // 사용자가 주문을 완료하기전 내역을 출력합니다.
        System.out.println("아래와 같이 주문 하시겠습니까?");
        cart.showCart();

        System.out.println("1. 주문 확정     2. 메인으로 돌아가기");
        int orderNum = scanner.nextInt();
        // 사용자의 입력이 1일때
        if (orderNum == 1) {
            // 주문을 완료합니다.
            cart.orderComplete();
        } else if (orderNum == 2) {
            System.out.println("메인 메뉴로 돌아갑니다.");
        } else {
            System.out.println("잘못 입력하셨습니다.");
        }
    }

}
