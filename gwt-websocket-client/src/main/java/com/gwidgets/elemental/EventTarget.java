package com.gwidgets.elemental;

import com.google.gwt.core.client.JavaScriptObject;

public class EventTarget extends JavaScriptObject {
	
	protected EventTarget() {}
	
	public final native void addEventListener(String type, Function listener) /*-{
		this.addEventListener(type, function(e) {
			listener.@com.gwidgets.elemental.Function::call(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
		});
	}-*/;
}
