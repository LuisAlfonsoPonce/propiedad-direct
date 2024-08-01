package com.devhive.propiedaddirect.web.app.controllers;

import com.devhive.propiedaddirect.web.app.models.beans.PropertyBean;
import com.devhive.propiedaddirect.web.app.models.entities.Agent;
import com.devhive.propiedaddirect.web.app.models.entities.Client;
import com.devhive.propiedaddirect.web.app.models.entities.Property;
import com.devhive.propiedaddirect.web.app.models.services.IClientService;
import com.devhive.propiedaddirect.web.app.models.services.IPropertyService;
import com.devhive.propiedaddirect.web.app.models.services.IStateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("propertyBean")
public class PropertyController {

    @Value("${application.controllers.titulo}")
    private String titulo;

    @Autowired
    private IPropertyService propertyService;

    @Autowired
    private IStateService stateService;

    @Autowired
    private IClientService clientService;

    @GetMapping("/list-properties")
    public String listProperties(Model model) {
        model.addAttribute("titulo", titulo);
        model.addAttribute("properties", propertyService.findAll());
        return "properties/list-properties";
    }

    @GetMapping(value = "/form-property")
    public String createProperty(Map<String, Object> model) {
        PropertyBean propertyBean = new PropertyBean();
        model.put("titulo", titulo);
        model.put("propertyBean", propertyBean);
        model.put("states", stateService.findAll());
        return "properties/form-property";
    }


    @PostMapping("/save-property")
    public String saveProperty(@Valid PropertyBean propertyBean, BindingResult bindingResult, Model model, SessionStatus sessionStatus) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("titulo", "Crear Propiedad");
            model.addAttribute("states", stateService.findAll());
            return "properties/form-property";
        }

        Property property = propertyService.convertToEntity(propertyBean);

        MultipartFile imageFile = propertyBean.getImageFile();
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                byte[] bytes = imageFile.getBytes();
                String base64Image = Base64.getEncoder().encodeToString(bytes);
                property.setImageBase64(base64Image);
            } catch (IOException e) {
                e.printStackTrace();
                // Manejar la excepción según sea necesario
            }
        }

        Agent agent= new Agent();
        agent.setAgentId(1L);
        property.setAgent(agent);
        propertyService.save(property);
        sessionStatus.setComplete();
        return "redirect:/list-properties";
    }


    @GetMapping(value = "/form-property/{propertyId}")
    public String editProperty(@PathVariable(value = "propertyId") Long propertyId, Map<String, Object> model) {
        Property property;
        PropertyBean propertyBean;
        if (propertyId > 0) {
            property = propertyService.findOne(propertyId);
            propertyBean = propertyService.convertToBean(property);
        } else {
            return "redirect:/list-properties";
        }
        model.put("propertyBean", propertyBean);
        model.put("titulo", titulo);
        model.put("states", stateService.findAll());
        return "properties/form-property";
    }

    @GetMapping(value = "/delete-property/{propertyId}")
    public String deleteProperty(@PathVariable(value = "propertyId") Long propertyId) {
        if (propertyId > 0) {
            propertyService.delete(propertyId);
        }
        return "redirect:/list-properties";
    }

    @GetMapping("/filter-properties")
    public String filterProperties(@RequestParam(value = "saleRent", required = false) String saleRent, Model model) {
        List<Property> properties;
        if (saleRent == null || saleRent.isEmpty()) {
            properties = propertyService.findAll();
        } else {
            properties = propertyService.findBySaleRent(saleRent);
        }
        model.addAttribute("titulo", titulo);
        model.addAttribute("properties", properties);
        model.addAttribute("saleRent", saleRent);
        return "properties/list-properties";
    }

    @GetMapping("/autocomplete-clients")
    @ResponseBody
    public List<Client> autocompleteClients(@RequestParam("term") String term) {
        return clientService.findByName(term);
    }
}
