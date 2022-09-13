package uk.gov.hmcts.reform.hmc.exceptions;

public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException(final String message) {
        this(message, null);
    }

    public ResourceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
