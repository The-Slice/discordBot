package discordBot_maven.discordBotMaven;

import javax.security.auth.login.LoginException;
import net.dv8tion.jda.*;
import net.dv8tion.jda.core.*;
//import net.dv8tion.jda.core.AccountType;
//import net.dv8tion.jda.core.JDA;
//import net.dv8tion.jda.core.JDABuilder;
//import net.dv8tion.jda.core.entities.*;
//import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
//import net.dv8tion.jda.core.events.*;
//import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.entities.MessageChannel;


public class main {
	public static void main(String[] args) {
		
		try {
			JDA api = (JDA) new JDABuilder("NDk1NzQxMzQ1NzUxNjI5ODQ2.DpGimg.aq53PYLgfSfzOfyt4DkQFLPMPMY")
			.addEventListener(new messageEvent()).build();
			api.awaitReady();
			
			
			
		} catch (LoginException e) {
			
			System.out.println("Failed to build.");
			e.printStackTrace();
			
		} catch (InterruptedException e) {

            e.printStackTrace();
        }
		
		
		
		
		
	}
}