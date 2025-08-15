package milos.davitkovic.javautil.utills.list;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.javautil.utills.annotations.UtilClass;
import milos.davitkovic.javautil.utills.logging.LoggingUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
@UtilClass
public class ListUtil {

    private ListUtil() {
    }

    /**
     * Splits a list into smaller batches of a specified size.
     *
     * @param originalList the original list to be split
     * @param batchSize    the size of each batch
     * @param <T>          the type of elements in the list
     * @return a list of lists, where each inner list is a batch of the specified size
     */
    public static <T> List<List<T>> splitListIntoBatchesList(List<T> originalList, int batchSize) {
        List<List<T>> batches = new ArrayList<>();
        for (int i = 0; i < originalList.size(); i += batchSize) {
            int end = Math.min(i + batchSize, originalList.size());
            batches.add(new ArrayList<>(originalList.subList(i, end)));
        }
        return batches;
    }

    public static <T> List<List<T>> splitList(final List<T> inputList, final int listBatchSize) {
        if (listBatchSize <= 0) {
            log.error("[Sap Marketing DocStore] [{}] Batch size must be positive",
                    LoggingUtil.getLogTimeStamp());
            // FIX: Return immediately to prevent infinite loop
            return new ArrayList<>();
        }

        final List<List<T>> subLists = new ArrayList<>();

        if (inputList == null || inputList.isEmpty()) {
            log.warn("[Sap Marketing DocStore] [{}] an Input list is null or empty, returning empty sublist.",
                    LoggingUtil.getLogTimeStamp());
            return subLists;
        }

        if (inputList.size() <= listBatchSize) {
            subLists.add(new ArrayList<>(inputList));
            log.debug("[Sap Marketing DocStore] [{}] an Input list is empty or smaller than batch size, returning original list as single sublist.",
                    LoggingUtil.getLogTimeStamp());
            return subLists;
        }

        for (int i = 0; i < inputList.size(); i += listBatchSize) {
            subLists.add(new ArrayList<>(inputList.subList(i, Math.min(i + listBatchSize, inputList.size()))));
        }

        log.debug("[Sap Marketing DocStore] [{}] Input list has been split into {} sublists of size {}.",
                LoggingUtil.getLogTimeStamp(), subLists.size(), listBatchSize);
        return subLists;
    }


    /**
     * Splits a stream into smaller batches of a specified size and returns a list of lists.
     *
     * @param stream    the original stream to be split
     * @param batchSize the size of each batch
     * @param <T>       the type of elements in the stream
     * @return a list of lists, where each inner list is a batch of the specified size
     */
    public static <T> List<List<T>> splitStreamIntoBatchesList(Stream<T> stream, int batchSize) {
        List<List<T>> batches = new ArrayList<>();
        Iterator<T> iterator = stream.iterator();

        while (iterator.hasNext()) {
            List<T> batch = new ArrayList<>(batchSize);
            for (int i = 0; i < batchSize && iterator.hasNext(); i++) {
                batch.add(iterator.next());
            }
            batches.add(batch);
        }

        return batches;
    }

    /**
     * Splits a list into a stream of batches of a specified size.
     *
     * @param source    the original list to be split
     * @param batchSize the size of each batch
     * @param <T>       the type of elements in the list
     * @return a stream of lists, where each inner list is a batch of the specified size
     */
    public static <T> Stream<List<T>> splitListIntoBatchsStream(List<T> source, int batchSize) {
        int size = source.size();
        int fullChunks = (size + batchSize - 1) / batchSize;

        return IntStream.range(0, fullChunks)
                .mapToObj(i -> source.subList(i * batchSize, Math.min(size, (i + 1) * batchSize)));
    }

    public static <T> Stream<List<T>> splitStreamIntoBatches(@NonNull Stream<T> source, int batchSize) {
        if (batchSize <= 0) {
            throw new IllegalArgumentException("Batch size must be positive");
        }

        Iterator<T> iterator = source.iterator();

        return Stream.generate(() -> {
                    List<T> batch = new ArrayList<>(batchSize);
                    while (iterator.hasNext() && batch.size() < batchSize) {
                        batch.add(iterator.next());
                    }
                    return batch.isEmpty() ? null : batch;
                })
                .takeWhile(Objects::nonNull) // Stop when we get a null (empty batch)
                .onClose(source::close); // Ensure original stream gets closed
    }

}