package com.sendel.pastebox.service;

import com.sendel.pastebox.api.request.PasteboxRequest;
import com.sendel.pastebox.api.response.PasteboxResponse;
import com.sendel.pastebox.api.response.PasteboxUrlResponse;


import java.util.List;

public interface PasteboxService {
    PasteboxResponse gerByHash(String hash);
    List<PasteboxResponse> getFirstPublicPastebox(int amount);
    PasteboxUrlResponse create (PasteboxRequest request);
}
