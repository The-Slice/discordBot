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
				
			if(naughtyText.contains("fuck") || naughtyText.contains("shit") || naughtyText.contains("ass") || naughtyText.contains("damn")) {
				
				channel.sendMessage("This is a christian server please no swearsies").queue();
				
			}
			
			if(eName.equals("wjnewhouse")) {
				
				channel.addReactionById(messageID, "E:471815289580027904").queue();
				//channel.editMessageById(messageID, "I'm a baby waaaaa").queue();
				
				//channel.sendMessage("I'll :nut: to that!").queue();
				
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