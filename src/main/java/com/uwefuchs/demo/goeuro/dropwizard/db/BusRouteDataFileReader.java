package com.uwefuchs.demo.goeuro.dropwizard.db;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.Validate;

import com.uwefuchs.demo.goeuro.exceptions.DataContraintViolationException;
import com.uwefuchs.demo.goeuro.exceptions.InconsistentDataException;

/**
 * reads bus-route-data from a given file. Caches the data into a given {@link Map}.
 * 
 * @author info@uwefuchs.com
 */
public class BusRouteDataFileReader
{
	public static Map<Integer, Map<Integer, Integer>> readAndCacheBusRouteData(String pathname)
	{
		Validate.notBlank(pathname, "path-name mus not be blank!");

		try (Scanner scanner = new Scanner(Paths.get(pathname)))
		{			
			assertFileNotEmpty(scanner, pathname);
			int numberOfBusRoutes = Integer.valueOf(scanner.next());	
			Map<Integer, Map<Integer, Integer>> dataMap = new ConcurrentHashMap<>(numberOfBusRoutes);
			
			while (scanner.hasNextLine())
			{
				processSingleLine(scanner.nextLine(), dataMap);
			}

			assertNumberOfBusRoutesWithinBounds(dataMap);
			assertNumberOfBusRoutesAsAnnounced(dataMap, numberOfBusRoutes);
			
			return dataMap;
		} catch (java.io.IOException ex)
		{
			throw new com.uwefuchs.demo.goeuro.exceptions.IOException("IOException when reading data-file!", ex);
		} catch (NumberFormatException ex)
		{
			throw new InconsistentDataException("NumberFormatException when reading data-file!", ex);
		}
	}

	private static void processSingleLine(String aLine, Map<Integer, Map<Integer, Integer>> dataMap)
	{
		// use a second Scanner to parse the content of each line
		try (Scanner scanner = new Scanner(aLine))
		{			
			scanner.useDelimiter(" ");
			
			if (!scanner.hasNext())
			{
				return;
			}
			
			int busRouteId = Integer.valueOf(scanner.next());
			assertUniqueBusRouteIds(dataMap, busRouteId);
			
			Map<Integer, Integer> busRouteMap = new HashMap<>();
			
			for (int counter = 0; scanner.hasNext(); counter++)
			{
				// use scanner.next() PLUS Integer.valueOf() because of implicit isNumeric()-check:
				int stationId = Integer.valueOf(scanner.next());	
				assertUniqueStationIdsInBusRoute(busRouteMap, stationId, busRouteId);				
				busRouteMap.put(stationId, counter);
			}
			
			assertNumberOfStationsWithinBounds(busRouteMap, busRouteId);
			
			dataMap.put(busRouteId, busRouteMap);
		}
	}
	
	private static void assertUniqueBusRouteIds(Map<Integer, Map<Integer, Integer>> dataMap, int busRouteId)
	{
		if (dataMap.containsKey(busRouteId))
		{
			throw new InconsistentDataException(String.format("non-unique bus-route-id: [%d]", busRouteId));
		}
	}
	
	private static void assertNumberOfStationsWithinBounds(Map<Integer, Integer> busRouteMap, int busRouteId)
	{
		if (busRouteMap.size() < DataConstraints.MIN_NUMBER_OF_STATIONS_PER_ROUTE || busRouteMap.size() > DataConstraints.MAX_NUMBER_OF_STATIONS_PER_ROUTE)
		{
			throw new DataContraintViolationException(
					String.format("number [%d] of stations not within defined bounds in bus-route [%d]", busRouteMap.size(), busRouteId));			
		}
	}
	
	private static void assertUniqueStationIdsInBusRoute(Map<Integer, Integer> busRouteMap, int stationId, int busRouteId)
	{
		if (busRouteMap.containsKey(stationId))
		{
			throw new InconsistentDataException(String.format("double occurrence of station-id [%d ] in bus-route [%d]", stationId, busRouteId));					
		}
	}
	
	private static void assertNumberOfBusRoutesWithinBounds(Map<Integer, Map<Integer, Integer>> dataMap)
	{
		if (dataMap.size() < 1 || dataMap.size() > DataConstraints.MAX_NUMBER_OF_BUS_ROUTES)
		{
			throw new DataContraintViolationException(String.format("more then [%d] bus-routes in given data", DataConstraints.MAX_NUMBER_OF_BUS_ROUTES));
		}
	}
	
	private static void assertNumberOfBusRoutesAsAnnounced(Map<Integer, Map<Integer, Integer>> dataMap, int numberOfBusRoutes)
	{
		if (numberOfBusRoutes != dataMap.size())
		{
			throw new InconsistentDataException(
					String.format("Real number [%d] of bus-routes differs from announced number [%d]", dataMap.size(), numberOfBusRoutes));			
		}
	}
	
	private static void assertFileNotEmpty(Scanner scanner, String pathname)
	{
		if (!scanner.hasNext())
		{
			throw new InconsistentDataException(String.format("No data found in given file [%s]", pathname));
		}
	}
}
