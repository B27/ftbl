package com.example.user.secondfootballapp;

import com.example.user.secondfootballapp.model.ActiveMatches;
import com.example.user.secondfootballapp.model.AddTeam;
import com.example.user.secondfootballapp.model.Advertisings;
import com.example.user.secondfootballapp.model.Announces;
import com.example.user.secondfootballapp.model.Clubs;
import com.example.user.secondfootballapp.model.DataClub;
import com.example.user.secondfootballapp.model.EditCommand;
import com.example.user.secondfootballapp.model.EditCommandResponse;
import com.example.user.secondfootballapp.model.EditProfile;
import com.example.user.secondfootballapp.model.EditProtocolBody;
import com.example.user.secondfootballapp.model.GetLeagueInfo;
import com.example.user.secondfootballapp.model.Matches;
import com.example.user.secondfootballapp.model.News;
import com.example.user.secondfootballapp.model.People;
import com.example.user.secondfootballapp.model.RefereeRequestList;
import com.example.user.secondfootballapp.model.ServerResponse;
import com.example.user.secondfootballapp.model.SetRefereeList;
import com.example.user.secondfootballapp.model.SignIn;
import com.example.user.secondfootballapp.model.Team;
import com.example.user.secondfootballapp.model.Tournaments;
import com.example.user.secondfootballapp.model.User;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FootballApi {
    //get all news
    @GET("/api/news")
    Observable<News> getAllNews(@Query("limit") String limit, @Query("offset") String offset);


    //get all news
    @GET("/api/announce")
    Observable<Announces> getAllAnnounce(@Query("limit") String limit, @Query("offset") String offset);


    //get advertising
    @GET("/api/ads")
    Observable<Advertisings> getAdvertising(@Query("limit") String limit, @Query("offset") String offset);



    //get all clubs
    @GET("/api/clubs")
    Observable<Clubs> getAllClubs();
//    Call<Clubs> getAllClubs();


    //get all tournaments
    @GET("/api/leagues/all")
    Observable<Tournaments> getAllTournaments( @Query("limit") String limit, @Query("offset") String offset);
//    Call<Tournaments> getAllTournaments();


    //get tournament's info
    @GET("/api/leagues/league/{id}")
    Observable<GetLeagueInfo> getLeagueInfo(@Path("id") String id);
//    Call<GetLeagueInfo> getLeagueInfo(@Path("id") String id);



    //get active matches
    @GET("/api/matches/active")
    Observable<ActiveMatches> getActiveMatches(@Query("limit") String limit, @Query("offset") String  offset,
                                         @Query("played") Boolean played);


    //get coming matches
    @GET("/api/matches/upcoming")
    Observable<ActiveMatches> getComingMatches();




    //get referees type==referee
    //get players type==player
    @GET("/api/getusers")
    Observable<People> getAllUsers(@Query("type") String type, @Query("search") String search, @Query("limit") String limit, @Query("offset") String offset);


    //edit user
    @Multipart
    @POST("/api/editPlayerInfo")
    Call<EditProfile> editProfile(@Header("auth") String authorization, @PartMap Map<String, RequestBody> params, @Part MultipartBody.Part file);


    //edit user profile
    @POST("/api/editPlayerInfo")
    Call<EditProfile> editProfileText(@Header("auth") String authorization, @PartMap Map<String, RequestBody> params);



    //edit match protocol event and playerList
    @POST("/api/matches/changeProtocol")
    Call<Matches> editProtocol(@Header("auth") String authorization, @Body EditProtocolBody body);

    //edit match protocol referees
    @POST("/api/matches/setreferees")
    Observable<Response<Matches>> editProtocolReferees(@Header("auth") String authorization, @Body RefereeRequestList body);


    //confirm protocol
    @FormUrlEncoded
    @POST("/api/matches/acceptProtocol")
    Observable<Response<ServerResponse>> confirmProtocol(@Header("auth") String authorization, @Field("_id") String id);

    //set referee
    @POST("/api/matches/setmultireferees")
    Observable<Response<ServerResponse>> setReferees(@Header("auth") String authorization, @Body SetRefereeList body);


    //refresh user
    @GET("/api/refresh")
    Observable<User> refreshUser(@Header("auth") String authorization);

    //sign up user
    @Multipart
    @POST("/api/signup")
    Call<User> signUp(@PartMap Map<String, RequestBody> params, @Part MultipartBody.Part file);


    //sign in user
    @POST("/api/signin")
    Call<User> signIn(@Body SignIn body);

    //add player to team
    @Multipart
    @POST("/api/team/addplayer")
    Call<ServerResponse> addPlayerToTeam(@Header("auth") String authorization, @PartMap Map<String, RequestBody> params);

    //create new team
    @Multipart
    @POST("/api/leagues/addrequest")
    Call<AddTeam> addTeam(@Header("auth") String authorization, @PartMap Map<String, RequestBody> params);

    //edit team
    @POST("/api/team/edit")
    Call<EditCommandResponse> editTeam(@Header("auth") String authorization, @Body EditCommand body);

    //player inv
    @Multipart
    @POST("/api/team/acceptrequest")
    Call<User> playerInv(@Header("auth") String authorization, @PartMap Map<String, RequestBody> params);


    //get all player's teams
    @GET("/api/team/teamsbyid")
    Call<Team> getTeam(@Query("_id") String id);

    //add new club
    @Multipart
    @POST("/api/clubs/add")
    Call<DataClub> addClub(@Header("auth") String authorization, @PartMap Map<String, RequestBody> params, @Part MultipartBody.Part file);

    //edit club
    @Multipart
    @POST("/api/clubs/edit")
    Call<DataClub> editClub(@Header("auth") String authorization, @PartMap Map<String, RequestBody> params, @Part MultipartBody.Part file);

}

