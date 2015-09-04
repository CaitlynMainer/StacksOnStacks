package com.tierzero.stacksonstacks;

import com.tierzero.stacksonstacks.api.IngotFinder;
import com.tierzero.stacksonstacks.compat.CompatHandler;
import com.tierzero.stacksonstacks.util.Config;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = SoS.MODID, name = SoS.MODID, version = SoS.VERSION)
public class SoS {
	public static final String VERSION = "0.8";
	public static final String MODID = "StacksOnStacks";
	public static final String TEXTURE_BASE = MODID + ":";
	public static BlockIngotPile ingotPile;
	public static Config config;
	@SidedProxy(serverSide = "com.tierzero.stacksonstacks.CommonProxy", clientSide = "com.tierzero.stacksonstacks.ClientProxy", modId = MODID)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		config = new Config(e.getSuggestedConfigurationFile());
		config.load();
		CompatHandler.config();
		proxy.registerTiles();
		ingotPile = new BlockIngotPile("ingotPile");
		MinecraftForge.EVENT_BUS.register(new IngotPileHandler());
		config.save();
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.registerRenders();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {

	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent e) {
		IngotFinder.registerIngots();
		proxy.serverLoad(e);
	}

}
