package com.guillaumegasnier.education.shell.services;

import org.jspecify.annotations.NonNull;

import java.util.Optional;

public interface ValidatorService {

    <T> Optional<T> validate(@NonNull T entity);

}
