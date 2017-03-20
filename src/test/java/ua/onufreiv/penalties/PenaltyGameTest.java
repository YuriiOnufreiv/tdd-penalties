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
    public void initialScoreIsZeros() {
        assertEquals("0-0", game.score());
    }

    @Test
    public void scoreNotChangedAfterMissedKick() {
        game.kick(false);
        assertEquals("0-0", game.score());
    }

    @Test
    public void scoreIsOneOneAfterTwoSuccessfulKicks() {
        game.kick(true);
        game.kick(true);
        assertEquals("1-1", game.score());
    }

    @Test
    public void gameNotFinishedAfterTenSuccessfulKicks() {
        for(int i = 0; i < 10; i++) {
            game.kick(true);
        }
        assertEquals(false, game.finished());
    }
}