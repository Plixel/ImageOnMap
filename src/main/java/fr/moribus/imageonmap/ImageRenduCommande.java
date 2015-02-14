package fr.moribus.imageonmap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.moribus.imageonmap.map.MapType;


public class ImageRenduCommande implements CommandExecutor
{
	Player joueur;
	boolean renderName, imgSvg;
	ImageOnMap plugin;
	boolean resize, rename;
	MapType type;
	
	public ImageRenduCommande(ImageOnMap p)
	{
		plugin = p;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] arg3) 
	{
		// On vérifie si celui qui exécute la commande est bien un joueur
		if (!ImgUtility.VerifierIdentite(sender))
			return false;
		
		joueur = (Player) sender;
		resize = false;
		rename = true;
		
		if(joueur.hasPermission("imageonmap.userender"))
		{
			
		}
		else
		{
			joueur.sendMessage("You are not allowed to use this command ( " + arg1.getName() + " )!");
			return false;
		}
		
		if (arg3.length < 1)
		{
			joueur.sendMessage(ChatColor.RED + "You must enter image url.");
		    return false;
		}
		if(!arg3[0].startsWith("http"))
		{
			joueur.sendMessage("You must enter a valid URL.");
			return true;
		}
		else if(arg3[0].startsWith("https"))
		{
			joueur.sendMessage("WARNING: you have entered a secure HTTP link, ImageOnMap can't guarantee " +
					"that the image is downloadable");
			return true;
		}
		
		if(arg3.length >= 2)
		{
			try
			{
				type = Enum.valueOf(MapType.class, arg3[1]);
			}
			catch(IllegalArgumentException ex)
			{
				joueur.sendMessage("Specified map type doesn't exist");
			}
			
		}
		
		
		TacheTraitementMap tache = new TacheTraitementNouvelleMap(joueur, arg3[0], type, resize, rename);
		tache.runTaskTimer(plugin, 0, 5);
		
		return true;
	}

}