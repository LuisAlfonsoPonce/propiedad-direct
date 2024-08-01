package com.devhive.propiedaddirect.web.app.controllers;

import com.devhive.propiedaddirect.web.app.models.beans.AgentBean;
import com.devhive.propiedaddirect.web.app.models.entities.Agent;
import com.devhive.propiedaddirect.web.app.models.services.IAgentService;
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
import java.util.Map;

@Controller
@SessionAttributes("agentBean")
public class AgentController {

    @Value("${application.controllers.titulo}")
    private String titulo;

    @Autowired
    private IAgentService agentService;

    @GetMapping("/list-agents")
    public String listAgents(Model model) {
        model.addAttribute("titulo", titulo);
        model.addAttribute("agents", agentService.findAll());
        return "agents/list-agents";
    }

    @GetMapping(value = "/form-agent")
    public String createAgent(Map<String, Object> model) {
        AgentBean agentBean = new AgentBean();
        model.put("titulo", titulo);
        model.put("agentBean", agentBean);
        return "agents/form-agent";
    }

    @PostMapping("/save-agent")
    public String saveAgent(@Valid AgentBean agentBean, BindingResult bindingResult, Model model, SessionStatus sessionStatus) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("titulo", titulo);
            return "agents/form-agent";
        }
        Agent agent = agentService.convertToEntity(agentBean);

        MultipartFile imageFile = agentBean.getImageFile();
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                byte[] bytes = imageFile.getBytes();
                String base64Image = Base64.getEncoder().encodeToString(bytes);
                agent.setImageBase64(base64Image);
            } catch (IOException e) {
                e.printStackTrace();
                // Manejar la excepción según sea necesario
            }
        }

        agentService.save(agent);
        sessionStatus.setComplete();
        return "redirect:/list-agents";
    }

    @GetMapping(value = "form-agent/{agentId}")
    public String editAgent(@PathVariable(value = "agentId") Long agentId, Map<String, Object> model) {
        Agent agent;
        AgentBean agentBean;
        if (agentId > 0) {
            agent = agentService.findOne(agentId);
            agentBean = agentService.convertToBean(agent);
            agentBean.setEdit(true);
        } else {
            return "redirect:/list-agents";
        }
        model.put("titulo", titulo);
        model.put("agentBean", agentBean);
        return "agents/form-agent";
    }

    @GetMapping("delete-agent/{agentId}")
    public String deleteAgent(@PathVariable(value = "agentId") Long agentId) {
        if (agentId > 0) {
            agentService.delete(agentId);
        } else {
            return "redirect:/list-agents";
        }
        return "redirect:/list-agents";
    }
}
