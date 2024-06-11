package ru.patseev.securityauthserver.auth.dto;


public record UserDTOForSingIn(
        String username,
        String password) {
}