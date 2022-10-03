package apap.ti.sisdm.service;

import apap.ti.sisdm.model.Karyawan;
import apap.ti.sisdm.model.Presensi;
import apap.ti.sisdm.repository.PresensiRepository;
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

    @Override
    public List<Presensi> getListPresensi() {
        return presensiDb.findAll();
    }

    @Override
    public void addPresensi(Presensi presensi) {
        presensiDb.save(presensi);
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
        presensiDb.save(presensi);
        return presensi;
    }

    @Override
    public Presensi deletePresensi(Presensi presensi) {
        presensiDb.delete(presensi);
        return presensi;
    }


}
