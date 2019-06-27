package com.example.user.secondfootballapp.tournament;

import com.example.user.secondfootballapp.model.Team;

import java.util.Comparator;

public class GroupTeamPlaceComparator implements Comparator<Team>
{
    public int compare(Team o1, Team o2)
    {
            return o1.getGroupScore() >= o2.getGroupScore() ? o1.getGroupScore() : o2.getGroupScore();
    }
}