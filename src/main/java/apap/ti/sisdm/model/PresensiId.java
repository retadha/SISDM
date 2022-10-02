package apap.ti.sisdm.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class PresensiId implements Serializable {
    private static final long serialVersionUID = 2833597170553209155L;
    @NotNull
    @Column(name = "id_presensi", nullable = false)
    private Long idPresensi;

    @NotNull
    @Column(name = "id_karyawan", nullable = false)
    private Long idKaryawan;

    public Long getIdPresensi() {
        return idPresensi;
    }

    public void setIdPresensi(Long idPresensi) {
        this.idPresensi = idPresensi;
    }

    public Long getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(Long idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PresensiId entity = (PresensiId) o;
        return Objects.equals(this.idKaryawan, entity.idKaryawan) &&
                Objects.equals(this.idPresensi, entity.idPresensi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKaryawan, idPresensi);
    }

}