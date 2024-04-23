
package com.tunyaa.pastestorage.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PasteStorageResponse {
    private final String data;
    private final boolean isPublic;
}
