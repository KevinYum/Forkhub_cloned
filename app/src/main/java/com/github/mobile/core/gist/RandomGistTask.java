/*
 * Copyright 2012 GitHub Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.mobile.core.gist;

import static com.github.mobile.RequestCodes.GIST_VIEW;
import android.accounts.Account;
import android.app.Activity;
import android.util.Log;

import com.github.mobile.R;
import com.github.mobile.core.gist.GistStore;
import com.github.mobile.ui.ProgressDialogTask;
import com.github.mobile.ui.gist.GistsViewActivity;
import com.github.mobile.util.ToastUtils;
import com.google.inject.Inject;

import java.util.Collection;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.service.GistService;

/**
 * Task to open a random Gist
 */
public class RandomGistTask extends ProgressDialogTask<Gist> {

    private static final String TAG = "RandomGistTask";

    @Inject
    private GistService service;

    @Inject
    private GistStore store;

    /**
     * Create task
     *
     * @param context
     */

    /**
     * Execute the task with a progress dialog displaying.
     * <p>
     * This method must be called from the main thread.
     */
    public void start() {
        showIndeterminate(R.string.random_gist);

        execute();
    }

    public RandomGistTask(final Activity context) {
        super(context);
    }

    public RandomGistTask policy(Policy _policy){
        this._policy = _policy;
        return this;
    }

    public RandomGistTask pageSize(int _pageSize){
        this._pageSize = _pageSize;
        return this;
    }

    public RandomGistTask repeatIt(int _repeatIt){
        this._repeatIt = _repeatIt;
        return this;
    }


    public enum Policy {
        RANDOM, SEQUENTIAL
    }

    private Policy _policy = Policy.RANDOM;
    private int lastPage = 0;
    private int _pageSize = 1;
    private int _repeatIt = 1;

    @Override
    protected Gist run(Account account) throws Exception {
        PageIterator<Gist> pages = service.pagePublicGists(1);
        pages.next();

        if (_policy==Policy.RANDOM){
            for (int i=0;i<_repeatIt;++i){
                int randomPage = 1 + (int) (Math.random() * ((pages.getLastPage() - 1) + 1));
                Collection<Gist> gists = service.pagePublicGists(randomPage, _pageSize).next();
                if (!gists.isEmpty())
                    return store.addGist(gists.iterator().next());
            }
        }
        else {
            for (int i=0;i<_repeatIt;++i){
                lastPage += 1;
                if (lastPage==pages.getLastPage())
                    lastPage = 1;
                Collection<Gist> gists = service.pagePublicGists(lastPage, _pageSize).next();
                if (!gists.isEmpty())
                    return store.addGist(gists.iterator().next());
            }
        }

        throw new IllegalArgumentException(getContext().getString(
                    R.string.no_gists_found));
    }

    @Override
    protected void onSuccess(Gist gist) throws Exception {
        super.onSuccess(gist);

        ((Activity) getContext()).startActivityForResult(
                GistsViewActivity.createIntent(gist), GIST_VIEW);
    }

    @Override
    protected void onException(Exception e) throws RuntimeException {
        super.onException(e);

        Log.d(TAG, "Exception opening random Gist", e);
        ToastUtils.show((Activity) getContext(), e.getMessage());
    }
}
