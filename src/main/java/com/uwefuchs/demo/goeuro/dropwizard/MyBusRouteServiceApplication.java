package com.uwefuchs.demo.goeuro.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uwefuchs.demo.goeuro.dropwizard.api.FileBasedBusRouteService;
import com.uwefuchs.demo.goeuro.dropwizard.api.IBusRouteService;
import com.uwefuchs.demo.goeuro.dropwizard.resources.BusRoutesResource;

public class MyBusRouteServiceApplication
	extends Application<MyBusRouteServiceConfiguration>
{
	private final Logger logger = LoggerFactory
		.getLogger(MyBusRouteServiceApplication.class);

	public static void main(final String[] args)
		throws Exception
	{
		new MyBusRouteServiceApplication().run(args);
	}

	@Override
	public String getName()
	{
		return "MyBusRouteService";
	}

	@Override
	public void initialize(
		final Bootstrap<MyBusRouteServiceConfiguration> bootstrap)
	{
		// TODO: application initialization
	}

	@Override
	public void run(final MyBusRouteServiceConfiguration configuration,
		final Environment environment)
	{
		logger.info("Starting BusRouteService...");
		
		FileBasedBusRouteService busRouteService = new FileBasedBusRouteService();
		busRouteService.setPathname(configuration.getPathname());
		busRouteService.cacheBusRouteData();

		AbstractBinder binder = new AbstractBinder()
		{
			@Override
			protected void configure()
			{
				bind(configuration).to(MyBusRouteServiceConfiguration.class);
				bind(environment).to(Environment.class);
				bind(busRouteService).to(IBusRouteService.class);
			}
		};

		environment.jersey().register(binder);
		environment.jersey().register(new BusRoutesResource());
	}
}
