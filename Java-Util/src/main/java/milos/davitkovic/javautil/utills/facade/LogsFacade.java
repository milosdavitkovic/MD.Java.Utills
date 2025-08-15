package milos.davitkovic.javautil.utills.facade;



public interface LogsFacade
{
	void createClearLogsFile(final String folderName, final String sourceFileName, final String resultFileName, final String keyMessage);

	String getO2OEmailPayload(final String inputLog);
	String getO2ODataPayload(final String inputLog);
}
