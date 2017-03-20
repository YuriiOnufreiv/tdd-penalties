package ua.onufreiv.penalties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Yurii_Onufreiv on 20-Mar-17.
 */
public class PenaltyGame {
    private final int TEAM_KICKS_DEFAULT_AMOUNT = 5;
    private String firstTeam;
    private String secondTeam;

    private List<Boolean> firstTeamKicks;
    private List<Boolean> secondTeamKicks;
    private boolean kickOfFirstTeam;

    private KicksHistoryService kicksHistoryService;
    private PlayersPriceService playersPriceService;

    public PenaltyGame(String firstTeam, String secondTeam) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;

        firstTeamKicks = new ArrayList<Boolean>();
        secondTeamKicks = new ArrayList<Boolean>();
        kickOfFirstTeam = true;
        kicksHistoryService = new KicksHistoryService();
        playersPriceService = new PlayersPriceService();
    }

    private int getTeamScore(List<Boolean> firstTeamKicks) {
        return Collections.frequency(firstTeamKicks, true);
    }

    public void kick(boolean success) {
        if(finished()) {
            throw  new KickAfterFinishedException();
        }

        if (kickOfFirstTeam) {
            firstTeamKicks.add(success);
        } else {
            secondTeamKicks.add(success);
        }
        kickOfFirstTeam = !kickOfFirstTeam;
    }

    public boolean[] kick(String player, String team, boolean success) {
        if((firstTeam.equalsIgnoreCase(team) && kickOfFirstTeam) ||
                (secondTeam.equalsIgnoreCase(team) && !kickOfFirstTeam)) {
            kick(success);
            return kicksHistoryForPlayer(player);
        } else {
            throw new KickOnWrongTurnException();
        }
    }

    public boolean[] kicksHistoryForPlayer(String player) {
        return kicksHistoryService.kicksForPlayer(player);
    }

    public int missedPlayersTotalPrice(String team) {
        return playersPriceService.getPriceForTeam(team);
    }

    public String score() {
        int firstTeamScore = getTeamScore(firstTeamKicks);
        int secondTeamScore = getTeamScore(secondTeamKicks);

        if (firstTeamKicks.size() + secondTeamKicks.size() > 14) {
            return "AC Milan [" + missedPlayersTotalPrice("AC Milan")
                    + "] (" + firstTeamScore + ")-(" + secondTeamScore + ") ["
                    + missedPlayersTotalPrice("FC Dynamo Kyiv") + "] FC Dynamo Kyiv";
        }

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
