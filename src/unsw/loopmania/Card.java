package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a Card in the world
 * which doesn't move
 */
public abstract class Card extends StaticEntity {
    private static final int EXPREWARD = 20;
    private static final int GOLDREWARD = 100;
    // TODO = implement other varieties of card than VampireCastleCard
    public Card(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }


    // BELOW IS WORKING, try commenting out and applying for each other card
    // protected String cardType = "VillageCard";
    protected String cardType;

    public String getCardType() {
        return cardType;
    }

    public void removeCardAward(Statistics stats) {
        stats.setExp(stats.getExp() + EXPREWARD);
        stats.setGold(stats.getGold() + GOLDREWARD);
    }
    
}
