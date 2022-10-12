package apap.ti.sisdm.service;

import apap.ti.sisdm.model.Presensi;
import apap.ti.sisdm.model.Tugas;
import apap.ti.sisdm.repository.TugasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TugasServiceImpl implements TugasService{
    @Autowired
    TugasRepository tugasDb;

    @Override
    public List<Tugas> getListTugas() {
        return tugasDb.findAll();
    }

    @Override
    public List<Tugas> getListAvailableTugas() {
        return tugasDb.findByIdPresensiNull();
    }





    @Override
    public void addTugas(Tugas tugas) {
        tugasDb.save(tugas);
    }

    @Override
    public Tugas getTugasById(Long idTugas) {
        Optional<Tugas> Tugas = tugasDb.findByIdTugas(idTugas);
        if (Tugas.isPresent()) {
            return Tugas.get();
        }
        return null;
    }

    @Override
    public Tugas updateTugas(Tugas tugas) {
        tugasDb.save(tugas);
        return tugas;
    }

    @Override
    public Tugas deleteTugas(Tugas tugas) {
        tugasDb.delete(tugas);
        return tugas;
    }

    @Override
    public List<Tugas> getListTugasByIdKaryawanAndStatus(Long idKaryawan, Integer status) {
        return tugasDb.findByIdKaryawanAndStatus(idKaryawan, status);
    }

    @Override
    public List<Tugas> getListTugasByIdPresensi(Presensi idPresensi) {
        return tugasDb.findByIdPresensi(idPresensi);
    }


}
