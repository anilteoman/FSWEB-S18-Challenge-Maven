package com.workintech.fswebs18challengemaven.util;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Color;
import com.workintech.fswebs18challengemaven.entity.Type;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import org.springframework.http.HttpStatus;

public class CardValidation {
    public static void validate(Card card) {
        if (card == null) {
            throw new CardException("Card is null", HttpStatus.BAD_REQUEST);
        }

        Type type = card.getType();
        Integer value = card.getValue();
        Color color = card.getColor();

        if (type == Type.JOKER) {
            if (value != null || color != null) {
                throw new CardException("JOKER must have null value and color", HttpStatus.BAD_REQUEST);
            }
            return;
        }

        if (type != null && value != null) {
            throw new CardException("Card cannot have both type and value", HttpStatus.BAD_REQUEST);
        }
        if (type == null && value == null) {
            throw new CardException("Card must have either type or value", HttpStatus.BAD_REQUEST);
        }
        if (value != null && (value < 1 || value > 13)) {
            throw new CardException("Value must be between 1 and 13", HttpStatus.BAD_REQUEST);
        }
        if (type != null && color == null) {
            throw new CardException("Non-joker typed cards must have color", HttpStatus.BAD_REQUEST);
        }
    }
}


