package apap.ti.sisdm.service;

import apap.ti.sisdm.model.Sertifikasi;
import apap.ti.sisdm.model.Sertifikasi;

import apap.ti.sisdm.repository.SertifikasiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SertifikasiServiceImpl implements SertifikasiService{
    @Autowired
    SertifikasiRepository sertifikasiDb;



    @Override
    public List<Sertifikasi> getListSertifikasi() {
        return sertifikasiDb.findAll();
    }

    @Override
    public Optional<Sertifikasi> getSertifikasiById(Long idSertifikasi) {
        return sertifikasiDb.findByIdSertifikasi(idSertifikasi);
    }



}
