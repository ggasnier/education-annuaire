package com.guillaumegasnier.education.core.domains;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
public abstract class AbstractSourcesEntity extends AbstractEntity {

    @Column(name = "sources", columnDefinition = "VARCHAR(50)", length = 50)
    protected String sources;

    public Set<String> getSources() {
        if (sources == null || sources.isBlank()) return new HashSet<>();
        return new HashSet<>(Arrays.asList(sources.split("\\|")));
    }

    public void setSources(Set<String> sources) {
        this.sources = String.join("|", sources);
    }

    public void addSource(@NonNull String source) {
        Set<String> sourcesSet = getSources();
        sourcesSet.add(source);
        this.sources = String.join("|", sourcesSet);
    }
}
