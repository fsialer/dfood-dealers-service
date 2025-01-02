package com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCatalog {
    DEALER_NOT_FOUND("DEALERS_MS_001", "Dealer not found."),
    DEALERS_BAD_PARAMETERS("DEALERS_MS_002", "Invalid parameters for creation."),
    INTERNAL_SERVER_ERROR("DEALERS_MS_000", "Internal server error.");

    private final String code;
    private final String message;
}
