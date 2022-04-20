package br.com.fergulha.challenge.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class CSVReader implements FileReader {

    private MultipartFile file;

    public CSVReader(MultipartFile file) {
        this.file = file;
    }

    @Override
    public List<String> read() {
        List<String> result = new ArrayList<>();
        try {
            String line;
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    @Override
    public String getFileName() {
        return StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
    }
}
