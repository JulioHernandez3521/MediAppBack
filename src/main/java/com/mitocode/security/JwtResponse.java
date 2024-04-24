package com.mitocode.security;

import com.fasterxml.jackson.annotation.JsonProperty;

public record JwtResponse(@JsonProperty(value = "acces_token") String accessTokrn) {
}
