package com.luma.testsuite;

import com.luma.customlisteners.CustomListeners;
import com.luma.pages.HomePage;
import com.luma.pages.MensPantPage;
import com.luma.pages.OverNightDufflePage;
import com.luma.pages.ShoppingCartPage;
import com.luma.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class GearTest extends BaseTest {
    ShoppingCartPage shoppingCartPage;
    OverNightDufflePage overNightDufflePage;
    HomePage homePage;
    MensPantPage mensPantPage;
    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        shoppingCartPage = new ShoppingCartPage();
        overNightDufflePage = new OverNightDufflePage();
        homePage = new HomePage();
        mensPantPage = new MensPantPage();
    }
    @Test(groups = {"sanity", "regression"})
    public void userShouldAddProductSuccessFullyToShoppingCart() throws InterruptedException {
        Thread.sleep(1000);
        homePage.mouseHoverToGearMenu();
        //* Click on Bags
        homePage.clickOnBag();
        //* Click on Product Name ‘Overnight Duffle’
        overNightDufflePage.clickOnOvernightDuffleBag();
        //* Verify the text ‘Overnight Duffle’
        Assert.assertEquals(overNightDufflePage.verifyTextOverNigtDuffleBag(), "Overnight Duffle");
        //* Change Qty 3
        overNightDufflePage.changeQtyOfBag();
        //* Click on ‘Add to Cart’ Button.
        overNightDufflePage.clickOnAddToCart();
        //* Verify the text ‘You added Overnight Duffle to your shopping cart.’
        Assert.assertEquals(overNightDufflePage.verifyTextAddedToShoppingCartSuccessfully(), "You added Overnight Duffle to your shopping cart.");
        //* Click on ‘shopping cart’ Link into message
        overNightDufflePage.clickOnShoppingCart();
        //* Verify the product name ‘Cronus Yoga Pant’
        Assert.assertEquals(shoppingCartPage.verifyTextBagName(), "Overnight Duffle");
        //* Verify the Qty is ‘3’
        Assert.assertEquals(shoppingCartPage.verifyQtyOfProduct(), "3");
        //* Verify the product price ‘$135.00’
        Assert.assertEquals(shoppingCartPage.verifyProductPrice(), "$135.00");
        //* Change Qty to ‘5’
        shoppingCartPage.changeQtyOfProduct();
        //* Click on ‘Update Shopping Cart’ button
        shoppingCartPage.updateShoppingCart();
        Thread.sleep(1000);
        Assert.assertEquals(shoppingCartPage.verifyUpdatedProductPrice(), "$225.00");
    }
}
