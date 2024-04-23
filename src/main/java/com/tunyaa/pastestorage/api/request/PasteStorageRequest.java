
package com.tunyaa.pastestorage.api.request;

import com.tunyaa.pastestorage.api.PublicStatus;
import lombok.Data;

@Data
public class PasteStorageRequest {
    private String data;
    private long expiraionTimeSeconds;
    private PublicStatus publicStatus;
}
