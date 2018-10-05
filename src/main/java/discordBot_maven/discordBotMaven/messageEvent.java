package discordBot_maven.discordBotMaven;


import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.MessageUpdateEvent;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.*;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class messageEvent extends ListenerAdapter {
	
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.getAuthor().isBot()){
			return;
		} else {
			Message message = event.getMessage();
			String messageID = event.getMessageId();
			String author = event.getAuthor().getName();
			String eName = event.getMember().getEffectiveName();
			String text = message.getContentDisplay();
			String naughtyText = message.getContentDisplay().toLowerCase();
			MessageChannel channel = event.getChannel();
				
			if(naughtyText.contains("fuck") || naughtyText.contains("shit") || naughtyText.contains("ass") || naughtyText.contains("damn")) { //Replace with swear file to match against in the future
				
				channel.sendMessage("*CLINK* Add one more to the jar!").queue();
				
			}
			
			if(eName.equals("wjnewhouse")) {
				
				channel.addReactionById(messageID, "E:471815289580027904").queue();
				
				
			}
			
			
			return;
		}
	
	}
	
	public void onMessageUpdate(MessageUpdateEvent event) {
		
		if (event.getAuthor().isBot()){
			
			return;
			
		} else {
		
			Message message = event.getMessage();
			String author = event.getAuthor().getName();
			String eName = event.getMember().getEffectiveName();
			String text = message.getContentDisplay();
			MessageChannel channel = event.getChannel();
		
		
			channel.sendMessage(eName + " is trying to gaslight you!").queue();
			channel.sendMessage(text).queue();
			return;
		}
		
		
	}
	
}