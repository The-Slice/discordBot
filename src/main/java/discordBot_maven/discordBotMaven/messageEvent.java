package discordBot_maven.discordBotMaven;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.MessageUpdateEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.*;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class messageEvent extends ListenerAdapter {
	//Every Sent Message
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.getAuthor().isBot()) {
			return;
		} else {
			Message message = event.getMessage();
			long messageID = Long.parseLong(event.getMessageId());
			String author = event.getAuthor().getName();
			String eName = event.getMember().getEffectiveName();
			String text = message.getContentDisplay();
			String naughtyText = message.getContentDisplay().toLowerCase();
			MessageChannel channel = event.getChannel();

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				// public static Connection getConnection(String url, String user, String
				// password)
				Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/discordbot", "root", "");

				PreparedStatement pst = connect
						.prepareStatement("INSERT INTO messageHistory(`messageID`, `message`) VALUES(?, ?)");
				pst.setLong(1, messageID);
				pst.setString(2, text);

				pst.executeUpdate();

				connect.close();

			} catch (Exception e) {
				System.out.println(e);
			}

			if (naughtyText.contains("fuck") || naughtyText.contains("shit") || naughtyText.contains("ass")
					|| naughtyText.contains("damn")) { // Replace with swear file to match against in the future

				channel.sendMessage("*CLINK* Add one more to the jar!").queue();

			}

			if (eName.equals("wjnewhouse")) {

				channel.addReactionById(messageID, "E:498681154531098654").queue();

			}

			return;
		}

	}

	public void onMessageUpdate(MessageUpdateEvent event) {

		if (event.getAuthor().isBot()) {

			return;

		} else {

			Message message = event.getMessage();
			long messageID = Long.parseLong(event.getMessageId());
			String author = event.getAuthor().getName();
			String eName = event.getMember().getEffectiveName();
			String text = message.getContentDisplay();
			String textEdit = "";
			String naughtyText = message.getContentDisplay().toLowerCase();
			MessageChannel channel = event.getChannel();

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				// public static Connection getConnection(String url, String user, String
				// password)
				Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/discordbot", "root", "");

				Statement stmt = connect.createStatement();

				String strSelect = "SELECT message FROM messageHistory WHERE messageID = ?";

				PreparedStatement pst = connect.prepareStatement(strSelect);

				pst.setLong(1, messageID);

				ResultSet rset = pst.executeQuery();

				rset.next();
				textEdit = rset.getString("message");

				channel.sendMessage(eName + " is trying to gaslight you!").queue();
				channel.sendMessage("The original message was \"" + textEdit + "\"").queue();

				connect.close();

			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}
}