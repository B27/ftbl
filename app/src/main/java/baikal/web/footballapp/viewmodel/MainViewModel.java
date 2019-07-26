package baikal.web.footballapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import baikal.web.footballapp.model.News_;

public class MainViewModel extends ViewModel {
    MutableLiveData<News_> newsData;
}
