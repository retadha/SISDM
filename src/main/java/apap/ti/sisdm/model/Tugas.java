package apap.ti.sisdm.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tugas")
public class Tugas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tugas", nullable = false)
    private Long idTugas;

    @Size(max = 255)
    @NotNull
    @Column(name = "nama", nullable = false)
    private String nama;

    @Size(max = 255)
    @NotNull
    @Column(name = "deskripsi", nullable = false)
    private String deskripsi;

    @NotNull
    @Column(name = "story_point", nullable = false)
    private Integer storyPoint;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_presensi", nullable = false, referencedColumnName = "id_presensi")
    private Presensi idPresensi;

}