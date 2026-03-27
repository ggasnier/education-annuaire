package com.guillaumegasnier.education.core.domains.formations;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "formations_onisep")
public class LienOnisepEntity {

    @EmbeddedId
    private LienOnisepPK pk;
}
