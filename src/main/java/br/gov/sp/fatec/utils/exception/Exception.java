package br.gov.sp.fatec.utils.exception;

import br.gov.sp.fatec.project.domain.Project;
import br.gov.sp.fatec.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;

public class Exception extends Throwable {

    public static class SendEmailFailedException extends RuntimeException {
        public SendEmailFailedException() {
            super("Failed to send email");
        }
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    public static class CreateUserException extends RuntimeException {
        public CreateUserException() {
            super("There is more than one user with this email");
        }
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public static class userNotFoundException extends RuntimeException {
        public userNotFoundException(Long id) {
            super(format("User with id '%d' not found", id));
        }

        public userNotFoundException() {
            super("User not found");
        }
    }

    public static class userInactiveException extends RuntimeException {
        public userInactiveException(Long id) {
            super(format("User with id '%d' is inactive", id));
        }

        public userInactiveException() {
            super("User not found");
        }
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public static class projectNotFoundException extends RuntimeException {
        public projectNotFoundException(Long id) {
            super(format("Project with id '%d' not found", id));
        }

        public projectNotFoundException() {
            super("Project not found");
        }
    }

    public static void throwIfUserIsInactive(User user) throws userNotFoundException {
        if (!user.getActive()) {
            throw new userInactiveException(user.getId());
        }
    }

    public static void throwIfUserIsNull(User user) throws userNotFoundException {
        if (user == null) {
            throw new userNotFoundException();
        }
    }

    public static void throwIfProjectIsNull(Project project) throws projectNotFoundException {
        if (project == null) {
            throw new projectNotFoundException();
        }
    }

}


