package example.객체연관관계.과제;

import ezenweb.EzenWebStart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class main {
    public static void main(String[] args) {
        System.out.println("main.main");
        SpringApplication.run( main.class );

        카테고리 A카테고리 = 카테고리.builder()
                .카테고리번호(1)
                .카테고리명("A")
                .build();

        제품 AA제품 = 제품.builder()
                .제품번호(1)
                .제품이름("AA")
                .카테고리번호(A카테고리)
                .build();

        제품 AB제품 = 제품.builder()
                .제품번호(2)
                .제품이름("AB")
                .카테고리번호(A카테고리)
                .build();


        A카테고리.get제품리스트().add(AA제품);
        A카테고리.get제품리스트().add(AB제품);

        주문 A주문 = 주문.builder()
                .주문번호(1)
                .주문수량(10)
                .제품번호(AA제품)
                .build();

        주문 B주문 = 주문.builder()
                .주문번호(2)
                .주문수량(5)
                .제품번호(AB제품)
                .build();
        주문 C주문 = 주문.builder()
                .주문번호(3)
                .주문수량(7)
                .제품번호(AB제품)
                .build();
        주문 D주문 = 주문.builder()
                .주문번호(4)
                .주문수량(10)
                .제품번호(AB제품)
                .build();

        AA제품.get주문리스트().add(A주문);
        AB제품.get주문리스트().add(B주문);
        AB제품.get주문리스트().add(C주문);
        AB제품.get주문리스트().add(D주문);



        // 출력을 해보기
         // 1. A 카테고리 안에 있는 AB 제품의 주문리스트 조회하기
        System.out.println(
                "A 카테고리 안에 있는 AB 제품의 주문리스트 조회하기 : " +
                        A카테고리.get제품리스트().get(1).get주문리스트()
        );
         // 2. 주문안에서 주문한 제품의 정보를 출력하기
        System.out.println(
                "A주문 주문안에서 주문한 제품의 정보를 출력하기 : " +
                        A주문.get제품번호().get제품이름()
        );
         // 3. AA제품에서 제품 카테고리를 출력하기
        System.out.println(
                "AA제품에서 제품 카테고리를 출력하기 : " +
                        AA제품.get카테고리번호().get카테고리번호()
        );

    }
}
