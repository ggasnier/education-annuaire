package com.guillaumegasnier.education.core.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InternatDto implements Serializable {

    private int disponible;

    private int occupe;
}
