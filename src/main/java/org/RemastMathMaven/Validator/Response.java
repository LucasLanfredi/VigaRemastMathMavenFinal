package org.RemastMathMaven.Validator;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Response<T> {

    private Boolean success;
    private String message;
    private T content;
    private List<String> errors;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public Response<T> addContent(T content) {
        this.content = content;
        return this;
    }

    public List<String> getErrors() {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Response<T> withStatus(HttpStatus httpStatus) {
        this.success = true;
        this.message = httpStatus.toString();
        return this;
    }

    public Response<T> withSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public static <E> ResponseBuilder<E> builder() {
        return new ResponseBuilder<>();
    }

    public static <E> ResponseBuilder<E> success() {
        return new ResponseBuilder<E>().success(true).message("Sucesso!");
    }

    public static <E> Response<E> success(E content) {
        return new ResponseBuilder<E>().success(true).message("Sucesso!").content(content).build();
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, errors, message, success);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if (!(obj instanceof Response)) {
            return false;
        }

        Response<?> other = (Response<?>) obj;
        return Objects.equals(this.content, other.content) &&
                Objects.equals(this.errors, other.errors) &&
                Objects.equals(this.message, other.message) &&
                Objects.equals(this.success, other.success);
    }

    @Override
    public String toString() {
        return "Response [success=" + success + ", message=" + message + ", content=" + content + ", errors=" + errors
                + "]";
    }


}