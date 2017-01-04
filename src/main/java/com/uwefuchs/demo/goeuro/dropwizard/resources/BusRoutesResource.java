package com.uwefuchs.demo.goeuro.dropwizard.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.common.base.Optional;
import com.uwefuchs.demo.goeuro.dropwizard.api.BusRouteInfo;
import com.uwefuchs.demo.goeuro.dropwizard.api.IBusRouteService;

@Path("api/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BusRoutesResource
{
	@Inject
	private IBusRouteService busRouteService;

	@Path("direct")
	@GET
	public Optional<BusRouteInfo> findDirectRoute(@QueryParam("dep_sid") Integer dep_sid,
		@QueryParam("arr_sid") Integer arr_sid)
	{
		BusRouteInfo info = busRouteService.lookUpBusRoute(dep_sid, arr_sid);
		Optional<BusRouteInfo> optional = Optional.fromNullable(info);
		
		return optional;
	}
}
