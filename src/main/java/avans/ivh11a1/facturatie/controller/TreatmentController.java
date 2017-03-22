package avans.ivh11a1.facturatie.controller;

import avans.ivh11a1.facturatie.aop.SecurityAnnotation;
import avans.ivh11a1.facturatie.domain.administration.Role;
import avans.ivh11a1.facturatie.domain.billing.Treatment;
import avans.ivh11a1.facturatie.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by kevin on 11-10-2016.
 */
@Controller
@RequestMapping("/treatments")
@SecurityAnnotation(allowedRole = { Role.ADMIN, Role.ADMINISTRATION })
public class TreatmentController {

    @Autowired
    private TreatmentRepository treatmentRepository;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("treatments", getTreatmentList());

        return "treatments/index";
    }


    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showInvoiceByCsn(Model theModel, @PathVariable int id) {
        //Get Customer by csn
        Treatment Treatment = treatmentRepository.findOne(id);

        ArrayList<Treatment> treatments = new ArrayList<Treatment>();
        treatments.add(Treatment);



        //Add invoices to domain
        theModel.addAttribute("treatments", treatments);

        //Return page name
        return "treatments/index";
    }


    private Iterable<Treatment> getTreatmentList() {
        return treatmentRepository.findAll();
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteTreatment(Model model, @PathVariable int id) {
        treatmentRepository.delete(id);

        model.addAttribute("message", "Treatment removed from the database");
        model.addAttribute("treatments", getTreatmentList());

        return this.index(model);
    }

    @RequestMapping("/new")
    public String add() {
        return "treatments/create";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addTreatment(String name, int duration, float price, @RequestParam(value = "id", required = false, defaultValue = "0") int id, final Model model) {
        try {

            if (id > 0) {
                //update
                Treatment treatment = treatmentRepository.findOne(id);
                treatment.setDuration(duration);
                treatment.setPrice(price);
                treatment.setName(name);

                treatmentRepository.save(treatment);
                model.addAttribute("message", "Treatment aangepast");
            } else {
                //add
                Treatment treatment = new Treatment(name, duration, price);
                treatmentRepository.save(treatment);

                model.addAttribute("message", "Treatment added to the database");
            }

            return this.index(model);
        } catch (Exception ex) {
            return "treatments/create";
        }

    }

    @RequestMapping(value = "/new/{id}", method = RequestMethod.GET)
    public String editTreatment(Model model, @PathVariable int id) {
        Treatment treatment = treatmentRepository.findOne(id);

        if (treatment != null) {
            model.addAttribute("Treatment", treatment);
            return "treatments/create";
        } else {
            return "treatments/index";
        }
    }






    @ModelAttribute("page")
    public String module() {
        return "treatments";
    }
}
