package com.BigBananaNotifier;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class BigBananaNotifierPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(BigBananaNotifierPlugin.class);
		RuneLite.main(args);
	}
}