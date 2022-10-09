package com.hprtech.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/mobile")  // localhost:8080/mobile
public class MobileResource {
    List<String> mobileList = new ArrayList<>();

    /**
     * This method will return list of mobile names when we hit /mobile url with GET request
     * @return
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMobileList(){
        return Response.ok(mobileList).build();
    }

    /**
     * This method add a new mobile name in list when we hit /mobile url with POST request
     * @param mobileName
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addNewMobile(String mobileName){
        mobileList.add(mobileName);
        return Response.ok(mobileName).build();
    }

    /**
     * This method will update mobile name in list
     * when we hit /mobile/{oldmobilename}?newmobilename=newname url with PUT request
     * @param oldMobileName
     * @param newMobileName
     * @return
     */
    @PUT
    @Path("/{oldmobilename}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateMobile(@PathParam("oldmobilename") String oldMobileName ,
                                 @QueryParam("newmobilename") String newMobileName){
        mobileList= mobileList.stream().map(mobile -> {
            if(mobile.equals(oldMobileName)){
                return newMobileName;
            }else{
                return mobile;
            }
        }).collect(Collectors.toList());
        return Response.ok(mobileList).build();
    }

    /**
     * This method will remove mobile name when we hit /mobile/{mobileToDelete}
     * @param mobileName
     * @return
     */
    @DELETE
    @Path("{mobileToDelete}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteMobile(@PathParam("mobileToDelete") String mobileName){
        boolean isRemoved  = mobileList.remove(mobileName);
        if (isRemoved){
            return Response.ok(mobileList).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
