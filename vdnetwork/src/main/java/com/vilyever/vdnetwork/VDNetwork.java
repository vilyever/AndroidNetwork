package com.vilyever.vdnetwork;

import android.content.Context;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * VDNetwork
 * AndroidNetwork <com.vilyever.vdnetwork>
 * Created by vilyever on 2015/9/10.
 * Feature:
 */
public class VDNetwork<T> {
    private final VDNetwork self = this;

    private static WeakReference<Context> mAppContext;

    private final Class<T> mClass;
    private final String mIdentifier;
    
    /* #Constructors */
    public VDNetwork(Class<T> objectClass) {
        this(objectClass, null);
    }

    public VDNetwork(Class<T> objectClass, String identifier) {
        this.mClass = objectClass;
        this.mIdentifier = identifier;
    }

    public VDNetworkRequest fetchData(String url, JSONObject params, VDNetworkDelegate delegate) {
        return new VDNetworkRequest(new VDVolley.VDJsonClient<>(mClass, mIdentifier).fetchData(url, params, delegate));
    }
    
    /* #Overrides */    
    
    /* #Accessors */     
     
    /* #Delegates */     
     
    /* #Private Methods */    
    
    /* #Public Methods */
    public static void initial(Context context) {
        mAppContext = new WeakReference<Context>(context);
        VDNetworkRequestManager.initial(mAppContext.get());
    }

    /* #Classes */

    /* #Interfaces */     
     
    /* #Annotations @interface */    
    
    /* #Enums */
}