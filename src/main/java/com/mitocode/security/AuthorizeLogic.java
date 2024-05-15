package com.mitocode.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class AuthorizeLogic {
    public boolean hasAccess(String path) {
        boolean result = false;

        String Roles = switch (path){
            case "findAll" -> "ADMIN";
            case "findById" -> "USER,DBA";
            default -> "ROOT";
        };
        List<String> rolesM = Arrays.stream(Roles.split(",")).toList();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("User: {}", auth.getName());

        for(GrantedAuthority ga : auth.getAuthorities()){
            String rolUser = ga.getAuthority();
            log.info("Role: {}", rolUser);
            result = rolesM.contains(rolUser);
            if (result) break;
        }
        return result;
    }
}
