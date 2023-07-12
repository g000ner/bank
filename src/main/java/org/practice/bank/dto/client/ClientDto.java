package org.practice.bank.dto.client;

import javax.persistence.Column;

public record ClientDto (
        Long id,
        String fullName,
        String passportNumber,
        String passportSeria
) {}
