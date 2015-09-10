package com.vilyever.vdnetwork;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

/**
 * VDNetworkError
 * AndroidNetwork <com.vilyever.vdnetwork>
 * Created by vilyever on 2015/9/10.
 * Feature:
 */
public class VDNetworkError extends Exception {
    private final VDNetworkError self = this;

    public final NetworkResponse networkResponse;
    private long networkTimeMs;

    private VolleyError mVolleyError;

    
    /* #Constructors */
    public VDNetworkError() {
        networkResponse = null;
    }

    public VDNetworkError(NetworkResponse response) {
        networkResponse = response;
    }

    public VDNetworkError(String exceptionMessage) {
        super(exceptionMessage);
        networkResponse = null;
    }

    public VDNetworkError(String exceptionMessage, Throwable cause) {
        super(exceptionMessage, cause);
        networkResponse = null;
    }

    public VDNetworkError(Throwable cause) {
        super(cause);
        networkResponse = null;
    }

    // for volley
    public VDNetworkError(VolleyError error) {
        networkResponse = null;
        mVolleyError = error;
    }

    /* #Overrides */
    @Override
    public void printStackTrace() {
        if (mVolleyError != null) {
            mVolleyError.printStackTrace();
        }
        else {
            super.printStackTrace();
        }
    }

    /* #Accessors */
    public long getNetworkTimeMs() {
        return networkTimeMs;
    }
     
    /* #Delegates */     
     
    /* #Private Methods */    
    
    /* #Public Methods */
    public Exception getRealError() {
        if (mVolleyError != null) {
            return mVolleyError;
        }
        else {
            return self;
        }
    }

    /* #Classes */

    /* #Interfaces */     
     
    /* #Annotations @interface */    
    
    /* #Enums */
}