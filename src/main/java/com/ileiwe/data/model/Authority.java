package com.ileiwe.data.model;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
//@Override
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Role authority;

    public Authority(Role role){
        this.authority = role;
    }

    public Authority() {

    }
}
