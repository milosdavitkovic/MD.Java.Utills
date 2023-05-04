package milos.davitkovic.utills.facade;

import milos.davitkovic.utills.annotations.Interface;


@Interface
public interface LogsFacade
{
	void createClearLogsFile(final String folderName, final String sourceFileName, final String resultFileName, final String keyMessage);

	String getO2OEmailPayload(final String inputLog);
	String getO2ODataPayload(final String inputLog);
}
