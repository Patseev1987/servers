package ru.patseev.securityauthserver.auth.dto;


public record UserDTO (
        String username,
        String password) {
}