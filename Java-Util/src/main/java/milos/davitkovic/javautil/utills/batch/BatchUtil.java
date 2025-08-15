package milos.davitkovic.javautil.utills.batch;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Value;
import milos.davitkovic.javautil.utills.annotations.UtilClass;

@Slf4j
@UtilClass
public class BatchUtil {

    @Value("${milos.davitkovic.java.utils.batch.background.processing:false}")
    private Boolean ENABLE_BATCH_BACKGROUND_PROCESSING;

    public boolean isEnabledBatchBackgroundProcessing() {
        return BooleanUtils.isTrue(ENABLE_BATCH_BACKGROUND_PROCESSING);
    }
}
