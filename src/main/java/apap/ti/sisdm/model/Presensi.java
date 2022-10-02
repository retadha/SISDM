package apap.ti.sisdm.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "presensi")
public class Presensi {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private PresensiId id;

    @MapsId("idKaryawan")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_karyawan", nullable = false)
    private Karyawan idKaryawan;

    @Size(max = 255)
    @Column(name = "status")
    private String status;

    @Column(name = "tanggal")
    private LocalDate tanggal;

    @Column(name = "waktu_masuk")
    private Instant waktuMasuk;

    @Column(name = "waktu_keluar")
    private Instant waktuKeluar;

    public PresensiId getId() {
        return id;
    }

    public void setId(PresensiId id) {
        this.id = id;
    }

    public Karyawan getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(Karyawan idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public Instant getWaktuMasuk() {
        return waktuMasuk;
    }

    public void setWaktuMasuk(Instant waktuMasuk) {
        this.waktuMasuk = waktuMasuk;
    }

    public Instant getWaktuKeluar() {
        return waktuKeluar;
    }

    public void setWaktuKeluar(Instant waktuKeluar) {
        this.waktuKeluar = waktuKeluar;
    }

}