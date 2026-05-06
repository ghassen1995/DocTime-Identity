package com.doctime.identity.adapter.inbound.web.DTO;

public record ApiError(
        String code,
        String message,
        int status
) {
}