package Exceptions.Environement;

public class BadEnvValueException extends Exception
{
    private String wrongEnvValue;

    public BadEnvValueException(String wrongEnvValue, String message)
    {
        super(message);
        setWrongEnvValue(wrongEnvValue);
    }

    public String getWrongEnvValue()
    {
        return wrongEnvValue;
    }

    public void setWrongEnvValue(String wrongEnvValue)
    {
        this.wrongEnvValue = wrongEnvValue;
    }

}
