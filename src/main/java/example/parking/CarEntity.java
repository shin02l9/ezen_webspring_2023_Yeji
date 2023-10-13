package example.parking;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int cno;
    private String cnum;
    private String ctype;
    private LocalDateTime ctime;
    
    // 엔티티를 dto로 변환해주는 함수
    public CarDto toDto(){
        return new CarDto(this.cno,this.cnum,this.ctype,this.ctime);
    }

}
