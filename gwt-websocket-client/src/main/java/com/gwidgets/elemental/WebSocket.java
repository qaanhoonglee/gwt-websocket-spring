package com.gwidgets.elemental;

import com.google.gwt.core.client.JavaScriptObject;

public class WebSocket extends JavaScriptObject {
	
	protected WebSocket() {}
	
	public static native WebSocket create(String url) /*-{
		return new $wnd.WebSocket(url);
	}-*/;
	
	public final native void send(String data) /*-{
		this.send(data);
	}-*/;
	
	public final native void close() /*-{
		this.close();
	}-*/;
	
	public final native String getUrl() /*-{
		return this.url;
	}-*/;
	
	public final native void setOnclose(Function callback) /*-{
		this.onclose = function(e) {
			return callback.@com.gwidgets.elemental.Function::call(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
		};
	}-*/;
	
	public final native void setOnerror(Function callback) /*-{
		this.onerror = function(e) {
			return callback.@com.gwidgets.elemental.Function::call(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
		};
	}-*/;
	
	public final native void setOnmessage(Function callback) /*-{
		this.onmessage = function(e) {
			return callback.@com.gwidgets.elemental.Function::call(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
		};
	}-*/;
	
	public final native void setOnopen(Function callback) /*-{
		this.onopen = function(e) {
			return callback.@com.gwidgets.elemental.Function::call(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
		};
	}-*/;
}
