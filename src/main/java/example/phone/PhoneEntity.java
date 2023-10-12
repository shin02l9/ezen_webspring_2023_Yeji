package example.phone;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneEntity {

    @Id // PK로 선정 한다는 뜻
    @GeneratedValue( strategy = GenerationType.IDENTITY) // auto-increment 로 사용
    private int pno;

    private String pname;

    private String pphone;

}
