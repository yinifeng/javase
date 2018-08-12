package com.hubo.patterns.strategy.github;

/**
 * 策略模式
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Green dragon spotted ahead!");
        DragonSlayer dragonSlayer=new DragonSlayer(new MeleeStrategy());
        dragonSlayer.goToBattle();
        System.out.println("Red dragon emerges.");
        dragonSlayer.changeStrategy(new ProjectileStrategy());
        dragonSlayer.goToBattle();
        System.out.println("Black dragon lands before you.");
        dragonSlayer.changeStrategy(new SpellStrategy());
        dragonSlayer.goToBattle();
        
        //java8 lamda Strategy pattern
        System.out.println("------------------");
        dragonSlayer= new DragonSlayer(() -> {
            System.out.println("With your Excalibur you severe the dragon's head!");
        });
        dragonSlayer.goToBattle();
        System.out.println("Red dragon emerges.");
        dragonSlayer.changeStrategy(()->{
            System.out.println("You shoot the dragon with the magical crossbow and it falls dead on the ground!");
        });
        dragonSlayer.goToBattle();
    }
}
