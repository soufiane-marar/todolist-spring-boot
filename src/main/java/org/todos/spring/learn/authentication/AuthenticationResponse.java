package org.todos.spring.learn.authentication;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.todos.spring.learn.user.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPOJOBuilder
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("user")
    private User user;

}