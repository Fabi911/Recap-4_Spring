package de.webdev.recap4_spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class idService {
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
