package tests.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ManagerRatings;

/**
 * This class performs integration tests on ManagerRatings class.
 * */
class DBTestsRating {

	DBTests test;
	ManagerRatings rating;
	
	@BeforeEach
	void setUp() throws Exception {
		test = new DBTests();
		rating = new ManagerRatings(DBTests.con);
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
	void testSubmitFeedback() throws SQLException {
		String orderID1 = "123456";
		String orderID2 = "123457";
		String feedback = "Great stuff!";
		int score = 5;
		
		assertEquals(true, rating.submitFeedback(orderID1, score, feedback));
		assertEquals(true, rating.submitFeedback(orderID2, score, ""));
	}
	
	@Test
	void testSubmitFeedbackFailed() throws SQLException {
		String orderID = "123456";
		String feedback = "Great stuff!";
		
		// rating score is required and score > 0
		assertFalse(rating.submitFeedback(orderID, 0, feedback));
	}

	@Test
	void testCalculateAvgRating() throws SQLException {
		rating.submitFeedback("123456", 5, "");
		rating.submitFeedback("123654", 4, "");
		double expected = 4.5;
		assertTrue(rating.calculateAvgRating() == expected);
	}

	@Test
	void testDisplayRatings() throws SQLException {
		rating.submitFeedback("123456", 5, "great");
		rating.submitFeedback("123654", 4, "decent");
		String expected = "123456\t5\tgreat\n123654\t4\tdecent\n";
		assertTrue(rating.displayRatings().contains(expected));
	}

}
