package ua.onufreiv.penalties;

/**
 * Created by Yurii_Onufreiv on 20-Mar-17.
 */
public class KickAfterFinishedException extends RuntimeException {
    @Override
    public String toString() {
        return "Game is finished! You can't change anything!!!";
    }
}