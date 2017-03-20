package ua.onufreiv.penalties;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Yurii_Onufreiv on 20-Mar-17.
 */
public class PenaltyGameTest {
    private PenaltyGame game;

    @Before
    public void setUp() {
        game = new PenaltyGame();
    }

    @Test
    public void kickPerformed() {
        game.kick(true);
    }

    @Test
    public void scoreIsReturned() {
        assertNotNull(game.score());
    }
}