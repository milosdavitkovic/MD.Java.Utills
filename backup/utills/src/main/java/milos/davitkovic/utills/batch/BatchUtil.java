package sapmarketing.docstore.core.util.batch;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Value;
import sapmarketing.docstore.core.configuration.annotation.UtilClass;

@Slf4j
@UtilClass
public class BatchUtil {

    @Value("${sbb.sap.marketing.docstore.batch.background.processing:false}")
    private Boolean ENABLE_BATCH_BACKGROUND_PROCESSING;

    public boolean isEnabledBatchBackgroundProcessing() {
        return BooleanUtils.isTrue(ENABLE_BATCH_BACKGROUND_PROCESSING);
    }
}
