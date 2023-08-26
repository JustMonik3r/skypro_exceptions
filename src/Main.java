import java.util.regex.Pattern;

public class Main {

    public static final String PASSWORD_REQUIREMENTS = "Пароль может состоять из 8-20 символов и содержать только латинские буквы, цифры и знак подчеркивания";
    public static final String LOGIN_REQUIREMENTS = "Имя пользователя может состоять из 3-20 символов и содержать только латинские буквы, цифры и знак подчеркивания";

    public static void main(String[] args) throws WrongLoginException, WrongPasswordException {
        String login = "login";
        String password = "password";
        String confirmPassword = "password";

        try {
            checkLoginAndPassword(login, password, confirmPassword);
        } catch (WrongLoginException e) {
            System.out.println(e.getMessage());
        } catch (WrongPasswordException e) {
            System.out.println(e.getMessage());
        } finally {
            if (checkLoginAndPassword(login, password, confirmPassword) == true) {
                System.out.println("Регистрация завершена");
            } else {
                System.out.println("Указаны неверные данные");
            }
        }
    }

    public static boolean checkLoginAndPassword(String login, String password, String confirmPassword)
            throws WrongLoginException, WrongPasswordException {
        checkLogin(login);
        checkPassword(password, confirmPassword);
        return true;
    }

    private static void checkLogin(String login) throws WrongLoginException {

        String loginPatternStr = "^[A-Za-z0-9_-]{3,20}$";
        if (!regexpCheck(loginPatternStr, login)) {
            throw new WrongLoginException(String.format("Имя пользователя не соответствует требованиям: %s", login, LOGIN_REQUIREMENTS));
        }
    }

    private static void checkPassword(String password, String confirmPassword) throws WrongPasswordException {

        String passwordPatternStr = "^[A-Za-z0-9_-]{8,20}$";
        if (!regexpCheck(passwordPatternStr, password)) {
            throw new WrongPasswordException(String.format("Пароль не соответствует требованиям: %s", PASSWORD_REQUIREMENTS));
        }

        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Пароли не совпадают");
        }
    }

    private static boolean regexpCheck(String pattern, String str) {
        Pattern loginPattern = Pattern.compile(pattern);
        return loginPattern.matcher(str).matches();
    }
}