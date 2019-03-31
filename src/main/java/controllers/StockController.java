package controllers;

import beans.ItemsBean;
import item.Item;
import item.Location;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("stock")
public class StockController {
    @Inject
    private ItemsBean itemsBean;

    @GET
    @Path("items")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems() {
        List<Item> items = itemsBean.getItems(Location.STOCK);
        return Response.status(Response.Status.OK).entity(items).build();
    }

    @GET
    @Path("items/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("id") Long id) {
        Item item = itemsBean.getItem(id);
        if (item == null ||
                item.getLocation() != Location.STOCK) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(item).build();
    }

    @POST
    @Path("items")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Item data) {
        return Response.status(Response.Status.OK).entity(itemsBean.add(data)).build();
    }

    @PUT
    @Path("items/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Item data) {
        Item item = itemsBean.getItem(id);
        if (item == null ||
                item.getLocation() != Location.STOCK) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(itemsBean.update(data)).build();
    }

    @DELETE
    @Path("items/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Item item = itemsBean.getItem(id);
        if (item == null ||
                item.getLocation() != Location.STOCK) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(itemsBean.delete(id)).build();
    }
}
