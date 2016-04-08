package com.loibv.t1p;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.loibv.t1p.utils.LruBitmapCache;

import android.app.Application;
import android.text.TextUtils;

/**
 * @author LoiBV
 * @date Dec 26, 2014
 */
public class ApplicationController extends Application {

	public static final String LOG_TAG = ApplicationController.class.getSimpleName();

	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;

	private static ApplicationController mInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
	}

	public static synchronized ApplicationController getInstance(){
		return mInstance;
	}

	public RequestQueue getRequestQueue(){
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}
		return mRequestQueue;
	}

	public ImageLoader getImageLoader(){
		getRequestQueue();
		if (mImageLoader == null) {
			mImageLoader = new ImageLoader(this.mRequestQueue, new LruBitmapCache());
		}
		return this.mImageLoader;
	}

	public <T> void addToRequestQueue(Request<T> request, String tag){
		//Set the default tag if tag is empty
		request.setTag(TextUtils.isEmpty(tag) ? LOG_TAG : tag);
		getRequestQueue().add(request);
	}

	public <T> void addToRequestQueue(Request<T> request){
		request.setTag(LOG_TAG);
		getRequestQueue().add(request);
	}

	public void cancelPendingRequests(Object tag){
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}
}
