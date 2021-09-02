package com.bank.bank;

import java.util.function.Supplier;

public interface ExceptionHelper {
    Supplier<IllegalArgumentException> CARD_NOT_FOUND = () -> new IllegalArgumentException("card not found");
    Supplier<IllegalArgumentException> CARD_NOT_FOUND_OR_NOT_ENOUGH_MONEY = () -> new IllegalArgumentException("card not found / not enough money");
    Supplier<IllegalArgumentException> FORBIDDEN_EDIT_TRANSACTION = () -> new IllegalArgumentException("forbidden edit transaction");
    Supplier<IllegalArgumentException> TRANSACTION_NOT_FOUND = () -> new IllegalArgumentException("transaction not found");
    Supplier<IllegalArgumentException> ENTITY_MISMATCH = () -> new IllegalArgumentException("entity mismatch");

}
