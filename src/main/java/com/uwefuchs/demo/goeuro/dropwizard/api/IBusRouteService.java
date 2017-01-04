package com.uwefuchs.demo.goeuro.dropwizard.api;


/**
 * A service containing bus-route-operations 
 * (reading, maybe manipulating in a future step).
 * 
 * @author info@uwefuchs.com
 */
public interface IBusRouteService
{
	/**
	 * looks up a bus-route containing dep_sid as start and arr_sid as arrival 
	 * (i.e.: station with dept_id is BEFORE station with arr_id).
	 * 
	 * @param dep_sid : department-station identifier.
	 * @param arr_sid : arrival station identifier.
	 * 
	 * @return direct bus-route exists.
	 */
	BusRouteInfo lookUpBusRoute(Integer dep_sid, Integer arr_sid);
}
