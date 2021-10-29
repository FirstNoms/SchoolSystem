package com.ileiwe.data.model;


import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data

public class Authority {

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
