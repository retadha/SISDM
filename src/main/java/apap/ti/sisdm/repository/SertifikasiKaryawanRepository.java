package apap.ti.sisdm.repository;

import apap.ti.sisdm.model.SertifikasiKaryawan;
import apap.ti.sisdm.model.SertifikasiKaryawanId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SertifikasiKaryawanRepository extends JpaRepository<SertifikasiKaryawan, SertifikasiKaryawanId> {
}