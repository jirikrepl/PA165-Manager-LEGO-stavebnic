package cz.muni.fi.PA165;

import junit.framework.TestCase;
import org.junit.runner.RunWith;


import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TransactionConfiguration(defaultRollback = true)
//@Transactional
public abstract class AbstractIntegrationTest extends TestCase{
}