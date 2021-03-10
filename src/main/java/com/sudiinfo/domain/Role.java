package com.sudiinfo.domain;

import org.springframework.security.core.GrantedAuthority;

public enum  Role implements GrantedAuthority  {

    ADMIN, MAIN_ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
