package com.kidaro.kael;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kidaro.kael.model.Item;
import com.kidaro.kael.model.Lapangan;
import com.kidaro.kael.model.Role;
import com.kidaro.kael.model.User;
import com.kidaro.kael.repository.ItemRepository;
import com.kidaro.kael.repository.LapanganRepository;
import com.kidaro.kael.repository.UserRepository;

@SpringBootApplication
public class KaelApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaelApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepo, ItemRepository itemRepo, LapanganRepository lapanganRepo) {
        return args -> {
            // Create default users if none exist
            if (userRepo.count() == 0) {
                User trainer = User.builder()
                    .username("vania")
                    .password("kopicoklat")
                    .role(Role.USER)
                    .build();
                userRepo.save(trainer);

                User nurse = User.builder()
                    .username("joy")
                    .password("chansey123")
                    .role(Role.ADMIN)
                    .build();   
                userRepo.save(nurse);
            }

            // Create default items if none exist
            if (itemRepo.count() == 0) {
                Item[] items = {
                    Item.builder().name("Potion").price(300.0).stockQuantity(50).build(),
                    Item.builder().name("Super Potion").price(700.0).stockQuantity(30).build(),
                    Item.builder().name("Hyper Potion").price(1200.0).stockQuantity(20).build(),
                    Item.builder().name("Max Potion").price(2500.0).stockQuantity(10).build(),
                    Item.builder().name("Revive").price(1500.0).stockQuantity(25).build(),
                    Item.builder().name("Max Revive").price(3000.0).stockQuantity(15).build()
                };
                
                for (Item item : items) {
                    itemRepo.save(item);
                }
            }

            // Create default Pokemon if none exist
            if (lapanganRepo.count() == 0) {
                User trainer = userRepo.findByUsername("ash").orElse(null);
                if (trainer != null) {
                    Lapangan[] lapangans = {
                        Lapangan.builder()
                            .name("Pikachu")
                            .owner(trainer)
                            .resting(false)
                            .build(),
                        Lapangan.builder()
                            .name("Charizard")
                            .owner(trainer)
                            .resting(false)
                            .build(),
                        Lapangan.builder()
                            .name("Bulbasaur")
                            .owner(trainer)
                            .resting(false)
                            .build()
                    };

                    for (Lapangan lapangan : lapangans) {
                        lapanganRepo.save(lapangan);
                    }
                }
            }
        };
    }
}
