package com.sysionng.gadsleaderboard;

import androidx.annotation.Nullable;

import java.util.List;

public interface ResultCallback {
    void onResultReady(@Nullable List<GadsModel> gadsModels);
}