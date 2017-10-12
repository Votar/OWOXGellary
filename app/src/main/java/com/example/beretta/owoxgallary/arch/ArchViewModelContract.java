package com.example.beretta.owoxgallary.arch;

/**
 * Created by beretta on 12.10.2017.
 */

public interface ArchViewModelContract<T extends ArchViewContract> {

    void attachView(T view);

    void detachView();
}
