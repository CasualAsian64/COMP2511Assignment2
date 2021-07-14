package unsw.loopmania;

import java.util.List;

import javafx.scene.image.Image;

public class ImageSelector {
    public Image getImage(Card card, List<Image> allImages) {
        Image image;
        String cardType = card.getCardType();
        System.out.println(cardType);
        switch(cardType) {
            case "VampireCastleCard":
                image = allImages.get(0);
                return image;
            case "ZombiePitCard":
                image = allImages.get(1);
                return image;
            case "TowerCard":
                image = allImages.get(2);
                return image;
            case "VillageCard":
                image = allImages.get(3);
                return image;
            case "BarracksCard":
                image = allImages.get(4);
                return image;
            case "TrapCard":
                image = allImages.get(5);
                return image;
            case "CampfireCard":
                image = allImages.get(6);
                return image;
        }
        return null;
    }

    public Image getImage(Item item, List<Image> allImages) {
        Image image;

        return null;
    }
}
