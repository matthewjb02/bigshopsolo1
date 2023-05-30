package nl.hu.bep.shopping.webservices;

import nl.hu.bep.shopping.model.Item;
import nl.hu.bep.shopping.model.Shop;
import nl.hu.bep.shopping.model.ShoppingList;

import javax.json.Json;
import javax.json.JsonReader;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import java.util.AbstractMap;
import java.util.List;

@Path("list")
public class ListResource {



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShoppingLists() {
        List<ShoppingList> shoppingLists = Shop.getShop().getAllShoppingLists();
        if (shoppingLists.isEmpty()) {
            var message = new AbstractMap.SimpleEntry<>("error", "no lists present");
            return Response.status(404).entity(message).build();
        }

        return Response.ok(shoppingLists).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{name}")
    public Response getShoppingListByName(@PathParam("name") String name) {
        Shop shop = Shop.getShop();
        ShoppingList list = shop.getShoppingListByName(name);
        return Response.ok(list).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{name}")
    public Response reset_list(@PathParam("name") String name, String jsonbody) {
        JsonReader jsonReader = Json.createReader(new StringReader(jsonbody));
        String naam = jsonReader.readObject().getString("name");
        ShoppingList allListsFromPerson = Shop.getShop().getShoppingListByName(naam);
        allListsFromPerson.getListItems().clear();
        return Response.status(200).entity(allListsFromPerson).build();

    }


}
