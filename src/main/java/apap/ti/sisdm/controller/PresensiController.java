package apap.ti.sisdm.controller;

import apap.ti.sisdm.model.Karyawan;
import apap.ti.sisdm.model.Presensi;
import apap.ti.sisdm.model.Tugas;
import apap.ti.sisdm.service.KaryawanService;
import apap.ti.sisdm.service.PresensiService;
import apap.ti.sisdm.service.TugasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PresensiController {
    @Qualifier("presensiServiceImpl")
    @Autowired
    private PresensiService presensiService;

    @Qualifier("karyawanServiceImpl")
    @Autowired
    private KaryawanService karyawanService;

    @Qualifier("tugasServiceImpl")
    @Autowired
    private TugasService tugasService;

    @GetMapping("/presensi")
    private String listPresensi(Model model) {
        List<Presensi> listPresensi = presensiService.getListPresensi();
        model.addAttribute("listPresensi", listPresensi);
        return "presensi/viewall-presensi";
    }

    @GetMapping("/presensi/tambah")
    private String addPresensiFormPage(Model model) {

        List<Karyawan> karyawanList = karyawanService.getListKaryawan();
        Presensi presensi = new Presensi();
        List<Tugas> listTugas = tugasService.getListAvailableTugas();
        List<Tugas> listTugasNew = new ArrayList<>();

        presensi.setListTugas(listTugasNew);
        presensi.getListTugas().add(new Tugas());

        model.addAttribute("karyawanList", karyawanList);
        model.addAttribute("presensi", presensi);
        model.addAttribute("listTugasExisting", listTugas);

        return "presensi/form-add-presensi";
    }
//
    @PostMapping(value = "/presensi/tambah", params = {"save"})
    private String addPresensiSubmitPage(@ModelAttribute Presensi presensi, Model model) {
        Presensi savedPresensi = presensiService.addPresensi(presensi);


        for (Tugas tugas: presensi.getListTugas()) {
            Long idTugas = tugas.getIdTugas();
            Integer status = tugas.getStatus();

            Tugas searchedTugas = tugasService.getTugasById(idTugas);
            searchedTugas.setIdPresensi(savedPresensi);
            searchedTugas.setStatus(status);

            tugas = searchedTugas;
//            String searchedTugasNama = searchedTugas.getNama();
//            String searchedTugasDeskripsi = searchedTugas.getDeskripsi();
//            Integer searchedTugasPoint = searchedTugas.getStoryPoint();
//
//            tugas.setNama(searchedTugasNama);
//            tugas.setDeskripsi(searchedTugasDeskripsi);
//            tugas.setStoryPoint(searchedTugasPoint);


            tugasService.updateTugas(tugas);
        }
        model.addAttribute("presensi", presensi);
        model.addAttribute("karyawan", presensi.getKaryawan());

        return "presensi/add-presensi";
    }

    @PostMapping(value = "/presensi/tambah", params = {"addRow"})
    private String addRowTugasMultiple(@ModelAttribute Presensi presensi, Model model) {
        if (presensi.getListTugas() == null || presensi.getListTugas().size() == 0) {
            presensi.setListTugas(new ArrayList<>());
        }

        presensi.getListTugas().add(new Tugas());
        List<Tugas> listTugas = tugasService.getListAvailableTugas();

        List<Karyawan> karyawanList = karyawanService.getListKaryawan();

        model.addAttribute("karyawanList", karyawanList);
        model.addAttribute("listTugas", listTugas);
        model.addAttribute("presensi", presensi);
        model.addAttribute("listTugasExisting", listTugas);


        return "presensi/form-add-presensi";
    }

    @PostMapping(value = "/presensi/tambah", params = {"deleteRow"})
    private String deleteRowTugasMultiple(@ModelAttribute Presensi presensi, @RequestParam("deleteRow") Integer row, Model model) {
        final Integer rowId = Integer.valueOf(row);
        presensi.getListTugas().remove(rowId.intValue());

        List<Tugas> listTugas = tugasService.getListAvailableTugas();
        List<Karyawan> karyawanList = karyawanService.getListKaryawan();

        model.addAttribute("karyawanList", karyawanList);
//        model.addAttribute("listTugas", listTugas);
        model.addAttribute("presensi", presensi);
        model.addAttribute("listTugasExisting", listTugas);


        return "presensi/form-add-presensi";
    }


    @GetMapping("presensi/{id}")
    public String viewDetailPresensi(@PathVariable String id, Model model) {
        Presensi presensi = presensiService.getPresensiById(Long.parseLong(id));
        List<Tugas> listTugas = presensiService.getListTugasById(presensi);
        model.addAttribute("presensi", presensi);
        model.addAttribute("listTugas", listTugas);

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

//    @GetMapping("presensi/{id}/hapus")
//    public String deleteCourse(@PathVariable String id, Model model) {
//        Presensi presensi = presensiService.getPresensiById(Long.parseLong(id));
//        presensiService.deletePresensi(presensi);
//        model.addAttribute("presensi", presensi);
//        return "presensi/delete-presensi";
//    }



}
