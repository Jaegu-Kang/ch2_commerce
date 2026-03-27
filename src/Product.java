public class Product {
    // Product 속성 변수를 선언합니다.(상품의 이름, 가격, 상세설명, 재고)
    public String name;
    public int price;
    public String description;
    public int stock;

    // Product 생성자를 지정합니다.
    public Product(String name, int price, String description, int stock) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }
}
