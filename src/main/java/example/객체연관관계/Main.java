package example.객체연관관계;

public class Main {
    public static void main(String[] args) {
        System.out.println("Main.main");


        // 1. [A 객체생성] '강호동' 회원가입 진행
        회원 A객체 = 회원.builder()
                .회원번호(1)
                .회원아이디("qwe")
                .회원이름("강호동")
                .build();


        // 2. A 객체 '강호동'이  글쓰기 진행
        게시물 B객체 = 게시물.builder()
                .게시물번호(1)
                .게시물제목("강호동이 쓴 글")
                .게시물내용("호동이는 귀여워!")
                .작성한회원( A객체 ) // B객체 게시물을 작성한 회원은 A객체
                .build();
        // 3. .작성한회원( A객체 ) 를 추가한 상태 -> B객체가 A객체를 참조하는 중
        System.out.println("B객체 = " + B객체);

        // 4. 게시물에 댓글을 달기 ( C객체 ) 비회원제라고 가정
        댓글 C객체 = 댓글.builder()
                .댓글번호(1)
                .댓글내용("댓글입니다.")
                .댓글의게시물( B객체 )
                .build();

        System.out.println("C객체 = " + C객체);

        // --------- 양방향 ------------------------
        // 5. 회원이 작성한 글을 보고 싶을 때... [ 내가쓴글 확인하기 ]
        System.out.println("A객체 = " + A객체);
        A객체.get내가쓴글().add( B객체 );

        게시물 D객체 = 게시물.builder()
                .게시물번호(2)
                .게시물제목("강호동이 쓴 글2")
                .게시물내용("호동이 배고파ㅠㅠ")
                .작성한회원( A객체 ) // D객체 게시물을 작성한 회원은 A객체
                .build();
        A객체.get내가쓴글().add( D객체 );
        System.out.println("A객체 = " + A객체);

    }
}
