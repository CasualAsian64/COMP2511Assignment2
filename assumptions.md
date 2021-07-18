# COMP2511 Major Project: Loop Mania
### Assumptions

### UML Diagram
- The goals are associated with the character AND the application
- Vampire is associated with VampireCastle building
- Zombie is associated with ZombiePitt
- Character is associated with Shop


### Game world
- There is always one complete loop that has the hero’s castle on some tile along the path.
- No entity can spawn outside of the tiles provided for the game world.

### Goals
- Goals cannot have two AND goals and two other AND goals OR'ed together for e.g. (Exp && Gold) || (Exp && Loops)

### Character
- Character attacks after all allied soldiers have attacked. 
- Character attacks before the enemies. 
- The character will only travel clockwise around the world path.


### Allied Soldier
- The allied soldier(s) will take all damage inflicted from enemies.
- Only when there are no allied soldiers, the character will be wounded in battle.
- A player character can attain multiple allied soldiers at a time.
- The allied soldier will always attack first, even before the player character.


### Items
- All items can be destroyed by the human player using the bin icon and gold is obtained in return.
- ‘The One Ring’ item can be sold for experience and gold
- Potions can be sold for experience and gold.
- When the character loses all its health, if the player has ‘The One Ring’ item, then that item will be removed from the inventory and the character will have full health.
- ‘The One Ring’ item can only be obtained after killing an enemy
- The character can only hold onto one ‘The One Ring’ item until it has been used.


### Enemies
- There can be one or more enemies in a single battle.
- Only one enemy can occupy a path tile
- Vampires can move the fastest amongst the slugs and zombies
- Vampires critical rate (A float value between 0 and 1)
- Zombies critical rate (A float value between 0 and 1)
- In a battle with more than one enemy, when the player wins the rewards distributed by each individual enemy are added together.
- Enemies attack after all allied soldiers and the character have attacked.


### Equipment
- The Helmet and Armour defensive items will increase the player’s defense statistic
- Each equipment piece has an endurance attribute that will cause the equipment to break after the character has participated in multiple battles


### Buffs
- A character can withhold multiple buffs such as increases in damage or defense.
- Enemies can also withhold multiple buffs
- The ‘trance’ effect will disappear after a certain amount of turns


### Buildings 
- All building cards can be sold by the human player using the bin icon
- A building can exist without a card (the Hero’s castle) 
- Once a card is placed, it persists on the map and cannot be taken off (except for the trap). 
- Two cards cannot be placed on the same tile. 
- Traps will not harm the character if the character walks over it.


### Inventory management 
- Any items dropped by the enemies will appear in the top-left most slot of the inventory slots that are available.
- If the inventory is full, then the incoming item will be sold for experience and gold.
- Only one item can be stored per slot.
- Any building card dropped by enemies will appear in the left-most slot in the card slots that is available.
- If the card slot is full, then the incoming card will be sold for experience and gold.
- Special items such as ‘The one ring’ and potions are stored separately from the inventory slot since they have a different user interaction.


### Shop
- The shop will always sell potions.
- Equipment sold in the shop will be randomised and only two will be available to the character every time they open the shop.
- Each item in the shop can be bought in bulk (0..* items can be bought at one time).


### Frontend
- The size of the game application is modelled for the game to be played on desktops.
- The human player is using a mouse and keyboard in order to interact with the user interface.
- The language of the game is in English and all text entities will be in English.



### MILESTONE 2 

### Shop
- The shops stocks all items and all are avialable for purchase every time the shop is visited. 
- There is no limit to the number of items you can purchase, provided you have enough gold.



### Buildings
- Once placed down, all buildings will persist on the tile they are placed on (excluding the trap). 



### Items
- Weapons have unlimited durability 


### AlliedSoldier
- There is no limit to the number of AlliedSoldiers the character has. 

