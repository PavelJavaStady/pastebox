package com.sendel.pastebox.api.response;

import com.sendel.pastebox.api.request.PublicStatus;
import lombok.Data;

@Data
public class PasteboxResponse {

    private String data;
    private PublicStatus publicStatus;
}
