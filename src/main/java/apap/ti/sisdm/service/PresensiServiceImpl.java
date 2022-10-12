package apap.ti.sisdm.service;

import apap.ti.sisdm.model.Karyawan;
import apap.ti.sisdm.model.Presensi;
import apap.ti.sisdm.model.Tugas;
import apap.ti.sisdm.repository.PresensiRepository;
import apap.ti.sisdm.repository.TugasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PresensiServiceImpl implements PresensiService{
    @Autowired
    PresensiRepository presensiDb;

    @Autowired
    TugasRepository tugasDb;

    @Override
    public List<Presensi> getListPresensi() {
        return presensiDb.findAll();
    }

    @Override
    public Presensi addPresensi(Presensi presensi) {
        return presensiDb.save(presensi);
    }

    @Override
    public Presensi getPresensiById(Long idPresensi) {
        Optional<Presensi> Presensi = presensiDb.findByIdPresensi(idPresensi);
        if (Presensi.isPresent()) {
            return Presensi.get();
        }
        return null;
    }

    @Override
    public Presensi updatePresensi(Presensi presensi) {
        return presensiDb.save(presensi);

    }

    @Override
    public Presensi deletePresensi(Presensi presensi) {
        presensiDb.delete(presensi);
        return presensi;
    }

    @Override
    public List<Tugas> getListTugasById(Presensi id) {
        return tugasDb.findByIdPresensi(id);
    }

}
