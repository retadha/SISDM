package apap.ti.sisdm.controller;

import apap.ti.sisdm.model.Karyawan;
import apap.ti.sisdm.model.Tugas;
import apap.ti.sisdm.model.Tugas;
import apap.ti.sisdm.service.KaryawanService;
import apap.ti.sisdm.service.TugasService;
import apap.ti.sisdm.service.TugasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TugasController {
    @Qualifier("tugasServiceImpl")
    @Autowired
    private TugasService tugasService;

    @Qualifier("karyawanServiceImpl")
    @Autowired
    private KaryawanService karyawanService;

    @GetMapping("/filter-tugas")
    private String fiterTugas(Model model) {
        List<Tugas> listTugas = tugasService.getListTugas();
        List<Karyawan> listKaryawan = karyawanService.getListKaryawan();

        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listTugas", listTugas);
        return "tugas/filter-tugas";
    }

    @PostMapping("/filter-tugas")
    private String fiterTugasSubmitPage(@RequestParam(value = "id-karyawan") String idKaryawan, @RequestParam(value = "status") String status,  Model model) {
        List<Tugas> listTugas = tugasService.getListTugasByIdKaryawanAndStatus(Long.parseLong(idKaryawan), Integer.parseInt(status));
        List<Karyawan> listKaryawan = karyawanService.getListKaryawan();

        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listTugas", listTugas);
        return "tugas/filter-tugas";
    }

    @GetMapping("/tambah-tugas")
    private String addtugasFormPage(Model model) {
        model.addAttribute("tugas", new Tugas());
        return "tugas/form-add-tugas";
    }

    @PostMapping("/tambah-tugas")
    private String addtugasSubmitPage(@ModelAttribute Tugas tugas, Model model) {
        tugasService.addTugas(tugas);
        model.addAttribute("tugas", tugas);
        return "tugas/add-tugas";
    }





}
