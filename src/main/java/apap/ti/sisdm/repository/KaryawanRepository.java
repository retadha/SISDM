package apap.ti.sisdm.repository;

import apap.ti.sisdm.model.Karyawan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KaryawanRepository extends JpaRepository<Karyawan, Long> {
    Optional<Karyawan> findByIdKaryawan(Long idKaryawan);

}