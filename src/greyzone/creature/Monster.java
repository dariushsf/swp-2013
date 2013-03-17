package greyzone.creature;

import java.awt.Color;
import java.util.Arrays;
import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;

public class Monster extends Creature
{
	public Monster() {
		this(ColoredChar.create('D', Color.red));
	}
    public Monster(ColoredChar face)
    {
        super(face);
    }

    @Override
    public void act()
    {
        move(Dice.global.choose(Arrays.asList(Direction.values())));
       
    }
}
