package com.mafemad.contentcalendar.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record Content(
        Integer id,
        String title,
        String description,
        Status status,
        Type contentType,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdated,
        String url
) {
}
