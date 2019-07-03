package com.example.user.secondfootballapp.user.activity;

import android.annotation.SuppressLint;
import androidx.core.widget.NestedScrollView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.secondfootballapp.CheckError;
import com.example.user.secondfootballapp.Controller;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.SaveSharedPreference;
import com.example.user.secondfootballapp.model.ActiveMatch;
import com.example.user.secondfootballapp.model.ActiveMatches;
import com.example.user.secondfootballapp.model.Person;
import com.example.user.secondfootballapp.model.Referee;
import com.example.user.secondfootballapp.model.RefereeRequest;
import com.example.user.secondfootballapp.model.RefereeRequestList;
import com.example.user.secondfootballapp.model.SetRefereeList;
import com.example.user.secondfootballapp.user.adapter.RVRefereesMatchesAdapter;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RefereesMatches extends AppCompatActivity {
    List<RefereeRequestList> refereeRequestLists;
    RVRefereesMatchesAdapter adapter;
    LinearLayout layout;
    Logger log = LoggerFactory.getLogger(RefereesMatches.class);
    List<ActiveMatch> matches = new ArrayList<>();
    NestedScrollView scroller;
    int count = 0;
    int limit = 5;
    int offset = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView recyclerView;
        ImageButton buttonBack;
        ImageButton buttonSave;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.referees_matches);
        getActiveMatches("5", "0");
        scroller = findViewById(R.id.scrollerRefereesMatches);
        buttonBack = findViewById(R.id.refereesMatchesBack);
        buttonSave = findViewById(R.id.refereesMatchesSave);
        buttonBack.setOnClickListener(v -> finish());
        recyclerView = findViewById(R.id.recyclerViewRefereesMatches);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        layout = findViewById(R.id.emptyRefereesMatch);
        refereeRequestLists = new ArrayList<>();
        try{
            layout.setVisibility(View.GONE);
            Person person = (Person) getIntent().getExtras().getSerializable("REFEREESMATCHES");
//            List<Match> matches = person.getParticipationMatches();
            adapter = new RVRefereesMatchesAdapter(this, matches, person, (id, personId, check, type, position) -> {
                if (!check && refereeRequestLists.get(position).getId().equals(id)){
                    RefereeRequest referee = new RefereeRequest();
                    referee.setPerson(personId);
                    referee.setType(type);
                    List<RefereeRequest> list = refereeRequestLists.get(position).getRefereeRequest();
                    list.remove(referee);
                    refereeRequestLists.get(position).setRefereeRequest(list);
                }
                if (check && refereeRequestLists.get(position).getId().equals(id)){
                    RefereeRequest referee = new RefereeRequest();
                    referee.setPerson(personId);
                    referee.setType(type);
                    List<RefereeRequest> list = refereeRequestLists.get(position).getRefereeRequest();
                    list.add(referee);
                    refereeRequestLists.get(position).setRefereeRequest(list);
                }
            });
            recyclerView.setAdapter(adapter);
            scroller.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    offset++;
                    int temp = limit*offset;
                    if (temp<=count) {
                        String str = String.valueOf(temp);
                        getActiveMatches("5", str);
                    }
                }
            });
            buttonSave.setOnClickListener(v -> setReferees());
        }catch (NullPointerException e){
            layout.setVisibility(View.VISIBLE);
        }

    }
    @SuppressLint("CheckResult")
    private void getActiveMatches(String limit, String offset) {
        Controller.getApi().getActiveMatches(limit, offset, false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen(completed -> completed.delay(5, TimeUnit.MINUTES))
                .subscribe(matches -> saveData(matches)
                        ,
                        error -> {
                            layout.setVisibility(View.VISIBLE);
                            log.error("ERROR: ", error);
                            CheckError checkError = new CheckError();
                            checkError.checkError(this, error);
                        }
                );
    }

    private void saveData(ActiveMatches matches1) {
        count = matches1.getCount();
        List<ActiveMatch> result;
        result = matches1.getMatches();
        if (result.size() != 0) {
            layout.setVisibility(View.GONE);
//            refereeRequestLists.clear();
            matches.addAll(matches.size(), result);
            List<RefereeRequestList> temp = new ArrayList<>();
            for (ActiveMatch activeMatch : result){
                RefereeRequestList count = new RefereeRequestList();
                count.setId(activeMatch.getId());
                List<RefereeRequest> requestList = new ArrayList<>();
                try {
                    for (Referee referee : activeMatch.getReferees()){
                        RefereeRequest refereeRequest = new RefereeRequest();
                        refereeRequest.setType(referee.getType());
                        refereeRequest.setPerson(referee.getPerson());
                        requestList.add(refereeRequest);
                    }
                }catch (NullPointerException e){
                    requestList.add(null);
                }
                count.setRefereeRequest(requestList);
                temp.add(count);
            }
            refereeRequestLists.addAll(refereeRequestLists.size(), temp);
            List<ActiveMatch> list = new ArrayList<>(matches);
            adapter.dataChanged(list);
        }
        if (matches.size()==0){
            layout.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("CheckResult")
    private void setReferees() {
        CheckError checkError = new CheckError();
        SetRefereeList setRefereeList = new SetRefereeList();
        setRefereeList.setRefereeRequestList(refereeRequestLists);
        Controller.getApi().setReferees(SaveSharedPreference.getObject().getToken(), setRefereeList)
                .map(responseBody -> {
                    if (!responseBody.isSuccessful()) {
                        String srt = responseBody.errorBody().string();
                        showToast(srt);
                    }
                    if (responseBody.errorBody() != null) {
                        checkError.checkHttpError(this, responseBody.errorBody().string());
                    }
                    return responseBody.body();
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                            String str = "Изменения сохранены";
                            showToastResult(str);
                            finish();
                        },
                        error -> checkError.checkError(this, error));

    }
    private void showToast(String str) {
        try {
            JSONObject jsonObject = new JSONObject(str);
            String str1 = jsonObject.getString("message");
            this.runOnUiThread(() -> Toast.makeText(RefereesMatches.this, str1, Toast.LENGTH_SHORT).show());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void showToastResult(String str) {
        this.runOnUiThread(() -> Toast.makeText(RefereesMatches.this, str, Toast.LENGTH_SHORT).show());
    }
}
