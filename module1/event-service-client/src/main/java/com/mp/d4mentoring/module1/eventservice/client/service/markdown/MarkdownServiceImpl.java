package com.mp.d4mentoring.module1.eventservice.client.service.markdown;

import java.util.List;
import java.util.stream.Collectors;

public class MarkdownServiceImpl implements MarkdownService {
    @Override
    public List<String> formatList(List<String> strings) {
        return strings.stream()
                .map(str -> "* " + str)
                .collect(Collectors.toUnmodifiableList());
    }
}
