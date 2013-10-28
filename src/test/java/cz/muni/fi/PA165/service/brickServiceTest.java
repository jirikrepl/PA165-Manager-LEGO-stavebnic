/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.PA165.service;

import junit.framework.TestCase;

/**
 *
 * @author Jiri
 */
public class brickServiceTest extends TestCase {
    
    public brickServiceTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        BrickService service = new BrickServiceImpl();        
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testBlah() {
        int x = 1;
    }

    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}
}
