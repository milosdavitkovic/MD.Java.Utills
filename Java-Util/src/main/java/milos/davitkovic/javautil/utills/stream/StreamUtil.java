package milos.davitkovic.javautil.utills.stream;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.javautil.utills.annotations.UtilClass;


import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Slf4j
@UtilClass
@NoArgsConstructor
public class StreamUtil {

    public static <T> Stream<List<T>> batch(Stream<T> source, int batchSize) {
        Spliterator<T> spliterator = source.spliterator();
        long estimatedSize = spliterator.estimateSize();

        return StreamSupport.stream(
                new Spliterators.AbstractSpliterator<List<T>>(
                        estimatedSize / batchSize + 1,
                        spliterator.characteristics() & ~Spliterator.SIZED
                ) {
                    final List<T> batch = new ArrayList<>(batchSize);

                    @Override
                    public boolean tryAdvance(Consumer<? super List<T>> action) {
                        while (batch.size() < batchSize && spliterator.tryAdvance(batch::add)) {
                            // Keep filling the batch
                        }

                        if (batch.isEmpty()) {
                            return false;
                        }

                        action.accept(new ArrayList<>(batch));
                        return true;
                    }
                },
                false
        ).onClose(source::close);
    }

    public static <T> Stream<List<T>> splitStreams(Stream<T> source, int batchSize) {
        if (batchSize <= 0) {
            throw new IllegalArgumentException("Batch size must be positive");
        }

        // Get the spliterator immediately before any other operations
        Spliterator<T> sourceSpliterator = source.spliterator();

        return StreamSupport.stream(new Spliterator<List<T>>() {
                    // Temporary list to hold current batch
                    private final List<T> currentBatch = new ArrayList<>(batchSize);

                    @Override
                    public boolean tryAdvance(Consumer<? super List<T>> action) {
                        // Fill the current batch
                        while (currentBatch.size() < batchSize &&
                                sourceSpliterator.tryAdvance(currentBatch::add)) {
                            // Keep adding elements until batch is full or source exhausted
                        }

                        // If we have a non-empty batch, process it
                        if (!currentBatch.isEmpty()) {
                            action.accept(new ArrayList<>(currentBatch));
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public Spliterator<List<T>> trySplit() {
                        return null; // Not splittable for simplicity
                    }

                    @Override
                    public long estimateSize() {
                        long sourceSize = sourceSpliterator.estimateSize();
                        return sourceSize == Long.MAX_VALUE ? Long.MAX_VALUE
                                : (sourceSize + batchSize - 1) / batchSize;
                    }

                    @Override
                    public int characteristics() {
                        return sourceSpliterator.characteristics() &
                                ~(Spliterator.SIZED | Spliterator.SUBSIZED);
                    }
                }, false)
                .onClose(source::close);
    }

    public static <T> Stream<Stream<T>> splitParallelStream(Stream<T> source, int batchSize) {
        Spliterator<T> spliterator = source.spliterator();

        return StreamSupport.stream(new Spliterators.AbstractSpliterator<Stream<T>>(Long.MAX_VALUE, Spliterator.ORDERED) {
            @Override
            public boolean tryAdvance(Consumer<? super Stream<T>> action) {
                List<T> batch = new ArrayList<>(batchSize);
                int count = 0;
                while (spliterator.tryAdvance(batch::add) && count++ < batchSize - 1) {
                    // fill batch
                }
                if (batch.isEmpty()) {
                    return false;
                }
                action.accept(batch.parallelStream());
                return true;
            }
        }, false);
    }
}
