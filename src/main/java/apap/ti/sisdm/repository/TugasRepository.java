package apap.ti.sisdm.repository;

import apap.ti.sisdm.model.Tugas;
import apap.ti.sisdm.model.Tugas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TugasRepository extends JpaRepository<Tugas, Long> {
    Optional<Tugas> findByIdTugas(Long idTugas);
}