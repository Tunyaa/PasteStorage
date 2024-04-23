
package com.tunyaa.pastestorage.repository;

import com.tunyaa.pastestorage.model.PasteStorageEntity;
import java.util.List;

//Что должен уметь репозиторий
public interface PasteStorageRepository {
    PasteStorageEntity getByHash(String hash);
    List<PasteStorageEntity> getListOfPublicAndAlive();
    String add(PasteStorageEntity entity);
}
