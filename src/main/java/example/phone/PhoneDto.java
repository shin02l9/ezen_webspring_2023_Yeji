package example.phone;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@Builder
public class PhoneDto {
    private int pno;
    private String pname;
    private String pphone;


}
