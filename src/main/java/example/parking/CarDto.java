package example.parking;

import lombok.*;

import java.time.LocalDateTime;
@Builder
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarDto {
    private  int cno;
    private String cnum;
    private String ctype;
    private LocalDateTime ctime;

    // dto를 엔티티로 변환해주는 함수
    public CarEntity toEntity(){
        return CarEntity.builder().cnum(this.cnum).ctype(this.ctype).ctime(this.ctime).build();
    }

}
