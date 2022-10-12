package apap.ti.sisdm.service;

import apap.ti.sisdm.model.Karyawan;
import apap.ti.sisdm.model.Sertifikasi;
import apap.ti.sisdm.model.SertifikasiKaryawan;


import java.util.List;
import java.util.Optional;

public interface KaryawanService {
    List<Karyawan> getListKaryawan();
    Karyawan addKaryawan(Karyawan karyawan);

    Karyawan getKaryawanById(Long id);

    Karyawan updateKaryawan(Karyawan karyawan);

    Karyawan deleteKaryawan(Karyawan karyawan);

    List<SertifikasiKaryawan> getListSertifikasiById(Karyawan id);
    List<Karyawan> getListKaryawanByIdSertifikasi(Optional<Sertifikasi> id);

}
