package nl.hu.bep.shopping.webservices;

import nl.hu.bep.shopping.model.Shop;
import nl.hu.bep.shopping.model.Shopper;
import nl.hu.bep.shopping.model.ShoppingList;

import javax.json.Json;
import javax.json.JsonReader;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;

@Path("shopper")
public class PersonResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShoppers() {
        Shop shop = Shop.getShop();
        return Response.ok(shop.getAllPersons()).build();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShoppingListsFromPerson(@PathParam("name") String name) {
        Shop shop = Shop.getShop();
        List<ShoppingList> allListsFromPerson = shop.getListFromPerson(name); //warning: might return null!
        if (allListsFromPerson == null)
            return Response.status(404)
                    .entity(new SimpleEntry<>("error", "No owner with that name apparently"))
                    .build();
        else
            return Response.ok(allListsFromPerson).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response add_shopper(String jsonbody) {
        JsonReader jsonReader = Json.createReader(new StringReader(jsonbody));
        String naam = jsonReader.readObject().getString("name");
        Shopper s1 = new Shopper(naam);
        Shopper.getAllShoppers().add(s1);
        return Response.status(200).entity(s1).build();
    }

    @DELETE
    @Path("{name}")
    public Response del_user(@PathParam("name") String name) {
        List<Shopper> ss = Shop.getShop().getAllPersons();
        ss.removeIf(s -> s.getName().equals(name));

        return Response.status(200).entity(ss).build();
    }


}
