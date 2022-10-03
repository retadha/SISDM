package apap.ti.sisdm.controller;

import apap.ti.sisdm.model.Tugas;
import apap.ti.sisdm.model.Tugas;
import apap.ti.sisdm.service.TugasService;
import apap.ti.sisdm.service.TugasService;
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
public class TugasController {
    @Qualifier("tugasServiceImpl")
    @Autowired
    private TugasService tugasService;

//    @GetMapping("/tugas")
//    private String listtugas(Model model) {
//        List<tugas> listtugas = tugasService.getListtugas();
//        model.addAttribute("listtugas", listtugas);
//        return "tugas/viewall-tugas";
//    }

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
