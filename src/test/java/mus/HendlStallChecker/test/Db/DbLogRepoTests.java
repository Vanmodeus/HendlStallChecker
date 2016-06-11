package mus.HendlStallChecker.test.Db;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;
import mus.HendlStallChecker.Repository.Chicken;
import mus.HendlStallChecker.Repository.DbFactory;
import mus.HendlStallChecker.Repository.DbLogRepo;
import mus.HendlStallChecker.Repository.Log;
import mus.HendlStallChecker.Repository.DbChickenRepo;

@RunWith(JUnit4.class)
public class DbLogRepoTests extends TestCase {

	@Test
	public void Test_Log_getAll() {
		DbLogRepo repo = DbFactory.Instance().CreateDbLogRepo();

		List<Log> logs = repo.getAll(1);
		assertTrue(logs.size() > 0);
	}

	@Test
	public void Test_Log_insert() {
		DbLogRepo repo = DbFactory.Instance().CreateDbLogRepo();
		List<Log> logs = repo.getAll(1);
		@SuppressWarnings("deprecation")
		Date date = new Date(2016, 6, 1);
		repo.insert(new Log(1l, date));
		assertEquals(logs.size() +1, repo.getAll(1).size());
	}
}
