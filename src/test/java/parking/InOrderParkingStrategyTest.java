package parking;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InOrderParkingStrategyTest {

	@Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
	    * With using Mockito to mock the input parameter */

      //private Receipt createReceipt(ParkingLot parkingLot, Car car)
      ParkingLot parkingLot = new ParkingLot("oocl", 10);
      Car car = new Car("audi");
      InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
      Receipt receipt = inOrderParkingStrategy.createReceipt(parkingLot, car);
      assertEquals("oocl", receipt.getParkingLotName());
      assertEquals("audi", receipt.getCarName());
  }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */
      InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
      Car car = new Car("audi");
      Receipt receipt = inOrderParkingStrategy.createNoSpaceReceipt(car);
      assertEquals("No Parking Lot", receipt.getParkingLotName());
    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt(){

	    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */

    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */

    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */

    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */

    }


}
