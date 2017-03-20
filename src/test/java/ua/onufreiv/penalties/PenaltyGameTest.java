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
        int kicksAmount = 10;
        performKicks(kicksAmount);
        assertEquals(false, game.finished());
    }

    @Test
    public void gameFinishedAfterNineSuccessfulOneMissedKicks() {
        performKicks(9);
        game.kick(false);
        assertEquals(true, game.finished());
    }

    @Test
    public void gameNotFinishedAfterFiveSuccessfulKicks() {
        performKicks(5);
        assertEquals(false, game.finished());
    }

    @Test
    public void gameNotFinishedForTwoZeroScore() {
        game.kick(true);
        game.kick(false);
        game.kick(true);
        game.kick(false);
        assertEquals(false, game.finished());
    }

    @Test
    public void gameFinishedDueToSpecialCase() {
        game.kick(true);
        game.kick(false);
        game.kick(true);
        game.kick(false);
        game.kick(true);
        game.kick(false);
        assertEquals(true, game.finished());
    }

    private void performKicks(int kicksAmount) {
        for(int i = 0; i < kicksAmount; i++) {
            game.kick(true);
        }
    }
}