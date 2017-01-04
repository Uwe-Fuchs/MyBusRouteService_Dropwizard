package com.uwefuchs.demo.goeuro.exceptions;

public class DataContraintViolationException
	extends BusRouteServiceRuntimeException
{
	private static final long serialVersionUID = 1L;

	public DataContraintViolationException()
	{
	}

	public DataContraintViolationException(String message)
	{
		super(message);
	}

	public DataContraintViolationException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
