package apap.ti.sisdm.repository;

import apap.ti.sisdm.model.Karyawan;
import apap.ti.sisdm.model.SertifikasiKaryawan;
import apap.ti.sisdm.model.SertifikasiKaryawanId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SertifikasiKaryawanRepository extends JpaRepository<SertifikasiKaryawan, SertifikasiKaryawanId> {
    List<SertifikasiKaryawan> findByIdKaryawan(Karyawan idKaryawan);



}