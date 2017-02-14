package com.github.mobile.core;

import com.github.mobile.core.commit.CommitStore;
import com.github.mobile.core.commit.ICommitStore;
import com.github.mobile.core.gist.GistStore;
import com.github.mobile.core.issue.IssueStore;

import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.GistService;
import org.eclipse.egit.github.core.service.IssueService;
import org.eclipse.egit.github.core.service.PullRequestService;

/**
 * Created by brok on 2/13/17.
 */

public class StoreFactory {
    public static ICommitStore createCommitStore(CommitService service){
        return new CommitStore(service);
    }

    public static IssueStore createIssueStore(IssueService issueService,
                                       PullRequestService pullService) {
        return new IssueStore(issueService, pullService);
    }

    public static GistStore createGistStore(GistService service){
        return new GistStore(service);
    }
}
