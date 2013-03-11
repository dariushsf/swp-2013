package rogue.level;

import jade.core.World;
import jade.gen.TestGenerator;
import jade.gen.map.Laboratory_1;
import rogue.creature.Player;

public class TestLevel extends World
{
    private final static TestGenerator gen = getLevelGenerator();

    public TestLevel(int width, int height, Player player)
    {
        super(width, height);
        gen.generate(this);
        addActor(player);
    }

    private static TestGenerator getLevelGenerator()
    {
        return new Laboratory_1();
    }
    
}