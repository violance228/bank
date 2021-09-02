package com.bank.bank;

import com.bank.bank.data.entity.CustomCardDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    private SecurityUtils(){}

    public static String getCurrentCardNumber() {
        return ((CustomCardDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
}
