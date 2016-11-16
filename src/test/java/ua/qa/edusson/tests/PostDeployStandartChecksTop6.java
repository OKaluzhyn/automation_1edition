package ua.qa.edusson.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by tester on 15.11.2016.
 */
public class PostDeployStandartChecksTop6 extends TestBase {

    StandartCheckOnlyEasyBiddingSitesPayPalTests easyBiddingPayPal = new StandartCheckOnlyEasyBiddingSitesPayPalTests();
    StandartCheckEdussonProductionTests edusson = new StandartCheckEdussonProductionTests();
    StandartCheckNotEasyBiddingSitesEdussonGatewayTests notEasyBiddingGateway = new StandartCheckNotEasyBiddingSitesEdussonGatewayTests();
    StandartCheckNotEasyBiddingSitesPayPalTests notEasyBiddingPayPal = new StandartCheckNotEasyBiddingSitesPayPalTests();

    @BeforeClass

    public void setUp() throws Exception {
        app.init();
    }

    @AfterClass
    public void tearDown() {
        app.stop();
    }


   /* @Test
    public void postDeployStandartCheck1() throws Exception {
        edusson.standartCheck_CreditCard_Production_Edusson();
    }

    @Test
    public void postDeployStandartCheck2() {
        app.driver.get("http://edubirdie.com/");
        notEasyBiddingGateway.standartCheck_CreditCard_Production_Not_EasyBidding();
    }

    @Test
    public void postDeployStandartCheck3() {
        app.driver.get("http://papersowl.com/");
        notEasyBiddingGateway.standartCheck_CreditCard_Production_Not_EasyBidding();

    }

 */   @Test
    public void postDeployStandartCheck4() {
        app.driver.get("http://essays.studymoose.com/");

        easyBiddingPayPal.standartCheck_Only_EasyBidding_Production();

    }


    @Test
    public void postDeployStandartCheck5() {
        app.driver.get("http://customwriting.com/ ");
        easyBiddingPayPal.standartCheck_Only_EasyBidding_Production();

    }

    @Test
    public void postDeployStandartCheck6() {
        app.driver.get("http://essayvikings.com/");
        notEasyBiddingPayPal.standartCheck_PayPal_Production_Not_EasyBidding();

    }

}



