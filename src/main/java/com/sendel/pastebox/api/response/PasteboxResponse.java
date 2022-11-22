package com.sendel.pastebox.api.response;

import com.sendel.pastebox.api.request.PublicStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PasteboxResponse {

    private final String data;
    private final boolean isPublic;

}
