package com.BigBananaNotifier;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
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

	@Subscribe
	public void onItemSpawned(ItemSpawned itemSpawned){
		if(getRegionId() == BABA_REGION_ID){
			int itemId = itemSpawned.getItem().getId();

			if(itemId == ItemID.BIG_BANANA) {
				audioPlayer.playAudio();
				log.info("Big Banana spawned");
			}
		}
	}

	private int getRegionId() {
		LocalPoint localPoint = client.getLocalPlayer().getLocalLocation();
		return WorldPoint.fromLocalInstance(client, localPoint).getRegionID();
	}

	@Provides
	BigBananaNotifierConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BigBananaNotifierConfig.class);
	}
}
