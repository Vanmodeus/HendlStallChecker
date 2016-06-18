package mus.HendlStallChecker.test.Db;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;
import mus.HendlStallChecker.Repository.DbFactory;
import mus.HendlStallChecker.Repository.DbIntrusionRepo;
import mus.HendlStallChecker.Repository.Intrusion;
import mus.HendlStallChecker.Repository.IntrusionAlertLevel;
import mus.utility.Helper;

@RunWith(JUnit4.class)
public class DbIntrusionRepoTests extends TestCase {

	@Test
	public void Test_Intrusion_getAll() {
		DbIntrusionRepo repo = DbFactory.Instance().CreateDbIntrusionRepo();

		List<Intrusion> chicks = repo.getAll();
		assertEquals(1l, chicks.get(0).getId());
		assertEquals(IntrusionAlertLevel.CRITICAL_SPECIFIC, chicks.get(0).getLevel());
		assertEquals(1l, chicks.get(0).getId());
	}

	@Test
	public void Test_Intrusion_get() {
		DbIntrusionRepo repo = DbFactory.Instance().CreateDbIntrusionRepo();

		Intrusion intru = repo.get(1l);
		assertEquals(IntrusionAlertLevel.CRITICAL_SPECIFIC, intru.getLevel());
		assertEquals(1l, intru.getId());
	}

	@Test
	public void Test_Intrusion_insert() throws IOException {
		DbIntrusionRepo repo = DbFactory.Instance().CreateDbIntrusionRepo();
		List<Intrusion> intrus = repo.getAll();
		
		// insert into  db
		Date date = Calendar.getInstance().getTime();
		byte[] img = Helper.convertImage("imgs/fox1.jpg");
		Intrusion intru = new Intrusion(1, date, img);
		long id = repo.insert(intru);
		assertEquals(intrus.size() + 1, repo.getAll().size());
	}
}
