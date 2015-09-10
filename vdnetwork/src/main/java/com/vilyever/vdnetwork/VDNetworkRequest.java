package com.vilyever.vdnetwork;

/**
 * VDNetworkRequest
 * AndroidNetwork <com.vilyever.vdnetwork>
 * Created by vilyever on 2015/9/10.
 * Feature:
 */
public class VDNetworkRequest<T> {
    private final VDNetworkRequest self = this;

    private final Class<T> mClass;
    private final String mIdentifier;

    private VDVolley.VDJsonRequest<T> mVolleyRequest;
    
    /* #Constructors */
    public VDNetworkRequest(Class<T> objectClass) {
        this(objectClass, null);
    }

    public VDNetworkRequest(Class<T> objectClass, String identifier) {
        this.mClass = objectClass;
        this.mIdentifier = identifier;
    }

    public VDNetworkRequest(VDVolley.VDJsonRequest<T> volleyRequest) {
        this.mClass = null;
        this.mIdentifier = null;
        this.mVolleyRequest = volleyRequest;
    }

    /* #Overrides */    
    
    /* #Accessors */
    public String getmIdentifier() {
        if (mVolleyRequest != null) {

            return mVolleyRequest.getTag() == null ? null: mVolleyRequest.getTag().toString();
        }
        return mIdentifier;
    }

    /* #Delegates */
     
    /* #Private Methods */    
    
    /* #Public Methods */
    public void cancel() {
        if (mVolleyRequest != null) {
            mVolleyRequest.cancel();
        }
    }

    /* #Classes */

    /* #Interfaces */     
     
    /* #Annotations @interface */    
    
    /* #Enums */
}