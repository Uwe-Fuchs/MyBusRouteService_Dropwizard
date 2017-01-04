package com.uwefuchs.demo.goeuro.dropwizard.api;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * contains all information for a specific bus-route.
 * 
 * @author info@uwefuchs.com
 */
@XmlRootElement
public class BusRouteInfo
{
	@NotEmpty
	private Integer dep_sid;
	
	@NotEmpty
	private Integer arr_sid;
	
	@NotEmpty
	private Boolean direct_bus_route;

	public BusRouteInfo()
	{
		super();
	}

	public BusRouteInfo(Integer dep_sid, Integer arr_sid, Boolean direct_bus_route)
	{
		super();
		
		this.dep_sid = dep_sid;
		this.arr_sid = arr_sid;
		this.direct_bus_route = direct_bus_route;
	}

	public Integer getDep_sid()
	{
		return dep_sid;
	}

	public void setDep_sid(Integer dep_sid)
	{
		this.dep_sid = dep_sid;
	}

	public Integer getArr_sid()
	{
		return arr_sid;
	}

	public void setArr_sid(Integer arr_sid)
	{
		this.arr_sid = arr_sid;
	}

	public Boolean getDirect_bus_route()
	{
		return direct_bus_route;
	}

	public void setDirect_bus_route(Boolean direct_bus_route)
	{
		this.direct_bus_route = direct_bus_route;
	}
}
