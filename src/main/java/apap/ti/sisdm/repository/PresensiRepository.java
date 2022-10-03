package apap.ti.sisdm.repository;


import apap.ti.sisdm.model.Presensi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PresensiRepository extends JpaRepository<Presensi, Long> {
    Optional<Presensi> findByIdPresensi(Long idPresensi);
}