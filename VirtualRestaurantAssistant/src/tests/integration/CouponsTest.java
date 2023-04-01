package tests.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Coupons;

/**
 * This is the integration testing class for Coupon features.
 * */
class CouponsTest {
	
	DBTests test;
	Coupons coupon;

	@BeforeEach
	void setUp() throws Exception {
		test = new DBTests();
		coupon = new Coupons(DBTests.con);
		try {
			test.clearDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@AfterEach
	void tearDown() throws Exception {
		try {
			test.clearDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testAddCoupon() throws SQLException {
		String expectedPassed = "Coupon Added!";
		String expectedInvalid = "Enter valid Coupon.";
		String expectedDuplicate = "Coupon exists.";
		
		assertEquals(expectedPassed, coupon.addCoupon(20));
		assertEquals(expectedDuplicate, coupon.addCoupon(20));
		assertEquals(expectedInvalid, coupon.addCoupon(0));
	}

	@Test
	void testRemoveCoupon() throws SQLException {
		String expectedPassed = "Coupon removed successfully!";
		String expectedInvalid = "This discount value is NOT in the database!";
		
		coupon.addCoupon(20);
		assertEquals(expectedPassed, coupon.removeCoupon("20"));
		assertEquals(expectedInvalid, coupon.removeCoupon("11"));
	}

	@Test
	void testActivateCoupon() throws SQLException {
		coupon.addCoupon(20);
		coupon.addCoupon(40);
		
		// coupon not in database
		assertFalse(coupon.activateCoupon("25"));
		
		// valid coupon in database
		assertTrue(coupon.activateCoupon("20"));
	}

	@Test
	void testGetActiveCoupon() throws SQLException {
		coupon.addCoupon(20);
		coupon.addCoupon(40);
		
		coupon.activateCoupon("20");
		assertEquals(0.2, coupon.getActiveCoupon());
		
		coupon.activateCoupon("40");
		assertEquals(0.4, coupon.getActiveCoupon());
	}

	@Test
	void testDisableCoupons() throws SQLException {
		coupon.addCoupon(20);
		coupon.addCoupon(40);
		
		coupon.activateCoupon("20");
		coupon.disableCoupons();
		
		assertEquals(0.0, coupon.getActiveCoupon());
	}

	@Test
	void testDisplayCoupons() throws SQLException {
		coupon.addCoupon(20);
		coupon.addCoupon(40);
		coupon.activateCoupon("20");
		
		ArrayList<String> c1 = new ArrayList<>();
		ArrayList<String> c2 = new ArrayList<>();
		ArrayList<ArrayList<String>> c3 = new ArrayList<>();
		
		ArrayList<ArrayList<String>> compare = coupon.displayCoupons();
		
		c1.add("20.0");
		c1.add("1");
		c2.add("40.0");
		c2.add("0");
		c3.add(c1);
		c3.add(c2);
		
		for(ArrayList<String> item : compare) {
			if(c3.contains(item)) {
				c3.remove(item);
			}
		}

		assertTrue(c3.size() == 0);
	}
	
	@Test
	void testCouponExists() throws SQLException {
		coupon.addCoupon(30);
		assertTrue(coupon.couponExists(0.3));
		assertFalse(coupon.couponExists(0.4));
	}

}
