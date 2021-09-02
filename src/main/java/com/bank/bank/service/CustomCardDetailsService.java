package com.bank.bank.service;

import com.bank.bank.data.entity.Card;
import com.bank.bank.data.entity.CustomCardDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomCardDetailsService implements UserDetailsService {
    private final CardService cardService;

    @Override
    public CustomCardDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Card card = cardService.getByNumber(username).orElse(new Card());
        return CustomCardDetails.fromCardToCustomCardDetails(card);
    }
}
