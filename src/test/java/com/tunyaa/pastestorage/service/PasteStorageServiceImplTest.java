
package com.tunyaa.pastestorage.service;

import com.tunyaa.pastestorage.api.response.PasteStorageResponse;
import com.tunyaa.pastestorage.exception.NotFoundEntityByHashException;
import com.tunyaa.pastestorage.model.PasteStorageEntity;
import com.tunyaa.pastestorage.repository.PasteStorageRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class PasteStorageServiceImplTest {
    
    @Autowired
    private PasteStorageService service;
    
//    @MockBean
//    private PasteStorageRepository repository;
    
    public PasteStorageServiceImplTest() {
    }

    @Test
    public void notExistHash(){
        assertThrows(NotFoundEntityByHashException.class, () -> service.getByHash("asdfatwt"));
    }
    
//    @Test
//    public void getExistHash(){
//        PasteStorageEntity entity = PasteStorageEntity.builder()
//                .hash("1")
//                .data("11")
//                .isPublic(true)
//                .build();
//        
//        Mockito.when(repository.getByHash("1")).thenReturn(entity);
//    
//        PasteStorageResponse expected = new PasteStorageResponse("11", true);
//        PasteStorageResponse actual = new PasteStorageResponse("1", true);
//        assertEquals(expected, actual);
//    }
}
