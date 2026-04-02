import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AdminSystem {
    // 속성
    private List<Category> categories;
    private CartSystem cart;
    private Scanner scanner;

    // 생성자
    public AdminSystem(List<Category> categories, CartSystem cart, Scanner scanner) {
        this.categories = categories;
        this.cart = cart;
        this.scanner = scanner;
    }
    // 기능
    // 관리자 모드 진입 메서드 입니다.
    public void handleAdminMode(){
        scanner.nextLine();// 줄바꿈 버퍼를 제거합니다.
        // 로그인시도횟수와 입력비밀번호를 선언합니다.
        int accessCount = 0;
        boolean authentication = false;

        // 비밀번호 인증을 위한 반복문
        while (accessCount < 3) {
            System.out.print("관리자 비밀번호를 입력하세요: ");
            String pwd = scanner.nextLine();
            // 비밀번호가 일치한다면 인증을 트루로 변경
            if ("admin123!".equals(pwd)) {
                authentication = true;
                break;
            // 아니라면 시도횟수를 증가합니다.
            } else {
                accessCount++;
                System.out.println("비밀번호가 틀렸습니다.");
            }
        }// 3회 입력이 모두 실패했다면
        if (!authentication) {
            System.out.println("비밀번호 3회 오류. 메인메뉴로 돌아갑니다.");
            return;
        }

        // 인증 성공 후 관리자 메뉴 반복문
        while (true){
            System.out.println("\n[ 관리자 모드 ]");
            System.out.println("1. 상품 추가");
            System.out.println("2. 상품 수정");
            System.out.println("3. 상품 삭제");
            System.out.println("4. 전체 상품 현황");
            System.out.println("0. 메인으로 돌아가기");
            System.out.print("입력: ");
            // parseInt를 통해 문자열을 숫자로 변환하며 문자가 입력되는 에러 발생시 catch로 잡아냅니다.
            try {
                int adminNum = Integer.parseInt(scanner.nextLine());
                if (adminNum == 0) {
                    break;
                } else if (adminNum == 1) {
                      adminAddProduct();
                } else if (adminNum == 2) {
                    adminEditProduct();
                } else if (adminNum == 3) {
                    adminDeleteProduct();
                } else if (adminNum == 4) {
                    adminShowAllProduct();

                }
            // 문자가 입력되면 메세지를 출력합니다.
            } catch (NumberFormatException e){
                System.out.println("잘못입력하셨습니다. 다시 입력해주세요.");
            }

        }

    }

    // 상품을 추가하는 메서드입니다.
    private void adminAddProduct() {
        System.out.println("어느 카테고리에 상품을 추가하시겠습니까?");
        // 전체 카테고리 목록을 번호와 함께 출력합니다.
        for (int i = 0; i < categories.size(); i++){
            System.out.println((i + 1) + "." + categories.get(i).getCategoryName());
        }
        System.out.print("입력: ");
        // parseInt를 통해 문자열을 숫자로 변환하며 문자가 입력되는 에러 발생시 catch로 잡아냅니다.
        int catNum;
        try {
            catNum = Integer.parseInt(scanner.nextLine());
            // 문자가 입력되면 메세지를 출력합니다.
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다.");
            return;
        }// 사용자가 목록에 없는 번호를 입력하면 에러메세지를 출력합니다, 0 이하의 숫자 입력 방지
        if (catNum < 0 || catNum > categories.size()) {
            System.out.println("존재하지 않는 카테고리 입니다.");
            return;
        }// 올바른 번호가 입력되면 카테고리를 출력후 상품명을 입력받습니다.
        Category selectCategory = categories.get(catNum - 1);
        System.out.println("[ " + selectCategory.getCategoryName() + "카테고리에 상품 추가. ]");
        System.out.print("상품명을 입력하세요: ");
        String name = scanner.nextLine();
        // 상품명을 중복 검사합니다.
        for (Product p : selectCategory.getProducts()) {
            if (p.getName().equals(name)) {
                System.out.println("이미 존재하는 상품입니다.");
                return;
            }
        }// 상품 정보를 입력합니다.문자 입력 방지 추가
        try {
            System.out.print("가격을 입력하세요: ");
            int price = Integer.parseInt(scanner.nextLine());
            System.out.print("상품 설명을 입력하세요: ");
            String description = scanner.nextLine();
            System.out.print("재고 수량을 입력하세요: ");
            int stock = Integer.parseInt(scanner.nextLine());

            System.out.printf("\n%s | %,d원 | %s | 재고: %d개\n", name, price, description, stock);
            System.out.println("위 정보로 상품을 추가하시겠습니까?");
            System.out.print("1.확인    2. 취소 \n입력: ");

            int addNum = Integer.parseInt(scanner.nextLine());
            if (addNum == 1) {
                selectCategory.getProducts().add(new Product(name, price, description, stock));
                System.out.println("상품이 성공적으로 추가되었습니다.");
            } else {
                System.out.println("상품등록이 취소되었습니다.");
            }
        }catch (NumberFormatException e) {
            System.out.println("잘못 입력하셨습니다. 숫자를 입력하세요.");
        }

    }

    // 상품을 수정하는 메서드입니다.
    public void adminEditProduct() {
        System.out.print("수정할 상품명을 입력하세요: ");
        String searchName = scanner.nextLine();
        // 찾은 상품을 담을 변수를 null로 초기화 하여 선언합니다.
        Product selectProduct = null;
        // 모든 상품을 순회해서 입력한 상품과 일치하는지 비교합니다.
        for (Category cat : categories) {
            for (Product p : cat.getProducts()) {
                if (p.getName().equals(searchName)) {
                    selectProduct = p;
                    break;
                }
            }// 일치하는 상품을 찾았다면 반복종료합니다.
            if (selectProduct != null)
                break;
        }// 찾지 못했다면 메세지를 출력합니다.
        if (selectProduct == null) {
            System.out.println("해당 상품을 찾을 수 없습니다.");
            return;
        }// 찾은 상품정보를 출력 후 수정 안내 문구를 출력합니다.
        System.out.printf("현재 상품 정보: %s | %,d원 | %s | 재고: %d개\n",selectProduct.getName(), selectProduct.getPrice(), selectProduct.getDescription(), selectProduct.getStock());
        System.out.println("수정할 항목을 선택하세요:");
        System.out.println("1. 가격");
        System.out.println("2. 설명");
        System.out.println("3. 재고");
        System.out.println("0. 뒤로가기");
        System.out.print("입력: ");
        // 선택한 상품 정보를 수정합니다.
        try {
            int editProduct = Integer.parseInt(scanner.nextLine());
            if (editProduct == 0) {
                return;
            } else if (editProduct == 1) {
                System.out.printf("현재가격: %,d원", selectProduct.getPrice());
                System.out.print("변경할 가격을 입력하세요: ");
                int newPrice = Integer.parseInt(scanner.nextLine());
                System.out.printf("가격이 %,d원 으로 수정되었습니다.", newPrice);
                selectProduct.setPrice(newPrice);
            } else if (editProduct == 2) {
                System.out.printf("현재설명: %s", selectProduct.getDescription());
                System.out.print("변경할 내용을 입력하세요: ");
                String newDesc = scanner.nextLine();
                System.out.printf("설명이 %s 으로 수정되었습니다.", newDesc);
                selectProduct.setDescription(newDesc);
            } else if (editProduct == 3) {
                System.out.printf("현재재고: %d개", selectProduct.getStock());
                System.out.print("변경할 재고 수량을 입력하세요: ");
                int newStock = Integer.parseInt(scanner.nextLine());
                System.out.printf("재고가 %d개 로 수정되었습니다.", newStock);
                selectProduct.setStock(newStock);

            } else {
                System.out.println("잘못 입력하셨습니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("잘못 입력하셨습니다. 숫자를 입력하세요.");
        }
    }

    // 상품 삭제 로직입니다.
    public void adminDeleteProduct() {
        System.out.print("삭제할 상품명을 입력하세요: ");
        String delName = scanner.nextLine();
        // 삭제할 상품 객체를 담을 변수와 그 상품의 카테고리를 담을 변수를 선언합니다.
        Product selectProduct = null;
        Category selectCategory = null;
        // 전체 상품을 순회하여 입력한 상품과 일치하는지 비교합니다.
        for (Category cat : categories) {
            for (Product p : cat.getProducts()) {
                if (p.getName().equals(delName)) {
                    selectProduct = p;
                    selectCategory = cat;
                    break;
                }
            }// 상품을 찾았다면 반복 종료합니다.
            if (selectProduct != null)
                break;
        }// 찾지 못했다면 에러메세지를 출력합니다.
        if (selectProduct == null) {
            System.out.println("해당 상품을 찾을 수 없습니다.");
            return;
        }// 찾아낸 상품 정보를 출력합니다.
        System.out.printf("현재 상품 정보: %s | %,d원 | %s | 재고: %d개",selectProduct.getName(), selectProduct.getPrice(), selectProduct.getDescription(), selectProduct.getStock());
        System.out.println("정말 삭제하시겠습니까? (삭제 시 장바구니에서도 비워집니다.)");
        System.out.print("1.확인    2. 취소 \n입력: ");
        // 찾아낸 상품을 리스트에서 삭제하고 장바구니에서도 삭제합니다.
        int delProduct = Integer.parseInt(scanner.nextLine());
        if (delProduct == 1) {
            selectCategory.getProducts().remove(selectProduct);
            cart.removeProduct(selectProduct.getName());
            System.out.println("상품이 성공적으로 삭제되었습니다.");
        } else {
            System.out.println("삭제가 취소되었습니다.");
        }
    }
    // 전체 현황을 출력하는 메서드입니다.
    public void adminShowAllProduct() {
        System.out.println("[ 전체 상품 현황 ]");
        // 전체 카테고리와 전체 상품정보를 순회해서 출력합니다.
        for (Category cat : categories) {
            System.out.println(cat.getCategoryName());
            for (Product p : cat.getProducts()) {
                System.out.printf("%-10s | %,-10d원 | %-10s | 재고: %d개%n", p.getName(), p.getPrice(), p.getDescription(), p.getStock());
            }
        }
    }

}
