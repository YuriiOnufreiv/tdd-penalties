package ua.onufreiv.penalties;

import org.junit.Test;

/**
 * Created by Yurii_Onufreiv on 20-Mar-17.
 */
public class PenaltyGameTest {
    @Test
    public void kickPerformed() {
        PenaltyGame game = new PenaltyGame();
        game.kick(true);
    }
}