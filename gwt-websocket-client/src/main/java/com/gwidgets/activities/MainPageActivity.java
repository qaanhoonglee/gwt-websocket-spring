package com.gwidgets.activities;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.gwidgets.client.ClientFactory;
import com.gwidgets.places.HomePlace;
import com.gwidgets.places.SendPlace;
import com.gwidgets.ui.MainPage;

public class MainPageActivity extends AbstractActivity implements MainPage.Presenter {
	
	MainPage mainPage;
	PlaceController controller;
	Place currentPlace;
	
	public MainPageActivity(ClientFactory factory, Place place) {
		this.mainPage = factory.getMainPageView();
		this.controller = factory.getPlaceController();
		this.currentPlace = place;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus bus) {
		panel.setWidget(mainPage);
	}
	
	public void refreshPlace(Place place){
		this.currentPlace = place;
		
		GWT.log(place.toString());
		
		if(place instanceof HomePlace){
			placeChangeWithoutClickEvent("home");
		}else if(place instanceof SendPlace){
			placeChangeWithoutClickEvent("send");
		}
		
	}

	@Override
	public void placeChangeWithoutClickEvent(String placeName) {
		// TODO Auto-generated method stub
		if ("home".equals(placeName)) {
			mainPage.getContentPanel().showWidget(0);
			mainPage.getHomeLink().addStyleName("selected");
			mainPage.getUsersLink().removeStyleName("selected");
		} else if ("send".equals(placeName)) {
			mainPage.getContentPanel().showWidget(1);
			mainPage.getUsersLink().addStyleName("selected");
			mainPage.getHomeLink().removeStyleName("selected");
		}
	}

}
