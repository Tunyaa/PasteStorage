package com.tunyaa.pastestorage.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

//Объект Пост
@Data
@Builder
public class PasteStorageEntity {
    private int id;
    private String data;
    private String hash;
    private LocalDateTime lifeTime;
    private boolean isPublic;
    
    
}
