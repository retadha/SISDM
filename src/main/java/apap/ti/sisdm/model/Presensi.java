package apap.ti.sisdm.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "presensi")
public class Presensi implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presensi", nullable = false)
    private Long idPresensi;

    @Column(name = "status")
    private Integer status;

    @Column(name = "tanggal")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggal;

    @Column(name = "waktu_masuk")
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime waktuMasuk;

    @Column(name = "waktu_keluar")
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime waktuKeluar;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_karyawan")
    private Karyawan karyawan;

    @Transient
    private String namaKaryawan;

    @OneToMany(mappedBy = "idPresensi")
    private Set<Tugas> listTugas = new LinkedHashSet<>();

    @PostLoad
    private void onLoad() {
        namaKaryawan = karyawan.getNamaPanjang();
    };






}