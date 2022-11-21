package com.sendel.pastebox.repo;

import com.sendel.pastebox.exeption.NotFoundEntityException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PasteboxRepositoryMap implements PasteboxRepository{

    private final Map<String,PasteboxEntity> vault = new HashMap<>();

    @Override
    public PasteboxEntity getHash(String hash) {
        PasteboxEntity pasteboxEntity =  vault.get(hash);
    if(pasteboxEntity==null){
       throw new NotFoundEntityException("Pastebox not Found with hash="+hash);
    }
    return pasteboxEntity;
    }

    @Override
    public List<PasteboxEntity> getListOfPublicAndAllive(int amount) {
        return null;
    }

    @Override
    public PasteboxEntity add(PasteboxEntity pasteboxEntity) {
        return null;
    }
}
