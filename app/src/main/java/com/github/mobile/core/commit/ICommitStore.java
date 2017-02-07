package com.github.mobile.core.commit;

import com.github.mobile.core.ItemStore;

import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.RepositoryCommit;

import java.io.IOException;

/**
 * Created by brok on 2/6/17.
 */

public interface ICommitStore {
    RepositoryCommit getCommit(final IRepositoryIdProvider repo, final String id);

    RepositoryCommit addCommit(IRepositoryIdProvider repo, RepositoryCommit commit);

    RepositoryCommit refreshCommit(final IRepositoryIdProvider repo, final String id)
            throws IOException ;

}
