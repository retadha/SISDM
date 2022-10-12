package apap.ti.sisdm.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "karyawan")
public class Karyawan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_karyawan", nullable = false)
    private Long idKaryawan;

    @Size(max = 255)
    @NotNull
    @Column(name = "nama_depan", nullable = false)
    private String namaDepan;

    @Size(max = 255)
    @NotNull
    @Column(name = "nama_belakang", nullable = false)
    private String namaBelakang;

    @NotNull
    @Column(name = "jenis_kelamin", nullable = false)
    private Integer jenisKelamin;


    @NotNull
    @Column(name = "tanggal_lahir", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalLahir;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Transient
    private String namaPanjang;

    @OneToMany(mappedBy = "karyawan", cascade = CascadeType.REMOVE)
    private List<Presensi> listPresensi;

    @OneToMany(mappedBy = "idKaryawan", cascade = CascadeType.REMOVE)
    private List<SertifikasiKaryawan> listSertifikasiKaryawan;

    @PostLoad
    private void onLoad() {
        namaPanjang = namaDepan + ' ' + namaBelakang;
    };







}