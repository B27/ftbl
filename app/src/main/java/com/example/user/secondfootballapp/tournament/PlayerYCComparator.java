package com.example.user.secondfootballapp.tournament;

import com.example.user.secondfootballapp.model.Player;

import java.util.Comparator;

public class PlayerYCComparator implements Comparator<Player>
{
    public int compare(Player o1, Player o2)
    {
        return o2.getYellowCards().compareTo(o1.getYellowCards());
    }
}
