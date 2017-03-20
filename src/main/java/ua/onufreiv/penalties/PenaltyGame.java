package ua.onufreiv.penalties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Yurii_Onufreiv on 20-Mar-17.
 */
public class PenaltyGame {
    private List<Boolean> firstTeamKicks;
    private List<Boolean> secondTeamKicks;
    private boolean kickOfFirstTeam;

    public PenaltyGame() {
        firstTeamKicks = new ArrayList<Boolean>();
        secondTeamKicks = new ArrayList<Boolean>();
        kickOfFirstTeam = true;
    }

    public void kick(boolean success) {
        if(kickOfFirstTeam) {
            firstTeamKicks.add(success);
        } else {
            secondTeamKicks.add(success);
        }
        kickOfFirstTeam = !kickOfFirstTeam;
    }

    public String score() {
        int firstTeamScore = Collections.frequency(firstTeamKicks, true);
        int secondTeamScore = Collections.frequency(secondTeamKicks, true);
        return firstTeamScore + "-" + secondTeamScore;
    }
}
