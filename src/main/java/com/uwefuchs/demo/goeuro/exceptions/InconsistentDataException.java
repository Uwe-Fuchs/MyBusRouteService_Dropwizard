package com.uwefuchs.demo.goeuro.exceptions;

public class InconsistentDataException
	extends BusRouteServiceRuntimeException
{
	private static final long serialVersionUID = 1L;

	public InconsistentDataException()
	{
	}

	public InconsistentDataException(String message)
	{
		super(message);
	}

	public InconsistentDataException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
