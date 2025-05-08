package com.gwidgets.examples;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTemplate {
	
	@Autowired
	SocketHandler socketHandler;

   ////@Scheduled(cron="*/15 * * * * ?")
   //@Scheduled(cron="0 0/5 * * * ?")
   // public void publishUpdates(){
	//   socketHandler.brodcastMessage("server notification " + LocalDateTime.now().toString());
   // }
}