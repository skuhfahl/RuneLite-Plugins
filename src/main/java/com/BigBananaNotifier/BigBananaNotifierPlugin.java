package com.BigBananaNotifier;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.ItemID;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.ItemSpawned;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

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

	private AudioPlayer audioPlayer = new AudioPlayer();
	private final int BABA_REGION_ID = 15188;
	private int CURRENT_REGION_ID;

	@Subscribe
	public void onItemSpawned(ItemSpawned itemSpawned){
		if(CURRENT_REGION_ID == BABA_REGION_ID){
			int itemId = itemSpawned.getItem().getId();

			if(itemId == ItemID.BIG_BANANA) {
				audioPlayer.playAudio();
				log.info("Big Banana spawned");
			}
		}
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged){
		CURRENT_REGION_ID = getRegionId();
		log.info("CURRENT_REGION_ID " + CURRENT_REGION_ID);
	}
	private int getRegionId() {
		LocalPoint localPoint = client.getLocalPlayer().getLocalLocation();
		int wp = WorldPoint.fromLocalInstance(client, localPoint).getRegionID();
		log.info("world point: " + Integer.toString(wp));
		return wp;
	}

	@Provides
	BigBananaNotifierConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BigBananaNotifierConfig.class);
	}
}
