package com.BigBananaNotifier;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.io.IOException;

@Slf4j
@PluginDescriptor(
	name = "Big Banana Notifier"
)
public class BigBananaNotifierPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private BigBananaNotifierConfig config;

	private AudioPlayer audioPlayer;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Example started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged) {
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Big Banana Plugin", null);
			audioPlayer = new AudioPlayer();
			audioPlayer.playAudio();
		}
	}

	@Subscribe

	@Provides
	BigBananaNotifierConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BigBananaNotifierConfig.class);
	}
}
