package com.example.user.secondfootballapp;

import com.example.user.secondfootballapp.model.Club;
import com.example.user.secondfootballapp.model.League;
import com.example.user.secondfootballapp.model.News_;
import com.example.user.secondfootballapp.model.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FootballApi {
    //get all news
    @GET("/api/news")
    Call<List<News_>> getAllNews();


    //get news _id == id
    @GET("/api/news")
    Call<List<News_>> getSomeNews(@Query("_id") String id);

    //get all clubs
    @GET("/api/clubs")
    Call<List<Club>> getAllClubs();

    //get all tournaments
    @GET("/api/leagues/all")
    Call<List<League>> getAllTournaments();

    //get ongoing tournaments
    @GET("/api/leagues/ongoing")
    Call<List<League>> getOngoingTournaments();

    //get upcoming tournaments
    @GET("/api/leagues/upcoming")
    Call<List<League>> getUpComingTournaments();


    //get referees type==referee
    //get players type==player
    @GET("/api/getusers")
    Call<List<Person>> getAllUsers(@Query("type") String type);




}

