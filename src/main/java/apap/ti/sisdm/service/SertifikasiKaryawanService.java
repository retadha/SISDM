package apap.ti.sisdm.service;

import apap.ti.sisdm.model.Karyawan;
import apap.ti.sisdm.model.Sertifikasi;
import apap.ti.sisdm.model.SertifikasiKaryawan;

import java.util.List;

public interface SertifikasiKaryawanService {
    List<SertifikasiKaryawan> getListSertifikasiKaryawan();

    SertifikasiKaryawan addSertifikasiKaryawan(SertifikasiKaryawan sertifikasiKaryawan);

    void deleteSertifikasiKaryawan(Sertifikasi idSertifikasi, Karyawan idKaryawan);


}
