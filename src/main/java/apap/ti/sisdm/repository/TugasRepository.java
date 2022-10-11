package apap.ti.sisdm.repository;

import apap.ti.sisdm.model.Karyawan;
import apap.ti.sisdm.model.Presensi;
import apap.ti.sisdm.model.Tugas;
import apap.ti.sisdm.model.Tugas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TugasRepository extends JpaRepository<Tugas, Long> {
    Optional<Tugas> findByIdTugas(Long idTugas);

    List<Tugas> findByIdPresensi(Presensi idPresensi);

    @Query("SELECT t FROM Karyawan k LEFT JOIN Presensi p ON k.idKaryawan = p.karyawan.idKaryawan LEFT JOIN Tugas t ON p.idPresensi = t.idPresensi.idPresensi WHERE k.idKaryawan = :idKaryawan AND t.status = :status")
    List<Tugas> findByIdKaryawanAndStatus(Long idKaryawan, Integer status);

    @Query("select t from Tugas t where t.idPresensi is null")
    List<Tugas> findByIdPresensiNull();







}