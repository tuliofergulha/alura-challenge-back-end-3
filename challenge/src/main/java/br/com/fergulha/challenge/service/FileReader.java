package br.com.fergulha.challenge.service;

import java.util.List;

public interface FileReader {

    List<String> read();

    String getFileName();
}
