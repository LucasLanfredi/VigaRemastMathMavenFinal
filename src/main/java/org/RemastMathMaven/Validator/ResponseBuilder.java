package org.RemastMathMaven.Validator;

import com.google.common.base.Preconditions;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ResponseBuilder<E> {

    E content = null;
    List<String> errors;
    String message;
    Boolean success;

    ResponseBuilder() {
    }

    public Response<E> build() {
        validate();
        Response<E> r = new Response<>();
        r.setContent(content);
        r.setErrors(errors);
        r.setMessage(message);
        r.setSuccess(success);
        return r;
    }

    private void validate() {
        if (success) {
            Preconditions.checkState(message != null && !message.isEmpty(), "Erro. Uma mensagem de sucesso deve ser informada.");
            Preconditions.checkState(errors == null, "Erro. Uma resposta de sucesso não pode conter erros.");
        } else {
            Preconditions.checkState(message != null && !message.isEmpty(), "Erro. Uma mensagem de erro deve ser informada.");
        }
    }

    public ResponseBuilder<E> message(String msg) {
        message = msg;
        return this;
    }

    public ResponseBuilder<E> success(boolean b) {
        success = b;
        return this;
    }

    public ResponseBuilder<E> status(HttpStatus status) {
        message = status.getReasonPhrase();
        return this;
    }

    public ResponseBuilder<E> content(E contentValue) {
        content = contentValue;
        return this;
    }

    public ResponseBuilder<E> errors(List<String> errorList) {
        errors = errorList;
        return this;
    }

    public ResponseBuilder<E> withErrors(List<String> allErrors) {
        return this.success(false)
                .message("Existem erros, verificar na lista")
                .content(content)
                .errors(allErrors);
    }

    public ResponseBuilder<E> noContent() {
        return this.success(true)
                .message("Não existem dados de retorno")
                .content(content)
                .status(HttpStatus.NO_CONTENT);
    }

    public ResponseBuilder<E> withContent(E content) {
        return this.success(true)
                .message("Sucesso!!")
                .content(content)
                .status(HttpStatus.OK);
    }
}