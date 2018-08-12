package com.hubo.patterns.strategy.github;

public class MeleeStrategy implements DragonSlayingStrategy{
    
    @Override
    public void execute() {
        System.out.println("With your Excalibur you sever the dragon's head!");
    }
}
