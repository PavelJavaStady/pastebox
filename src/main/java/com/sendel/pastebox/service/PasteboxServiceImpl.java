package com.sendel.pastebox.service;

import com.sendel.pastebox.api.request.PasteboxRequest;
import com.sendel.pastebox.api.response.PasteboxResponse;
import com.sendel.pastebox.api.response.PasteboxUrlResponse;

import java.util.List;

public class PasteboxServiceImpl implements PasteboxService {
    @Override
    public PasteboxResponse gerByHash(String hash) {
        return null;
    }

    @Override
    public List<PasteboxResponse> getFirstPublicPastebox(int amount) {
        return null;
    }

    @Override
    public PasteboxUrlResponse create(PasteboxRequest request) {
        return null;
    }
}
