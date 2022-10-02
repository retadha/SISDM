package apap.ti.sisdm.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sertifikasi_karyawan")
public class SertifikasiKaryawan {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private SertifikasiKaryawanId id;

    @MapsId("idSertifikasi")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_sertifikasi", nullable = false)
    private Sertifikasi idSertifikasi;

    @MapsId("idKaryawan")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_karyawan", nullable = false)
    private Karyawan idKaryawan;

    @Column(name = "tanggal_pengambilan")
    private LocalDate tanggalPengambilan;

    @Size(max = 14)
    @Column(name = "no_sertifikasi", length = 14)
    private String noSertifikasi;

    public SertifikasiKaryawanId getId() {
        return id;
    }

    public void setId(SertifikasiKaryawanId id) {
        this.id = id;
    }

    public Sertifikasi getIdSertifikasi() {
        return idSertifikasi;
    }

    public void setIdSertifikasi(Sertifikasi idSertifikasi) {
        this.idSertifikasi = idSertifikasi;
    }

    public Karyawan getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(Karyawan idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public LocalDate getTanggalPengambilan() {
        return tanggalPengambilan;
    }

    public void setTanggalPengambilan(LocalDate tanggalPengambilan) {
        this.tanggalPengambilan = tanggalPengambilan;
    }

    public String getNoSertifikasi() {
        return noSertifikasi;
    }

    public void setNoSertifikasi(String noSertifikasi) {
        this.noSertifikasi = noSertifikasi;
    }

}