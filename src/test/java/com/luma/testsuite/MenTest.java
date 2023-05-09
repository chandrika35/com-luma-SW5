package com.luma.testsuite;

import com.luma.customlisteners.CustomListeners;
import com.luma.pages.HomePage;
import com.luma.pages.MensPantPage;
import com.luma.pages.OverNightDufflePage;
import com.luma.pages.ShoppingCartPage;
import com.luma.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class MenTest extends BaseTest {
    HomePage homePage;
    MensPantPage mensPantPage;
    ShoppingCartPage shoppingCartPage;
    OverNightDufflePage overNightDufflePage;
    public void inIt() {
        shoppingCartPage = new ShoppingCartPage();
        overNightDufflePage = new OverNightDufflePage();
        homePage = new HomePage();
        mensPantPage = new MensPantPage();
    }
    @Test(groups = {"sanity", "regression"})
    public void userShouldAddProductSuccessFullyToShoppingCart() throws InterruptedException {
        Thread.sleep(1000);
        homePage.mouseHoverToMenMenu();
        homePage.mouseHoverToBottomMenu();
        homePage.mouseHoverAndClickOnPantMenu();
        mensPantPage.mouseHoverAndClickOnPantSize();
        mensPantPage.mouseHoverAndClickOnBlackColour();
        mensPantPage.mouseHoverAndClickOnAddToCart();
        Assert.assertEquals(mensPantPage.verifyProductAddedToCartSuccessfully(), "You added Cronus Yoga Pant to your shopping cart.");
        mensPantPage.clickOnShoppingCart();
        Assert.assertEquals(shoppingCartPage.verifyTextShoppingCart(), "Shopping Cart");
        Assert.assertEquals(shoppingCartPage.verifyProductName(), "Cronus Yoga Pant");
        Assert.assertEquals(shoppingCartPage.verifyProductSize(), "32");
        Assert.assertEquals(shoppingCartPage.verifyProductColour(), "Black");
    }
}
