/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tinnovatest.domain.commons.exception;

import java.util.List;
import lombok.Getter;

/**
 *
 * @author clayton.salgueiro
 */
@Getter
public class InvalidParameterException extends RuntimeException {

    private static final long serialVersionUID = 5801845680307347087L;

    private final List<ApiInnerError> errors;

    public InvalidParameterException(final String msg) {
        super(msg);
        this.errors = null;
    }

    public InvalidParameterException(final String msg, final List<ApiInnerError> errors) {
        super(msg);
        this.errors = errors;
    }

}
