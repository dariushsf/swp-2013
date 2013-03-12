package rogue;

import jade.core.World;
import jade.ui.TiledTermPanel;
import jade.util.datatype.ColoredChar;
import java.awt.Color;
import java.util.HashMap;
import rogue.creature.Monster;
import rogue.creature.Player;
import rogue.level.Level;

public class Rogue
{
    public static void main(String[] args) throws InterruptedException
    {
    	HashMap<String, Boolean> switches = new HashMap<String, Boolean>();
    	
    	for (String sw : args) {
    		switches.put(sw, true);
    	}
    	System.out.println(switches.toString());
    	
        TiledTermPanel term = TiledTermPanel.getFramedTerminal("Grey Zone");
        term.registerTile("dungeon.png", 1, 1, ColoredChar.create('#'));
        term.registerTile("dungeon.png", 1, 34, ColoredChar.create('.'));
        term.registerTile("dungeon.png", 1, 17, ColoredChar.create('@'));
        term.registerTile("dungeon.png", 17, 17, ColoredChar.create('D', Color.red));
        term.registerTile("dungeon.png", 17, 34, ColoredChar.create('+'));
        term.registerTile("texfeld.png", 1, 1, ColoredChar.create('A'));
        term.registerTile("texfeld.png", 17, 1, ColoredChar.create('B'));
        term.registerTile("texfeld.png", 34, 1, ColoredChar.create('C'));
        term.registerTile("texfeld.png", 1, 17, ColoredChar.create('D'));
        term.registerTile("texfeld.png", 17, 17, ColoredChar.create('E'));
        term.registerTile("texfeld.png", 34, 17, ColoredChar.create('F'));
        term.registerTile("texfeld.png", 1, 34, ColoredChar.create('G'));
        term.registerTile("texfeld.png", 17, 34, ColoredChar.create('H'));
        term.registerTile("texfeld.png", 34, 34, ColoredChar.create('I'));
        
        term.registerMenu();
        
        Player player = new Player(term);
        World world = new Level(80, 40, player);
        world.addActor(new Monster(ColoredChar.create('D', Color.red)));
        term.registerCamera(player, 40,20);
  
        /*
         * buggy animated startscreen
         * 
        ScreenThread startScreen = new ScreenThread(term,"startscreen",4);
        while(term.getKey()!='s'){}
        startScreen.kill();
        */
        
        term.bufferFile("screens/startscreen/title.txt");
        term.refreshScreen();
        
        while(term.getKey()!='s'){}
        while(!player.expired())
        {
            term.clearBuffer();
            //term.bufferStatusBar();

            if(switches.containsKey("a")) term.bufferWorld(world);
            term.bufferFov(player);
            if (term.getMenu("Inv")) term.bufferBoxes(world, "text/itemEXP/frame_item_exp.txt","text/itemEXP/h1_item_exp.txt");    	
            term.refreshScreen();
            world.tick();
        }
 
        term.clearBuffer();
        term.bufferFile("screens/endscreen/end.txt");
        term.refreshScreen();
        while(term.getKey()!='q'){}
        /*
         * buggy animated endscreen
         * 
        ScreenThread endScreen = new ScreenThread(term,"endscreen",1);
        while(term.getKey()!='q'){}
        endScreen.kill();
        */
        System.exit(0);
    }
}
