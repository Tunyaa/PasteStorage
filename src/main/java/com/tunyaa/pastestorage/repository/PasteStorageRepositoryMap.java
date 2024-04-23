package com.tunyaa.pastestorage.repository;

import com.tunyaa.pastestorage.exception.NotFoundEntityByHashException;
import com.tunyaa.pastestorage.model.PasteStorageEntity;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

//Реализация репозитория
@Repository
@AllArgsConstructor
public class PasteStorageRepositoryMap implements PasteStorageRepository {

    //Хранилище
    private final Map<String, PasteStorageEntity> vault = new ConcurrentHashMap<>();

    @Override//Получить объект по hash
    public PasteStorageEntity getByHash(String hash) {
        PasteStorageEntity get;
        if ((get = vault.get(hash)) == null) {
            throw new NotFoundEntityByHashException("PasteStorage not found with hash = " + hash);
        }
        return get;
    }

    @Override//Получить список живых и public объектов
    public List<PasteStorageEntity> getListOfPublicAndAlive() {
        LocalDateTime now = LocalDateTime.now();

        return vault.values().stream()
                .filter(PasteStorageEntity::isPublic)//Фильтр по статусу
                .filter(entity -> entity.getLifeTime().isAfter(now))//Фильтр по времени(Время объекта раньше текущего)
                .sorted(Comparator.comparing(PasteStorageEntity::getId))//Сортировка по id(получить последние добавленные объекты)
                .limit(2)//Количество*****************************************можно передавать через запрос
                .collect(Collectors.toList());//Сложить в лист
    }

    @Override//Добавить объект
    public String add(PasteStorageEntity entity) {
        vault.put(entity.getHash(), entity);
        return "Post add";
    }

}
