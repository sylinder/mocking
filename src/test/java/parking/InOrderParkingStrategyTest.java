package parking;

import org.junit.Test;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//use mock or spy ???
public class InOrderParkingStrategyTest {

	@Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
	    * With using Mockito to mock the input parameter */

      ParkingLot parkingLot = mock(ParkingLot.class);
      Car car = mock(Car.class);
      when(parkingLot.getName()).thenReturn("oocl");
      when(parkingLot.isFull()).thenReturn(false);
      when(car.getName()).thenReturn("audi");
      InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
      Receipt receipt = inOrderParkingStrategy.createReceipt(parkingLot, car);
      assertEquals("oocl", receipt.getParkingLotName());
      assertEquals("audi", receipt.getCarName());
  }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */
      Car car = mock(Car.class);
      InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
      Receipt receipt = inOrderParkingStrategy.createNoSpaceReceipt(car);
      assertEquals(ParkingStrategy.NO_PARKING_LOT, receipt.getParkingLotName());
    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt(){

	    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */

      ParkingLot parkingLot = spy(new ParkingLot("oocl", 20));
      doReturn(true).when(parkingLot).isFull();
      List<ParkingLot> parkingLots = new ArrayList<>();
      parkingLots.add(parkingLot);

      Car car = spy(new Car("audi"));
      InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
      Receipt receipt = inOrderParkingStrategy.park(parkingLots, car);

      verify(inOrderParkingStrategy, times(0)).createReceipt(parkingLot, car);
      verify(inOrderParkingStrategy, times(1)).createNoSpaceReceipt(car);
    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */
      ParkingLot parkingLot = spy(new ParkingLot("cosco",10));
      doReturn(false).when(parkingLot).isFull();
      List<ParkingLot> parkingLots = new ArrayList<>();
      parkingLots.add(parkingLot);

      Car car = spy(new Car("BMW"));
      InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
      Receipt receipt = inOrderParkingStrategy.park(parkingLots, car);

      verify(inOrderParkingStrategy,times(1)).createReceipt(parkingLot,car);
      verify(inOrderParkingStrategy, times(0)).createNoSpaceReceipt(car);
    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */
      ParkingLot parkingLot = spy(new ParkingLot("oocl", 20));
      doReturn(true).when(parkingLot).isFull();
      List<ParkingLot> parkingLots = new ArrayList<>();
      parkingLots.add(parkingLot);
      Car car = spy(new Car("audi"));

      InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
      Receipt receipt = inOrderParkingStrategy.park(parkingLots, car);

      verify(inOrderParkingStrategy, times(1)).createNoSpaceReceipt(car);

    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */
      ParkingLot firstParkingLot = mock(ParkingLot.class);
      ParkingLot secondParkingLot = mock(ParkingLot.class);

      when(firstParkingLot.isFull()).thenReturn(true);
      when(secondParkingLot.isFull()).thenReturn(false);
      when(secondParkingLot.getName()).thenReturn("oocl");
      List<ParkingLot> parkingLots = new ArrayList<>();
      parkingLots.add(firstParkingLot);
      parkingLots.add(secondParkingLot);
      Car car = mock(Car.class);
      when(car.getName()).thenReturn("audi");

      InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
      Receipt receipt = inOrderParkingStrategy.park(parkingLots, car);

      verify(inOrderParkingStrategy, times(0)).createNoSpaceReceipt(car);
      verify(inOrderParkingStrategy, times(1)).createReceipt(secondParkingLot, car);
      assertEquals("audi", receipt.getCarName());
      assertEquals("oocl", receipt.getParkingLotName());
    }


}
