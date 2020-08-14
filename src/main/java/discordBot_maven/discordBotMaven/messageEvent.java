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

	public ArduinoInterface aI;

	public messageEvent(ArduinoInterface aI) {

		this.aI = aI;

	}

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

						.prepareStatement("INSERT INTO messagehistory(`messageID`, `message`) VALUES(?, ?)");

				pst.setLong(1, messageID);

				pst.setString(2, text);

				pst.executeUpdate();

				String[] words = text.split(" ");

				for (int i = 0; i < words.length; i++) {

					pst = connect

							.prepareStatement("SELECT swear FROM swears WHERE swear = ?;");

					pst.setString(1, words[i]);

					ResultSet rs = pst.executeQuery();

					if (rs.first()) {			

						channel.sendMessage("*CLINK*\nAdd one more to the jar!").queue();
						
						aI.runOnce();

						pst = connect

								.prepareStatement("SELECT name FROM users WHERE name = ?;");

						pst.setString(1, eName);

						rs = pst.executeQuery();

						if (rs.first()) {
							rs.close();

							pst.close();

							pst = connect

									.prepareStatement("UPDATE users SET gdp = gdp + 1 WHERE name = ?;");

							pst.setString(1, eName);

							pst.executeUpdate();

							pst.close();

						} else {

							rs.close();

							pst.close();

							pst = connect

									.prepareStatement("INSERT INTO users(`name`, `gdp`) VALUES(?, ?)");

							pst.setString(1, eName);

							pst.setInt(2, 1);

							pst.executeUpdate();

							pst.close();

						}

					} else {

					}

				}

				if (eName.equals("wjnewhouse")) {

					channel.addReactionById(messageID, "E:498681154531098654").queue();

				}

				connect.close();

			} catch (Exception e) {

				System.out.println(e);

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

				String strSelect = "SELECT message FROM messagehistory WHERE messageID = ?";

				PreparedStatement pst = connect.prepareStatement(strSelect);

				pst.setLong(1, messageID);

				ResultSet rset = pst.executeQuery();

				rset.next();

				textEdit = rset.getString("message");

				channel.sendMessage(
						eName + " is trying to gaslight you!\nThe original message was \"" + textEdit + "\"").queue();

				connect.close();

			} catch (Exception e) {

				System.out.println(e);

			}

		}

	}

}