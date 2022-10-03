package apap.ti.sisdm.service;

import apap.ti.sisdm.model.Karyawan;
import apap.ti.sisdm.repository.KaryawanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KaryawanServiceImpl implements KaryawanService{
    @Autowired
    KaryawanRepository karyawanDb;

    @Override
    public List<Karyawan> getListKaryawan() {
        return karyawanDb.findAll();
    }

    @Override
    public void addKaryawan(Karyawan karyawan) {
        karyawanDb.save(karyawan);
    }

    @Override
    public Karyawan getKaryawanById(Long idKaryawan) {
        Optional<Karyawan> karyawan = karyawanDb.findByIdKaryawan(idKaryawan);
        if (karyawan.isPresent()) {
            return karyawan.get();
        }
        return null;
    }

    @Override
    public Karyawan updateKaryawan(Karyawan karyawan) {
        karyawanDb.save(karyawan);
        return karyawan;
    }

    @Override
    public Karyawan deleteKaryawan(Karyawan karyawan) {
        karyawanDb.delete(karyawan);
        return karyawan;
    }

}
