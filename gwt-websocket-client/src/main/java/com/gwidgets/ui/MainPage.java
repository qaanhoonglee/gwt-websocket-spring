package com.gwidgets.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwidgets.elemental.MessageEvent;
import com.gwidgets.elemental.WebSocket;
import com.gwidgets.places.HomePlace;
import com.gwidgets.places.SendPlace;

public class MainPage extends Composite {	
	
	@UiField
    VerticalPanel menuPanel;
	@UiField
    Anchor homeLink;
	@UiField
    Anchor sendLink;

	@UiField
	DeckPanel contentPanel;
	@UiField
	HTMLPanel homePanel;
	@UiField
	HTML notificationsToast;
	@UiField
	TextBox messageInput;
	@UiField
	Button sendButton;

	@UiField
	TextBox userNameRecieveInput;

	@UiField
	TextBox userNameInput;
	
    public WebSocket socket;

	private static MainPageUiBinder uiBinder = GWT
			.create(MainPageUiBinder.class);

	interface MainPageUiBinder extends UiBinder<Widget, MainPage> {
	}

	//main page constructor
	public MainPage() {
		initWidget(uiBinder.createAndBindUi(this));
		socket = WebSocket.create("ws://localhost:8082/notification-ws");
		
		socket.setOnmessage(new com.gwidgets.elemental.Function() {
			@Override
			public com.google.gwt.core.client.JavaScriptObject call(com.google.gwt.core.client.JavaScriptObject evt) {
				MessageEvent event = evt.cast();
				DivElement div = DOM.createDiv().cast();
				div.setInnerText(event.getData());
				homePanel.getElement().appendChild(div);
				showToast(event.getData());
				Window.alert(event.getData());
				return evt;
			}
		});
		
		socket.setOnopen(new com.gwidgets.elemental.Function() {
			@Override
			public com.google.gwt.core.client.JavaScriptObject call(com.google.gwt.core.client.JavaScriptObject evt) {
				GWT.log("socket open");
				Window.alert("socket open");
				return evt;
			}
		});
		
		socket.setOnclose(new com.gwidgets.elemental.Function() {
			@Override
			public com.google.gwt.core.client.JavaScriptObject call(com.google.gwt.core.client.JavaScriptObject evt) {
				GWT.log("socket closed");
				Window.alert("socket closed");
				return evt;
			}
		});
        
        // Mặc định hiển thị trang home
        contentPanel.showWidget(0);
	}
	
	
	public void initializeEvents(final PlaceController controller){
		homeLink.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                contentPanel.showWidget(0);
                homeLink.addStyleName("selected");
                sendLink.removeStyleName("selected");
                controller.goTo(new HomePlace("home"));
            }
        });
		 
		sendLink.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                contentPanel.showWidget(1);
                sendLink.addStyleName("selected");
                homeLink.removeStyleName("selected");
                controller.goTo(new SendPlace("send"));
            }
        });
        
        sendButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                socket.send(messageInput.getText());
            }
        });
	}
	
	
	
	public Anchor getHomeLink(){
		return homeLink;
	}
	
	public Anchor getUsersLink(){
		return sendLink;
	}
	
	public VerticalPanel getPaperMenu(){
		return menuPanel;
	}
	
	public DeckPanel getContentPanel(){
		return contentPanel;
	}
	
	public interface Presenter {
		//handles clicking on back and forward buttons of the browser
		public void placeChangeWithoutClickEvent(String placeName);
	}
	
    private void showToast(String message) {
        notificationsToast.setText(message);
        notificationsToast.getElement().getStyle().setProperty("display", "block");
        
        // Auto hide after 3 seconds
        new com.google.gwt.user.client.Timer() {
            @Override
            public void run() {
                notificationsToast.getElement().getStyle().setProperty("display", "none");
            }
        }.schedule(3000);
    }
}
