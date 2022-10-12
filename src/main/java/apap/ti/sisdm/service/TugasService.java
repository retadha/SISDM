package apap.ti.sisdm.service;

import apap.ti.sisdm.model.Presensi;
import apap.ti.sisdm.model.Tugas;

import java.util.List;

public interface TugasService {
    List<Tugas> getListTugas();

    List<Tugas> getListTugasByIdPresensi(Presensi idPresensi);

    List<Tugas> getListAvailableTugas();
    void addTugas(Tugas tugas);

    Tugas getTugasById(Long id);

    Tugas updateTugas(Tugas tugas);

    Tugas deleteTugas(Tugas tugas);

    List<Tugas> getListTugasByIdKaryawanAndStatus(Long idKaryawan, Integer status);
}
