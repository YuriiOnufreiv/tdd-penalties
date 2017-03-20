package ua.onufreiv.penalties;

/**
 * Created by Yurii_Onufreiv on 20-Mar-17.
 */
public class KickOnWrongTurnException extends RuntimeException {
    @Override
    public String toString() {
        return "It's not turn of Your team!";
    }
}