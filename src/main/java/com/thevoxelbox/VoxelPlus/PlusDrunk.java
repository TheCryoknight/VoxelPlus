package com.thevoxelbox.VoxelPlus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

/**
 * 
 * @author alex
 */
public class PlusDrunk implements Runnable {

	private final Player drunkplayer;
	private int drunkduration;
	//private Packet41MobEffect drunkpacket;
	//private Packet42RemoveMobEffect removepacket;
	private int taskid;
	private boolean drunkloggedout = false;
	private boolean drunkmessages = true;

	public PlusDrunk(Player user, int dur, PotionEffect effect, boolean messages) {
		drunkplayer = user;
		drunkduration = dur;
		drunkmessages = messages;

		if (drunkmessages) {
			if (drunkduration == 6800) {
				drunkplayer.sendMessage(ChatColor.GOLD + "Your liver starts and sputters and you can barely contain yourself.  Seek treatment!");
			} else {
				drunkplayer.sendMessage(ChatColor.GOLD + "The VoxeLager goes down smooth and easy.");
			}
		}
		drunkplayer.addPotionEffect(effect);

	}

	@Override
	public void run() {
		if (drunkplayer.isOnline()) {
			if (drunkloggedout) {
				if (drunkmessages) {
					drunkplayer.sendMessage(ChatColor.GOLD + "You can't just keep runing from your problems, you know...");
				}
				drunkloggedout = false;
			}
			if (drunkduration >= 5500 && drunkduration <= 5600 && drunkmessages) {
				drunkplayer.sendMessage(ChatColor.GOLD + "Better lay off the sauce, friend!");
			} else if (drunkduration >= 4000 && drunkduration <= 4100 && drunkmessages) {
				drunkplayer.sendMessage(ChatColor.GOLD + "The world curves around you like never before. Maybe you should take it easy.");
			} else if (drunkduration >= 2000 && drunkduration <= 2100 && drunkmessages) {
				drunkplayer.sendMessage(ChatColor.GOLD + "You are gonna wanna ride that feelin', brah.");
			} else if (drunkduration >= 1000 && drunkduration <= 1100 && drunkmessages) {
				drunkplayer.sendMessage(ChatColor.GOLD + "You feel tipsy.  Whee!");
			}
			drunkduration -= 200;
			if (drunkduration <= 0) {
				if (drunkmessages) {
					drunkplayer.sendMessage(ChatColor.GOLD + "You feel your buzz start to fade.");
				}
				Bukkit.getScheduler().cancelTask(taskid);
			}
		} else {
			drunkloggedout = true;
		}
	}

	public void settaskid(int id) {
		taskid = id;
	}
}