package home_work.hw5.error_handlers;

public class NullPathVariableException extends RuntimeException{


    private final String variableName;

    public String getVariableName() {
        return variableName;
    }

    public NullPathVariableException(String message, String variableName) {
        super("Введите корректное значение: " + variableName);
        this.variableName = variableName;
    }
}
