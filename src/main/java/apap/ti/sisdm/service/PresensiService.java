package apap.ti.sisdm.service;


import apap.ti.sisdm.model.Presensi;
import apap.ti.sisdm.model.Tugas;

import java.util.List;

public interface PresensiService {
    List<Presensi> getListPresensi();
    void addPresensi(Presensi presensi);

    Presensi getPresensiById(Long id);

    Presensi updatePresensi(Presensi presensi);

    Presensi deletePresensi(Presensi presensi);

    List<Tugas> getListTugasById(Presensi id);
}
