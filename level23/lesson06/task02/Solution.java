package com.javarush.test.level23.lesson06.task02;

/* Рефакторинг
Отрефакторите класс Solution: вынесите все константы в public вложенный(nested) класс Constants.
Запретите наследоваться от Constants.
*/
public class Solution {
    public final static class Constants {
        public static final String notAccessible = "Server is not accessible for now.";
        public static final String notAuthorized = "User is not authorized.";
        public static final String isBanned = "User is banned.";
        public static final String isDenied = "Access is denied.";
    }
    public class ServerNotAccessibleException extends Exception {
        public ServerNotAccessibleException() {
            super(Constants.notAccessible);
        }

        public ServerNotAccessibleException(Throwable cause) {
            super(Constants.notAccessible, cause);
        }
    }

    public class UnauthorizedUserException extends Exception {
        public UnauthorizedUserException() {
            super(Constants.notAuthorized);
        }

        public UnauthorizedUserException(Throwable cause) {
            super(Constants.notAuthorized, cause);
        }
    }

    public class BannedUserException extends Exception {
        public BannedUserException() {
            super(Constants.isBanned);
        }

        public BannedUserException(Throwable cause) {
            super(Constants.isBanned, cause);
        }
    }

    public class RestrictionException extends Exception {
        public RestrictionException() {
            super(Constants.isDenied);
        }

        public RestrictionException(Throwable cause) {
            super(Constants.isDenied, cause);
        }
    }
}
