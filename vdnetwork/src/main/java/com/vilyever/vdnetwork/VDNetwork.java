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
    // 默认将会取消之前发起的未完成的同url同parma的请求，若不希望被取消，则指定一个新的identifier
    public VDNetwork(Class<T> objectClass) {
        this(objectClass, null);
    }

    // 将会取消之前发起的未完成的同identifier的请求
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