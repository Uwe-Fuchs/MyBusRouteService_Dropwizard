package com.uwefuchs.demo.goeuro.dropwizard;

import io.dropwizard.Configuration;

import org.hibernate.validator.constraints.NotEmpty;

public class MyBusRouteServiceConfiguration
	extends Configuration
{
	@NotEmpty
	private String pathname;

	public String getPathname()
	{
		return pathname;
	}
}
