

# CH2 커머스 과제

<br>

##  기능

> * 


## 🛠️ 사용 기술 (Tech Stack)

* **Language:** Java (JDK 17 이상 권장)
* **IDE:** IntelliJ IDEA

<br><br>

## 🚨 작업내용
* STEP1
* 개별 상품을 관리하는 클래스 Product를 생성했습니다, 현재는 전자제품만 관리
* Main 클래스에서 Product 클래스를 생성하여 상품 목록을 추가했습니다.
* List 선언 후 여러 Product를 추가했습니다.
* 반복문을 활용해 등록한 상품을 출력했습니다.
* 조건문을 활용해 0 입력시 프로그램이 종료되도록 했습니다.
* STEP2
* STEP1에서 누락한 주석을 작성했습니다.
* CommerceSystem 클래스를 생성했습니다.
* Main에서 관리하던 입력과 반복 로직을 CommerceSystem로 가져와 start함수를 만들어 관리하게 수정했습니다.
* STEP3
* Category 클래스를 생성했습니다.
* Customer 클래스를 생성했습니다.
* Main 클래스에서 Product를 직접 List에 담던 것을, Category 객체 안에 담아 묶어서 관리하게 수정했습니다.
* CommerceSystem 클래스가 Product 리스트 대신 Category 리스트를 받아 관리하게 수정했습니다.
* CommerceSystem의 start 함수 내부의 분기처리를 하는 반복문을 switch문에서 if 문으로 변경했습니다.
* 메인메뉴와 상세메뉴가 동작할 수 있도록 반복문을 중첩 사용했습니다.
* Product, Category, Customer, CommerceSystem 클래스에 캡슐화를 적용 Getter와 Setter 메서드를 통해 데이터를 관리하게 했습니다.


