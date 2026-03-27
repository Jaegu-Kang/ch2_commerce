public class Product {
    // Product 속성 변수를 선언합니다.(상품의 이름, 가격, 상세설명, 재고)
    // 모든 변수를 private로 선언합니다.
    private String name;
    private int price;
    private String description;
    private int stock;

    // Product 생성자를 지정합니다.
    public Product(String name, int price, String description, int stock) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }
    // 외부에서 변수의 값을 읽을 수 있도록 하는 게터 매서드
    public String getName(){
        return this.name;
    }
    public int getPrice(){
        return this.price;
    }
    public String getDescription(){
        return this.description;
    }
    public int getStock(){
        return this.stock;
    }

    // 외부에서 변수의 값을 수정할 수 있도록 하는 세터 메서드
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}
