package apap.ti.sisdm.controller;

import apap.ti.sisdm.model.Karyawan;
import apap.ti.sisdm.model.Presensi;
import apap.ti.sisdm.service.KaryawanService;
import apap.ti.sisdm.service.PresensiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PresensiController {
    @Qualifier("presensiServiceImpl")
    @Autowired
    private PresensiService presensiService;

    @Qualifier("karyawanServiceImpl")
    @Autowired
    private KaryawanService karyawanService;

    @GetMapping("/presensi")
    private String listPresensi(Model model) {
        List<Presensi> listPresensi = presensiService.getListPresensi();
        model.addAttribute("listPresensi", listPresensi);
        return "presensi/viewall-presensi";
    }

    @GetMapping("/presensi/tambah")
    private String addPresensiFormPage(Model model) {
        List<Karyawan> karyawanList = karyawanService.getListKaryawan();
        model.addAttribute("presensi", new Presensi());
        model.addAttribute("karyawanList", karyawanList);

        return "presensi/form-add-presensi";
    }
//
    @PostMapping("/presensi/tambah")
    private String addPresensiSubmitPage(@ModelAttribute Presensi presensi, Model model) {
        presensiService.addPresensi(presensi);
        model.addAttribute("presensi", presensi);
        model.addAttribute("karyawan", presensi.getKaryawan());

        return "presensi/add-presensi";
    }


    @GetMapping("presensi/{id}")
    public String viewDetailPresensi(@PathVariable String id, Model model) {
        Presensi presensi = presensiService.getPresensiById(Long.parseLong(id));
        model.addAttribute("presensi", presensi);

        return "presensi/view-presensi";
    }

    @GetMapping("presensi/{id}/ubah")
    public String updatePresensiFormPage(@PathVariable String id, Model model) {
        Presensi presensi = presensiService.getPresensiById(Long.parseLong(id));

        model.addAttribute("presensi", presensi);
        model.addAttribute("karyawan", presensi.getKaryawan());

        return "presensi/form-update-presensi";
    }

    @PostMapping("presensi/{id}/ubah")
    public String updatePresensiSubmitPage(@ModelAttribute Presensi presensi, Model model) {

        Presensi updatedPresensi = presensiService.updatePresensi(presensi);

        model.addAttribute("presensi", updatedPresensi);
        model.addAttribute("karyawan", updatedPresensi.getKaryawan());

        return "presensi/update-presensi";
    }
//
//    @GetMapping("Presensi/{id}/hapus")
//    public String deleteCourse(@PathVariable String id, Model model) {
//        Presensi Presensi = PresensiService.getPresensiById(Long.parseLong(id));
//        PresensiService.deletePresensi(Presensi);
//        model.addAttribute("Presensi", Presensi);
//        return "Presensi/delete-Presensi";
//    }



}
