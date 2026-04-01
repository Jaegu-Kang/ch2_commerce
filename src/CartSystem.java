import java.util.ArrayList;
import java.util.List;

public class CartSystem {
    // 속성
    private List<CartItem> items;
    // 생성자
    public CartSystem(){
        this.items = new ArrayList<>();
    }
    // 기능
    // 장바구니에 상품을 추가할때 기존 수량을 확인하는 메서드입니다.
    public int checkQuantity(Product product) {
        // 장바구니의 항목만큼 반복하여 같은 이름의 상품이 있다면 그 수량을 반환합니다.
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(product.getName())) {
                return item.getQuantity();
            }
        }// 아니라면 0을 반환합니다.
        return 0;
    }
    // 주문진행 조건 판별을 위한 메서드입니다.
    public boolean empty() {
        return items.isEmpty();
    }
    // 장바구니에 상품을 추가하는 메서드입니다.
    public void addProduct(Product product, int quantity) {
        // 장바구니의 항목만큼 반복하여 같은 이름의 상품이 있다면 수량만 더해줍니다.
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(product.getName())) {
                item.addQuantity(quantity);
                System.out.println(product.getName() + "가 장바구니에 추가되었습니다.");
                return;
            }
        } // 아니라면 새로운 상품을 등록해 줍니다.
        items.add(new CartItem(product, quantity));
        System.out.println(product.getName() + "가 장바구니에 추가되었습니다.");
    }
    // 장바구니의 내역을 보여주는 메서드입니다.
    public void showCart() {
        int totalPrice = 0;
        System.out.println("[ 장바구니 내역 ]");
        // 장바구니의 항목수만큼 호출하여.
        for (CartItem item : items) {
            Product p = item.getProduct();
            // 해당상품의 가격과 수량으로 상품의 총금액을 계산후 출력합니다.
            int cartTotal = p.getPrice() * item.getQuantity();
            System.out.printf("%s | %,d원 | %s | 수량: %d개\n", p.getName(), p.getPrice(), p.getDescription(), item.getQuantity());
            // 전체 누적 금액을 합산합니다.
            totalPrice += cartTotal;
        }
        System.out.println("[ 총 주문 금액 ]");
        System.out.printf("%,d원\n", totalPrice);
    }
    // 상품을 주문하는 메서드입니다.
    public void orderComplete() {
        // 주문금액 출력을 위한 변수 문자열을 선언합니다.
        int totalPrice = 0;
        String order = "";
        // 장바구니의 항목수만큼 호출하여
        for (CartItem item : items) {
            Product p = item.getProduct();
            // 총 금액을 합산합니다.
            totalPrice += p.getPrice() * item.getQuantity();
            // 변경 전 재고를 저장합니다.
            int oldStock = p.getStock();
            // 변경 전 재고에서 장바구니의 수 만큼 차감합니다.
            int afterStock = p.getStock() - item.getQuantity();
            // 차감된 수량을 현재 수량으로 설정 후 결과를 출력합니다.
            p.setStock(afterStock);
            order += p.getName() + " 재고가 " + oldStock + "개 -> " + afterStock + "개로 업데이트 되었습니다.\n";
        }// 주문 결과 출력 후 장바구니를 초기화 합니다.
        System.out.printf("주문이 완료되었습니다. 총 금액: %,d원\n", totalPrice);
        System.out.println(order);
        items.clear();
    }
    // 주문 취소를 위한 메서드입니다.
    public void cancelOrder() {
        items.clear();
        System.out.println("주문이 취소되었습니다.");
    }

    // 관리자 모드로 상품을 삭제했을 때 장바구니에서도 해당 상품을 지우는 메서드입니다.
    public void removeProduct(String productName) {
        items.removeIf(item -> item.getProduct().getName().equals(productName));
    }
}
