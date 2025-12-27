package com.guillaumegasnier.education.core.services.impl;

import com.guillaumegasnier.education.core.services.CoreReferentielService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CoreReferentielServiceImpl implements CoreReferentielService {

    private final CoreReferentielService coreReferentielService;
}
