package ua.onufreiv.penalties;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    public void initialScoreIsZeros() {
        assertEquals("0-0", game.score());
    }

    @Test
    public void scoreNotChangedAfterMissedKick() {
        game.kick(false);
        assertEquals("0-0", game.score());
    }
}