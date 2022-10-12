package apap.ti.sisdm.repository;

import apap.ti.sisdm.model.Karyawan;
import apap.ti.sisdm.model.Sertifikasi;
import apap.ti.sisdm.model.SertifikasiKaryawan;
import apap.ti.sisdm.model.SertifikasiKaryawanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SertifikasiKaryawanRepository extends JpaRepository<SertifikasiKaryawan, SertifikasiKaryawanId> {
    List<SertifikasiKaryawan> findByIdKaryawan(Karyawan idKaryawan);

    List<SertifikasiKaryawan> findByIdSertifikasi(Optional<Sertifikasi> idSertifikasi);

    @Transactional
    @Modifying
    @Query("delete from SertifikasiKaryawan s where s.idSertifikasi = ?1 and s.idKaryawan = ?2")
    int deleteByIdSertifikasiAndIdKaryawan(Sertifikasi idSertifikasi, Karyawan idKaryawan);










}