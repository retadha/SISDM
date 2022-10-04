package apap.ti.sisdm.service;

import apap.ti.sisdm.model.Sertifikasi;
import apap.ti.sisdm.model.Sertifikasi;


import java.util.List;
import java.util.Optional;

public interface SertifikasiService {
    List<Sertifikasi> getListSertifikasi();

    Optional<Sertifikasi> getSertifikasiById(Long idSertifikasi);




}
