package mus.HendlStallChecker.test.Db;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;
import mus.HendlStallChecker.Repository.DbFactory;

@RunWith(JUnit4.class)
public class DbFactoryTests extends TestCase {

	@Test
	public void TestRepoCreation() {
		Assert.assertNotNull(DbFactory.Instance());
		Assert.assertNotNull(DbFactory.Instance().CreateDbChickenRepo());
	}
}
