package business;

public class Validation {

    public static void nonEmpty(String str) throws ValidationException {
        if(str.trim().isEmpty()) {
            throw new ValidationException("All fields must be non-empty!");
        }
    }

    public static void isIsbn(String isbn) throws ValidationException {
        if(!isbn.matches("^\\d{10}$") && !isbn.matches("^\\d{13}$")) {
            throw new ValidationException("ISBN must be numeric and consist of either 10 or 13 characters!");
        }
        if(isbn.length() == 10 && !isbn.matches("^([01])\\d{9}$")) {
            throw new ValidationException("If ISBN has length 10, the first digit must be 0 or 1!");
        }
        if(isbn.length() == 13 && !isbn.matches("^(978|979)\\d{10}$")) {
            throw new ValidationException("If ISBN has length 13, the first 3 digits must be either 978 or 979!");
        }
    }

    public static void isZip(String str) throws ValidationException {
        if(!str.matches("^\\d{5}$")) {
            throw new ValidationException("Zip must be numeric and consist of 5 characters!");
        }
    }

    public static void isTelephone(String tel) throws ValidationException {
        if (!tel.matches("^\\d{10}$")) {
            throw new ValidationException("Tel Number must be numeric and consist of 5 characters!");
        }
    }

    public static void isID(String id) throws ValidationException {
        if (!id.matches("^\\d+$")) {
            throw new ValidationException("ID must be numeric!");
        }
    }

}
