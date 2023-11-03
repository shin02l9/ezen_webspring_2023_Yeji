package ezenweb.model.dto;


import ezenweb.model.entity.MemberEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class MemberDto implements UserDetails,OAuth2User {

    private int mno;            // 1. 회원번호
    private String memail;      // 2. 이메일
    private String mpassword;   // 3. 비밀번호
    private String mname;       // 4. 이름
    private String mphone;      // 5. 전화번호
    private String mrole;       // 6. 회원등급 ( 일반회원과 관리자회원 구분하기 )

    private String mpasswordnew;

    private LocalDateTime cdate;
    private LocalDateTime udate;

    // dto -> entity 함수 필요
    public MemberEntity toMemberEntity() {
        return MemberEntity.builder()
                .mno( this.mno )
                .memail( this.memail )
                .mpassword( this.mpassword )
                .mname( this.mname )
                .mphone( this.mphone )
                .mrole( this.mrole )
                .build();
    }

    /* ----------------------------- UserDetails ----------------------------- */
    private Map<String, Object> 소셜회원정보;
    @Override // 회원의 정보
    public Map<String, Object> getAttributes() {return 소셜회원정보;    }
    @Override // 회원의 아이디
    public String getName() {return memail;    }

    /* ----------------------------- UserDetails ----------------------------- */
    private List<GrantedAuthority> 권한목록;

    @Override // 계정 권한
    public Collection<? extends GrantedAuthority> getAuthorities() { return 권한목록;    }

    @Override // 계정 비밀번호
    public String getPassword() {return mpassword;    }

    @Override // 계정 아이디
    public String getUsername() {return memail;    }

    @Override // 계정 만료 여부
    public boolean isAccountNonExpired() {return true;    }

    @Override // 계정 잠금 여부
    public boolean isAccountNonLocked() {return true;    }

    @Override // 계정 증명 여부
    public boolean isCredentialsNonExpired() {return true;    }

    @Override // 계정 활성화 여부
    public boolean isEnabled() {return true;    }
}
