package apap.ti.sisdm.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
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
    private LocalDate tanggal;

    @Column(name = "waktu_masuk")
    private Instant waktuMasuk;

    @Column(name = "waktu_keluar")
    private Instant waktuKeluar;




}