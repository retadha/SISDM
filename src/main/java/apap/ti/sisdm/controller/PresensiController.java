package apap.ti.sisdm.controller;

import apap.ti.sisdm.model.Karyawan;
import apap.ti.sisdm.model.Presensi;
import apap.ti.sisdm.model.SertifikasiKaryawan;
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

        if (presensi.getListTugas() == null || presensi.getListTugas().size() == 0) {
            presensi.setListTugas(new ArrayList<>());

        }
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
        savedPresensi.setListTugas(presensi.getListTugas());
        savedPresensi = presensiService.updatePresensi(savedPresensi);

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
    List<Tugas> listTugasToLoad;
    @GetMapping("presensi/{id}/ubah")
    public String updatePresensiFormPage(@PathVariable String id, Model model) {
        Presensi presensi = presensiService.getPresensiById(Long.parseLong(id));

        listTugasToLoad = new ArrayList<>();

        if (presensi.getListTugas().size() != 0) {
            listTugasToLoad.addAll(presensi.getListTugas());
        }
        listTugasToLoad.addAll(tugasService.getListAvailableTugas());


        model.addAttribute("listTugasExisting", listTugasToLoad);
        model.addAttribute("presensi", presensi);
        model.addAttribute("karyawan", presensi.getKaryawan());

        return "presensi/form-update-presensi";
    }

    @PostMapping(value = "presensi/{id}/ubah", params = {"addRow"})
    private String addRowUpdateTugasMultiple(@ModelAttribute Presensi presensi, Model model) {
        if (presensi.getListTugas() == null || presensi.getListTugas().size() == 0) {
            presensi.setListTugas(new ArrayList<>());
        }

        presensi.getListTugas().add(new Tugas());

        model.addAttribute("presensi", presensi);
        model.addAttribute("karyawan", presensi.getKaryawan());
        model.addAttribute("listTugasExisting", listTugasToLoad);


        return "presensi/form-update-presensi";
    }

    ArrayList<Tugas> removedTugas = new ArrayList<>();
    @PostMapping(value = "presensi/{id}/ubah", params = {"deleteRow"})
    private String deleteRowUpdateTugasMultiple(@ModelAttribute Presensi presensi, @RequestParam("deleteRow") Integer row, Model model) {
        final Integer rowId = Integer.valueOf(row);

        Tugas toBeRemoved = presensi.getListTugas().get(rowId);
        removedTugas.add(toBeRemoved);

        presensi.getListTugas().remove(rowId.intValue());

        model.addAttribute("presensi", presensi);
        model.addAttribute("karyawan", presensi.getKaryawan());
        model.addAttribute("listTugasExisting", listTugasToLoad);


        return "presensi/form-update-presensi";
    }



    @PostMapping(value = "presensi/{id}/ubah", params = {"save"})
    public String updatePresensiSubmitPage(@ModelAttribute Presensi presensi, Model model) {
        if (presensi.getListTugas() == null || presensi.getListTugas().size() == 0) {
            presensi.setListTugas(new ArrayList<>());
        }

        Presensi savedPresensi = presensiService.updatePresensi(presensi);


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

        for (Tugas tugas: removedTugas) {
            Long idTugas = tugas.getIdTugas();
            Tugas searchedTugas = tugasService.getTugasById(idTugas);

            tugasService.deleteTugas(searchedTugas);
        }
        removedTugas.clear();

        savedPresensi.setListTugas(presensi.getListTugas());
        savedPresensi = presensiService.updatePresensi(savedPresensi);

        model.addAttribute("presensi", savedPresensi);
        model.addAttribute("karyawan", savedPresensi.getKaryawan());

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
