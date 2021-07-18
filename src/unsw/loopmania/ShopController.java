package unsw.loopmania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.beans.binding.Bindings;


public class ShopController {
    
    // TODO - need to feed this 
    private LoopManiaWorld world;

    private MenuSwitcher gameSwitcher;


    // public ShopController(Shop world.getShop(), LoopManiaWorld world) {
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
        world.getShop().subtractPotion();
    }

    @FXML 
    public void handleConsumablePlus(ActionEvent event) { 
        int value = Integer.parseInt(consumableNumberField.getText());
        consumableNumberField.setText(Integer.toString(value + 1));
        world.getShop().addPotion();
    }


    @FXML 
    public void handleSwordMinus(ActionEvent event) {
        int value = Integer.parseInt(swordNumberField.getText());
        swordNumberField.setText(Integer.toString(value - 1)); 
        world.getShop().subtractSword();
    }

    @FXML 
    public void handleSwordPlus(ActionEvent event) {
        int value = Integer.parseInt(swordNumberField.getText());
        swordNumberField.setText(Integer.toString(value + 1)); 
        world.getShop().addSword();
    }

    @FXML 
    public void handleStakeMinus(ActionEvent event) { 
        int value = Integer.parseInt(stakeNumberField.getText());
        stakeNumberField.setText(Integer.toString(value - 1));
        world.getShop().subtractStake();
    }

    @FXML 
    public void handleStakePlus(ActionEvent event) { 
        int value = Integer.parseInt(stakeNumberField.getText());
        stakeNumberField.setText(Integer.toString(value + 1));
        world.getShop().addStake();
    }

    @FXML 
    public void handleStaffMinus(ActionEvent event) { 
        int value = Integer.parseInt(staffNumberField.getText());
        staffNumberField.setText(Integer.toString(value - 1));
        world.getShop().subtractStaff();
    }

    @FXML 
    public void handleStaffPlus(ActionEvent event) { 
        int value = Integer.parseInt(staffNumberField.getText());
        staffNumberField.setText(Integer.toString(value + 1));
        world.getShop().addStaff();
    }

    @FXML 
    public void handleHelmetMinus(ActionEvent event) { 
        int value = Integer.parseInt(helmetNumberField.getText());
        helmetNumberField.setText(Integer.toString(value - 1));
        world.getShop().subtractHelmet();
    }

    @FXML 
    public void handleHelmetPlus(ActionEvent event) { 
        int value = Integer.parseInt(helmetNumberField.getText());
        helmetNumberField.setText(Integer.toString(value + 1));
        world.getShop().addHelmet();
    }

    @FXML 
    public void handleArmourMinus(ActionEvent event) { 
        int value = Integer.parseInt(armourNumberField.getText());
        armourNumberField.setText(Integer.toString(value - 1));
        world.getShop().subtractArmour();
    }

    @FXML 
    public void handleArmourPlus(ActionEvent event) { 
        int value = Integer.parseInt(armourNumberField.getText());
        armourNumberField.setText(Integer.toString(value + 1));
        world.getShop().addArmour();
    }

    @FXML 
    public void handleShieldMinus(ActionEvent event) { 
        int value = Integer.parseInt(shieldNumberField.getText());
        shieldNumberField.setText(Integer.toString(value - 1));
        world.getShop().subtractShield();
    }

    @FXML 
    public void handleShieldPlus(ActionEvent event) { 
        int value = Integer.parseInt(shieldNumberField.getText());berField.setText(Integer.toString(value + 1));
        world.getShop().addShield();
    }

    @FXML
    public void handlePurchase(ActionEvent event) {

        if (world.getShop().sufficientFunds()) {
            world.getShop().finaliseTransaction(world.getCharacter().getStats(), world.getUnequippedInventoryItems());
            // TODO - trigger the purchase successful pop up

        }        

        else {
            // TODO - trigger the not enough gold
            System.out.println("not enough gold!");
        }

    }

   

    @FXML
    public void handleLeaveShop(){
        // TODO - getting an exception here, to investigate further
        gameSwitcher.switchMenu();
        world.getShop().setShopping(false);
    }


    @FXML
    public void initialize() {
        consumableNumberField.setText("0");
        swordNumberField.setText("0");
        stakeNumberField.setText("0");
        staffNumberField.setText("0");
        helmetNumberField.setText("0");
        armourNumberField.setText("0");
        shieldNumberField.setText("0");
        totalNumberField.setText("0");
        availableGold.setText("0");


        // TODO - bindings not working for labels
        // availableGold.textProperty().bind(Bindings.convert(world.getCharacter().getStats().goldValueProperty()));

    }



}
