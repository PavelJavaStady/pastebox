package com.sendel.pastebox.repo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PasteboxEntity {
    private String data;
    private String hash;
    private LocalDateTime lifetime;
    private boolean isPublic;
}
