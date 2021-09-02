package com.bank.bank.rest;

import com.bank.bank.config.jwt.JwtProvider;
import com.bank.bank.data.dto.CardDto;
import com.bank.bank.data.entity.Card;
import com.bank.bank.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static com.bank.bank.ExceptionHelper.ENTITY_MISMATCH;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final CardService cardService;
    private final JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<CardDto> registerUser(@RequestBody CardDto cardDto) {
        Card saveEntity = cardService.save(Optional.of(cardDto).map(CardDto::toCard)
                .orElseThrow(ENTITY_MISMATCH));
        return ResponseEntity.ok(CardDto.toCardDto(saveEntity));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> auth(@RequestBody CardDto cardDto) {
        Card userEntity = cardService.getByNumberAndPinCode(cardDto.getNumber(), cardDto.getPinCode());
        String token = jwtProvider.generateToken(userEntity.getNumber());
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}
