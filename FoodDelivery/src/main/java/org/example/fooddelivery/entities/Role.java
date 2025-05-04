package org.example.fooddelivery.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

        client, employee, supplier;

        @Override
        public String getAuthority() {
                return "ROLE_" + name();
        }
}
