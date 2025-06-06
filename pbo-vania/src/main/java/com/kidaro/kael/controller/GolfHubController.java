package com.kidaro.kael.controller;

import com.kidaro.kael.model.Lapangan;
import com.kidaro.kael.model.RestTransaction;
import com.kidaro.kael.model.ItemPurchaseTransaction;
import com.kidaro.kael.model.Item;
import com.kidaro.kael.service.LapanganService;
import com.kidaro.kael.service.TransactionService;
import com.kidaro.kael.service.ItemService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pokemart")
public class GolfHubController {
    private final ItemService itemService;
    private final LapanganService lapanganService;
    private final TransactionService transactionService;

    @GetMapping("/items")
    public List<Item> getItems() {
        return itemService.getAll();
    }

    @PostMapping("/restock/{id}")
    public Item restock(@PathVariable Long id, @RequestParam int qty, @RequestParam double price) {
        return itemService.restock(id, qty, price);
    }

    @PostMapping("/rest")
    public RestTransaction restPokemon(@RequestParam Long lapanganId, @RequestParam int days) {
        return lapanganService.restPokemon(lapanganId, days);
    }

    @GetMapping("/rested-pokemon")
    public List<Lapangan> getRestedPokemon() {
        return lapanganService.getRestedPokemon();
    }

    @PostMapping("/buy-items")
    public ItemPurchaseTransaction buyItems(@RequestBody Map<String, Object> payload) {
        String username = (String) payload.get("username");
        Map<String, Integer> itemsRaw = (Map<String, Integer>) payload.get("items");
        Map<Long, Integer> items = new HashMap<>();
        for (Map.Entry<String, Integer> entry : itemsRaw.entrySet()) {
            items.put(Long.parseLong(entry.getKey()), entry.getValue());
        }
        return transactionService.purchaseItems(username, items);
    }

    @GetMapping("/receipt/{transactionId}")
    public String getReceipt(@PathVariable Long transactionId) {
        return transactionService.generateReceipt(transactionId);
    }

    @GetMapping("/all-transactions")
    public List<ItemPurchaseTransaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/my-pokemon")
    public List<Lapangan> getMyPokemon(@RequestParam String username) {
        return lapanganService.findUserPokemons(username);
    }

    @GetMapping("/my-transactions")
    public List<ItemPurchaseTransaction> getMyTransactions(@RequestParam String username) {
        return transactionService.getTransactionsByUsername(username);
    }


}