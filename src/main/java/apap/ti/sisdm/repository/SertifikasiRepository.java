package apap.ti.sisdm.repository;

import apap.ti.sisdm.model.Sertifikasi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SertifikasiRepository extends JpaRepository<Sertifikasi, Long> {
    Optional<Sertifikasi> findByIdSertifikasi(Long idSertifikasi);

}