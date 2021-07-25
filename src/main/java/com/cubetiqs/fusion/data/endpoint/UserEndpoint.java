package com.cubetiqs.fusion.data.endpoint;

import java.util.Optional;

import com.cubetiqs.fusion.data.entity.User;
import com.cubetiqs.fusion.security.AuthenticatedUser;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.fusion.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;

@Endpoint
@AnonymousAllowed
public class UserEndpoint {
    private final AuthenticatedUser authenticatedUser;

    @Autowired
    public UserEndpoint(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }

    public Optional<User> getAuthenticatedUser() {
        return authenticatedUser.get();
    }
}
