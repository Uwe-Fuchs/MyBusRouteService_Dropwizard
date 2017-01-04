package com.uwefuchs.demo.goeuro.exceptions;

public class IOException
	extends BusRouteServiceRuntimeException
{
	private static final long serialVersionUID = 1L;

	public IOException()
	{
	}

	public IOException(String message)
	{
		super(message);
	}

	public IOException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
