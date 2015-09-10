package com.vilyever.vdnetwork;

/**
 * VDNetworkDelegate
 * AndroidNetwork <com.vilyever.vdnetwork>
 * Created by vilyever on 2015/9/10.
 * Feature:
 */
public interface VDNetworkDelegate<T> {
    T willParseResponse(String response);
    void didResponse(T data);
    void didOccurError(VDNetworkError error);
}
