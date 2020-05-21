package pl.plenczewski.szahome4.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.plenczewski.szahome4.models.FormEncoder;
import pl.plenczewski.szahome4.service.EncoderService;

import java.beans.Encoder;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    EncoderService encoderService;

    @GetMapping
    public String mainPage(Model model){
        model.addAttribute("formEncoder", new FormEncoder());
        return "home";
    }

    @PostMapping("/encode")
    public String encode(@ModelAttribute FormEncoder formEncoder, Model model){
        encoderService.encodeText(formEncoder.getRawPassword());
        formEncoder.setEncodedPassword(encoderService.encodeText(formEncoder.getRawPassword()));
        model.addAttribute("formEncoder", formEncoder);
        model.addAttribute("matchFalse", encoderService.matchText("bad",formEncoder.getEncodedPassword()));
        model.addAttribute("matchTrue", encoderService.matchText(formEncoder.getRawPassword(),formEncoder.getEncodedPassword()));
        return "matches";
    }


}
