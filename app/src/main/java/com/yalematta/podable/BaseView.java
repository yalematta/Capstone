package com.yalematta.podable;

/**
 * Created by yalematta on 6/16/18.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);
    void ShowToast(String text);
}
