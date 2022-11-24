package com.sendel.pastebox.service;

import com.sendel.pastebox.api.request.PasteboxRequest;
import com.sendel.pastebox.api.request.PublicStatus;
import com.sendel.pastebox.api.response.PasteboxResponse;
import com.sendel.pastebox.api.response.PasteboxUrlResponse;
import com.sendel.pastebox.repo.PasteboxEntity;
import com.sendel.pastebox.repo.PasteboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Setter
@ConfigurationProperties(prefix = "app")
public class PasteboxServiceImpl implements PasteboxService {

    private String host;
    private int publiclistize;

    @Autowired
    private PasteboxRepository pasteboxRepository;

    private AtomicInteger idGenerator = new AtomicInteger(0);

    @Override
    public PasteboxResponse getByHash(String hash) {
        PasteboxEntity pasteboxEntity = pasteboxRepository.getHash(hash);
        return new PasteboxResponse(pasteboxEntity.getData(), pasteboxEntity.isPublic());
    }

    @Override
    public List<PasteboxResponse> getFirstPublicPastebox() {

        List<PasteboxEntity> list = pasteboxRepository.getListOfPublicAndAllive(10);

        return list.stream().map(pasteboxEntity ->
                        new PasteboxResponse(pasteboxEntity.getData(), pasteboxEntity.isPublic()))
                .collect(Collectors.toList());

    }

    @Override
    public PasteboxUrlResponse create(PasteboxRequest request) {
        int hash = generateId();
        PasteboxEntity pasteboxEntity = new PasteboxEntity();
        pasteboxEntity.setData(request.getData());
        pasteboxEntity.setId(hash);
        pasteboxEntity.setHash(Integer.toHexString(hash));
        pasteboxEntity.setPublic(request.getPublicStatus() == PublicStatus.PUBLIC);
        pasteboxEntity.setLifetime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        pasteboxRepository.add(pasteboxEntity);

        return new PasteboxUrlResponse(host + "/" + pasteboxEntity.getHash());
    }

    private int generateId() {
        return idGenerator.getAndIncrement();
    }
}
