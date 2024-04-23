
package com.tunyaa.pastestorage.service;

import com.tunyaa.pastestorage.api.request.PasteStorageRequest;
import com.tunyaa.pastestorage.api.response.PasteStorageResponse;
import com.tunyaa.pastestorage.api.response.PasteStorageUrlResponse;
import java.util.List;


public interface PasteStorageService {
    PasteStorageResponse getByHash(String hash);
    List<PasteStorageResponse> getFirstPublicPasteStorages();
    PasteStorageUrlResponse create(PasteStorageRequest request);
}
