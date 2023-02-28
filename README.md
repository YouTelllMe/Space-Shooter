# Space Shooter 


***Project Description***

My project will be an arcade style space shooter. 
In my vision, the player (the sprite) will first spawn
in the middle of the screen when the game begins. Enemies
moving in a pattern will shoot projectiles at the player
and contact with the bullets would damage the player. The
player would have a health bar, which would decrease as
the player gets hurt. If the player's health gets below 0,
the game will terminate. The player is able to move to dodge
projectiles and shooter projectiles of their own to kill
the enemies. 

There will be different tiers of enemies (identified by
different shapes, color, or size). Stronger enemies 
will take more hits to kill. If enemies are not killed off,
they will remain and wander on the screen and as more 
spawn, more will be present and shooting, making the game
more challenging. As the game drags on, enemies will spawn
more often and stronger enemies will spawn more as well, 
making the game more challenging. As long as the game 
doesn't end (player doesn't die), the game will continue
indefinitely. 

There will be powerups and boosts to collect, such as 
more health, or power boost to player projectiles for a 
durations. There will be an icon that's counting down
to showcase this duration. 

***Who will use it?***

Anyone interested to play an arcade style space shooter
will use it.

***Why is this project interesting?***

I think this project is interesting because I love video
games and the concept is very flexible and customizable 
with the whole power-up system. 


***User Stories***
- As a user, I want to be able to move my sprite to position for shooting or dodging
- As a user, I want to be able to shoot projectiles and damage/kill enemies
- As a user, I want to be able to powerup at certain scores
- As a user, I want to be able to start a game and restart the game after a GameOver
- As a user, I want to be able to pause the game and unpause the game
- As a user, I want to be able to save my entire current game 
- As a user, I want to be able to load and continue with a save I've saved previously

***Citation***
- Data persistence code modelled after CPSC210-JsonSerializationDemo Repository (https://github.students.cs.ubc.ca/CPSC
210/JsonSerializationDemo)


***Instructions for Grader***
- You can generate a bullet to the game by left clicking with your mouse / trackpad
- You can move the player with the "WASD" keys
- You can kill enemies with the bullets you shoot
- You will get powerups every 15 seconds
- GameOver when you get to 0 HP or below
- My visual component is my JFrame window
- You can save the state of my application by clicking on the save button in the top bar (2nd from left)
- You can load the state of my application by clicking on the load button in the top bar (3rd from left)

***Phase 4: Task 2***
Tue Nov 29 21:51:13 PST 2022- Game Started.
Tue Nov 29 21:51:15 PST 2022- Friendly Bullet Added.
Tue Nov 29 21:51:15 PST 2022- Friendly Bullet Added.
Tue Nov 29 21:51:16 PST 2022- Type 3Enemy Spawned
Tue Nov 29 21:51:16 PST 2022- Unfriendly Bullet Added.
Tue Nov 29 21:51:17 PST 2022- Friendly Bullet Added.
Tue Nov 29 21:51:17 PST 2022- Friendly Bullet Added.
Tue Nov 29 21:51:18 PST 2022- Type 2Enemy Spawned
Tue Nov 29 21:51:18 PST 2022- Unfriendly Bullet Added.
Tue Nov 29 21:51:18 PST 2022- Unfriendly Bullet Added.
Tue Nov 29 21:51:18 PST 2022- Game Paused.
Tue Nov 29 21:51:19 PST 2022- Game Unpaused.
Tue Nov 29 21:51:20 PST 2022- Player Shot: Bullet Removed.
Tue Nov 29 21:51:20 PST 2022- Type 4Enemy Spawned
Tue Nov 29 21:51:20 PST 2022- Unfriendly Bullet Added.
Tue Nov 29 21:51:20 PST 2022- Unfriendly Bullet Added.
Tue Nov 29 21:51:20 PST 2022- Unfriendly Bullet Added.
Tue Nov 29 21:51:21 PST 2022- Type 2Enemy Spawned
Tue Nov 29 21:51:21 PST 2022- Unfriendly Bullet Added.
Tue Nov 29 21:51:21 PST 2022- Unfriendly Bullet Added.
Tue Nov 29 21:51:21 PST 2022- Unfriendly Bullet Added.
Tue Nov 29 21:51:21 PST 2022- Unfriendly Bullet Added.
Tue Nov 29 21:51:22 PST 2022- Player Shot: Bullet Removed.
Tue Nov 29 21:51:22 PST 2022- Player Shot: Bullet Removed.
Tue Nov 29 21:51:23 PST 2022- Type 3Enemy Spawned
Tue Nov 29 21:51:23 PST 2022- Unfriendly Bullet Added.
Tue Nov 29 21:51:23 PST 2022- Unfriendly Bullet Added.
Tue Nov 29 21:51:23 PST 2022- Unfriendly Bullet Added.
Tue Nov 29 21:51:23 PST 2022- Unfriendly Bullet Added.
Tue Nov 29 21:51:23 PST 2022- Unfriendly Bullet Added.
Tue Nov 29 21:51:23 PST 2022- Game Over.
Tue Nov 29 21:51:24 PST 2022- Game Over.

***Phase 4: Task 3***
Looking at my UML diagram, I notice that the Object class was redundant. I believe my original intent was to make
powerups "collectable" in the game where it would have a coordinate and the player would go retrieve it by contacting 
it, and in that way it would be similar to a bullet since bullets also detect collision, but I ended up changing the 
game mechanic so that the player gets a stat-powerup every 15 seconds instead. 

I would also refrator my tests for sure since I have had to tweek a lot of constants such as windowsize, coordinate 
system (Jframe uses a coordinate system that started from 0 but I used a system where 0 was the middle), the speed of 
the player/enemy/bullet. I would've done the single point of control style tests if I could so that I wouldn't have to 
change the values everytime something changes.

Lastly, I believe that a lot of repetition can be avoided with the use of abstract classes and by making more methods. 
Also, the Game class has too many responsibilities and functionality. I think I could make the game variables, such as
game paused, game over, time, score, into another class (called configuration or something?), which would help improve
reability and cohesion. 