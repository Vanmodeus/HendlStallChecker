package mus.HendlStallChecker.test.Db;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;
import mus.HendlStallChecker.Repository.Chicken;
import mus.HendlStallChecker.Repository.DbFactory;
import mus.HendlStallChecker.Repository.DbChickenRepo;

@RunWith(JUnit4.class)
public class DbChickenRepoTests extends TestCase {

	@Test
	public void Test_Chicken_getAll() {
		DbChickenRepo repo = DbFactory.Instance().CreateDbChickenRepo();
		
		List<Chicken> chicks = repo.getAll();
		assertEquals(2, chicks.size());
		assertEquals(1l, chicks.get(0).getId());
		assertEquals("Henrietta", chicks.get(0).getName());
		assertEquals("3635CA329000", chicks.get(0).getNfcid());
		assertTrue(chicks.get(0).isInside() || !chicks.get(0).isInside());
	}
	
	@Test
	public void Test_Chicken_get() {
		DbChickenRepo repo = DbFactory.Instance().CreateDbChickenRepo();
		
		Chicken chick = repo.get(1);
		assertEquals(1l, chick.getId());
		assertEquals("Henrietta", chick.getName());
		assertEquals("3635CA329000", chick.getNfcid());
		assertTrue(chick.isInside() || !chick.isInside());
	}
	
	@Test
	public void Test_Chicken_changeAvailability() {
		DbChickenRepo repo = DbFactory.Instance().CreateDbChickenRepo();
		
		Chicken chick = repo.get(1);
		assertNotNull(chick);

		repo.changeAvailability(1);
		
		assertEquals(!chick.isInside(), repo.get(1).isInside());
	}
}
