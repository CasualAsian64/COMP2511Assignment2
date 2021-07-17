package unsw.loopmania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ShopController {
    
    private LoopManiaWorld world; 

    private Shop shop; 

    private MenuSwitcher gameSwitcher;


    // public ShopController(Shop shop, LoopManiaWorld world) {
    //     this.world = world;
    //     this.shop = shop; 
    // }


    public void setGameSwitcher(MenuSwitcher gameSwitcher){
        this.gameSwitcher = gameSwitcher;
    }

    @FXML
    public Label availableGold = new Label(); 


    @FXML
    public Button minusConsumableButton = new Button();
    @FXML
    public Button plusConsumableButton = new Button();
    @FXML
    public TextField consumableNumberField = new TextField(); 


    @FXML
    public Button minusSwordButton = new Button();
    @FXML
    public Button plusSwordButton = new Button();
    @FXML
    public TextField swordNumberField = new TextField(); 


    @FXML
    public Button minusStakeButton = new Button();
    @FXML
    public Button plusStakeutton = new Button();
    @FXML
    public TextField stakeNumberField = new TextField(); 

    @FXML
    public Button minusStaffButton = new Button();
    @FXML
    public Button plusStaffButton = new Button();
    @FXML
    public TextField staffNumberField = new TextField(); 

    @FXML
    public Button minusHelmetButton = new Button();
    @FXML
    public Button plusHelmetButton = new Button();
    @FXML
    public TextField helmetNumberField = new TextField(); 

    @FXML
    public Button minusArmourButton = new Button();
    @FXML
    public Button plusArmourButton = new Button();
    @FXML
    public TextField armourNumberField = new TextField(); 


    @FXML
    public Button minusShieldButton = new Button();
    @FXML
    public Button plusShieldButton = new Button();
    @FXML
    public TextField shieldNumberField = new TextField(); 


    @FXML 
    public Label totalNumberField = new Label(); 
    @FXML
    public Button purchaseButton = new Button(); 


    @FXML
    public Label notEnoughGold = new Label(); 
    @FXML
    public Label purchaseSucecssful = new Label(); 


    @FXML 
    public Button leaveShopButton = new Button(); 
    



    @FXML 
    public void handleConsumableMinus(ActionEvent event) { 

        int value = Integer.parseInt(consumableNumberField.getText());
        consumableNumberField.setText(Integer.toString(value - 1));
        shop.subtractPotion();
    }

    @FXML 
    public void handleConsumablePlus(ActionEvent event) { 
        int value = Integer.parseInt(consumableNumberField.getText());
        consumableNumberField.setText(Integer.toString(value + 1));
        shop.addPotion();
    }


    @FXML 
    public void handleSwordMinus(ActionEvent event) {
        int value = Integer.parseInt(swordNumberField.getText());
        swordNumberField.setText(Integer.toString(value - 1)); 
        shop.subtractSword();
    }

    @FXML 
    public void handleSwordPlus(ActionEvent event) {
        int value = Integer.parseInt(swordNumberField.getText());
        swordNumberField.setText(Integer.toString(value + 1)); 
        shop.addSword();
    }

    @FXML 
    public void handleStakeMinus(ActionEvent event) { 
        int value = Integer.parseInt(stakeNumberField.getText());
        stakeNumberField.setText(Integer.toString(value - 1));
        shop.subtractStake();
    }

    @FXML 
    public void handleStakePlus(ActionEvent event) { 
        int value = Integer.parseInt(stakeNumberField.getText());
        stakeNumberField.setText(Integer.toString(value + 1));
        shop.addStake();
    }

    @FXML 
    public void handleStaffMinus(ActionEvent event) { 
        int value = Integer.parseInt(staffNumberField.getText());
        staffNumberField.setText(Integer.toString(value - 1));
        shop.subtractStaff();
    }

    @FXML 
    public void handleStaffPlus(ActionEvent event) { 
        int value = Integer.parseInt(staffNumberField.getText());
        staffNumberField.setText(Integer.toString(value + 1));
        shop.addStaff();
    }

    @FXML 
    public void handleHelmetMinus(ActionEvent event) { 
        int value = Integer.parseInt(helmetNumberField.getText());
        helmetNumberField.setText(Integer.toString(value - 1));
        shop.subtractHelmet();
    }

    @FXML 
    public void handleHelmetPlus(ActionEvent event) { 
        int value = Integer.parseInt(helmetNumberField.getText());
        helmetNumberField.setText(Integer.toString(value + 1));
        shop.addHelmet();
    }

    @FXML 
    public void handleArmourMinus(ActionEvent event) { 
        int value = Integer.parseInt(armourNumberField.getText());
        armourNumberField.setText(Integer.toString(value - 1));
        shop.subtractArmour();
    }

    @FXML 
    public void handleArmourPlus(ActionEvent event) { 
        int value = Integer.parseInt(armourNumberField.getText());
        armourNumberField.setText(Integer.toString(value + 1));
        shop.addArmour();
    }

    @FXML 
    public void handleShieldMinus(ActionEvent event) { 
        int value = Integer.parseInt(shieldNumberField.getText());
        shieldNumberField.setText(Integer.toString(value - 1));
        shop.subtractShield();;
    }

    @FXML 
    public void handleShieldPlus(ActionEvent event) { 
        int value = Integer.parseInt(shieldNumberField.getText());
        shieldNumberField.setText(Integer.toString(value + 1));
        shop.addShield();
    }

    @FXML
    public void handlePurchase(ActionEvent event) {

        if (shop.sufficientFunds()) {
            shop.finaliseTransaction(world.getCharacter().getStats(), world.getUnequippedInventoryItems());
            // TODO - trigger the purchase successful pop up

        }        

        else {
            // TODO - trigger the not enough gold
            System.out.println("not enough gold!");
        }

    }

    @FXML
    public void handleLeaveShop(){
        // TODO - set isInShop to false;
        gameSwitcher.switchMenu();
    }


}
