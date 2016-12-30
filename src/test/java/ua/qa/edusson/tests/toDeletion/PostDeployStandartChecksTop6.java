package ua.qa.edusson.tests.toDeletion;

import org.testng.annotations.Test;
import ua.qa.edusson.tests.*;

/**
 * Created by tester on 15.11.2016.
 */
public class PostDeployStandartChecksTop6 extends TestBase {

    StandartCheckOnlyEasyBiddingSitesPayPalTests easyBiddingPayPal = new StandartCheckOnlyEasyBiddingSitesPayPalTests();
    StandartCheckEdussonProductionTests edusson = new StandartCheckEdussonProductionTests();
    StandartCheckNotEasyBiddingSitesEdussonGatewayTests notEasyBiddingGateway = new StandartCheckNotEasyBiddingSitesEdussonGatewayTests();
    StandartCheckNotEasyBiddingSitesPayPalTests notEasyBiddingPayPal = new StandartCheckNotEasyBiddingSitesPayPalTests();
    StandartCheckVasChooseBestWritersPayPalTests withVasChooseBestWritersPayPalTests = new StandartCheckVasChooseBestWritersPayPalTests();
    ChatTests chat = new ChatTests();

    @Test//(enabled = false)
    public void postDeployStandartCheck1() {
        edusson.standartCheck_CreditCard_Production_Edusson();
    }

    @Test
    public void testChat() {
        chat.chatTesting();
    }

    @Test//(enabled = false)
    public void postDeployStandartCheck2() {
        app.driver.get("http://edubirdie.com/");
        notEasyBiddingGateway.standartCheck_CreditCard_Production_Not_EasyBidding();
    }

    @Test
           // (enabled = false)
    public void postDeployStandartCheck3() {
        app.driver.get("http://papersowl.com/");
        notEasyBiddingGateway.standartCheck_CreditCard_Production_Not_EasyBidding();
    }

    @Test

    public void postDeployStandartCheck4() {
        app.driver.get("http://essays.studymoose.com/");
        easyBiddingPayPal.standartCheck_Only_EasyBidding_Production();
    }

    @Test
    public void postDeployStandartCheck5() {
        app.driver.get("http://customwriting.com/");
        easyBiddingPayPal.standartCheck_Only_EasyBidding_Production();
    }

    @Test //(enabled = false)
    public void postDeployStandartCheck6() {
        app.driver.get("http://essayvikings.com/");
        withVasChooseBestWritersPayPalTests.standartCheck_Vas_ChooseBestWriter_Production();

    }

}



