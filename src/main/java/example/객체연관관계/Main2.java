package example.객체연관관계;

public class Main2 {
    public static void main(String[] args) {
        System.out.println("Main2.main");

        // 1. 상위클래스 객체 만들기
        상위클래스 A객체 = 상위클래스.builder().data("A").build();
        // 2. 하위클래스 객체 만들기
        하위클래스 B객체 = 하위클래스.builder().value("B").build();
        하위클래스 C객체 = 하위클래스.builder().value("C").build();
        // 3. 관계
            // 상위클래스[1] <----> 하위클래스[M]  | 1:M 하나가 여러개를 참조한다.
        // A객체가 B/C 객체를 참조할 수 있게 담아준다.
        A객체.get참조중인하위객체들().add(B객체);
        A객체.get참조중인하위객체들().add(C객체);

        System.out.println("A객체 = " + A객체);


        // --------- 양방향 --------------------------------
        B객체.set상위객체(A객체);
        System.out.println("B객체 = " + B객체);
        C객체.set상위객체(A객체);
        System.out.println("C객체 = " + C객체);

        // 양방향의 장점
        // 상위 객체를 통해 하위 객체의 필드를 알 수 있다.
        System.out.println("A객체가 가지고 있는 하위객체들 중 첫번째 객체의 데이터 출력 : "
                + A객체.get참조중인하위객체들().get(0).getValue() );
        // 하위 객체를 통해 상위 객체의 필드를 알 수 있다.
        System.out.println("B객체가 가지고 있는 상위객체의 데이터 출력 : "
                + B객체.get상위객체().getData());
    }
}
