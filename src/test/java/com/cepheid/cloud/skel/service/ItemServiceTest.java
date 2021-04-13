package com.cepheid.cloud.skel.service;

import com.cepheid.cloud.skel.model.Description;
import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.model.ItemState;
import com.cepheid.cloud.skel.repository.ItemRepository;
import com.cepheid.cloud.skel.service.item.ItemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ItemServiceTest {

    private static String DEFAULT_TEST_DESCRIPTION = "TEST_DESCRIPTION";
    private static String DEFAULT_TEST_NAME = "TEST_NAME";
    private static Long DEFAULT_TEST_ID = 1l;

    private static String UPDATED_TEST_NAME = "UPDATED_TEST_NAME";

    @Mock
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    private Item item;

    @Before
    public void setup() {
        item = populteDefaultItem();
    }

    /**
     * Populates default Item
     * @return
     */
    private Item populteDefaultItem() {
        item = new Item();
        item.setName(DEFAULT_TEST_NAME);
        item.setState(ItemState.VALID);
        item.setId(DEFAULT_TEST_ID);
        item.setDescriptions(defaultDescription());
        return item;
    }

    /**
     * Populates default Description
     * @return
     */
    private Set<Description> defaultDescription() {
        Set<Description> descriptionSet = new HashSet<>();
        descriptionSet.add(new Description(DEFAULT_TEST_DESCRIPTION));
        return descriptionSet;
    }

    /**
     * Test Get item based on id
     */
    @Test
    public void testGetItem() {
        Long id = 2l;
        Item result = itemService.getItem(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    /**
     * Test get all items with pagination
     */
    @Test
    public void testGetAllItems() {
        List<Item> result = itemService.getAllItem(new PageRequest(0, 10));
        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

    /**
     * Test for Create new Items
     */
    @Test
    public void testCreateItem() {
        item.setId(null);
        Item result = itemService.createItem(item);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(result.getName(), DEFAULT_TEST_NAME);
    }

    /**
     * Test for update existing item
     */
    @Test
    public void testUpdateItem() {
        item.setId(null);
        Item result = itemService.createItem(item);
        assertNotNull(result);
        assertNotNull(result.getId());

        result.setName(UPDATED_TEST_NAME);

        Item updatedResult = itemService.updateItem(result);
        assertNotNull(updatedResult);
        assertEquals(updatedResult.getName(), UPDATED_TEST_NAME);
    }
}
