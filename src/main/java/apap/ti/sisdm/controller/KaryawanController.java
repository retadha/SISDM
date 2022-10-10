package apap.ti.sisdm.controller;

import apap.ti.sisdm.model.Karyawan;
import apap.ti.sisdm.model.Sertifikasi;
import apap.ti.sisdm.model.SertifikasiKaryawan;
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
        List<SertifikasiKaryawan> listSertifikasiKaryawan = sertifikasiKaryawanService.getListSertifikasiKaryawan();
        List<SertifikasiKaryawan> listSertifikasiKaryawanNew = new ArrayList<>();

        karyawan.setListSertifikasiKaryawan(listSertifikasiKaryawanNew);
        karyawan.getListSertifikasiKaryawan().add(new SertifikasiKaryawan());
        
        model.addAttribute("karyawan", karyawan);
        model.addAttribute("listSertifikasiKaryawanExisting", listSertifikasiKaryawan);
        return "karyawan/form-add-karyawan";
    }

    @PostMapping(value = "/karyawan/tambah", params = {"addRow"})
    private String addRowSertifikasiKaryawanMultiple(@ModelAttribute Karyawan karyawan, Model model) {
        if (karyawan.getListSertifikasiKaryawan() == null || karyawan.getListSertifikasiKaryawan().size() == 0) {
            karyawan.setListSertifikasiKaryawan(new ArrayList<>());
        }

        karyawan.getListSertifikasiKaryawan().add(new SertifikasiKaryawan());
        List<SertifikasiKaryawan> listSertifikasiKaryawan = sertifikasiKaryawanService.getListSertifikasiKaryawan();

        model.addAttribute("karyawan", karyawan);
        model.addAttribute("listSertifikasiKaryawanExisting", listSertifikasiKaryawan);


        return "karyawan/form-add-karyawan";
    }

    @PostMapping(value = "/karyawan/tambah", params = {"deleteRow"})
    private String deleteRowSertifikasiKaryawanMultiple(@ModelAttribute Karyawan karyawan, @RequestParam("deleteRow") Integer row, Model model) {
        final Integer rowId = Integer.valueOf(row);
        karyawan.getListSertifikasiKaryawan().remove(rowId.intValue());

        List<SertifikasiKaryawan> listSertifikasiKaryawan = sertifikasiKaryawanService.getListSertifikasiKaryawan();

        model.addAttribute("karyawan", karyawan);
        model.addAttribute("listSertifikasiKaryawanExisting", listSertifikasiKaryawan);

        return "karyawan/form-add-karyawan";
    }

    @PostMapping("/karyawan/tambah")
    private String addKaryawanSubmitPage(@ModelAttribute Karyawan karyawan, Model model) {
        karyawanService.addKaryawan(karyawan);
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

    @GetMapping("karyawan/{id}/ubah")
    public String updateKaryawanFormPage(@PathVariable String id, Model model) {
        Karyawan karyawan = karyawanService.getKaryawanById(Long.parseLong(id));
        model.addAttribute("karyawan", karyawan);

        return "karyawan/form-update-karyawan";
    }

    @PostMapping("karyawan/{id}/ubah")
    public String updateKaryawanSubmitPage(@ModelAttribute Karyawan karyawan, Model model) {
        Karyawan updatedKaryawan = karyawanService.updateKaryawan(karyawan);
        model.addAttribute("karyawan", karyawan);

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
