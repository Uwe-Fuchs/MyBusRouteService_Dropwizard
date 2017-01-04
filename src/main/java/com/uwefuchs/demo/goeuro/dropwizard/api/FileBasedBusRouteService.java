package com.uwefuchs.demo.goeuro.dropwizard.api;

import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uwefuchs.demo.goeuro.dropwizard.db.BusRouteDataFileReader;

/**
 * file-based implementation of {@link IBusRouteService}
 * 
 * @author info@uwefuchs.com
 */
public class FileBasedBusRouteService
	implements IBusRouteService
{
	private static final Logger LOG = LoggerFactory.getLogger(FileBasedBusRouteService.class);
		
	private Map<Integer, Map<Integer, Integer>> dataMap = null;	
	private String pathname;

	public FileBasedBusRouteService()
	{
		super();
	}

	public void setPathname(String pathname)
	{
		Validate.notBlank(pathname, "path-name must not be blank!");
		this.pathname = pathname;
	}

	@Override
	public BusRouteInfo lookUpBusRoute(Integer dep_sid, Integer arr_sid)
	{
		LOG.debug("Lookup direct busroute with dep_sid {} and arr_sid {} ... ", dep_sid, arr_sid);
		
		boolean eq = dataMap
				.values()
				.stream()
				.anyMatch(m -> m.containsKey(dep_sid) && m.containsKey(arr_sid) && m.get(dep_sid) < m.get(arr_sid));
		
		BusRouteInfo info = new BusRouteInfo(dep_sid, arr_sid, eq);
		
		LOG.info("Returning busRouteInfo {}", info);
		
		return info;
		
//		for (Map<Integer, Integer> busRoute : dataMap.values())
//		{
//			if (busRoute.containsKey(dep_sid) && busRoute.containsKey(arr_sid) && busRoute.get(dep_sid) < busRoute.get(arr_sid))
//			{
//				return new BusRouteInfo(dep_sid, arr_sid, Boolean.TRUE);
//			}
//		}
//		
//		return new BusRouteInfo(dep_sid, arr_sid, Boolean.FALSE);
	}
	
	/**
	 * builds up the cache containing all bus-route-data.
	 */
	public void cacheBusRouteData()
	{	
		dataMap = BusRouteDataFileReader.readAndCacheBusRouteData(this.pathname);
	}
}
