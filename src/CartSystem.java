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
    public int checkQuantity(Product product) {
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(product.getName())) {
                return item.getQuantity();
            }
        }
        return 0;
    }

    public boolean empty() {
        return items.isEmpty();
    }

    public void addProduct(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(product.getName())) {
                item.addQuantity(quantity);
                System.out.println(product.getName() + "가 장바구니에 추가되었습니다.");
                return;
            }
        }
        items.add(new CartItem(product, quantity));
        System.out.println(product.getName() + "가 장바구니에 추가되었습니다.");
    }

    public void showCart() {
        int totalPrice = 0;
        System.out.println("[ 장바구니 내역 ]");
        for (CartItem item : items) {
            Product p = item.getProduct();
            int cartTotal = p.getPrice() * item.getQuantity();
            System.out.printf("%s | %,d원 | %s | 수량: %d개\n", p.getName(), p.getPrice(), p.getDescription(), item.getQuantity());
            totalPrice += cartTotal;
        }
        System.out.println("[ 총 주문 금액 ]");
        System.out.printf("%,d원\n", totalPrice);
    }

    public void orderComplete() {
        int totalPrice = 0;
        String order = "";
        for (CartItem item : items) {
            Product p = item.getProduct();
            totalPrice += p.getPrice() * item.getQuantity();
            int oldStock = p.getStock();
            int afterStock = p.getStock() - item.getQuantity();
            p.setStock(afterStock);
            order += p.getName() + " 재고가 " + oldStock + "개 -> " + afterStock + "개로 업데이트 되었습니다.\n";
        }
        System.out.printf("주문이 완료되었습니다. 총 금액: %,d원\n", totalPrice);
        System.out.println(order);
        items.clear();
    }

    public void cancelOrder() {
        items.clear();
        System.out.println("주문이 취소되었습니다.");
    }
}
