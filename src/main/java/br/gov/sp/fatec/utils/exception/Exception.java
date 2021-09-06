package br.gov.sp.fatec.utils.exception;

import br.gov.sp.fatec.medal.domain.Medal;
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
    public static class EmailDuplicateException extends RuntimeException {
        public EmailDuplicateException() {
            super("Duplicate email");
        }
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(Long id) {
            super(format("User with id '%d' not found", id));
        }

        public UserNotFoundException() {
            super("User not found");
        }
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public static class MedalNotFoundException extends RuntimeException {
        public MedalNotFoundException() {
            super("User not found");
        }
    }

    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public static class userInactiveException extends RuntimeException {
        public userInactiveException(Long id) {
            super(format("User with id '%d' is inactive", id));
        }
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    public static class StudentAlreadyInTeamException extends RuntimeException {
        public StudentAlreadyInTeamException() {
            super(format("Student already resgistered in a team."));
        }
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public static class ProjectNotFoundException extends RuntimeException {
        public ProjectNotFoundException() {
            super("Project not found");
        }
    }

    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    public static class ProjectCannotBeDeletedException extends RuntimeException {
        public ProjectCannotBeDeletedException() {
            super("This project canot be deleted.");
        }
    }

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public static class UserInvalidException extends RuntimeException {
        public UserInvalidException() {
            super("You cannot change another user's information.");
        }
    }

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public static class UserRetrievingInvalidException extends RuntimeException {
        public UserRetrievingInvalidException() {
            super("You cannot change another user's information.");
        }
    }

    public static void throwIfUserIsInactive(User user) throws UserNotFoundException {
        if (!user.getActive()) {
            throw new userInactiveException(user.getId());
        }
    }

    public static void throwIfUserIsNull(User user) throws UserNotFoundException {
        if (user == null) {
            throw new UserNotFoundException();
        }
    }

    public static void throwIfProjectIsNull(Project project) throws ProjectNotFoundException {
        if (project == null) {
            throw new ProjectNotFoundException();
        }
    }

    public static void throwIfMedalIsNull(Medal medal) throws UserNotFoundException {
        if (medal == null) {
            throw new MedalNotFoundException();
        }
    }
}


