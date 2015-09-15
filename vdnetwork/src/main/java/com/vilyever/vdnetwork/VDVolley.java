package com.vilyever.vdnetwork;

import android.content.Context;
import android.net.Uri;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * VDVolley
 * AndroidNetwork <com.vilyever.vdnetwork>
 * Created by vilyever on 2015/9/10.
 * Feature:
 */
public class VDVolley {
    private final VDVolley self = this;

    
    /* #Constructors */    
    
    /* #Overrides */    
    
    /* #Accessors */     
     
    /* #Delegates */     
     
    /* #Private Methods */    
    
    /* #Public Methods */

    /* #Classes */
    public static class VDJsonClient<T> {
        private final Class<T> mClass;
        private final String mIdentifier;

        public VDJsonClient(Class<T> objectClass) {
            this(objectClass, null);
        }

        public VDJsonClient(Class<T> objectClass, String identifier) {
            this.mClass = objectClass;
            this.mIdentifier = identifier;
        }

        public VDJsonRequest<T> fetchData(String url, JSONObject params, VDNetworkDelegate delegate) {

            Uri.Builder uriBuilder = Uri.parse(url).buildUpon();

            String uri = uriBuilder.build().toString();

            String identifier = mIdentifier;
            if (identifier == null) {
                identifier = url + (params == null ? "" : params.toString());
            }

            VDJsonRequest<T> request = new VDJsonRequest<>(Request.Method.POST
                    , uri
                    , params
                    , mClass
                    , delegate);

            request.setTag(identifier);

            // cancel last similar request
            // e.g. 加载评论最多数据未加载完成时用户又执行了切换点击量最多的数据，这时就应取消先前未完成的加载评论最多数据的操作
            VDVolleyRequestManager.getRequestQueue().cancelAll(request.getTag());
            VDVolleyRequestManager.getRequestQueue().add(request);

            return request;
        }
    }

    public static class VDJsonRequest<T> extends Request<T> {
        /**
         * Class type for the response
         */
        private final Class<T> mClass;

        /**
         * Callback for response delivery
         */
        private final VDNetworkDelegate mDelegate;

        private Map<String, String> mParams;

        public VDJsonRequest(int method, String url, JSONObject params, Class<T> objectClass,
                             VDNetworkDelegate delegate) {
            super(method, getUrl(method, url, params), null);
            this.mParams = getMappedParams(params);
            this.mClass = objectClass;
            this.mDelegate = delegate;
        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return this.mParams;
        }

        @Override
        protected Response<T> parseNetworkResponse(NetworkResponse response) {
            try {
                String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                T data = (T) mDelegate.willParseResponse(json);
                return Response.success(data,
                        HttpHeaderParser.parseCacheHeaders(response));
            } catch (Exception e) {
                return Response.error(new ParseError(e));
            }
        }

        @Override
        protected void deliverResponse(T response) {
            mDelegate.didResponse(response);
        }

        @Override
        public void deliverError(VolleyError error) {
            mDelegate.didOccurError(new VDNetworkError(error));
        }

        public static Map<String, String> getMappedParams(JSONObject params)
        {
            if (params == null) {
                return null;
            }

            HashMap<String, String> map = new HashMap<>();
            Iterator<String> iterator = params.keys();
            while (iterator.hasNext()) {
                String key = iterator.next();
                try {
                    map.put(key, params.getString(key));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return map;
        }

        /**
         * @function for use the same mParams to GET and POST method.
         * 			if method is GET, call this to make mParams into url.
         * 			Be careful, if mParams include "{" , "}" or ", ", this may not work!
         * @param method
         * @param url
         * @param params
         * @return
         */
        public static String getUrl(int method, String url, JSONObject params)
        {
            if (method == Method.GET && params != null) {
                Uri.Builder uriBuilder = Uri.parse(url).buildUpon();

                Iterator<Map.Entry<String, String> > iterator = getMappedParams(params).entrySet().iterator();
                while(iterator.hasNext() ) {
                    Map.Entry<String, String> entry = iterator.next();
                    String key = entry.getKey();
                    String value = entry.getValue();

                    uriBuilder.appendQueryParameter(key, value);
                }

                return uriBuilder.build().toString();
            }

            return url;
        }
    }

    public static class VDVolleyRequestManager {
        /**
         * the queue :-)
         */
        private static RequestQueue mRequestQueue;

        /**
         * Nothing to see here.
         */
        private VDVolleyRequestManager() {
            // no instances
        }

        /**
         * @param context
         * 			application context
         */
        public static void initial(Context context) {
            mRequestQueue = Volley.newRequestQueue(context);
        }

        /**
         * @return
         * 		instance of the queue
         * @throws
         * 		IllegalStateException if init has not yet been called
         */
        public static RequestQueue getRequestQueue() {
            if (mRequestQueue != null) {
                return mRequestQueue;
            }
            else {
                throw new IllegalStateException("VDRequestManager Not initialized");
            }
        }
    }

    /* #Interfaces */     
     
    /* #Annotations @interface */    
    
    /* #Enums */
}