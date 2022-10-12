package apap.ti.sisdm.service;

import apap.ti.sisdm.model.Karyawan;
import apap.ti.sisdm.model.Sertifikasi;
import apap.ti.sisdm.model.SertifikasiKaryawan;
import apap.ti.sisdm.repository.KaryawanRepository;
import apap.ti.sisdm.repository.SertifikasiKaryawanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KaryawanServiceImpl implements KaryawanService{
    @Autowired
    KaryawanRepository karyawanDb;

    @Autowired
    SertifikasiKaryawanRepository sertifikasiKaryawanDb;

    @Override
    public List<Karyawan> getListKaryawan() {
        return karyawanDb.findAll();
    }

    @Override
    public Karyawan addKaryawan(Karyawan karyawan) {
        return karyawanDb.save(karyawan);
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

    @Override
    public List<SertifikasiKaryawan> getListSertifikasiById(Karyawan id) {
        return sertifikasiKaryawanDb.findByIdKaryawan(id);
    }

    @Override
    public List<Karyawan> getListKaryawanByIdSertifikasi(Optional<Sertifikasi> id) {
        List<Karyawan> listKaryawan = new ArrayList<>();

        List<SertifikasiKaryawan> listSertifikasiKaryawan = sertifikasiKaryawanDb.findByIdSertifikasi(id);
        for(SertifikasiKaryawan sertifikasiKaryawan: listSertifikasiKaryawan) {
            listKaryawan.add(sertifikasiKaryawan.getIdKaryawan());
        }
        return listKaryawan;
    }

}
