package apap.ti.sisdm.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SertifikasiKaryawanId implements Serializable {
    private static final long serialVersionUID = 2412433922327294502L;
    @NotNull
    @Column(name = "id_sertifikasi", nullable = false)
    private Long idSertifikasi;

    @NotNull
    @Column(name = "id_karyawan", nullable = false)
    private Long idKaryawan;

    public Long getIdSertifikasi() {
        return idSertifikasi;
    }

    public void setIdSertifikasi(Long idSertifikasi) {
        this.idSertifikasi = idSertifikasi;
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
        SertifikasiKaryawanId entity = (SertifikasiKaryawanId) o;
        return Objects.equals(this.idSertifikasi, entity.idSertifikasi) &&
                Objects.equals(this.idKaryawan, entity.idKaryawan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSertifikasi, idKaryawan);
    }

}