package com.testExam.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Message {
    CNPJ_ALREADY_EXISTS("Nesse CNPJ já existe uma instituição cadastrada."),
    REGISTRATION_NOT_LOCATED("Não foi possível localizar esse registro!"),
    NO_PERMISSION_FOR_THIS_EXAM("Sem permissão para esse exame!"),
    BUY_MORE_COINS("Por favor adquirir mais moedas!");

    String message;
}
