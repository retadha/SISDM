package apap.ti.sisdm.service;

import apap.ti.sisdm.model.Sertifikasi;
import apap.ti.sisdm.model.SertifikasiKaryawan;
import apap.ti.sisdm.repository.SertifikasiKaryawanRepository;
import apap.ti.sisdm.repository.SertifikasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SertifikasiKaryawanServiceImpl implements SertifikasiKaryawanService{
    @Autowired
    SertifikasiKaryawanRepository sertifikasiKaryawanDb;



    @Override
    public List<SertifikasiKaryawan> getListSertifikasiKaryawan() {
        return sertifikasiKaryawanDb.findAll();
    }





}
