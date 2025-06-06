package com.kidaro.kael.repository;

import com.kidaro.kael.model.Lapangan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LapanganRepository extends JpaRepository<Lapangan, Long> {
    List<Lapangan> findAllByRestingTrue();
    List<Lapangan> findAllByOwnerId(Long ownerId); // Assuming you have a userId field in Pokemon entity
}
