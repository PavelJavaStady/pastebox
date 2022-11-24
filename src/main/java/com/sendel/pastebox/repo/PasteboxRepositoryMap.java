package com.sendel.pastebox.repo;

import com.sendel.pastebox.exeption.NotFoundEntityException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class PasteboxRepositoryMap implements PasteboxRepository {

    private final Map<String, PasteboxEntity> vault = new ConcurrentHashMap<>();

    @Override
    public PasteboxEntity getHash(String hash) {

        PasteboxEntity pasteboxEntity = vault.get(hash);
        if (pasteboxEntity == null) {
            throw new NotFoundEntityException("Pastebox not Found with hash=" + hash);
       }
        return pasteboxEntity;
    }

    @Override
    public List<PasteboxEntity> getListOfPublicAndAllive(int amount) {

        LocalDateTime now = LocalDateTime.now();

        return vault.values().stream()
                .filter(PasteboxEntity::isPublic)
                .filter(pasteboxEntity -> pasteboxEntity.getLifetime().isAfter(now))
                .sorted(Comparator.comparing(PasteboxEntity::getId).reversed())
                .limit(amount)
                .collect(Collectors.toList());

    }

    @Override
    public void add(PasteboxEntity pasteboxEntity) {
        vault.put(pasteboxEntity.getHash(), pasteboxEntity);
    }
}
