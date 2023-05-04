package milos.davitkovic.utills.facade.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.utills.annotations.Facade;
import milos.davitkovic.utills.facade.LogsFacade;
import milos.davitkovic.utills.services.logs.LogsService;


@Facade
@Slf4j
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

	@Override
	public String getO2ODataPayload(final String inputLog)
	{
		final String o2ODataPayload = logsService.getO2ODataPayload(inputLog);
		if(StringUtils.isEmpty(o2ODataPayload)) {
			log.error("O2O Data from the input log message cannot be extracted!");
		}
		return o2ODataPayload;
	}
}
