package com.tunyaa.pastestorage.service;

import com.tunyaa.pastestorage.api.PublicStatus;
import com.tunyaa.pastestorage.api.request.PasteStorageRequest;
import com.tunyaa.pastestorage.api.response.PasteStorageResponse;
import com.tunyaa.pastestorage.api.response.PasteStorageUrlResponse;
import com.tunyaa.pastestorage.model.PasteStorageEntity;
import com.tunyaa.pastestorage.repository.PasteStorageRepositoryMap;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

//Реализация сервиса
@Service
@AllArgsConstructor
@ConfigurationProperties(prefix = "app")
public class PasteStorageServiceImpl implements PasteStorageService {

    public PasteStorageServiceImpl(PasteStorageRepositoryMap repository) {
        this.repository = repository;
    }

    public PasteStorageServiceImpl() {
    }

    private String host = "http://pastestorage.tunyaa.ru";
    private int publicListSize = 10;
    @Autowired
    private PasteStorageRepositoryMap repository;

    @Override
    public PasteStorageResponse getByHash(String hash) {
        PasteStorageEntity entity = repository.getByHash(hash);
        return PasteStorageResponse.builder().data(entity.getData()).isPublic(entity.isPublic()).build();
    }

    @Override
    public List<PasteStorageResponse> getFirstPublicPasteStorages() {
        List<PasteStorageEntity> list = repository.getListOfPublicAndAlive();

        return list.stream().map(pasteStorageEntity
                -> new PasteStorageResponse(pasteStorageEntity.getData(), pasteStorageEntity.isPublic()))
                .collect(Collectors.toList());

    }

    @Override
    public PasteStorageUrlResponse create(PasteStorageRequest request) {
        int id = request.hashCode();
        String hash = String.valueOf(id);
        System.out.println(id + " - ID");

        PasteStorageEntity entity = PasteStorageEntity.builder()
                .data(request.getData())
                .id(id)
                .hash(hash)
                .lifeTime(LocalDateTime.now().plusSeconds(request.getExpiraionTimeSeconds()))
                .isPublic(request.getPublicStatus() == PublicStatus.PUBLIC)
                .build();
        System.out.println(entity.toString() + " - PasteStorageEntity entity");
        repository.add(entity);
        PasteStorageUrlResponse pasteStorageUrlResponse = new PasteStorageUrlResponse(host + "/" + entity.getHash());

        return pasteStorageUrlResponse;
    }

}
