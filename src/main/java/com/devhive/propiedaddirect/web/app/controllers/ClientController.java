package com.devhive.propiedaddirect.web.app.controllers;

import com.devhive.propiedaddirect.web.app.models.entities.Client;
import com.devhive.propiedaddirect.web.app.models.services.IClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes("client")
public class ClientController {

    @Value("${application.controllers.titulo}")
    private String titulo;

    @Autowired
    private IClientService clientService;

    @GetMapping("/list-clients")
    public String listClients(Model model) {
        model.addAttribute("titulo", titulo);
        model.addAttribute("clients", clientService.findAll());
        return "clients/list-clients";
    }

    @GetMapping(value = "/form-client")
    public String createClient(Map<String, Object> model) {
        Client client = new Client();
        model.put("titulo", titulo);
        model.put("client", client);
        return "clients/form-client";
    }

    @PostMapping("/save-client")
    public String saveClient(@Valid Client client, BindingResult bindingResult, Model model, SessionStatus sessionStatus) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("titulo", titulo);
            return "clients/form-client";
        }
        clientService.save(client);
        sessionStatus.setComplete();
        return "redirect:/list-clients";
    }

    @GetMapping(value = "form-client/{clientId}")
    public String editClient(@PathVariable(value = "clientId") Long clientId, Map<String, Object> model) {
        Client client = null;
        if (clientId > 0) {
            client = clientService.findOne(clientId);
        }else{
            return "redirect:/list-clients";
        }
        model.put("titulo", titulo);
        model.put("client", client);
        return "clients/form-client";
    }

    @GetMapping("delete-client/{clientId}")
    public String deleteClient(@PathVariable(value = "clientId") Long clientId) {
        if (clientId > 0) {
            clientService.delete(clientId);
        }else{
            return "redirect:/list-clients";
        }
        return "redirect:/list-clients";
    }

}
