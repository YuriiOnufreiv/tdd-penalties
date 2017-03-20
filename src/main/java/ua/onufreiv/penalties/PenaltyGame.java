package ua.onufreiv.penalties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Yurii_Onufreiv on 20-Mar-17.
 */
public class PenaltyGame {
    private final int TEAM_KICKS_DEFAULT_AMOUNT = 5;

    private List<Boolean> firstTeamKicks;
    private List<Boolean> secondTeamKicks;
    private boolean kickOfFirstTeam;

    private KicksHistoryService kicksHistoryService;

    public PenaltyGame() {
        firstTeamKicks = new ArrayList<Boolean>();
        secondTeamKicks = new ArrayList<Boolean>();
        kickOfFirstTeam = true;

        kicksHistoryService = new KicksHistoryService();
    }

    private int getTeamScore(List<Boolean> firstTeamKicks) {
        return Collections.frequency(firstTeamKicks, true);
    }

    public void kick(boolean success) {
        if (kickOfFirstTeam) {
            firstTeamKicks.add(success);
        } else {
            secondTeamKicks.add(success);
        }
        kickOfFirstTeam = !kickOfFirstTeam;
    }

    public boolean[] kick(String player, String team, boolean success) {
        kick(success);
        return kicksHistoryForPlayer(player);
    }

    public boolean[] kicksHistoryForPlayer(String player) {
        return kicksHistoryService.kicksForPlayer(player);
    }

    public String score() {
        int firstTeamScore = getTeamScore(firstTeamKicks);
        int secondTeamScore = getTeamScore(secondTeamKicks);
        return firstTeamScore + "-" + secondTeamScore;
    }

    public boolean finished() {
        if (firstTeamKicks.size() + secondTeamKicks.size() < TEAM_KICKS_DEFAULT_AMOUNT * 2) {
            return dueToSpecialCaseFinished();
        }
        return firstTeamKicks.size() == secondTeamKicks.size()
                && getTeamScore(firstTeamKicks) != getTeamScore(secondTeamKicks);
    }

    private boolean dueToSpecialCaseFinished() {
        int firstTeamScore = getTeamScore(firstTeamKicks);
        int secondTeamScore = getTeamScore(secondTeamKicks);
        if (firstTeamScore > secondTeamScore) {
            return firstTeamScore - secondTeamScore > TEAM_KICKS_DEFAULT_AMOUNT - secondTeamKicks.size();
        } else {
            return secondTeamScore - firstTeamScore > TEAM_KICKS_DEFAULT_AMOUNT - firstTeamKicks.size();
        }
    }
}
