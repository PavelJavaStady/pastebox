package com.sendel.pastebox.repo;

import java.util.List;

public interface PasteboxRepository {
    PasteboxEntity getHash(String hash);
    List<PasteboxEntity> getListOfPublicAndAllive(int amount);
    void add(PasteboxEntity pasteboxEntity);
}
