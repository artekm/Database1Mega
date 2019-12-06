package artur;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Filters {

    List<String> filterVersion1(int... ids) {
        if (ids.length < 20)
            throw new IllegalArgumentException("Ids count must be at least 20");
        return Arrays.stream(ids)
                     .skip(ids.length / 2 - 10)
                     .limit(20)
                     .mapToObj(String::valueOf)
                     .collect(Collectors.toList());
    }

    List<String> filterVersion2(int... ids) {
        if (ids.length < 20)
            throw new IllegalArgumentException("Ids count must be at least 20");
        int middle = ids.length / 2;
        return IntStream.range(middle - 10, middle + 10)
                        .mapToObj(i -> String.valueOf(ids[i]))
                        .collect(Collectors.toList());
    }

    Map<Character, Long> countChars(String input) {
        return input.chars()
                    .mapToObj(i -> (char) i)
                    .collect(Collectors.groupingBy(chr -> chr, Collectors.counting()));
    }

}
