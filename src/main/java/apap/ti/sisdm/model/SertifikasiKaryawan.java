package apap.ti.sisdm.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalPengambilan;

    @Size(max = 14)
    @Column(name = "no_sertifikasi", length = 14)
    private String noSertifikasi;




}