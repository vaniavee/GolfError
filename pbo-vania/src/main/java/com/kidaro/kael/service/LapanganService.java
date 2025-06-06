package com.kidaro.kael.service;

import com.kidaro.kael.model.Lapangan;
import com.kidaro.kael.model.RestTransaction;
import com.kidaro.kael.repository.LapanganRepository;
import com.kidaro.kael.repository.RestTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kidaro.kael.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LapanganService {
    private final LapanganRepository lapanganRepo;
    private final RestTransactionRepository restTransactionRepo;
    private final UserRepository userRepo; // Assuming you have a UserRepository for user-related operations

    public RestTransaction restPokemon(Long lapanganId, int days) {
        Lapangan lapangan = lapanganRepo.findById(lapanganId).orElseThrow();
        lapangan.setResting(true);
        lapangan.setRestStartDate(LocalDate.now());
        lapanganRepo.save(lapangan);

        RestTransaction transaction = new RestTransaction();
        transaction.setLapangan(lapangan);
        transaction.setDaysRested(days);
        transaction.setDate(LocalDate.now());
        transaction.setTotalPrice(days * 10.0); // Example cost per day
        return restTransactionRepo.save(transaction);
    }

    public List<Lapangan> getRestedPokemon() {
        return lapanganRepo.findAllByRestingTrue();
    }

    public List<Lapangan> findUserPokemons(String username) {
        return userRepo.findByUsername(username)
                .map(user -> lapanganRepo.findAllByOwnerId(user.getId()))
                .orElse(List.of());
    }
}