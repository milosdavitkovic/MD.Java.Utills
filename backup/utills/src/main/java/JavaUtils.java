package milos.davitkovic.utills;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnableAsync
@Slf4j
public class JavaUtils {

	public static void main(String[] args) {
		SpringApplication.run(JavaUtils.class, args);
	}

}
