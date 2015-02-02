package com.innometrics.integration.app.recommendation.resource;

import com.innometrics.integration.app.recommendation.model.Item;
import com.innometrics.integration.app.recommendation.service.ItemRepository;
import com.innometrics.integration.app.recommendation.service.SingletonService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author andrew, Innometrics
 */
@Path("/item")
public class ItemResource {

    ItemRepository itemRepo = SingletonService.getInstanceFromConfig(ItemRepository.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Item get(@QueryParam("itemId") long itemId) {
        return itemRepo.getItem(itemId);
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void put(Item item) {
        itemRepo.setItem(item);
    }
}
