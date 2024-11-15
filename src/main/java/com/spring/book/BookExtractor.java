package com.spring.book;

import dev.langchain4j.service.spring.AiService;

@AiService
interface BookExtractor {

    Book generate(String query);

    Books generateAll(String query);
}
