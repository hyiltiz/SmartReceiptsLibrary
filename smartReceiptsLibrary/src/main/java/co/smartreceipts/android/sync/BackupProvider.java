package co.smartreceipts.android.sync;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import java.lang.ref.WeakReference;

/**
 * A top level interface to track the core behaviors that are shared by all automatic backup providers
 */
public interface BackupProvider {

    /**
     * Initialize the backup provider
     *
     * @param activity the current {@link FragmentActivity} if one is required for connection error resolutions
     */
    void initialize(@Nullable FragmentActivity activity);

    /**
     * De-initialize the backup provider to stop it from being used
     */
    void deinitialize();

    /**
     * Passes an activity result along to this provider for processing if required
     *
     * @param requestCode the request code
     * @param resultCode the result code
     * @param data any intent data
     * @return {@code true} if we handle the request, {@code false} otherwse
     */
    boolean onActivityResult(int requestCode, int resultCode, @Nullable Intent data);

}
