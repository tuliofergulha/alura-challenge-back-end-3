package br.com.fergulha.challenge.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @GetMapping
    public String index() {
        return "transaction/home";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message",
                "Selecione um arquivo para realizar a importação");
            return "redirect:/transaction";
        }

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            String uploadDir = "./uploads/";
            Path path = Paths.get(uploadDir + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        attributes.addFlashAttribute("message",
            "O arquivo " + fileName + " foi importado com sucesso!");

        return "redirect:/transaction";
    }
}
