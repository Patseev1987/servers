package ru.patseev.securityauthserver.auth.dto;


public record UserDTOForUpdate(
        String username,
        String oldPassword,
        String newPassword) {
}