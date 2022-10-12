package apap.ti.sisdm.controller;

import apap.ti.sisdm.model.Karyawan;
import apap.ti.sisdm.model.Sertifikasi;
import apap.ti.sisdm.model.SertifikasiKaryawan;
import apap.ti.sisdm.model.SertifikasiKaryawanId;
import apap.ti.sisdm.service.KaryawanService;
import apap.ti.sisdm.service.SertifikasiKaryawanService;
import apap.ti.sisdm.service.SertifikasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class KaryawanController {
    @Qualifier("karyawanServiceImpl")
    @Autowired
    private KaryawanService karyawanService;

    @Qualifier("sertifikasiServiceImpl")
    @Autowired
    private SertifikasiService sertifikasiService;

    @Qualifier("sertifikasiKaryawanServiceImpl")
    @Autowired
    private SertifikasiKaryawanService sertifikasiKaryawanService;

    @GetMapping("/karyawan")
    private String listKaryawan(Model model) {
        List<Karyawan> listKaryawan = karyawanService.getListKaryawan();
        model.addAttribute("listKaryawan", listKaryawan);
        return "karyawan/viewall-karyawan";
    }

//    @GetMapping("/karyawan/tambah")
//    private String addKaryawanFormPage(Model model) {
//        model.addAttribute("karyawan", new Karyawan());
//        return "karyawan/form-add-karyawan";
//    }
    @GetMapping("/karyawan/tambah")
    private String addKaryawanFormPage(Model model) {
        Karyawan karyawan = new Karyawan();
//        List<SertifikasiKaryawan> listSertifikasiKaryawan = sertifikasiKaryawanService.getListSertifikasiKaryawan();
        List<SertifikasiKaryawan> listSertifikasiKaryawanNew = new ArrayList<>();

        karyawan.setListSertifikasiKaryawan(listSertifikasiKaryawanNew);

        SertifikasiKaryawan sertifikasiKaryawan = new SertifikasiKaryawan();
        SertifikasiKaryawanId sertifikasiKaryawanId = new SertifikasiKaryawanId();
        sertifikasiKaryawan.setId(sertifikasiKaryawanId);

        karyawan.getListSertifikasiKaryawan().add(sertifikasiKaryawan);
        List<Sertifikasi> listSertifikasi = sertifikasiService.getListSertifikasi();

        model.addAttribute("listSertifikasi", listSertifikasi);
        model.addAttribute("karyawan", karyawan);
//        model.addAttribute("listSertifikasiKaryawanExisting", listSertifikasiKaryawan);
        return "karyawan/form-add-karyawan";
    }

    @PostMapping(value = "/karyawan/tambah", params = {"addRow"})
    private String addRowSertifikasiKaryawanMultiple(@ModelAttribute Karyawan karyawan, Model model) {
        if (karyawan.getListSertifikasiKaryawan() == null || karyawan.getListSertifikasiKaryawan().size() == 0) {
            karyawan.setListSertifikasiKaryawan(new ArrayList<>());
        }

        SertifikasiKaryawan sertifikasiKaryawan = new SertifikasiKaryawan();
        SertifikasiKaryawanId sertifikasiKaryawanId = new SertifikasiKaryawanId();
        sertifikasiKaryawan.setId(sertifikasiKaryawanId);

        karyawan.getListSertifikasiKaryawan().add(sertifikasiKaryawan);

//        List<SertifikasiKaryawan> listSertifikasiKaryawan = sertifikasiKaryawanService.getListSertifikasiKaryawan();
        List<Sertifikasi> listSertifikasi = sertifikasiService.getListSertifikasi();

        model.addAttribute("listSertifikasi", listSertifikasi);
        model.addAttribute("karyawan", karyawan);
//        model.addAttribute("listSertifikasiKaryawanExisting", listSertifikasiKaryawan);


        return "karyawan/form-add-karyawan";
    }

    @PostMapping(value = "/karyawan/tambah", params = {"deleteRow"})
    private String deleteRowSertifikasiKaryawanMultiple(@ModelAttribute Karyawan karyawan, @RequestParam("deleteRow") Integer row, Model model) {
        final Integer rowId = Integer.valueOf(row);
        karyawan.getListSertifikasiKaryawan().remove(rowId.intValue());

//        List<SertifikasiKaryawan> listSertifikasiKaryawan = sertifikasiKaryawanService.getListSertifikasiKaryawan();
        List<Sertifikasi> listSertifikasi = sertifikasiService.getListSertifikasi();

        model.addAttribute("listSertifikasi", listSertifikasi);
        model.addAttribute("karyawan", karyawan);
//        model.addAttribute("listSertifikasiKaryawanExisting", listSertifikasiKaryawan);

        return "karyawan/form-add-karyawan";
    }

    @PostMapping(value = "/karyawan/tambah", params = {"save"})
    private String addKaryawanSubmitPage(@ModelAttribute Karyawan karyawan, Model model) {
        if (karyawan.getListSertifikasiKaryawan() == null) {
            karyawan.setListSertifikasiKaryawan(new ArrayList<>());
        }
        Karyawan savedKaryawan = karyawanService.addKaryawan(karyawan);
        Long idKaryawan = savedKaryawan.getIdKaryawan();

        for (SertifikasiKaryawan sertifikasiKaryawan: karyawan.getListSertifikasiKaryawan()) {

            sertifikasiKaryawan.getId().setIdKaryawan(idKaryawan);

            sertifikasiKaryawan.setIdKaryawan(savedKaryawan);
            Long idSertifikasi = sertifikasiKaryawan.getId().getIdSertifikasi();
            Optional<Sertifikasi> sertifikasi = sertifikasiService.getSertifikasiById(idSertifikasi);
            if (sertifikasi.isPresent()) {
                sertifikasiKaryawan.setIdSertifikasi(sertifikasi.get());
            }

            sertifikasiKaryawanService.addSertifikasiKaryawan(sertifikasiKaryawan);
        }

        savedKaryawan.setListSertifikasiKaryawan(karyawan.getListSertifikasiKaryawan());
        model.addAttribute("karyawan", karyawan);
        return "karyawan/add-karyawan";
    }


    @GetMapping("karyawan/{id}")
    public String viewDetailKaryawan(@PathVariable String id, Model model) {
        Karyawan karyawan = karyawanService.getKaryawanById(Long.parseLong(id));
        List<SertifikasiKaryawan> listSertifikasiKaryawan = karyawanService.getListSertifikasiById(karyawan);

        model.addAttribute("karyawan", karyawan);
        model.addAttribute("listSertifikasiKaryawan", listSertifikasiKaryawan);
        return "karyawan/view-karyawan";
    }

//    @GetMapping("/karyawan/tambah")
//    private String addKaryawanFormPage(Model model) {
//        Karyawan karyawan = new Karyawan();
//        List<SertifikasiKaryawan> listSertifikasiKaryawan = sertifikasiKaryawanService.getListSertifikasiKaryawan();
//        List<SertifikasiKaryawan> listSertifikasiKaryawanNew = new ArrayList<>();
//
//        karyawan.setListSertifikasiKaryawan(listSertifikasiKaryawanNew);
//
//        SertifikasiKaryawan sertifikasiKaryawan = new SertifikasiKaryawan();
//        SertifikasiKaryawanId sertifikasiKaryawanId = new SertifikasiKaryawanId();
//        sertifikasiKaryawan.setId(sertifikasiKaryawanId);
//
//        karyawan.getListSertifikasiKaryawan().add(sertifikasiKaryawan);
//        List<Sertifikasi> listSertifikasi = sertifikasiService.getListSertifikasi();
//
//        model.addAttribute("listSertifikasi", listSertifikasi);
//        model.addAttribute("karyawan", karyawan);
//        model.addAttribute("listSertifikasiKaryawanExisting", listSertifikasiKaryawan);
//        return "karyawan/form-add-karyawan";
//    }

    @GetMapping("karyawan/{id}/ubah")
    public String updateKaryawanFormPage(@PathVariable String id, Model model) {
        Karyawan karyawan = karyawanService.getKaryawanById(Long.parseLong(id));

        List<Sertifikasi> listSertifikasi = sertifikasiService.getListSertifikasi();
//
        model.addAttribute("listSertifikasi", listSertifikasi);
        model.addAttribute("karyawan", karyawan);
//

        model.addAttribute("karyawan", karyawan);
        return "karyawan/form-update-karyawan";
    }

    @PostMapping(value = "karyawan/{id}/ubah", params = {"addRow"})
    private String addRowUpdateSertifikasiKaryawanMultiple(@ModelAttribute Karyawan karyawan, Model model) {

        if (karyawan.getListSertifikasiKaryawan() == null || karyawan.getListSertifikasiKaryawan().size() == 0) {
            karyawan.setListSertifikasiKaryawan(new ArrayList<>());

        }

        SertifikasiKaryawan sertifikasiKaryawan = new SertifikasiKaryawan();
        SertifikasiKaryawanId sertifikasiKaryawanId = new SertifikasiKaryawanId();
        sertifikasiKaryawan.setId(sertifikasiKaryawanId);

        karyawan.getListSertifikasiKaryawan().add(sertifikasiKaryawan);

        List<Sertifikasi> listSertifikasi = sertifikasiService.getListSertifikasi();

        model.addAttribute("listSertifikasi", listSertifikasi);
        model.addAttribute("karyawan", karyawan);


        return "karyawan/form-update-karyawan";
    }

    ArrayList<SertifikasiKaryawan> removedSertifikasiList = new ArrayList<>();
    @PostMapping(value = "karyawan/{id}/ubah", params = {"deleteRow"})
    private String deleteRowUpdateSertifikasiKaryawanMultiple(@ModelAttribute Karyawan karyawan, @RequestParam("deleteRow") Integer row, Model model) {
        final Integer rowId = Integer.valueOf(row);

        SertifikasiKaryawan toBeRemoved = karyawan.getListSertifikasiKaryawan().get(rowId);
        removedSertifikasiList.add(toBeRemoved);


        karyawan.getListSertifikasiKaryawan().remove(rowId.intValue());



        List<Sertifikasi> listSertifikasi = sertifikasiService.getListSertifikasi();

        model.addAttribute("listSertifikasi", listSertifikasi);
        model.addAttribute("karyawan", karyawan);

        return "karyawan/form-update-karyawan";
    }

    @PostMapping(value = "karyawan/{id}/ubah", params = {"save"})
    public String updateKaryawanSubmitPage(@ModelAttribute Karyawan karyawan, Model model) {
        if (karyawan.getListSertifikasiKaryawan() == null || karyawan.getListSertifikasiKaryawan().size() == 0) {
            karyawan.setListSertifikasiKaryawan(new ArrayList<>());

        }

        Long idKaryawan = karyawan.getIdKaryawan();
        Karyawan searchedKaryawan = karyawanService.getKaryawanById(idKaryawan);

        searchedKaryawan.setNamaDepan(karyawan.getNamaDepan());
        searchedKaryawan.setNamaBelakang(karyawan.getNamaBelakang());
        searchedKaryawan.setTanggalLahir(karyawan.getTanggalLahir());
        searchedKaryawan.setJenisKelamin(karyawan.getJenisKelamin());
        searchedKaryawan.setEmail(karyawan.getEmail());


        for (SertifikasiKaryawan sertifikasiKaryawan: karyawan.getListSertifikasiKaryawan()) {

            sertifikasiKaryawan.getId().setIdKaryawan(idKaryawan);
            sertifikasiKaryawan.setIdKaryawan(searchedKaryawan);

            Long idSertifikasi = sertifikasiKaryawan.getId().getIdSertifikasi();
            Optional<Sertifikasi> sertifikasi = sertifikasiService.getSertifikasiById(idSertifikasi);

            if (sertifikasi.isPresent()) {
                sertifikasiKaryawan.setIdSertifikasi(sertifikasi.get());
            }
            sertifikasiKaryawanService.deleteSertifikasiKaryawan(sertifikasi.get(), searchedKaryawan);
            sertifikasiKaryawanService.addSertifikasiKaryawan(sertifikasiKaryawan);

        }

        for (SertifikasiKaryawan sertifikasiKaryawan: removedSertifikasiList) {
            Long idSertifikasi = sertifikasiKaryawan.getId().getIdSertifikasi();
            Optional<Sertifikasi> sertifikasi = sertifikasiService.getSertifikasiById(idSertifikasi);

            sertifikasiKaryawanService.deleteSertifikasiKaryawan(sertifikasi.get(), searchedKaryawan);
        }
        removedSertifikasiList.clear();

        searchedKaryawan.setListSertifikasiKaryawan(karyawan.getListSertifikasiKaryawan());
        Karyawan updatedKaryawan = karyawanService.updateKaryawan(searchedKaryawan);



//        Karyawan updatedKaryawan = karyawanService.updateKaryawan(karyawan);
        model.addAttribute("karyawan", updatedKaryawan);

        return "karyawan/update-karyawan";
    }

    @GetMapping("karyawan/{id}/hapus")
    public String deleteKaryawan(@PathVariable String id, Model model) {
        Karyawan karyawan = karyawanService.getKaryawanById(Long.parseLong(id));
        karyawanService.deleteKaryawan(karyawan);
        model.addAttribute("karyawan", karyawan);
        return "karyawan/delete-karyawan";
    }

    @GetMapping("/filter-sertifikasi")
    private String filterKaryawan(Model model) {
        List<Karyawan> listKaryawan = karyawanService.getListKaryawan();
        List<Sertifikasi> listSertifikasi = sertifikasiService.getListSertifikasi();

        model.addAttribute("listSertifikasi", listSertifikasi);
        model.addAttribute("sertifikasi", new Sertifikasi());
        model.addAttribute("listKaryawan", listKaryawan);
        return "karyawan/filter-karyawan";
    }

    @PostMapping("/filter-sertifikasi")
    private String filterKaryawanSubmitPage(@RequestParam(value = "id-sertifikasi") String idSertifikasi, Model model) {
        List<Karyawan> listKaryawan;
        Optional<Sertifikasi> sertifikasi = sertifikasiService.getSertifikasiById(Long.parseLong(idSertifikasi));

        listKaryawan = karyawanService.getListKaryawanByIdSertifikasi(sertifikasi);
        List<Sertifikasi> listSertifikasi = sertifikasiService.getListSertifikasi();

        model.addAttribute("listSertifikasi", listSertifikasi);
        model.addAttribute("sertifikasi", new Sertifikasi());
        model.addAttribute("listKaryawan", listKaryawan);
        return "karyawan/filter-karyawan";
    }





}
