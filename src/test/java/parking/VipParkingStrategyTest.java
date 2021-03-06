package parking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VipParkingStrategyTest {

    @Mock
    CarDao carDao;

    @InjectMocks
    VipParkingStrategy vipParkingStrategy;

	@Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 4, Write a test case on VipParkingStrategy.park()
	    * With using Mockito spy, verify and doReturn */
      ParkingLot parkingLot = mock(ParkingLot.class);
      when(parkingLot.isFull()).thenReturn(true);
      Car car = mock(Car.class);
      List<ParkingLot> parkingLots = new ArrayList<>();
      parkingLots.add(parkingLot);

      VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
      doReturn(true).when(vipParkingStrategy).isAllowOverPark(car);
      Receipt receipt = vipParkingStrategy.park(parkingLots, car);

      verify(vipParkingStrategy, times(1)).createReceipt(parkingLot, car);
  }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.isFull()).thenReturn(true);
        Car car = mock(Car.class);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        doReturn(false).when(vipParkingStrategy).isAllowOverPark(car);
        Receipt receipt = vipParkingStrategy.park(parkingLots,car);

        verify(vipParkingStrategy,times(1)).createNoSpaceReceipt(car);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */

        Car car = mock(Car.class);
        when(car.getName()).thenReturn("A BMW");
        when(carDao.isVip(car.getName())).thenReturn(true);
        boolean allowOverPark = vipParkingStrategy.isAllowOverPark(car);
        assertEquals(true, allowOverPark);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        Car car = mock(Car.class);
        when(car.getName()).thenReturn("B BMW");
        when(carDao.isVip(car.getName())).thenReturn(true);
        boolean allowOverPark = vipParkingStrategy.isAllowOverPark(car);
        assertEquals(false, allowOverPark);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */

        Car car = mock(Car.class);
        when(car.getName()).thenReturn("A BMW");
        when(carDao.isVip(car.getName())).thenReturn(false);
        boolean allowOverPark = vipParkingStrategy.isAllowOverPark(car);
        assertEquals(false, allowOverPark);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */

        Car car = mock(Car.class);
        when(car.getName()).thenReturn("B BMW");
        when(carDao.isVip(car.getName())).thenReturn(false);
        boolean allowOverPark = vipParkingStrategy.isAllowOverPark(car);
        assertEquals(false, allowOverPark);
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
