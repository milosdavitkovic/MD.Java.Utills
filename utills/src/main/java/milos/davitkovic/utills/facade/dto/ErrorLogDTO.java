package milos.davitkovic.utills.facade.dto;

import lombok.Data;

@Data
public class ErrorLogDTO {

    private String className;
    private String method;
    private Integer lineNumber;
    private String packagePath;
}
