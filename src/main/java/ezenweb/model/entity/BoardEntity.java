package ezenweb.model.entity;

import ezenweb.model.dto.BoardDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

// --------------------- ①. 엔티티를 이용한 테이블을 설계할 때 ---------------------
@Entity // 해당 클래스를 엔티티로 사용
@Table(name = "board") // 테이블명 설정
@DynamicInsert // @ColumnDefault 가 적용 될 수 있도록 해주는 어노테이션
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
@Builder
public class BoardEntity extends BaseTime{
    // 필드 설계
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL 기준으로 AUTO_INCREMENT
    private int bno;

    @Column(name = "btitle", length = 100, nullable = false)
    private String btitle;

    @Column(name = "bcontent", columnDefinition = "Longtext", nullable = true )
    private String bcontent;

    @Column(name = "bview")
    @ColumnDefault("0")
    private int bview;

    // private LocalDateTime bdate; // BaseTime 클래스로부터 상속 받으면 자동이다.
    // BaseTime 클래스가 상속해 주는 필드 : 1. 작성일, 2.수정일

    @Column(name = "bfile",  nullable = true)
    private String bfile;

    @Column(name = "mno")
    private int mno;


    // Entity -> Dto [ 사용 하는 상황 : 엔티티를 저장할 때 ]
    // 1. 전체 출력할 때
    public BoardDto printAllToBoardDto() {
        return BoardDto.builder()
                .bno( this.bno )
                .btitle( this.btitle )
                .bcontent( this.bcontent )
                .bview( this.bview )
                .bfile( this.bfile )
                .cdate( this.cdate )
                .udate( this.udate )
                .mno( this.mno )
                .build();
    };
    // 2. 개별 출력할 때
    public BoardDto printOneToBoardDto() {
        return BoardDto.builder()
                .bno( this.bno )
                .btitle( this.btitle )
                .bcontent( this.bcontent )
                .bview( this.bview )
                .bfile( this.bfile )
                .cdate( this.cdate )
                .udate( this.udate )
                .mno( this.mno )
                .build();
    };


    // 3. 저장할 때
    public BoardDto saveToBoardDto() {
        return BoardDto.builder()
                .bno( this.bno )
                .btitle( this.btitle )
                .bcontent( this.bcontent )
                .bview( this.bview )
                .bfile( this.bfile )
                .cdate( this.getCdate() )
                .udate( this.getUdate() )
                .mno( this.mno )
                .build();
    };


}

/*
    --------------------- ②. MySQL에서 직접 DDL 작성해서 테이블 생성할 때 ---------------------
        create table board (
            bno int(20) NOT NULL AUTO_INCREMENT,
            btitle varchar(100) NOT NULL,
            bcontent Longtext NOT NULL,
            bview int,
            cdate datetime default now()
            udate datetime default now()
            bfile Longtext,
            mno int,
            primary key (bno)
        );
        ----- JPA 가 만들어낸 쿼리문이랑 비교하기
        create table board (
            bno integer not null auto_increment,
            cdate datetime(6),
            udate datetime(6),
            bcontent Longtext,
            bfile varchar(255),
            btitle varchar(100) not null,
            bview integer,
            mno integer,
            primary key (bno)
        )

*/