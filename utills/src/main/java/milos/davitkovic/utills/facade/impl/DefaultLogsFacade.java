package milos.davitkovic.utills.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;

import milos.davitkovic.utills.annotations.Facade;
import milos.davitkovic.utills.facade.LogsFacade;
import milos.davitkovic.utills.services.logs.LogsService;


@Facade
public class DefaultLogsFacade implements LogsFacade
{

	@Autowired
	private LogsService logsService;

	@Override
	public void createClearLogsFile(final String folderName, final String sourceFileName, final String resultFileName, final String keyMessage) {
		logsService.createClearLogsFile(folderName, sourceFileName, resultFileName, keyMessage);
	}

	@Override
	public String getO2OEmailPayload(final String inputLog)
	{
		return logsService.getO2OEmailPayload(inputLog);
	}
}
