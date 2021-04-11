package com.cepheid.cloud.skel;

import com.cepheid.cloud.skel.model.Description;
import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.model.ItemState;
import com.cepheid.cloud.skel.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ItemControllerTest extends TestBase {

    private Long queryItemId = 3L;
    private Long deleteItemId = 4L;
    private String updateName = "updateName";

    @Autowired
    private ItemRepository itemRepository;


    /**
     * Populate Item POJO
     * @return Enity<Item>
     */
    private Entity<Item> getItemEntity() {
        Item item = new Item();
        item.setName("King Kong");
        item.setState(ItemState.VALID);
        item.setDescriptions(getDescription());

        return Entity.entity(item, MediaType.APPLICATION_JSON);
    }

    /**
     * Populate Description POJO
     * @return Description
     */
    private Set<Description> getDescription() {
        Set<Description> result = new HashSet<>();

        Description description = new Description();
        description.setDescription("Movie");

        result.add(description);
        return result;
    }

    /**
     * Test Get Items
     * @Returs All Items present in database
     * @throws Exception
     */
    @Test
    public void testGetItems() throws Exception {
        Builder itemController = getBuilder("/api/1.0/items");
        Collection<Item> items = itemController.get(new GenericType<Collection<Item>>() {
        });
        assertThat(items.size() > 0);
    }

    /**
     * Test Get Item By id
     * @Returs Item of an Id
     * @throws Exception
     */
    @Test
    public void testGetItemById() throws Exception {
        Builder itemController = getBuilder("/api/1.0/items/" + queryItemId);
        Item item = itemController.get(Item.class);
        assertThat(item != null);
        assertThat(item.getId().equals(queryItemId));
    }

    /**
     * Test Post Item
     * @Returs Created Item
     * @throws Exception
     */
    @Test
    public void testPostItems() throws Exception {
        Builder itemController = getBuilder("/api/1.0/items");
        Item item = itemController.post(getItemEntity(), Item.class);
        assertThat(item.getId() != null);
        assertThat(item.getName().equals(getItemEntity().getEntity().getName()));
    }

    /**
     * Test Post Item which throws exception if Id is already present
     * @throws Exception
     */
    @Test(expected = Exception.class)
    public void testPostItems_whenItemIdIsNotNull_resultInException() throws Exception {
        Builder itemController = getBuilder("/api/1.0/items");
        Entity<Item> entityItem = getItemEntity();
        entityItem.getEntity().setId(1L);
        Item item = itemController.post(entityItem, Item.class);
    }

    /**
     * Test PUt Item which throws exception when id is not present
     * @throws Exception
     */
    @Test(expected = Exception.class)
    public void testPutItems_whenItemIdIsNull_resultInException() throws Exception {
        Builder itemController = getBuilder("/api/1.0/items");
        Item item = itemController.put(getItemEntity(), Item.class);
    }

    /**
     * Test Put Item
     * @Returs updated Item
     */
    @Test
    public void testPutItems() {
        Builder itemController = getBuilder("/api/1.0/items");
        Entity<Item> entityItem = getItemEntity();
        entityItem.getEntity().setId(queryItemId);
        entityItem.getEntity().setName(updateName);
        Item item = itemController.put(entityItem, Item.class);
        assertThat(item.getId() != null);
        assertThat(item.getName().equals(updateName));
    }

    /**
     * Test Delete Item
     */
    @Test
    public void testDeleteItemById() {
        Builder itemController = getBuilder("/api/1.0/items/" + deleteItemId);
        itemController.delete();
        assertThat(itemRepository.findById(deleteItemId).isPresent() == false);
    }

}
