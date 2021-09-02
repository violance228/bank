package com.bank.bank.data.dto;

import com.bank.bank.data.entity.Card;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDto {
    private String number;
    private String pinCode;
    private BigDecimal balance;

    public static Card toCard(CardDto cardDto) {
        Card card = new Card();
        card.setNumber(cardDto.getNumber());
        card.setPinCode(cardDto.getPinCode());
        card.setBalance(BigDecimal.ZERO);
        return card;
    }

    public static CardDto toCardDto(Card card) {
        CardDto cardDto = new CardDto();
        cardDto.setNumber(card.getNumber());
        cardDto.setBalance(card.getBalance());
        return cardDto;
    }
}
