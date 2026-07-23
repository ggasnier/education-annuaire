package com.guillaumegasnier.education.shell.services;

import org.springframework.lang.NonNull;

import java.util.Optional;

public interface ValidatorService {

    <T> Optional<T> validate(@NonNull T entity);

}
