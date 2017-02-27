package co.smartreceipts.android.rating;

import rx.Single;

public interface AppRatingStorage {

    Single<AppRatingModel> readAppRatingData();

    void incrementLaunchCount();

    void saveInstallTime();

    void setDontShowRatingPromptMore();

    void prorogueRatingPrompt(int prorogueDays);

    void crashOccurred();
}