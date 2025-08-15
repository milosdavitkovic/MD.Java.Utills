package milos.davitkovic.utills.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.utills.facade.LogsFacade;


@RestController
@RequestMapping(value = "logs/")
@Slf4j
public class LogsController
{
	@Autowired
	private LogsFacade logsFacade;

	@PostMapping(value = "/createCleanLogs")
	@ResponseStatus(value = HttpStatus.OK)
	public void createCleanLogs(@RequestParam final String inputFileName, @RequestParam final String folderName,
			@RequestParam final String resultFileName, @RequestParam final String keyMessage)
	{
		logsFacade.createClearLogsFile(folderName, inputFileName, resultFileName, keyMessage);
	}

	@PostMapping(value = "/getO2OPayload", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public String getO2OEmailPayload(@RequestBody final String inputLog)
	{
		log.info("Logs Controller: Get O2O Email payload.");
		return logsFacade.getO2OEmailPayload(inputLog);
	}

	@PostMapping(value = "/getO2ODataPayload", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public String getO2OEmailDataPayload(@RequestBody final String inputLog)
	{
		log.info("Logs Controller: Get O2O Email Data payload.");
		return logsFacade.getO2ODataPayload(inputLog);
	}
}
