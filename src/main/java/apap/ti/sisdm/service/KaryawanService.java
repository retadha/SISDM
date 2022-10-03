package apap.ti.sisdm.service;

import apap.ti.sisdm.model.Karyawan;


import java.util.List;

public interface KaryawanService {
    List<Karyawan> getListKaryawan();
    void addKaryawan(Karyawan karyawan);

    Karyawan getKaryawanById(Long id);

    Karyawan updateKaryawan(Karyawan karyawan);

    Karyawan deleteKaryawan(Karyawan karyawan);
}
