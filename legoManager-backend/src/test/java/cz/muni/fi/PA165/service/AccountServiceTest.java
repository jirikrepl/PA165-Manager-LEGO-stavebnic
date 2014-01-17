package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.api.dto.AccountDto;
import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.dao.AccountDao;
import cz.muni.fi.PA165.daoDtoConversion.AccountConversion;
import cz.muni.fi.PA165.daoDtoConversion.CategoryConversion;
import cz.muni.fi.PA165.entity.Account;
import junit.framework.TestCase;
import org.mockito.ArgumentCaptor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


/**
 *
 * @author Tomas Kopecky
 */
public class AccountServiceTest extends TestCase {

    private AccountServiceImpl accountService;
    private AccountDao accountDao;

    @Override
    protected void setUp() throws Exception {
        accountDao = mock(AccountDao.class);
        accountService = new AccountServiceImpl();
        accountService.setAccountDao(accountDao);
    }

    /**
     * test create account method on service layer
     */
    public void testCreateAccount() {
        System.out.println("testing create account on Service layer");
        // test null argument
        try {
            accountService.create(null);
            fail();
        } catch (DataAccessExceptionService ex) {
        }

        // on Dao layer, create method with null argument was never called
        verify(accountDao, never()).create(null);

        // test creation with ServiceDto object
        AccountDto accountDto = createAccountDto("name", "pwd", Boolean.FALSE);
        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);
        accountService.create(accountDto);
        Account account = AccountConversion.convertToEntity(accountDto);
        // verify that create method was called on mock accountDao object
        verify(accountDao).create(captor.capture());
        assertEquals(account.getId(), captor.getValue().getId());
        assertEquals(account.getName(), captor.getValue().getName());
        assertEquals(new ShaPasswordEncoder(256).encodePassword(account.getPassword(),null), captor.getValue().getPassword());
        assertEquals(account.getIsAdmin(), captor.getValue().getIsAdmin());
    }

    /**
     * helper method to create AccountDto
     */
    private AccountDto createAccountDto(String name, String password, Boolean isAdmin) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(new Long(5));
        accountDto.setName(name);
        accountDto.setPassword(password);
        accountDto.setIsAdmin(isAdmin);
        return accountDto;
    }

    /**
     * test update method of account class on service layer
     */
    public void testUpdateAccount() {
        System.out.println("testing update account on Service layer");
        // test null argument
        try {
            accountService.update(null);
            fail();
        } catch (DataAccessExceptionService ex) {
        }

        AccountDto accountDto = createAccountDto("name", "pwd", Boolean.FALSE);
        Long id = accountDto.getId();
        accountService.create(accountDto);
        ArgumentCaptor<Account> captor1 = ArgumentCaptor.forClass(Account.class);
        verify(accountDao).create(captor1.capture());
        assertEquals(accountDto.getId(), captor1.getValue().getId());
        assertEquals(accountDto.getName(), captor1.getValue().getName());
        assertEquals(new ShaPasswordEncoder(256).encodePassword(accountDto.getPassword(),null), captor1.getValue().getPassword());
        assertEquals(accountDto.getIsAdmin(), captor1.getValue().getIsAdmin());

        //update
        accountDto.setName("name2");
        accountDto.setPassword("pwd2");
        accountDto.setIsAdmin(Boolean.TRUE);

        accountService.update(accountDto);
        ArgumentCaptor<Account> captor2 = ArgumentCaptor.forClass(Account.class);
        verify(accountDao).update(captor2.capture());
        assertEquals(accountDto.getId(), captor2.getValue().getId());
        assertEquals(accountDto.getName(), captor2.getValue().getName());
        assertEquals(new ShaPasswordEncoder(256).encodePassword(accountDto.getPassword(),null), captor2.getValue().getPassword());
        assertEquals(accountDto.getIsAdmin(), captor2.getValue().getIsAdmin());

        // mock dao
        when(accountDao.findById(id)).thenReturn(AccountConversion.convertToEntity(accountDto));
        AccountDto retrievedDto = accountService.findById(id);
        assertEquals(retrievedDto, accountDto);
        assertEquals(accountDto.getId(), retrievedDto.getId());
        assertEquals(accountDto.getName(), retrievedDto.getName());
        assertEquals(accountDto.getPassword(), retrievedDto.getPassword());
        assertEquals(accountDto.getIsAdmin(), retrievedDto.getIsAdmin());
    }

    /**
     * test deletion of account Object on service layer
     */
    public void testDeleteAccount() {
        System.out.println("testing delete account on Service layer");
        // test null argument
        try {
            accountService.delete(null);
            fail();
        } catch (DataAccessExceptionService ex) {
        }

        AccountDto accountDto = createAccountDto("name", "pwd", Boolean.FALSE);
        accountService.delete(accountDto.getId());
        Long id = accountDto.getId();
        verify(accountDao).delete(id);

        when(accountDao.findById(id)).thenThrow(new DataAccessException("Account doesn't exist.") {});
        try {
            AccountDto deleted = accountService.findById(id);
            fail();
        }
        catch (DataAccessException e) {}
    }

    /**
     * test find all objects on service layer
     */
    public void testFindAll() {
        System.out.println("testing findAll method on Service layer");
        // test when no entities are in table (returs empty list)
        List<AccountDto> accountDtoList = new ArrayList<AccountDto>();
        when(accountDao.findAll()).thenReturn(new ArrayList<Account>());

        List<AccountDto> returnedAccountDtoList = accountService.findAll();
        assertTrue(returnedAccountDtoList.isEmpty());

        // return some entities
        // create list of dto objects
        AccountDto firstDto = createAccountDto("name", "pwd", Boolean.FALSE);
        AccountDto secondDto = createAccountDto("name2", "pwd2", Boolean.TRUE);
        accountDtoList.add(firstDto);
        accountDtoList.add(secondDto);

        // create list of category entity objects
        Account firstEntity = new Account();
        firstEntity.setId(firstDto.getId());
        firstEntity.setName("name");
        firstEntity.setPassword("pwd");
        firstEntity.setIsAdmin(Boolean.FALSE);
        Account secondEntity = new Account();
        secondEntity.setId(secondDto.getId());
        secondEntity.setName("name2");
        secondEntity.setPassword("pwd2");
        secondEntity.setIsAdmin(Boolean.TRUE);
        List<Account> entities = new ArrayList<Account>();
        entities.add(firstEntity);
        entities.add(secondEntity);
        when(accountDao.findAll()).thenReturn(entities);
        List<AccountDto> accountDtoList2 = accountService.findAll();
        assertTrue(accountDtoList2.size() == 2);
        for (AccountDto ac : accountDtoList) {
            assertTrue(accountDtoList2.contains(ac));
        }
    }

    /**
     * test findById method on service Layer
     */
    public void testFindById() {
        System.out.println("testing findAll method on Service layer");
        // test null argument
        try {
            accountService.findById(null);
            fail();
        } catch (DataAccessExceptionService ex) {
        }

        AccountDto accountDto = createAccountDto("name", "pwd", Boolean.FALSE);

        // test, when no entity is in table
        when(accountDao.findById(accountDto.getId())).thenThrow(new DataAccessException("Account is null.") {});
        AccountDto returnedDto;
        try {
            returnedDto = accountService.findById(accountDto.getId());
            fail();
        }
        catch (DataAccessException e) {}

        // test - save some entity in table, return it by id

        AccountDto accountDto2 = createAccountDto("name", "pwd", Boolean.FALSE);
        accountDto2.setId(new Long(6));
        Account account = AccountConversion.convertToEntity(accountDto2);
        Long id = accountDto2.getId();
        when(accountDao.findById(accountDto2.getId())).thenReturn(account);

        returnedDto = accountService.findById(accountDto2.getId());
        assertEquals(accountDto2.getId(), returnedDto.getId());
        assertEquals(accountDto2.getIsAdmin(), returnedDto.getIsAdmin());
        assertEquals(accountDto2.getName(), returnedDto.getName());
        assertEquals(accountDto2.getPassword(), returnedDto.getPassword());
    }

    /**
     * test method findByName on serviceLayer
     */
    public void testFindByName() {
        System.out.println("testing findMyName method on Service layer");
        // test null argument
        try {
            accountService.findByName(null);
            fail();
        } catch (DataAccessExceptionService ex) {
        }

        AccountDto accountDto = createAccountDto("name", "pwd", Boolean.FALSE);

        // test, when no entity is in table
        when(accountDao.findByName(accountDto.getName())).thenThrow(new DataAccessException("Account is null.") {});
        AccountDto returnedDto;
        try {
            returnedDto = accountService.findByName(accountDto.getName());
            fail();
        }
        catch (DataAccessException e) {}

        // test - save some entity in table, return it by id
        AccountDto accountDto2 = createAccountDto("name2", "pwd", Boolean.FALSE);
        when(accountDao.findByName(accountDto2.getName())).thenReturn(AccountConversion.convertToEntity(accountDto2));
        returnedDto = accountService.findByName(accountDto2.getName());
        assertEquals(accountDto2.getId(), returnedDto.getId());
        assertEquals(accountDto2.getIsAdmin(), returnedDto.getIsAdmin());
        assertEquals(accountDto2.getName(), returnedDto.getName());
        assertEquals(accountDto2.getPassword(), returnedDto.getPassword());
    }
}
