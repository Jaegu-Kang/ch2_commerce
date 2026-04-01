public class CartItem {
    // 속성
    private Product product;
    private int quantity;
    // 생성자
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    // 기능
    public Product getProduct() {
        return this.product;
    }
    public int getQuantity() {
        return this.quantity;
    }
    // 기존의 담긴 상품을 추가할경우 수량증가를 위한 메서드입니다.
    public void addQuantity(int addStock) {
        this.quantity += addStock;
    }
}
