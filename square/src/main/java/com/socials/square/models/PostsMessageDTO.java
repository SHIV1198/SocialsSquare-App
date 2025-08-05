package com.socials.square.models;

import lombok.Builder;

@Builder
public record PostsMessageDTO(String userId, String content) {
}