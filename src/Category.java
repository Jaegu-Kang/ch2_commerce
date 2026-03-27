import java.util.ArrayList;
import java.util.List;

public class Category {
    // Category 속성 변수를 선언합니다.(카테고리의 이름)
    public String categoryName;

    // 상품들을 관리할 product List를 생성합니다.
    public List<Product> products = new ArrayList<>();

    // Category 생성자를 지정합니다.
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
    // 카테고리 이름을 반환하는 메서드를 구현합니다.
    public String getCategoryName() {
        return this.categoryName;
    }
}
