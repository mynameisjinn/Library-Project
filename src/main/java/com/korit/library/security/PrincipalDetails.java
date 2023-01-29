package com.korit.library.security;

import com.korit.library.entity.RoleDtl;
import com.korit.library.entity.RoleMst;
import com.korit.library.entity.UserMst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@AllArgsConstructor
public class PrincipalDetails implements UserDetails {

    @Getter
    private final UserMst user;
    private Map<String, Object> response;


    // 권한을 리스트로 관리하는 부분
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        List<RoleDtl> roleDtlList = user.getRoleDtl();
        for(int i = 0; i < roleDtlList.size(); i++) {
            RoleDtl dtl = roleDtlList.get(i); // 0 = ROLE_USER, 1 = ROLE_ADMIN
            RoleMst roleMst = dtl.getRoleMst();
            String roleName = roleMst.getRoleName();

            GrantedAuthority role = new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return roleName;
                }
            };
            authorities.add(role);
        }

//        user.getRoleDtl().forEach(dtl -> {
//            authorities.add(() -> dtl.getRoleMst().getRoleName());
//        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /*
        계정 만료 여부
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /*
        계정 잠김 여부 : 휴면계정
    */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /*
        비밀번호 만료 여부 :
        일정횟수이상 비밀번호 틀렸을 경우
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /*
        사용자 활성화 여부 :
        회원가입 시 본인 인증을 한경우 or 휴면계정이 풀린 경우 -> true
        인증이 되지않은 경우 -> false
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}