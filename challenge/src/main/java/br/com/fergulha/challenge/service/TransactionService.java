package br.com.fergulha.challenge.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class TransactionService {

    public String readCsvFile(MultipartFile file, RedirectAttributes attributes) {
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message",
                "Selecione um arquivo para realizar a importação");
            return "redirect:/transaction";
        }

        FileReader fileReader = new CSVReader(file);
        String fileName = fileReader.getFileName();
        List<String> list = fileReader.read();
        list.forEach(System.out::println);

        attributes.addFlashAttribute("message",
            "O arquivo " + fileName + " foi importado com sucesso!");

        return "redirect:/transaction";
    }
}
