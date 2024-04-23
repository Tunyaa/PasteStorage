package com.tunyaa.pastestorage.controller;

import com.tunyaa.pastestorage.api.request.PasteStorageRequest;
import com.tunyaa.pastestorage.api.response.PasteStorageResponse;
import com.tunyaa.pastestorage.api.response.PasteStorageUrlResponse;
import com.tunyaa.pastestorage.service.PasteStorageService;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ps")
@AllArgsConstructor
public class PasteStorageController {

    private PasteStorageService service;

    @PostMapping("/")
    public String add(@RequestBody PasteStorageRequest request) {
        PasteStorageUrlResponse create = service.create(request);
        String url = create.getUrl();
        System.out.println(url + " - public String add");
        return url;
    }

    @GetMapping("/{hash}")
    public String getByHAsh(@PathVariable String hash) {
        PasteStorageResponse byHash = service.getByHash(hash);
        return byHash.getData();
    }

    @GetMapping("/")
    public List<PasteStorageResponse> getPublicPasteList() {
        List<PasteStorageResponse> firstPublicPasteStorages = service.getFirstPublicPasteStorages();
        System.out.println(firstPublicPasteStorages + " - List<PasteStorageResponse> firstPublicPasteStorages");
        return firstPublicPasteStorages;
    }
}
