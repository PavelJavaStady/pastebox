package com.sendel.pastebox;


import com.sendel.pastebox.api.response.PasteboxResponse;
import com.sendel.pastebox.exeption.NotFoundEntityException;
import com.sendel.pastebox.repo.PasteboxEntity;
import com.sendel.pastebox.repo.PasteboxRepository;
import com.sendel.pastebox.service.PasteboxService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@SpringBootTest
public class PasteboxServiceTest {
    @Autowired
    PasteboxService pasteboxService;
    @MockBean
    PasteboxRepository pasteboxRepository;

    @Test
    public void notExistHash() {
        when(pasteboxRepository.getHash(anyString())).thenThrow(NotFoundEntityException.class);
        Assertions.assertThrows(NotFoundEntityException.class, () -> pasteboxService.getByHash("dslfkjlsdkjf"));
    }
    @Test
    public void getExistHash(){
        PasteboxEntity entity = new PasteboxEntity();
        entity.setHash("1");
        entity.setData("11");
        entity.setPublic(true);

        when(pasteboxRepository.getHash("1")).thenReturn(entity);

        PasteboxResponse expected = new PasteboxResponse("11", true);
        PasteboxResponse actual = pasteboxService.getByHash("1");

        assertEquals(expected, actual);
    }

}
