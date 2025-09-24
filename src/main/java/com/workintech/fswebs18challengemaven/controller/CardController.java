package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.repository.CardRepositoryImpl;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workintech")
@Slf4j
public class CardController {

    private final CardRepositoryImpl cardRepository;

    public CardController(CardRepositoryImpl cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping("/cards")
    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    @GetMapping("/cards/byColor/{color}")
    public List<Card> getByColor(@PathVariable String color) {
        return cardRepository.findByColor(color);
    }

    @GetMapping("/cards/byValue/{value}")
    public List<Card> getByValue(@PathVariable Integer value) {
        return cardRepository.findByValue(value);
    }

    @GetMapping("/cards/byType/{type}")
    public List<Card> getByType(@PathVariable String type) {
        return cardRepository.findByType(type);
    }

    @PostMapping("/cards")
    public Card create(@RequestBody Card card) {
        log.info("Creating card");
        CardValidation.validate(card);
        return cardRepository.save(card);
    }

    @PutMapping("/cards")
    public Card update(@RequestBody Card card) {
        log.info("Updating card id: {}", card.getId());
        CardValidation.validate(card);
        return cardRepository.update(card);
    }

    @DeleteMapping("/card/{id}")
    public Card delete(@PathVariable Long id) {
        log.info("Deleting card id: {}", id);
        return cardRepository.remove(id);
    }
}


