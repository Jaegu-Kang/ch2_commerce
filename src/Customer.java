public class Customer {
    // Customer 속성 변수를 선언합니다.(고객의 이름, 주소, 등급)
    // 모든 변수를 private로 선언합니다.
    private String name;
    private String email;
    private String grade;

    // Customer 생성자를 지정합니다.
    public Customer(String name, String email, String grade) {
        this.name = name;
        this.email = email;
        this.grade = grade;
    }

    // 외부에서 변수의 값을 읽을 수 있도록 하는 게터 매서드
    public String getName() {
        return this.name;
    }
    public String getEmail() {
        return this.email;
    }
    public String getGrade() {
        return this.grade;
    }
    // 외부에서 변수의 값을 수정할 수 있도록 하는 세터 메서드
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }

}
