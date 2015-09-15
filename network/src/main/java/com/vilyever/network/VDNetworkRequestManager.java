package com.vilyever.network;

import android.content.Context;

import com.android.volley.RequestQueue;

/**
 * VDNetworkRequestManager
 * AndroidNetwork <com.vilyever.vdnetwork>
 * Created by vilyever on 2015/9/10.
 * Feature:
 */
public class VDNetworkRequestManager {
    private final VDNetworkRequestManager self = this;

    /* #Constructors */

    /* #Overrides */    
    
    /* #Accessors */     
     
    /* #Delegates */     
     
    /* #Private Methods */    
    
    /* #Public Methods */
    /**
     * @param context
     * 			application context
     */
    public static void initial(Context context) {
        VDVolley.VDVolleyRequestManager.initial(context);
    }

    /**
     * @return
     * 		instance of the queue
     * @throws
     * 		IllegalStateException if init has not yet been called
     */
    public static RequestQueue getRequestQueue() {
        return VDVolley.VDVolleyRequestManager.getRequestQueue();
    }

    /* #Classes */

    /* #Interfaces */     
     
    /* #Annotations @interface */    
    
    /* #Enums */
}