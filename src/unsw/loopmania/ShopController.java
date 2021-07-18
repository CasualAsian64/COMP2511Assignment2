package unsw.loopmania;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.beans.binding.Bindings;


public class ShopController {
    
    private LoopManiaWorld world;

    private Shop shop;

    private MenuSwitcher gameSwitcher;


    public ShopController(LoopManiaWorld world) {
        this.world = world;
        this.shop = world.getShop();
    }
    
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
    public Label consumableLabel = new Label();


    @FXML
    public Button minusSwordButton = new Button();
    @FXML
    public Button plusSwordButton = new Button();
    @FXML
    public TextField swordNumberField = new TextField();
    
    @FXML
    public Label swordLabel = new Label();


    @FXML
    public Button minusStakeButton = new Button();
    @FXML
    public Button plusStakeutton = new Button();
    @FXML
    public TextField stakeNumberField = new TextField(); 

    @FXML Label stakeLabel = new Label();

    @FXML
    public Button minusStaffButton = new Button();
    @FXML
    public Button plusStaffButton = new Button();
    @FXML
    public TextField staffNumberField = new TextField(); 

    @FXML
    public Label staffLabel = new Label();

    @FXML
    public Button minusHelmetButton = new Button();
    @FXML
    public Button plusHelmetButton = new Button();
    @FXML
    public TextField helmetNumberField = new TextField(); 

    @FXML
    public Label helmetLabel = new Label();

    @FXML
    public Button minusArmourButton = new Button();
    @FXML
    public Button plusArmourButton = new Button();
    @FXML
    public TextField armourNumberField = new TextField(); 

    @FXML
    public Label armourLabel = new Label();


    @FXML
    public Button minusShieldButton = new Button();
    @FXML
    public Button plusShieldButton = new Button();
    @FXML
    public TextField shieldNumberField = new TextField(); 

    @FXML
    public Label shieldLabel = new Label();


    @FXML 
    public Label totalNumberField = new Label(); 
    @FXML
    public Button purchaseButton = new Button(); 


    @FXML
    public Label notEnoughGold = new Label(); 
    @FXML
    public Label purchaseSuccessful = new Label(); 


    @FXML 
    public Button leaveShopButton = new Button(); 
    




    @FXML 
    public void handleConsumableMinus(ActionEvent event) { 
        world.getShop().subtractPotion();
    }

    @FXML 
    public void handleConsumablePlus(ActionEvent event) { 

        shop.addPotion();
    }


    @FXML 
    public void handleSwordMinus(ActionEvent event) {

        world.getShop().subtractSword();
    }

    @FXML 
    public void handleSwordPlus(ActionEvent event) {

        world.getShop().addSword();
    }

    @FXML 
    public void handleStakeMinus(ActionEvent event) { 

        world.getShop().subtractStake();
    }

    @FXML 
    public void handleStakePlus(ActionEvent event) { 

        world.getShop().addStake();
    }

    @FXML 
    public void handleStaffMinus(ActionEvent event) { 

        world.getShop().subtractStaff();
    }

    @FXML 
    public void handleStaffPlus(ActionEvent event) { 

        world.getShop().addStaff();
    }

    @FXML 
    public void handleHelmetMinus(ActionEvent event) { 

        world.getShop().subtractHelmet();
    }

    @FXML 
    public void handleHelmetPlus(ActionEvent event) { 

        world.getShop().addHelmet();
    }

    @FXML 
    public void handleArmourMinus(ActionEvent event) { 

        world.getShop().subtractArmour();
    }

    @FXML 
    public void handleArmourPlus(ActionEvent event) { 

        world.getShop().addArmour();
    }

    @FXML 
    public void handleShieldMinus(ActionEvent event) { 

        world.getShop().subtractShield();
    }

    @FXML 
    public void handleShieldPlus(ActionEvent event) { 

        world.getShop().addShield();
    }

     /**
    * Display whether the purchase is successful or if there is not enough gold 
    * to complete the transaction after pressing the purchase button. 
    * @param event
    */
    @FXML
    public void handlePurchase(ActionEvent event) {

        if (world.getShop().sufficientFunds(world)) {
            world.getShop().finaliseTransaction(world.getCharacter().getStats(), world);
            notEnoughGold.setVisible(false);
            purchaseSuccessful.setVisible(true);
        }        

        else {

            purchaseSuccessful.setVisible(false);
            notEnoughGold.setVisible(true);
        }

    }

   

    @FXML
    public void handleLeaveShop(){
        gameSwitcher.switchMenu();

        // Set both labels to not visible whenever the shop is left. 
        notEnoughGold.setVisible(false);
        purchaseSuccessful.setVisible(false);

        world.getShop().setShopping(false);
    }


    @FXML
    public void initialize() {

        totalNumberField.setText("0");
        availableGold.setText("0");

        
        availableGold.textProperty().bind(Bindings.convert(world.getCharacter().getStats().goldValueProperty()));
        totalNumberField.textProperty().bind(Bindings.convert(world.getShop().totalValue()));

        consumableLabel.textProperty().bind(Bindings.convert(world.getShop().potionsValueProperty()));
        swordLabel.textProperty().bind(Bindings.convert(world.getShop().swordsValueProperty()));
        stakeLabel.textProperty().bind(Bindings.convert(world.getShop().stakesValueProperty()));
        staffLabel.textProperty().bind(Bindings.convert(world.getShop().staffsValueProperty()));
        helmetLabel.textProperty().bind(Bindings.convert(world.getShop().helmetsValueProperty()));
        armourLabel.textProperty().bind(Bindings.convert(world.getShop().armoursValueProperty()));
        shieldLabel.textProperty().bind(Bindings.convert(world.getShop().shieldsValueProperty()));

    }



}
