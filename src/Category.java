import java.util.ArrayList;
import java.util.List;

public class Category {
    // Category 속성 변수를 선언합니다.(카테고리의 이름)
    // 모든 변수를 private로 선언합니다.
    private String categoryName;
    // 상품들을 관리할 product List를 생성합니다.
    private List<Product> products = new ArrayList<>();

    // Category 생성자를 지정합니다.
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    // 카테고리 이름을 반환하는 메서드를 구현합니다.
    // 외부에서 변수의 값을 읽을 수 있도록 하는 게터 매서드
    public String getCategoryName() {
        return this.categoryName;
    }
    public List<Product> getProducts() {
        return this.products;
    }
    // 외부에서 변수의 값을 수정할 수 있도록 하는 세터 메서드
    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
