/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.entity.Brick;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Jiri
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BrickServiceTest {

    @Autowired
    private BrickService service;


    @Test
    public void testBlah() {
        try {
//            service.create(new Brick());
        } catch (UnsupportedOperationException e) {

        }

        int x = 1;
    }

    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}
}
