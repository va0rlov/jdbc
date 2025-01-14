package ru.productstar.spring.jdbc1.service;

import java.io.IOException;
import java.io.InputStream;

public interface ContactService {
    void importContactsFromCsv(InputStream inputStream) throws IOException;
}