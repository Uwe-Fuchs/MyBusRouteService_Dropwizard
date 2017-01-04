package com.uwefuchs.demo.goeuro.exceptions;

public class BusRouteServiceRuntimeException
	extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public BusRouteServiceRuntimeException()
	{
		super();
	}

	public BusRouteServiceRuntimeException(String message)
	{
		super(message);
	}

	public BusRouteServiceRuntimeException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
